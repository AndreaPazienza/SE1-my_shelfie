package VIEW;

import CONTROLLER.GameController;
import CONTROLLER.GameState;
import Errors.NotCatchableException;
import Errors.SameNicknameException;
import MODEL.*;

import java.util.Scanner;

public class GameInterface implements Runnable{

    Scanner keyboard = new Scanner(System.in);

    //inserimento nickname per la prima volta
    public String firstRun() {

        String nick = null;
        boolean ok = false;

        do {
            try {
                System.out.print("Inserire il nickname: ");
                nick = keyboard.nextLine();
                ok = true;

            } catch (SameNicknameException) {
                System.out.print("Il nickname inserito è già esistente");
                ok = false;
            }
        } while (!ok);

        return nick;
    }

    public void run() {

        int nChoices = 0;

        nChoices = playerMoveSelection();
        if (nChoices != 1)
            playerOrder(nChoices);

    }


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

            System.out.print("");

            //Inserimento e controllo tessera singola
            do {
                try {
                    System.out.println("Inserire le coordinate della tessera da prendere: ");
                    System.out.println("X: ");
                    int x = keyboard.nextInt();
                    System.out.println("Y: ");
                    int y = keyboard.nextInt();
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
                    }while (pos1 < 1 || pos1>3);

                    do {
                        System.out.println("Quale tessera vuoi inserire per seconda?");
                        pos2 = keyboard.nextInt();
                    }while (pos1 < 1 || pos1>3);

                    do {
                        System.out.println("Quale tessera vuoi inserire per ultima?");
                        pos3 = keyboard.nextInt();
                    }while (pos1 < 1 || pos1>3);

                }while (pos1==pos2 ||pos1==pos3 ||pos2==pos3);


                OrderChoice c = new OrderChoice(pos1, pos2, pos3);
                setChanged();
                notifyObservers(c);
                System.out.print("Hai ordinato correttamente le tessere!");
            }

            else if(nChoices==1){
                System.out.println("Non c'è bisogno di riordinare! Hai selezionato solo una tessera!");
            }


        }
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
 /*
    public void playerOrder() {  //Eccezioni da gestire: scelta di due numeri uguali, numero diverso da 1/2/3
            String string;
            String yes = "si";
            String no = "no";
            do { //blocco per avere solo vero o farlo
                System.out.println("Vuoi ordinare le tessere selezionate? si o no?");
                string = keyboard.nextLine();
                //Controllo che non andrebbe qua ma delegato al controller
                if (string.equals(yes)) {
                   if(selectedTails==3) {
                       System.out.println("Quale tessera vuoi inserire per prima?");
                       int pos1 = keyboard.nextInt();
                       System.out.println("Quale tessera vuoi inserire per seconda?");
                       int pos2 = keyboard.nextInt();
                       System.out.println("Quale tessera vuoi inserire per ultima?");
                       int pos3 = keyboard.nextInt();
                       controller.reorderChoice(pos1, pos2, pos3); //questione RMI
                   } else if(selectedTails == 1){
                        System.out.println("Non c'è bisogno di riordinare! Hai selezionato solo una tessera!");
                       }
                   else{
                       controller.reorderChoice(); //questione RMI
                   }

                } else if (string.equals(no)) {
                    System.out.println("Ok! Procediamo con l'inserimento!");
                } else {
                    System.out.println("Scusami non ho capito! Rispondi si o no per favore!");
                }
            } while (!string.equals(yes) && !string.equals(no));

            System.out.println("Ordinamento concluso con successo");
        }
 */

public void reorderParameters(){

}

    public void displayDashboard(){
        for(int k = 0; k < Dashboard.getSide(); k++){
            System.out.print("\t  "+k+"  \t");
        }
        System.out.print("\n");
        for (int i = 0; i < Dashboard.getSide(); i ++) {
            System.out.println(""+i);
            for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                if (GameView.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print("\t     \t");
                else if (!GameView.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY))
                    System.out.print("\t" + GameView.getTable().getSingleSlot(i,j).getColor() + "\t");
            }
            System.out.print("\n");
        }
    }

    public void displayPersonalShelf(){
        for(int k = 0; k < PersonalShelf.N_COLUMN; k++){
            System.out.print("\t  "+k+"  \t");
        }
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i ++) {
            System.out.println(""+i);
            for (int j = 0; j < PersonalShelf.N_COLUMN; j ++ ) {
                if (game.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                    System.out.print("\t     \t");
                else if (!game.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY))
                    System.out.print("\t" + game.getTable().getSingleSlot(i,j).getColor() + "\t");
            }
            System.out.print("\n");
        }
    }

    public void displayCommonGoals(){

    }
}
