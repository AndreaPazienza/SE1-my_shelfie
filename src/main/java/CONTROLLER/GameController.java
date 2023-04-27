package CONTROLLER;

import MODEL.*;

import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {
    private Game game;

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

  /*  public void gameOnGoing() { //spezzare in controlli separati delle mosse: checkCoordinates, CheckmoreSelections
        while (gameState.equals(GameState.NOTSTARTED) || gameState.equals(GameState.ONWAIT)) {
            this.startGame();
        }
        beginGame();
        while(game.isGameOn()){
            turnManagement();
        }
        System.out.println("-- Congratulazioni, il gioco è finito --");
    }*/





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













