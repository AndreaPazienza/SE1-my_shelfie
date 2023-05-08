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
    private final GameError gameError;
    public GameView(Game game){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        gameState = game.isGameOn();
        pgoal = null;
        commonGoal1 = null;
        commonGoal2 = null;
        gameError = null;
        state = game.getCurrentState();
    }
    public GameView(int index, Game game){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        commonGoal1 = game.getCommonGoal1();
        commonGoal2 = game.getCommonGoal2();
        state = game.getCurrentState();
        gameState = game.isGameOn();
        gameError = null;
        pgoal = game.getPlayer()[index].getPgoal();
    }

    //Utilizzare Optional<T>
    public GameView(Game game, int ind){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        gameState = game.isGameOn();
        pgoal = null;
        commonGoal1 = null;
        commonGoal2 = null;
        gameError = game.getLastError();
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

}
