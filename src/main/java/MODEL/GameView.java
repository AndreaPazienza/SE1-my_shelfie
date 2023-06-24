package MODEL;

import java.io.Serializable;

public class GameView implements Serializable {

    static final long serialVersionUID = 1L;
    private final Dashboard table;
    private final PersonalShelf shelf;
    private final boolean gameState;
    private final PersonalGoal pgoal;
    private final CommonGoalAbs commonGoal1, commonGoal2;
    private final GameState state;

    private final Player[] ranking;
    private final GameError gameError;

    //gameView for the start game
    public GameView(Game game){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        gameState = game.isGameOn();
        pgoal = null;
        commonGoal1 = null;
        commonGoal2 = null;
        gameError = null;
        ranking = null;
        state = game.getCurrentState();
    }

    //gameview for the single turn
    public GameView(int index, Game game){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        commonGoal1 = game.getCommonGoal1();
        commonGoal2 = game.getCommonGoal2();
        state = game.getCurrentState();
        gameState = game.isGameOn();
        gameError = null;
        ranking = null;
        pgoal = game.getPlayer()[index].getPgoal();
    }

    //Utilizzare Optional<T>
    //gameview for notifying of the errors
    public GameView(Game game, int ind){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        gameState = game.isGameOn();
        pgoal = null;
        commonGoal1 = null;
        commonGoal2 = null;
        gameError = game.getLastError();
        ranking = null;
        state = game.getCurrentState();
    }

    //gameview for the endgame, ranks is the players of the game array ordered by their points
    public GameView(Player[] ranks, Game game){
        table = null;
        shelf = null;
        gameState = game.isGameOn();
        pgoal = null;
        commonGoal1 = null;
        commonGoal2 = null;
        gameError = null;
        ranking = ranks;
        state = game.getCurrentState();
    }

    //da gestire il fatto che viene in seguito viene resettato a null
    public CommonGoalAbs getCommonGoal1() {
        return commonGoal1;
    }

    public CommonGoalAbs getCommonGoal2() {
        return commonGoal2;
    }

    public Dashboard getTable() {
        return table;
    }

    public PersonalShelf getShelf() {
        return shelf;
    }

    public PersonalGoal getPgoal() {
        return pgoal;
    }

    public GameState getState(){return state;}

    public GameError getGameError() {
        return gameError;
    }

    public boolean getGameState(){return gameState;}

    public Player[] getRanking() {
        return ranking;
    }
    public GameView(PersonalShelf s, Dashboard t){
        table = t;
        shelf = s;
        pgoal = null;
        commonGoal1 = null;
        gameState = true;
        commonGoal2 = null;
        gameError = null;
        state = null;
        ranking = null;
    }

}
