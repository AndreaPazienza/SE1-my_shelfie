package MODEL;

import java.io.Serializable;

/**
 * Class that represents the current view of the game.
 */
public class GameView implements Serializable {

    /**
     * The serial version of the class.
     */
    static final long serialVersionUID = 1L;

    /**
     * The current dashboard of the match.
     */
    private final Dashboard table;

    /**
     * The current shelf of the player who is playing.
     */
    private final PersonalShelf shelf;

    /**
     * The value that marks if the match is started.
     */
    private final boolean gameState;

    /**
     * The personal goal of the player who is playing.
     */
    private final PersonalGoal pgoal;

    /**
     * The first common goal of the match.
     */
    private final CommonGoalAbs commonGoal1;

    /**
     * The secondo common goal of the match.
     */
    private final CommonGoalAbs commonGoal2;

    /**
     * The current stage of the game (and the turn).
     */
    private final GameState state;

    /**
     * The final ranking of the match.
     */
    private final Player[] ranking;

    /**
     * The last error occurred in the game, if it occurred.
     */
    private final GameError gameError;

    /**
     * Constructor for GameView class used in the pregame phase.
     *
     * @param game The current game situation.
     */
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

    /**
     * Constructor for GameView class used in the game phase.
     *
     * @param index The index of the current player.
     * @param game The current game situation.
     */
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

    /**
     * Constructor for GameView class used to report an error.
     *
     * @param game The current game situation.
     * @param ind A fictive parameter to distinguish this constructor from the others.
     */
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

    /**
     * Constructor for GameView class used in the endgame phase.
     *
     * @param ranks The final ranking of the match.
     * @param game The current game situation.
     */
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

    /**
     * Retrieves the first common goal of the match.
     *
     * @return The first common goal of the match.
     */
    public CommonGoalAbs getCommonGoal1() {
        return commonGoal1;
    }

    /**
     * Retrieves the second common goal of the match.
     *
     * @return The second common goal of the match.
     */
    public CommonGoalAbs getCommonGoal2() {
        return commonGoal2;
    }

    /**
     * Retrieves the current dashboard of the match.
     *
     * @return The current dashboard of the match.
     */
    public Dashboard getTable() {
        return table;
    }

    /**
     * Retrieves the current shelf of the player who is playing.
     *
     * @return The current shelf of the player who is playing.
     */
    public PersonalShelf getShelf() {
        return shelf;
    }

    /**
     * Retrieves the personal goal of the player who is playing.
     *
     * @return The personal goal of the player who is playing.
     */
    public PersonalGoal getPgoal() {
        return pgoal;
    }

    //public GameState getState(){return state;}

    /**
     * Retrieves the last error occurred in the game, if it occurred.
     *
     * @return the last error occurred in the game, if it occurred.
     */
    public GameError getGameError() {
        return gameError;
    }

    /**
     * Retrieves the current stage of the game (and the turn).
     *
     * @return The current stage of the game.
     */
    public boolean getGameState(){return gameState;}

    /**
     * Retrieves the final ranking of the match.
     *
     * @return The final ranking of the match.
     */
    public Player[] getRanking() {
        return ranking;
    }

    //Constructor used for test
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
