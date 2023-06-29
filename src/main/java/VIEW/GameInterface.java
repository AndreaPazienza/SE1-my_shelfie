package VIEW;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.viewListeners;
import MODEL.*;
import MODEL.Color;

/**
 * Class that represents the textual user interface.
 */
public class GameInterface implements Runnable, viewListeners {

    /**
     * The list of viewListener that take the notifies from the view.
     */
    private final List<viewListeners> listeners = new ArrayList<>();

    /**
     * The standard input.
     */
    public Scanner keyboard;

    /**
     * Retrieves the nickname insert by the player.
     *
     * @return The nickname insert by the user from the standard input.
     */
    public String firstRun() {
    String nick = null;
    do{
        System.out.print("Inserire il nickname: ");
        nick = getCorrectString();
        System.out.print("\n");
        if(nick.isBlank()){
            System.out.println("Il nickname inserito è nullo o formato solo da spazi! Sceglierne un altro!");
        }
    }while(nick.isBlank());
    return nick;
    }

    /**
     * Retrieves the number of players that are playing insert by the first player.
     *
     * @return The number of players insert by the user from standard input.
     */
    public int numberOfPlayers() {
        int number = 0;

        System.out.print("Inserire il numero dei giocatori: ");
        do {
            number = getCorrectInt();
            if (number < 2 || number > 4)
                System.out.print("Il gioco è da 2 a 4 giocatori, inserire un numero corretto: ");

        } while (number < 2 || number > 4);
        System.out.print("\n");

        return number;
    }

    /**
     * Starts the two main phases of the turn.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotEnoughSpaceChoiceException If the user wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    public void playing() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {
            playerMoveSelection();
            playerInsert();
    }


    /**
     * Manages the selection .
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotEnoughSpaceChoiceException If the user wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    private void playerMoveSelection() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {

        int nChoices = 0;

        //Insertion of the number of tiles to be selected, checking and creation of the array
        System.out.print("Inserire il numero di tessere da selezionare: ");
        do {
            nChoices = getCorrectInt();
            notifyChoices(nChoices);
            if (nChoices < 1)
                System.out.print("Scegliere almeno una tessera: ");
            if (nChoices > 3) {
                System.out.print("Scegliere al massimo tre tessere: ");
            }
        } while (nChoices < 1 || nChoices > 3);

        int x = -1;
        int y = -1;
        SlotChoice[] selection = new SlotChoice[nChoices];

        for (int i = 0; i < nChoices; i++) {

            boolean alreadySelected = false;

            System.out.println("Inserire le coordinate della tessera da prendere");
            do {
                //Insertion of the single tile and insertion into the tiles array
                alreadySelected = false;
                do {
                    System.out.print("X: ");
                    x = getCorrectInt();
                    System.out.print("Y: ");
                    y = getCorrectInt();
                    if (x < 0 || x > 8 || y < 0 || y > 8) {
                        System.out.println("Inserire parametri compresi tra 0 e 8");
                    }
                } while (x < 0 || x > 8 || y < 0 || y > 8);

                for (int j = 0; j < i; j++)
                    if(x == selection[j].getX() && y == selection[j].getY()) {
                        System.out.println("La tessera selezionata è già stata scelta prima, inserirne una diversa");
                        alreadySelected = true;
                    }
            } while(alreadySelected);

            selection[i] = new SlotChoice(x, y);
        }

        notifySelectedCoordinates(selection);

        if (nChoices != 1) {
            if (playerOrder()) {
                if (nChoices == 2) {
                    //Creating an OrderChoice with conventionally chosen parameters
                    OrderChoice order = new OrderChoice(1, 1, 1);
                    notifyOrder(order);
                }

                if (nChoices == 3) {
                    int pos1;
                    int pos2;
                    int pos3;
                    do {
                        do {
                            System.out.print("Quale tessera vuoi inserire per prima? ");
                            pos1 = getCorrectInt();
                            if (pos1 < 1 || pos1 > 3) {
                                System.out.print("Inserire posizione da 1 a 3: ");
                            }
                        } while (pos1 < 1 || pos1 > 3);
                    do {
                            System.out.print("Quale tessera vuoi inserire per seconda? ");
                            pos2 = getCorrectInt();
                            if (pos2 < 1 || pos2 > 3) {
                                System.out.print("Inserire posizione da 1 a 3: ");
                            }
                        } while (pos2 < 1 || pos2 > 3);
                    do {
                            System.out.print("Quale tessera vuoi inserire per ultima? ");
                            pos3 = getCorrectInt();
                            if (pos3 < 1 || pos3 > 3) {
                                System.out.print("Inserire posizione da 1 a 3: ");
                            }
                        } while (pos3 < 1 || pos3 > 3);
                    if (pos1 == pos2 || pos1 == pos3 || pos2 == pos3) {
                            System.out.println("Inserire una combinazione di coordinate valide");
                        }

                    } while (pos1 == pos2 || pos1 == pos3 || pos2 == pos3);


                    OrderChoice order = new OrderChoice(pos1, pos2, pos3);
                    notifyOrder(order);

                    System.out.println("Hai ordinato correttamente le tessere! \n");
                }
            }
        }
    }

    /**
     *
     *
     * @return
     */
    private boolean playerOrder() {
        keyboard = new Scanner(System.in);
        boolean reorder = false;
        String string;
        String yes = "si";
        String no = "no";
        System.out.print("Vuoi ordinare le tessere selezionate? si o no? ");
        do {
            string = getCorrectString();

            if (!string.equals(yes) && !string.equals(no)) {
                System.out.print("Inserire 'si' oppure 'no' come risposta: ");
            }

        } while (!string.equals(yes) && !string.equals(no));
        System.out.print("\n");

        if (string.equals(yes)) {
            reorder = true;
        }

        return reorder;
    }

    //Insertion of the selected tiles into the shelf.
    public void playerInsert() throws NotEnoughSpaceChoiceException, RemoteException, NotAdjacentSlotsException, NotCatchableException {
        keyboard = new Scanner(System.in);
        int column;
        boolean ok;

        System.out.print("Scrivere il numero della colonna in cui inserire le tessere: ");
        do {
            column = getCorrectInt();

            if (column < 0 || column > 4) {
                System.out.print("Inserire un numero compreso tra 0 e 4: ");
            }
        } while (column < 0 || column > 4);
        System.out.print("\n");

        do {
            notifyInsert(column);
            ok = true;
        } while (!ok);
    }

    //Method that checks if the input is actually an int value
    private int getCorrectInt() {
        keyboard = new Scanner(System.in);
        int val = 0;
        boolean ok;
        do {
            try {
                val = keyboard.nextInt();
                ok = true;
            } catch (InputMismatchException e) {
                System.out.print("Inserire un numero intero per favore: ");
                keyboard.nextLine();
                ok = false;
            } catch (NoSuchElementException e) {
                System.out.print("Inserire un input per favore: ");
                keyboard.nextLine();
                ok = false;
            }

            if (val < 0) {
                System.out.print("Inserire un numero non negativo per favore: ");
                keyboard.nextLine();
                ok = false;
            }

        } while (!ok);
        return val;
    }

    //Method that checks if the input is actually a string
    private String getCorrectString() {
        keyboard = new Scanner(System.in);
        String string = "";
        boolean ok;
        do {
            try {
                string = keyboard.nextLine();
                ok = true;
            } catch (InputMismatchException e) {
                System.out.print("Inserire una stringa per favore: ");
                keyboard.nextLine();
                ok = false;
            } catch (NoSuchElementException e) {
                System.out.print("Inserire un input per favore: ");
                keyboard.nextLine();
                ok = false;
            }
        } while (!ok);
        return string;
    }

    /**
     * Prints the dashboard on screen.
     *
     * @param board The dashboard to print.
     */
    public void displayDashboard(Dashboard board) {
        System.out.print("\t");
        for (int k = 0; k < Dashboard.getSide(); k++) {
            System.out.print("\t    " + k + "    \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < Dashboard.getSide(); i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < Dashboard.getSide(); j++) {
                if ((!board.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !board.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(board.getSingleSlot(i, j).getColor()) + "["+ Image.colorToImage(board.getSingleSlot(i, j).getColor()) +"]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "         " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================================================================\n");
    }

    /**
     * Prints the user's personal shelf on screen.
     *
     * @param shelf The personal shelf to print.
     */
    public void displayPersonalShelf(PersonalShelf shelf) {
        System.out.print("\n\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t    " + k + "    \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!shelf.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !shelf.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(shelf.getSingleSlot(i, j).getColor()) + "[" + Image.colorToImage(shelf.getSingleSlot(i, j).getColor()) +"]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "         " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================================================================\n");
    }

    /**
     * Prints the user's personal goal on screen.
     *
     * @param pGoal The personal goal to print.
     */
    public void displayPersonalGoal(PersonalGoal pGoal) {

        boolean[][] isTarget = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
        System.out.println("Il Personal Goal (Tienilo segreto!!) che ti assegnerà punti extra è il seguente: \n");

        for (int countTarget = 0; countTarget < pGoal.getGoal().length; countTarget++) {
            isTarget[pGoal.getSingleTarget(countTarget).getPosX()][pGoal.getSingleTarget(countTarget).getPosY()] = true;
        }

        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t    " + k + "    \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if (isTarget[i][j]) {
                    for (int countTarget = 0; countTarget < pGoal.getGoal().length; countTarget++) {
                        if ((pGoal.getSingleTarget(countTarget).getPosX() == i) && (pGoal.getSingleTarget(countTarget).getPosY() == j)) {
                            System.out.print("\t" + ColorPrint.convertColor(pGoal.getSingleTarget(countTarget).getTile()) + "[" + Image.colorToImage(pGoal.getSingleTarget(countTarget).getTile()) +"]" + ColorPrint.RESET + "\t");
                        }
                    }
                } else System.out.print("\t" + "   ...   " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================================================================\n");
    }

    public void displayWin(String winner){
        System.out.println("Il gioco è finito!! Il vincitore è: " + winner);
    }
    public void notifyAlmostOver(){
        System.out.println("Alla fine del giro il gioco terminerà, affrettatevi!!");
    }
    public void arrived() {
        System.out.println("E' entrato un nuovo player");
    }
    public void startTurn() {
        System.out.print("-- Inizio del nuovo turno -- \n");
    }
    public void onWait() {
        System.out.print("-- Non è il tuo turno --");
    }
    public void run() {
        System.out.print("");
    }
    //Adding listener
    @Override
    public void addviewEventListener(viewListeners listener) {
            listeners.add(listener);
    }

    //Notification to all listeners (Clients) of the completed selection.
    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {
        for( viewListeners listener : listeners  ) {
            listener.notifySelectedCoordinates(SC);
        }
    }
    //Notify all listeners (Clients) of the successful notification.
    @Override
    public void notifyOrder(OrderChoice o){
        for( viewListeners listener : listeners  ) {
            try {
                listener.notifyOrder(o);
            } catch (RemoteException e) {
                System.out.println("RemoteExcpetion throw this. ");
            } catch (NotEnoughSpaceChoiceException | NotAdjacentSlotsException | NotCatchableException e) {
                throw new RuntimeException(e);
            }
        }
        }
     //Notification to all listeners (clients) of the successful insertion.
    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            listener.notifyInsert(column);
        }
    }
    @Override
    public void notifyOneMoreTime() throws RemoteException, SameNicknameException {
        for( viewListeners listener : listeners  ) {
            listener.notifyOneMoreTime();
        }
    }
    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            listener.notifyChoices(number);
        }
    }

    /**
     * Prints the two common goals on screen.
     *
     * @param gameView The game view to obtain the common goals.
     */
    public void displayCommonGoal(GameView gameView){
            System.out.println("I common goal che sono stati estratti in questa partita sono: ");
            gameView.getCommonGoal1().show();
            gameView.getCommonGoal2().show();
        }

    /**
     * Asks for the reinsertion of the nickname
     *
     * @param message
     * @throws SameNicknameException
     * @throws RemoteException
     */
    public void errorNick(String message) throws SameNicknameException, RemoteException {
           System.out.println(message);
           System.out.println("\n Vuoi provare ad entrare nella partita con un nuovo nickname? ");
           String yes = "si";
           String no = "no";
           String response = keyboard.nextLine();
        do {
            if (!response.equals(yes) && !response.equals(no)) {
                System.err.print("Inserire 'si' oppure 'no' come risposta: ");
                response = keyboard.nextLine();
            }

        } while (!response.equals(yes) && !response.equals(no));
        System.out.print("\n");
            if(response.equals("si")){
                notifyOneMoreTime();
            }
    }

    public void endgame(){
        System.out.println(" Il gioco è finito! ");
    }

    public void denyAcess() {
        System.err.println("La partita è già iniziata, troppo tardi :/ ");
    }

    public void playerCrash() {System.err.println("E' crashato un player ");}

    public void gameCancelled() {System.err.println("E' crashato un player in pregame, chiusura della partita, scusate! ");}

    public void waitingForPlayers() {System.out.println("Non ci sono abbastanza giocatori per continuare, attesa riconnessione o fine partita in 30s ");}

    /**
     * Prints an error for the selection of a single slot not catchable.
     */
    public void errorNotCatchable() {
        System.out.println("La tessera selezionata non è prendibile! Ripetere la selezione!");
    }

    /**
     * Prints an error for the selection of a slot not catchable in a multiple choice.
     */
    public void errorOneNotCatchable() {
        System.out.println("Una delle tessere selezionate non è prendibile! Ripetere la selezione!");
    }

    /**
     * Prints an error for the selection of not adjacent tiles.
     */
    public void errorNotAdjacent() {
        System.out.println("Le tessere selezionate non sono adiacenti! Ripetere la selezione!");
    }

    /**
     * Prints an error message for the number of choices from the dashboard.
     */
    public void errorSpaceChoicesError() {
        System.out.println("Non c'è abbastanza spazio nella shelf per così tante tessere!");
    }

    /**
     * Prints an error message for the insert.
     */
    public void errorInsert() {
        System.out.println("La colonna selezionata non ha abbastanza spazio per tutte le tessere! Scegline un'altra!");
    }

    /**
     * Prints a reduced personal goal on screen.
     *
     * @param pgoal The personal goal to print.
     */
    public void displayPersonalGoal2(PersonalGoal pgoal) {
        System.out.println("Il tuo Personal Goal è il seguente, non dimenticarlo!");
        for(int i=0; i < pgoal.getGoal().length; i++){
            System.out.print("["+ pgoal.getSingleTarget(i).getPosX()+"," +
                    pgoal.getSingleTarget(i).getPosY() + ","+ pgoal.getSingleTarget(i).getTile()+"]. ");
        }
        System.out.print("\n");
    }

    /**
     * Prints a textual description of the input common goal on screen.
     *
     * @param CG The common goal to describe.
     */
    public void commonGoalReminder(CommonGoalAbs CG) {
        System.out.println("Common Goal attivo: " + CG.description());
    }
}




