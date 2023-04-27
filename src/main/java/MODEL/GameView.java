package MODEL;

import java.io.Serializable;

public class GameView implements Serializable {

    static final long serialVersionUID = 1L;
    private final Dashboard table;
    private final PersonalShelf shelf;
    private final CommonGoal commonGoal1, commonGoal2;
    private final PersonalGoal pgoal;

    public GameView(Game game){
        table = game.getTable();
        shelf = game.getPlayer()[game.getPlayerInGame()].getShelf();
        commonGoal1 = game.getCommonGoal1();
        commonGoal2 = game.getCommonGoal2();
        pgoal = game.getPlayer()[game.getPlayerInGame()].getPgoal();
    }

    public Dashboard getTable() {
        return table;
    }

    public PersonalShelf getShelf() {
        return shelf;
    }

    public CommonGoal getCommonGoal1() {
        return commonGoal1;
    }

    public CommonGoal getCommonGoal2() {
        return commonGoal2;
    }

    public PersonalGoal getPgoal() {
        return pgoal;
    }
}
