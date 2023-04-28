package CONTROLLER;

import Errors.NotCatchableException;
import MODEL.*;

import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {
    private Game game;

    private Slot[] selectedSlot;

    public int k;

    private GameState gameState = GameState.NOTSTARTED;

    public GameState getGameState() {
        return this.gameState;
    }
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
    }*/

    //controllo se il nickname è stato già preso
    public boolean checkNick(String name){
        for(int i = 0; i < game.getNplayers(); i++){
            if(game.getPlayer()[i].nickname.equals(name)){
                return false; //genera un eccezione SAMENICK
            }
        }
        return true;
    }

    public void beginGame(){  //prima creazione della GameView
        if(gameState.equals(GameState.CANSTART)){
            //popolazione dashboard
            game.getTable().refill(game.getBag());
            game.getTable().catchAfterRefill();
            //estrazione degli obiettivi personali
            game.assignPGoal();
            //La partita può iniziare
            game.setGameOn(true);
            }
    }


    //method which controls if a tile can be caught
    public boolean checkCoordinates(int x, int y){
        return game.getTable().getSingleSlot(x,y).isCatchable();
    }

    //method which controls if two tiles are nearby
    public boolean checkAdjacent(int x, int y, int x1, int y1){
        if((x == x1 && (y == y1 + 1 || y == y1-1)) || (y == y1 && (x == x1+1 || x == x1-1))){
            return true;
        }
        return false;
    }

    //method which control the selection of ONE tile
    public void checkSelect(Moves m) throws NotCatchableException{
        int x = m.getTarget()[k].getX();
        int y = m.getTarget()[k].getY();
        if(checkCoordinates(x,y)){
           selectedSlot[k] = game.getPlayer()[game.getPlayerInGame()].selectCard(game.getTable(), x, y);
        } else {
            throw new NotCatchableException("The tile that you choose is not catchable!");
        }
    }

    //method which control the selection of TWO tile
    public void checkSelect2(Moves m){

    }

    public void checkSelect3(Moves m){

    }

    public void checkReorder(Moves m){

    }

    public void checkInsert(Moves m){

    }
    public void completeRound(){
        //int countdown = game.getNplayers()-game.getPlayerInGame();
        while(game.getPlayerInGame()!= 0){
            //turnManagement();
        }
    }
    public void endGame(){
        Player winner;
        for(int i = 0; i < game.getPlayer().length; i++){
            game.getPlayer()[i].checkScore();
        }
        winner = game.finalScore();
        //System.out.println("Il vincitore è :"+winner.getNickname()+" Complimenti!");
        game.setGameOn(false);
    }



    @Override
    public void update(Observable o, Object arg) {

    }
}













