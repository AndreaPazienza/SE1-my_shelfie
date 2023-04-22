package VIEW;

import CONTROLLER.GameController;
import Errors.NotCatchableException;
import MODEL.Slot;

import java.util.Scanner;

public class GameInterface {
    protected int selectedTails=0;
    private GameController controller = new GameController();
    private final Scanner keyboard = new Scanner(System.in);

    public void startGame() {

        System.out.println("Inserisci il numero di giocatori: \n");
        int nPlayers = keyboard.nextInt();


        System.out.println("Inserisci il nickname: \n");
        String nick = keyboard.nextLine();
    }


    public void playerMoveSelection() {

        int k = 0;
        String yes = "si";
        String no = "no";
        String string;
        int x1 = -1;
        int y1 = -1;
        Moves select = new Moves();

        do {
            //Da gestire all'interno di un blocco try/catch; errori possibili numero negativo o out of bound
            try {
                    System.out.println("Inserire le coordinate della tessera da prendere: ");
                    System.out.println("X: ");
                    int x = keyboard.nextInt();
                    System.out.println("Y: ");
                    int y = keyboard.nextInt();
                    controller.selectInDashboard(x, y);
            } catch (NotCatchableException e) {
                    System.out.println("La tessera che hai selezionato non è prendibile! Scegline un'altra!");
                    System.out.println("Inserire le coordinate della tessera da prendere:");
                    System.out.println("X: ");
                    int x = keyboard.nextInt();
                    System.out.println("Y: ");
                    int y = keyboard.nextInt();

            } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("La tessera selezionata non esiste nella plancia di gioco! Selezionane un'altra!");
                    System.out.println("Inserire le coordinate della tessera da prendere: ");
                    System.out.println("X: ");
                    int x = keyboard.nextInt();
                    System.out.println("Y: ");
                    int y = keyboard.nextInt();
                    select.setX(x);
                    select.setY(y);
            } finally {
                    do {
                    System.out.println("Ne vuoi scegliere altre? si o no?");
                    string = keyboard.nextLine();

                    if (string.equals(yes) && k != 2) {
                        k++;
                        selectedTails++;
                    } else if (string.equals(yes)) {
                        System.out.println("Hai già selezionato 3 tessere!");
                        k++;
                        selectedTails++;
                    } else if (string.equals(no)) {
                        k = 3;
                    } else {
                        System.out.println("Scusami non ho capito! Rispondi si o no per favore!");
                    }
                } while (!string.equals(yes) && !string.equals(no));


            }
        } while (k < 3);
    }

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


public void reorderParameters(){

}
}
