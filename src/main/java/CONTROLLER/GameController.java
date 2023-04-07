package CONTROLLER;

import MODEL.Game;

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


    }



}


