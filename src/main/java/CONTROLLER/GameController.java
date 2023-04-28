package CONTROLLER;

import MODEL.*;

import java.util.Observable;
import java.util.Observer;

public class GameController{
    private final Game game;

    public GameController(Game game){
        this.game=game;
    }


    public void startGame () {
        if(game.isGameOn()) {
            game.getTable().refill(game.getBag());
            game.getTable().catchAfterRefill();
            game.assignPGoal();
        }
    }

    //controllo se il nickname è stato già preso
    public boolean checkNick(String name){
        for(int i = 0; i < game.getNplayers(); i++){
            if(game.getPlayer()[i].nickname.equals(name)){
                return false; //genera un eccezione SAMENICK
            }
        }
        return true;
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


}













