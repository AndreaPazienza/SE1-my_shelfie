package MODEL;

import java.io.Serializable;

public class GameView implements Serializable {

    static final long serialVersionUID = 1L;
    private final Dashboard table;
    private final PersonalShelf shelf;
    private final PersonalGoal pgoal;

    private final CommonGoalAbs commonGoal1, commonGoal2;
    private final GameState state;

    public GameView(Game game){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        pgoal = game.getPlayer()[game.getPlayerInGame()].getPgoal();
        commonGoal1 = game.getCommonGoal1();
        commonGoal2 = game.getCommonGoal2();
        state = game.getCurrentState();
    }


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
}
