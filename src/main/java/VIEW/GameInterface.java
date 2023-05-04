package VIEW;
import java.rmi.RemoteException;
import java.util.ArrayList;


import Errors.NotEnoughSpaceChoiceException;
import Listeners.viewListeners;
import MODEL.*;

import java.util.List;
import java.util.Scanner;

public class GameInterface implements Runnable, viewListeners {

    private List<viewListeners> listeners = new ArrayList<>();
    public Scanner keyboard = new Scanner(System.in);


    //inserimento nickname per la prima volta
    public String firstRun() {

        String nick = null;
        boolean ok = false;

        System.out.print("Inserire il nickname: ");
        nick = keyboard.nextLine();

        return nick;
    }

    //insert of the players number
    public int numberOfPlayers() {

        int number = 0;

        do {
            System.out.print("Inserire il numero dei giocatori: ");
            number = keyboard.nextInt();
            if (number < 2 || number > 4)
                System.out.print("Il giioco è da 2 a 4 giocatori, inserire un numero corretto ");
        } while (number < 2 || number > 4);

        return number;
    }
    public void playing() throws RemoteException {
            playerMoveSelection();
            try {
                playerInsert();
            } catch (NotEnoughSpaceChoiceException e) {
                System.out.println("Colonna errata");
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

    }


    //Selection of the cards from dashboard
    public void playerMoveSelection() throws RemoteException {

        int countChoices = 0;
        int nChoices = 0;
        int maxChoices = 3;
        boolean ok = false;
        // The maximum number of tiles that can be taken is determined based on the free spaces in the shelf.

        //Insertion of the number of tiles to be selected, checking and creation of the array
        do {
            System.out.println("Inserire il numero di tessere da selezionare: ");
            nChoices = keyboard.nextInt();
            if (nChoices > maxChoices) {
                System.out.println("Nessuna colonna della shelf ha così tanto spazio disponibile: ");
            }
        } while (nChoices < 1 || nChoices > maxChoices);

        SlotChoice[] selection = new SlotChoice[nChoices];

        int x = -1;
        int y = -1;

        do {
            for (int i = 0; i < nChoices; i++) {


                //Insertion of the single tile and insertion into the tiles array.//Inserimento della tessera songola e inserimento nell'array di tessere
                do {
                    System.out.println("Inserire le coordinate della tessera da prendere: ");
                    System.out.println("X: ");
                    x = keyboard.nextInt();
                    System.out.println("Y: ");
                    y = keyboard.nextInt();
                    if (x < 0 || x > 8 || y < 0 || y > 8) {
                        System.out.print("Inserire parametri compresi tra 0 e 8");
                    }
                } while (x < 0 || x > 8 || y < 0 || y > 8);

                selection[i] = new SlotChoice(x, y);
                countChoices++;
            }

            notifySelectedCoordinates(selection);
            ok = true;

            /*} catch (NotCatchableException e) {
                System.out.println("Una delle tessere selezionate non è giocabile, sceglierne delle altre");
                ok = false;

            } catch (NotAdjacentSlotsException e) {
                System.out.println("Le tessere selezionate non sono adiacenti, scelierne delle altre");
                ok = false;
            }*/
        } while (!ok);

        System.out.println("Le tessere sono state selezionate");

        if (nChoices != 1) {
            if (playerOrder()) {
                if (nChoices == 2) {

                    //Creating an OrderChoice with conventionally chosen parameters.
                    OrderChoice order = new OrderChoice(1, 1, 1);

                    notifyOrder(order);
                }

                if (nChoices == 3) {

                    int pos1;
                    int pos2;
                    int pos3;

                    do {
                        do {
                            System.out.println("Quale tessera vuoi inserire per prima?");
                            pos1 = keyboard.nextInt();
                            if (pos1 < 1 || pos1 > 3) {
                                System.out.println("Inserire posizione da 1 a 3");
                            }
                        } while (pos1 < 1 || pos1 > 3);

                        do {
                            System.out.println("Quale tessera vuoi inserire per seconda?");
                            pos2 = keyboard.nextInt();
                            if (pos2 < 1 || pos2 > 3) {
                                System.out.println("Inserire posizione da 1 a 3");
                            }
                        } while (pos2 < 1 || pos2 > 3);

                        do {
                            System.out.println("Quale tessera vuoi inserire per ultima?");
                            pos3 = keyboard.nextInt();
                            if (pos3 < 1 || pos3 > 3) {
                                System.out.println("Inserire posizione da 1 a 3");
                            }
                        } while (pos3 < 1 || pos3 > 3);

                    } while (pos1 == pos2 || pos1 == pos3 || pos2 == pos3);


                    OrderChoice order = new OrderChoice(pos1, pos2, pos3);

                    notifyOrder(order);

                    System.out.print("Hai ordinato correttamente le tessere!");
                }
            }
        }
    }

    //Richiesta di ordinamento
    public boolean playerOrder() {

        boolean reorder = false;
        String string;
        String yes = "si";
        String no = "no";

        do {
            System.out.println("Vuoi ordinare le tessere selezionate? si o no?");
            string = keyboard.nextLine();
            if (string.equals(yes))
                reorder = true;

        } while (!string.equals(yes) && !string.equals(no));

        return reorder;
    }

    //Insertion of the selected tiles into the shelf.
    public void playerInsert() throws NotEnoughSpaceChoiceException, RemoteException {

        int column;
        boolean ok;

        System.out.println("Scrivere il numero della colonna in cui inserire le tessere: ");
        do {
            column = keyboard.nextInt();
            if (column < 0 || column > 4) {
                System.out.print("Inserire un numero compreso tra 0 e 4");
            }
        } while (column < 0 || column > 4);

        do {
            notifyInsert(column);
            // try {
            //---------------------------Notifica con column
            ok = true;
           /* } catch (NotEnoughSpaceChoiceException e) {
                System.out.println("La colonna inserita non ha abbastanza spazio disponibile");
                ok = false;
            }*/
        } while (!ok);
    }

    //Printing the dashboard on screen.
    public void displayDashboard(Dashboard board) {
        System.out.print("\t");
        for (int k = 0; k < Dashboard.getSide(); k++) {
            System.out.print("\t " + k + " \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < Dashboard.getSide(); i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < Dashboard.getSide(); j++) {
                if ((!board.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !board.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(board.getSingleSlot(i, j).getColor()) + "[ ]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "   " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }

    //Printing the personal shelf on screen.
    public void displayPersonalShelf(PersonalShelf shelf) {
        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t " + k + " \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!shelf.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !shelf.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(shelf.getSingleSlot(i, j).getColor()) + "[ ]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "   " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }

    //Printing the Personal Goal on screen.
    public void displayPersonalGoal(PersonalGoal pGoal) {

        boolean isTarget[][] = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];

        for (int countTarget = 0; countTarget < pGoal.getGoal().length; countTarget++) {
            isTarget[pGoal.getSingleTarget(countTarget).getPosX()][pGoal.getSingleTarget(countTarget).getPosY()] = true;
        }

        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t " + k + " \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if (isTarget[i][j]) {
                    for (int countTarget = 0; countTarget < pGoal.getGoal().length; countTarget++) {
                        if ((pGoal.getSingleTarget(countTarget).getPosX() == i) && (pGoal.getSingleTarget(countTarget).getPosY() == j)) {
                            System.out.print("\t" + ColorPrint.convertColor(pGoal.getSingleTarget(countTarget).getTile()) + "[ ]" + ColorPrint.RESET + "\t");
                        }
                    }
                } else System.out.print("\t" + " . " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }

    public void displayWin(String winner){
        System.out.println("Il gioco è finito!! \n " + winner);
    }
    public void notifyAlmostOver(){
        System.out.println("Alla fine del giro il gioco terminerà, affrettatevi!! \n");
    }
    public void arrived() {
        System.out.println("A new player as signed");
    }
    public void startTurn() {
        System.out.print("-- Inizio del nuovo turno -- \n");
    }

    public void onWait() {

            System.out.print("-- Non è il tuo turno -- \n");

    }
    public void run() {
        System.out.println("waiting...");
    }


    //Adding listener
    @Override
    public void addviewEventListener(viewListeners listener) {
            listeners.add(listener);
            System.out.println("Creato bond client / view \n");
    }

    //Notification to all listeners (Clients) of the completed selection.
    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException {
        for( viewListeners listener : listeners  ) {
            listener.notifySelectedCoordinates(SC);
        }
    }

    //Notify all listeners (Clients) of the successful notification.
    @Override
    public void notifyOrder(OrderChoice o) {
        for( viewListeners listener : listeners  ) {
            try {
                listener.notifyOrder(o);
            } catch (RemoteException e) {
                System.out.println("ciao");
            }
        }
        }

    //Notification to all listeners (clients) of the successful insertion.
    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException {
        for( viewListeners listener : listeners  ) {
            listener.notifyInsert(column);
        }
          //  catch(RemoteException e){
            //   System.out.println("ciao");
           // }
        }
    }



