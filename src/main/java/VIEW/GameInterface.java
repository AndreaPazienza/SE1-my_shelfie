package VIEW;
import java.util.ArrayList;



import Listeners.viewListeners;
import MODEL.*;

import java.util.List;
import java.util.Scanner;

public class GameInterface implements Runnable, viewListeners {

    private final List<viewListeners> listeners = new ArrayList<>();
    public Scanner keyboard = new Scanner(System.in);

    //inserimento nickname per la prima volta
    public String firstRun() {

        String nick = null;
        boolean ok = false;

        System.out.print("Inserire il nickname: ");
        nick = keyboard.nextLine();

        return nick;
    }

    //inserimento del numero dei giocatori
    public int numberOfPlayers() {

        int number = 0;

        do {
            System.out.print("Inserire il numero dei giocatori: ");
            number = keyboard.nextInt();
            if  (number < 2 || number > 4)
                System.out.print("Il giioco è da 2 a 4 giocatori, inserire un numero corretto ");
        } while (number < 2 || number > 4);

        return number;
    }

    public void run() {

        int nChoices = 0;
        GameView gameView;
    //Da sistemare
        //update(gameView);
        playerMoveSelection();
        playerInsert();
    }


    //Selezione delle tessere dalla dashboard
    public void playerMoveSelection() {

        int countChoices = 0;
        int nChoices = 0;
        int maxChoices = 3;
        boolean ok = false;
        //Viene stabilito il massimo numero di tessere prendibili in base agli spazi liberi nella shelf

        //Inserimento del numero di tessere da selezionare, controllo e creazione dell'array
        do {
            System.out.println("Inserire il numero di tessere da selezionare: ");
            nChoices = keyboard.nextInt();
            if (nChoices > maxChoices) {
                System.out.println("Nessuna colonna della shelf ha così tanto spazio disponibile: ");
            }
        } while (nChoices < 1 || nChoices > maxChoices);
        ArrayList<SlotChoice> choices = new ArrayList<>();

        int x = -1;
        int y = -1;

        System.out.print("");

        do {
           // try {
                do {
                    //Inseriemento della tessera songola e inserimento nell'array di tessere
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
                    choices.add(new SlotChoice(x,y));
                    countChoices++;

                } while (countChoices < nChoices);

                notifySelectedCoordinates((SlotChoice[]) choices.toArray());

                ok = true;

            /*} catch (NotCatchableException e) {
                System.out.println("Una delle tessere selezionate non è giocabile, sceglierne delle altre");
                ok = false;

            } catch (NotAdjacentSlotsException e) {
                System.out.println("Le tessere selezionate non sono adiacenti, scelierne delle altre");
                ok = false;
            }*/
        }while (!ok);

        System.out.println("Le tessere sono state selezionate");

        if (nChoices != 1) {
            if (playerOrder()) {
                if (nChoices == 2) {

                    //Creazione di una OrderChoice con parametri convenzionalmente scelti
                    OrderChoice order = new OrderChoice(1,1,1);

                    //---------------------Notifica con OrderChoice
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

                    //--------------------------Notifica con OrderChoice

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

    //Inserimento delle tessere prese nella shelf
    public void playerInsert() {

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
           // try {
                //---------------------------Notifica con column
                ok = true;
           /* } catch (NotEnoughSpaceChoiceException e) {
                System.out.println("La colonna inserita non ha abbastanza spazio disponibile");
                ok = false;
            }*/
        } while (!ok);

        System.out.print("Hai inserito correttamente le tessere!");
    }

//Stampa della dashboard a schermo
    public void displayDashboard(Dashboard board){
        System.out.print("\t");
        for(int k = 0; k < Dashboard.getSide(); k++){
            System.out.print("\t" + k + "\t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            System.out.print("" + i +"\t");
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if ((!board.getSingleSlot(i,j).getColor().Equals(Color.BLACK) && !board.getSingleSlot(i,j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(board.getSingleSlot(i, j).getColor()) + "[]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "  " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
    }

    //Stampa della personal shelf a schermo
    public void displayPersonalShelf(PersonalShelf shelf){
        System.out.print("\t");
        for(int k = 0; k < PersonalShelf.N_COLUMN; k++){
            System.out.print("\t" + k + "\t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i ++) {
            System.out.print("" + i +"\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!shelf.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !shelf.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(shelf.getSingleSlot(i, j).getColor()) + "[]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t " + "  " + " \t");
            }
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    @Override
    public void addviewEventListener(viewListeners listener) {
        listeners.add(listener);
    }

    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) {
        for(viewListeners listener: listeners){
            listener.notifySelectedCoordinates(SC);
            listener.selecteCoordinates();
        }

    }

    @Override
    public void selecteCoordinates() {
    }

}
