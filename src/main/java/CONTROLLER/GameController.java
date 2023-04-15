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
            //popolazione dashboard
            Bag bag = new Bag();
            game.getTable().refill(bag);
            game.getTable().catchAfterRefill();
            //estrazione degli obiettivi personali
            game.assignPGoal();
            //La partita può iniziare
            game.setGameOn(true);
            }
        }

    public void gameOnGoing() {
        while (gameState.equals(GameState.NOTSTARTED) || gameState.equals(GameState.ONWAIT)) {
            this.startGame();
        }
        beginGame();
        while(game.isGameOn()){
            turnManagement();
        }
        System.out.println("-- Congratulazioni, il gioco è finito --");
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
        int k = 0, x1=-1, x2=-1, y1=-1, y2 = -1;
        String yes = "si";
        String no = "no";
        String string;
        boolean r = false;
        boolean c = false;
        //selezione (manca controllo se tessere scelte sono adiacenti)
        do {
            System.out.println("Inserire le coordinate della tessera da prendere: ");
            System.out.println("X: ");
            int x = scanner.nextInt();
            System.out.println("Y: ");
            int y = scanner.nextInt();
            if(k==0) {
                slots[k] = game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y);
            }
            do {
                if (k == 1 && checkSelection2(x, y, x1, y1, x2, y2)) {
                    slots[k] = game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y);
                }
                //()
            } while(!checkSelection2(x, y, x1, y1, x2, y2));
            if(k==2 && checkSelection3(x, y, x1, y1, x2, y2))
            {
                slots[k] = game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y);
            }

            do {
                System.out.println("Ne vuoi scegliere altre? si o no?");
                string = scanner.nextLine();

                if (string.equals(yes) && k != 2) {
                    x1 = x;
                    y1 = y;
                    k++;
                } else if (string.equals(yes) && k == 2) {
                    System.out.println("Hai già selezionato 3 tessere!");
                    k++;
                } else if (string.equals(no)) {
                    k = 3;
                } else {
                    System.out.println("Scusami non ho capito! Rispondi si o no per favore!");
                }
            } while (!string.equals(yes) && !string.equals(no));
        } while (k < 3);
        //ordinamento //reloop
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
            if (column < 0 || column >= game.getPlayer()[0].getShelf().N_COLUMN){
                System.out.println("La colonna inserita non è valida!");
            }
        }   while (column < 0 || column >= game.getPlayer()[0].getShelf().N_COLUMN);
        game.getPlayer()[game.getPlayerInGame()].getShelf().insert(slots, column);
        //controllo se la shelf è piena: se la partita non è finita, passo il turno
        game.getPlayer()[game.getPlayerInGame()].getShelf().checkLastLine();
        if(game.getPlayer()[game.getPlayerInGame()].getShelf().isItsFull()){
            game.updateTurn();
            completeRound();
            endGame();
        }   else  {
            game.updateTurn();
        }
        }

        public boolean checkSelection2(int x, int y, int x1, int y1, int x2, int y2){
            if((x==x1 && ((y==y1+1 || y==y1-1)) || ((x==x1+1 || x==x1-1) && y==y1))){
                if(x==x1){
                    y2 = y1;  //in y2 tengo salvata la prima tessera selezionata
                } else {
                    x2 = x1;
                }
                return true;
            } else {
            return false;
            }
        }
        public boolean checkSelection3(int x, int y, int x1, int y1, int x2, int y2) {
            if (x == x2) {
                if ((y == y1 + 1 || y == y1 - 1 && y != y2) || (y == y2 + 1 || y == y2 - 1 && y != y1)) {
                    return true;
                }
            }
            if (y == y2) {
                if ((x == x1 + 1 || x == x1 - 1) || (x == x2 + 1 || x == x2 - 1)) {
                    return true;
                }
            }
            return false;
        }

        public void completeRound(){
            //int countdown = game.getNplayers()-game.getPlayerInGame();
            while(game.getPlayerInGame()!= 0){
                turnManagement();
            }
        }
        public void endGame(){
            Player winner;
            for(int i = 0; i < game.getPlayer().length; i++){
                game.getPlayer()[i].checkScore();
            }
            winner = game.finalScore();
            System.out.println("Il vincitore è :"+winner.getNickname()+" Complimenti!");
            game.setGameOn(false);
        }


        // Stampa della dashboard, come si vuole fare?
        public void displayDashboard(){
            for(int k = 0; k < Dashboard.getSide(); k++){
                System.out.print("\t  "+k+"  \t");
            }
            System.out.print("\n");
            for (int i = 0; i < Dashboard.getSide(); i ++) {
                System.out.println(""+i);
                for (int j = 0; j < Dashboard.getSide(); j ++ ) {
                    if (game.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK))
                        System.out.print("\t     \t");
                    else if (!game.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY))
                        System.out.print("\t" + game.getTable().getSingleSlot(i,j).getColor() + "\t");
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













