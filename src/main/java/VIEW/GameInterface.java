package VIEW;

import CONTROLLER.GameController;
import CONTROLLER.GameState;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import MODEL.*;

import java.util.Scanner;

public class GameInterface implements Runnable{

    public Scanner keyboard = new Scanner(System.in);
    //public Game  gameView; //solo per fare i test

    public GameInterface (){
        //gameView = new Game(4); sempre per test
    }



    //inserimento nickname per la prima volta
    public String firstRun() {

        String nick = null;
        boolean ok = false;

        do {
            try {
                System.out.print("Inserire il nickname: ");
                nick = keyboard.nextLine();
                ok = true;

            } catch (SameNicknameException e) {
                System.out.print("Il nickname inserito è già esistente");
                ok = false;
            }
        } while (!ok);

        return nick;
    }

    public void run() {

        int nChoices = 0;

        displayDashboard();
        displayPersonalShelf();
        displayCommonGoals();
        nChoices = playerMoveSelection();
        if (nChoices != 1)
            playerOrder(nChoices);
        playerInsert();


    }


    //Selezione delle tessere dalla dashboard
    public int playerMoveSelection() {

        int countChoices = 0;
        int nChoices = 0;
        int maxChoices = 0;
        boolean ok = false;
        //Viene stabilito il massimo numero di tessere prendibili in base agli spazi liberi nella shelf

        //Inserimento del numero di tessere da selezionare e controllo
        do {
            System.out.println("Inserire il numero di tessere da selezionare: ");
            nChoices = keyboard.nextInt();
            if (nChoices > maxChoices) {
                System.out.println("Nessuna colonna della shelf ha così tanto spazio disponibile: ");
            }
        } while (nChoices < 1 || nChoices > maxChoices);

        //Selezione effettiva delle tessere e controllo di esse (singolarmente)
        do {

            int x = -1;
            int y = -1;

            System.out.print("");

            //Inserimento e controllo tessera singola
            do {
                try {
                    do {
                        System.out.println("Inserire le coordinate della tessera da prendere: ");
                        System.out.println("X: ");
                        x = keyboard.nextInt();
                        System.out.println("Y: ");
                        y = keyboard.nextInt();
                        if (x < 0 || x > 8 || y < 0 || y > 8) {
                            System.out.print("Inserire parametri compresi tra 0 e 8");
                        }
                    } while(x < 0 || x > 8 || y < 0 || y > 8);

                    SlotChoice c = new SlotChoice(x, y);
                    setChanged();
                    notifyObservers(c);
                    countChoices++;
                    ok = true;

                } catch (NotCatchableException e) {
                    System.out.println("La tessera che hai selezionato non è prendibile! Scegline un'altra!");
                    countChoices--;
                    ok = false;

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("La tessera selezionata non esiste nella plancia di gioco! Selezionane un'altra!");
                    countChoices--;
                    ok = false;
                }
            }while (!ok);

        }while (countChoices < nChoices);

        System.out.println("Le tessere sono state selezionate");

        return nChoices;
    }

    //Ordinamento delle tessere prese prima dell'inserimento nella shelf
    public void playerOrder(int nChoices) {

        boolean reorder = false;
        String string;
        String yes = "si";
        String no = "no";
        int pos1;
        int pos2;
        int pos3;

        do {
            System.out.println("Vuoi ordinare le tessere selezionate? si o no?");
            string = keyboard.nextLine();
            if (string.equals(yes))
                reorder = true;

        } while (!string.equals(yes) && !string.equals(no));

        if (string.equals(yes)) {

            if (nChoices == 2) {
                setChanged();
                notifyObservers(reorder);
            }

            if (nChoices == 3) {

                do {
                    do {
                        System.out.println("Quale tessera vuoi inserire per prima?");
                        pos1 = keyboard.nextInt();
                        if(pos1 < 1 || pos1 > 3){
                            System.out.println("Inserire posizione da 1 a 3");
                        }
                    }while (pos1 < 1 || pos1>3);

                    do {
                        System.out.println("Quale tessera vuoi inserire per seconda?");
                        pos2 = keyboard.nextInt();
                        if(pos2 < 1 || pos2 > 3){
                            System.out.println("Inserire posizione da 1 a 3");
                        }
                    }while (pos2 < 1 || pos2>3);

                    do {
                        System.out.println("Quale tessera vuoi inserire per ultima?");
                        pos3 = keyboard.nextInt();
                        if(pos3 < 1 || pos3 > 3){
                            System.out.println("Inserire posizione da 1 a 3");
                        }
                    }while (pos3 < 1 || pos3>3);

                }while (pos1==pos2 ||pos1==pos3 ||pos2==pos3);

                OrderChoice c = new OrderChoice(pos1, pos2, pos3);
                setChanged();
                notifyObservers(c);
                System.out.print("Hai ordinato correttamente le tessere!");
            }
        }
    }

    //Inserimento delle tessere prese nella shelf
    public void playerInsert() {

        int column = -1;
        boolean ok = false;

        System.out.println("Scrivere il numero della colonna in cui inserire le tessere: ");
        do {
            column = keyboard.nextInt();
            if (column < 0 || column > 4) {
                System.out.print("Inserire un numero compreso tra 0 e 4");
            }
        } while (column < 0 || column > 4);

        do {
            try {
                setChanged();
                notifyObservers(c);
                ok = true;
            } catch (NotEnoughSpaceChoiceException e) {
                System.out.println("La colonna inserita non ha abbastanza spazio disponibile");
                ok = false;
            }
        } while (!ok);

        System.out.print("Hai inserito correttamente le tessere!");
    }

/*
    public void startGame() {
        GameState gameState = controller.getGameState();
        switch (gameState){
            case NOTSTARTED -> {
                System.out.println("Inserisci il numero di giocatori: \n");
                int nPlayers = keyboard.nextInt();
                //rmi

            }
        }

        System.out.println("Inserisci il nickname: \n");
        String nick = keyboard.nextLine();
    }
*/


    //Stampa della dashboard a schermo
    public void displayDashboard(){
        System.out.print("\t");
        for(int k = 0; k < Dashboard.getSide(); k++){
            System.out.print("\t" + k + "\t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            System.out.print("" + i +"\t");
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if ((!gameView.getTable().getSingleSlot(i,j).getColor().Equals(Color.BLACK) && !gameView.getTable().getSingleSlot(i,j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(gameView.getTable().getSingleSlot(i, j).getColor()) + "[]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "  " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
    }

    //Stampa della personal shelf a schermo
    public void displayPersonalShelf(){
        System.out.print("\t");
        for(int k = 0; k < PersonalShelf.N_COLUMN; k++){
            System.out.print("\t" + k + "\t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i ++) {
            System.out.print("" + i +"\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!gameView.getTable().getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !gameView.getTable().getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(gameView.getTable().getSingleSlot(i, j).getColor()) + "[]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t " + "  " + " \t");
            }
        }
        System.out.print("\n");
        System.out.print("\n");
    }


    public void displayCommonGoals(){
        //Vedere come stampare ogn singolo common goal
    }
}
