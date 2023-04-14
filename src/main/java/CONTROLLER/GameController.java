package CONTROLLER;

import MODEL.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Game game;

    private GameState gameState = GameState.NOTSTARTED;
    // riferimento alla view


    public void startGame () {
        Scanner input = new Scanner(System.in);
        int counter = 0;
        switch (gameState) {
            case NOTSTARTED :{
                System.out.println("Inserisci il numero di giocatori: \n");
                int nPlayers = input.nextInt();
                game = new Game(nPlayers);
                gameState = GameState.ONWAIT;
            }

            case ONWAIT :{
                System.out.println("Inserisci il nickname: \n");
                String nick = input.nextLine();
                while (checkNick(nick)) {
                    System.out.println("Inserisci il nickname: \n");
                    nick = input.nextLine();
                }
                game.signPlayer(nick);
                counter++;
                if (counter == game.getNplayers()) {
                    gameState = GameState.CANSTART;
                }
            }
        }
    }

    //controllo se il nickname è stato già preso
    public boolean checkNick(String name){
        for(int i = 0; i < game.getNplayers(); i++){
            if(game.getPlayer()[i].nickname.equals(name)){
                return false;
            }
        }
        return true;
    }

    public void beginGame(){
        if(gameState.equals(GameState.CANSTART)){
            //preparazione della dashboard: vengono posizionate le tessere e vengono settate a true quelle prendibili
            Bag bag = new Bag();
            game.getTable().refill(bag);
            game.getTable().catchAfterRefill();

            //estrazione degli obiettivi comuni e personali
            game.assignPGoal(); //manca assegnazione degli shared goal

            //
            }
        }

    //turn management inizialmente era implementato, nello schizzo, con il passaggio di una mossa come parametro.
    //Suggerisco invece questa implementazione, in cui le mosse vengono eseguite in sequenza. manca il controllo
    //dello shared goal, su cui ho qualche dubbio
    public void turnManagement() {
            Scanner scanner = new Scanner(System.in);
            Slot[] slots = new Slot[3];
            for (int i = 0; i < 3; i++) {
                slots[i] = new Slot(Color.GREY);
            }
            int k = 0;
            String yes = "si";
            String no = "no";
            String string;
        //selezione
            do {
                System.out.println("Inserire le coordinate della tessera da prendere: ");
                System.out.println("X: ");
                int x = scanner.nextInt();
                System.out.println("Y: ");
                int y = scanner.nextInt();
                slots[k] = game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y);
                System.out.println("Ne vuoi scegliere altre? si o no?");
                string = scanner.nextLine();
                if (string.equals(yes) && k != 2) {
                    k++;
            }   else if (string.equals(yes) && k == 2) {
                    System.out.println("Hai già selezionato 3 tessere!");
                    k++;
            }   else if (string.equals(no)) {
                    k = 3;
            }   else {
                    System.out.println("Scusami non ho capito! Rispondi si o no per favore!");
                }
            } while (k < 3);
        //ordinamento
            System.out.println("Vuoi ordinare le tessere selezionate? si o no?");
            string = scanner.nextLine();
            if (string.equals(yes)) {
                int realLength = 0;
                for (int j = 0; j < 3; j++) {
                    if (!slots[j].getColor().Equals(Color.GREY)) {
                        realLength++;
                    }
                }
                if (realLength == 1) {
                    System.out.println("Hai selezionato solo una tessera: non serve riordinare!");
                }   else if (realLength == 2) {
                    game.getPlayer()[game.getPlayerInGame()].orderCards(slots);
                }   else  {
                    System.out.println("Quale tessera vuoi inserire per prima? 1, 2 o 3?");
                    int first = scanner.nextInt();
                    System.out.println("Quale tessera vuoi inserire per seconda?");
                    int second = scanner.nextInt();
                    System.out.println("Quale tessera vuoi inserire per ultima? ");
                    int last = scanner.nextInt();
                    game.getPlayer()[game.getPlayerInGame()].orderCards(slots, first, second, last);
                    System.out.println("Le tessere sono state ordinate! Procediamo con l'inserimento!");
                }
            }   else if (string.equals(no)) {
                System.out.println("Ok! Procediamo con l'inserimento!");
            }   else  {
                System.out.println("Scusami non ho capito! Rispondi si o no per favore!");
            }
        //inserimento
            int column = 0;
            do {
                System.out.println("In che colonna vuoi mettere le tessere?");
                column = scanner.nextInt();
                if (column < 0 && column >= game.getPlayer()[0].getShelf().N_COLUMN){
                    System.out.println("La colonna inserita non è valida!");
                }
            }   while (column < 0 && column >= game.getPlayer()[0].getShelf().N_COLUMN);
            game.getPlayer()[game.getPlayerInGame()].getShelf().insert(slots, column);
        //Controllo dell'obiettivo comune

        //controllo se la shelf è piena: se la partita non è finita, passo il turno
            game.getPlayer()[game.getPlayerInGame()].getShelf().checkLastLine();
            if(game.getPlayer()[game.getPlayerInGame()].getShelf().isItsFull()){
                endGame();
            }   else  {
                game.updateTurn();
            }
        }

        public void endGame(){

        }

        /*
        // Stampa della dashboard, come si vuole fare?
        public void displayDashboard(){
        ArrayList<Slot> playable = new ArrayList<Slot>;
        ArrayList<Slot> onTable = new ArrayList<Slot>;
        Slot tmp;

        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++){
               tmp = game.getTable().getSingleSlot(i, j);
                if(tmp.getColor().equals(Color.BLACK) && tmp.getColor().equals(Color.GREY) )
                    if(tmp.isCatchable())

                    else


            }
        }



        }
*/


}








