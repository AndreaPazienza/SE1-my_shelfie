package MODEL;


import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Listeners.GameEventListener;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the model of the game and manages the match events flow.
 */
public class Game implements GameEventListener {

    /**
     * The list of GameEventListener that take the notifies from the game.
     */
    private final List<GameEventListener> listeners = new ArrayList<>();

    /**
     * The number of players in the game.
     */
    protected static int Nplayers;

    /**
     * The number associated to the current player who is playing.
     */
    private int playerInGame;

    /**
     * The number associated to the current player who is playing.
     */
    private boolean firstPlayerFinished = false;

    /**
     * The value that marks if the match is started.
     */
    private boolean gameOn;

    /**
     * The set of player in the game ordered by their position in the turn.
     */
    private final Player[] player;

    /**
     * The dashboard of the match.
     */
    private final Dashboard table;

    /**
     * The bag of the match.
     */
    private final Bag bag;

    /**
     * The deck that contains all the personal goal cards.
     */
    private final PersonalGoalDeck deck;

    /**
     * The deck that contains all the common goal cards.
     */
    private final CommonGoalDeck commonGoalDeck;

    /**
     * The first common goals to achieve in the match.
     */
    private CommonGoalAbs commonGoal1;

    /**
     * The second common goals to achieve in the match.
     */
    private CommonGoalAbs commonGoal2;

    /**
     * The current stage of the game.
     */
    private GameState state;

    /**
     * The last error occurred in the game, if it occurred.
     */
    private GameError lastError;

    /**
     * Retrieves the last error occurred in the game, if it occurred.
     *
     * @return The last error occurred in the game, if it occurred.
     */
    public GameError getLastError (){
        return lastError;
    }

    /**
     * Retrieves the last error occurred in the game, if it occurred.
     *
     * @param lastError The last error occurred in the game, if it occurred.
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    public void setLastError(GameError lastError) throws RemoteException {
        this.lastError = lastError;
        notifyLastError();
    }

    /**
     * Constructor for game class.
     *
     * @param numberOfPlayers The number of the players that are joining the match.
     * @throws IOException If an error occurred during the IO operation.
     * @throws ParseException If an error occurred during the reading af a json file.
     */
    public Game (int numberOfPlayers) throws IOException, ParseException {
        Nplayers = numberOfPlayers;
        playerInGame = 0;
        gameOn = false;
        player = new Player[numberOfPlayers];
        table = new Dashboard(numberOfPlayers);
        bag = new Bag();
        deck = new PersonalGoalDeck();
        commonGoalDeck = new CommonGoalDeck(numberOfPlayers);
        state = GameState.LOGIN;
    }

    /**
     * Assigns to a player his personal goal randomly extracted from the deck.
     */
    public void assignPGoal(){
        for(int i = 0; i < player.length; i++){
            player[i].setPgoal(deck.extractionPGoal());
        }
    }

    /**
     * Retrieves the order in the turn of the player with the input nickname or a default value of -1 if there isn't a player whose nickname corresponds to the input.
     *
     * @param name The nickname o the player.
     * @return The order in turn of the player.
     */
    public int positionInGame(String name){
        for(int i=0; i<Nplayers; i++){
            if(player[i].getNickname().equals(name)){
                return player[i].getOrderInTurn()-1;
            }
        }
        return -1;
    }

    /**
     * Inserts the player in the next available spot of the set of players and increment the number of players.
     *
     * @param nick The nickname of the player to enroll.
     */
    public void signPlayer(String nick) {
        Player player = new Player(nick);
        this.player[playerInGame] = player;
        this.player[playerInGame].setOrderInTurn(playerInGame+1);

        if (playerInGame == Nplayers-1) {
            setGameOn(true);
            playerInGame = 0;
        }else{
            playerInGame ++;
        }

    }

    /**
     * Retrieves the player that is currently playing.
     *
     * @return The player in turn.
     */
    public Player playerOnStage() {
        return player[playerInGame];
    }



    /**
     * Fills the dashboard for the first time, assigns the personal goal to each player and extracts the two common goals.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    public void startGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        state = GameState.PLAYING;
        getTable().refill(getBag());
        getTable().catchAfterRefill();
        assignPGoal();
        commonGoal1 = commonGoalDeck.getACommonGoal();
        commonGoal2 = commonGoalDeck.getACommonGoal();
        this.gameStateChanged();
        this.readyToStart();
    }


    /**
     * Manages the end of the turn checking if the current player completed a common goal, refilling the dashboard if it's needed and passing to the next player.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    public void updateTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
            //Checking the if the current player completed a common goal
            this.commonGoal1.control(player[playerInGame]);
            this.commonGoal1.incrementCG();
            this.commonGoal2.control(player[playerInGame]);
            this.commonGoal2.incrementCG();
            //Checks if it's not the last turn
            if (this.player[playerInGame].getShelf().checkLastLine() && !firstPlayerFinished) {
                firstPlayerFinished = true;
                notifyEndGame();
            }
            if (firstPlayerFinished && player[playerInGame].getOrderInTurn() == Nplayers) {
                notifyGameFinished();
            }
            //Calling the refill if it's needed
            if (table.checkRefill()) {
                table.refill(bag);
            }
            table.catchAfterRefill();
            //Switching of the turn
            playerInGame++;
            if (playerInGame == Nplayers) {
                playerInGame = 0;
            }
            //If the game isn't finished notifies the end of the turn
            if(gameOn) {
                this.turnIsOver();
            }

    }


    /**
     * Retrieves the winner and rearranges the set of player according to the scores and the order in turn of each player.
     *
     * @return The winner of the match.
     */
    public Player finalScore() {

        Player provvisoryPlayer = null;

        for (int i = 0; i < player.length - 1; i ++) {
            for (int j = i + 1; j < player.length; j ++) {
                if ((player[j].getScore() > player[i].getScore()) || (player[j].getScore() == player[i].getScore() && player[j].getOrderInTurn() > player[i].getOrderInTurn())) {
                    provvisoryPlayer = player[i];
                    player[i] = player[j];
                    player[j] = provvisoryPlayer;
                }
            }
        }

        return player[0];
    }

    /**
     * Retrieves the number of players in the game.
     *
     * @return The number of players in the game.
     */
    public int getNplayers(){ return Nplayers;}

    /**
     * Retrieves the dashboard of the match.
     *
     * @return The dashboard of the match.
     */
    public Dashboard getTable() {
        return table;
    }
   // public boolean isStarted(){ return state == GameState.NOTSTARTED; }

    /**
     * Retrieves the current stage of the game.
     *
     * @return The current stage of the game.
     */
    public GameState getCurrentState(){return state;}

    /**
     * Retrieves the set of player in the game ordered by their position in the turn.
     *
     * @return The set of player in the game ordered by their position in the turn.
     */
    public Player[] getPlayer() {return player;}

    /**
     * Retrieves the first common goals to achieve in the match.
     *
     * @return The first common goals to achieve in the match.
     */

    public CommonGoalAbs getCommonGoal1() {
        return commonGoal1;
    }

    /**
     * Sets the first common goal to achieve in the match.
     *
     * @param commonGoal1 The first common goal to achieve in the match.
     */
    public void setCommonGoal1(CommonGoalAbs commonGoal1) {
        this.commonGoal1 = commonGoal1;
    }

    /**
     * Sets the second common goal to achieve in the match.
     *
     * @param commonGoal2 The second common goal to achieve in the match.
     */
    public void setCommonGoal2(CommonGoalAbs commonGoal2) {
        this.commonGoal2 = commonGoal2;
    }

    /**
     * Retrieves the deck that contains all the common goal cards.
     *
     * @return The deck that contains all the common goal cards.
     */
    public CommonGoalDeck getCommonGoalDeck() {
        return commonGoalDeck;
    }

    /**
     * Retrieves The second common goal to achieve in the match.
     *
     * @return The second common goal to achieve in the match.
     */
    public CommonGoalAbs getCommonGoal2() {
        return commonGoal2;
    }

    /**
     * Retrieves the value that marks if the match is started.
     *
     * @return The value that marks if the match is started.
     */
    public boolean isGameOn() {
        return gameOn;
    }

    /**
     * Sets the value that marks if the match is started.
     *
     * @param gameOn The value that marks if the match is started.
     */
    public void setGameOn(boolean gameOn){this.gameOn=gameOn;}

    /**
     * Retrieves the number associated to the current player who is playin
     *
     * @return The number associated to the current player who is playin
     */
    public int getPlayerInGame() {
        return playerInGame;
    }


    /**
     * Retrieves the deck that contains all the personal goal cards.
     *
     * @return The deck that contains all the personal goal cards.
     */
    public PersonalGoalDeck getDeck() {
        return deck;
    }

    /**
     * Sets the index of the current player to the next one.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    public void nextPlayerInGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        if (playerInGame == Nplayers-1) {
            playerInGame = 0;
        }else{
            playerInGame ++;
        }
        notifySkipTurn();
    }

    /**
     * Retrieves the bag of the match.
     *
     * @return The bag of the match.
     */
    public Bag getBag() {
        return this.bag;
    }


    /**
     * Adds a listener to the list of GameEventListener.
     *
     * @param listener The listener to add.
     */
    @Override
    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies the listeners the changing of the state of the game.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    @Override
    public void gameStateChanged() throws RemoteException {
    for(GameEventListener listener: listeners){
        listener.gameStateChanged();
        }
    }

    /**
     * Notifies the listeners the start of the first player's turn.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    public void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for(GameEventListener listener: listeners){
            listener.readyToStart();
        }
    }

    /**
     * Notifies the listeners the beginning of the last turn of the match.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    @Override
    public void notifyEndGame() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.notifyEndGame();
        }
    }

    /**
     * Notifies the listeners the end of the match.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    @Override
    public void notifyGameFinished() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.notifyGameFinished();
        }
    }

    /**
     * Notifies the listeners the skip of the turn because in turn the player is disconnected.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    @Override
    public void notifySkipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for(GameEventListener listener: listeners){
            listener.notifySkipTurn();
        }
    }

    /**
     * Notifies the transition to the next player during the game.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    @Override
    public void turnIsOver () throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for (GameEventListener listener : listeners) {
            listener.turnIsOver();
        }
    }

    /**
     * Notifies the listeners the last error occurred to the match.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    @Override
    public void notifyLastError() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.notifyLastError();
        }
    }

    /**
     * Resets the selected slots placing them on the dashboard again.
     *
     * @param selectedSlots The slots selected by the player.
     * @param coordinatesSaver The variable that kept on track the selected slots.
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    public void undoTurn(Slot[] selectedSlots, Target[] coordinatesSaver) throws NotEnoughSpaceChoiceException, RemoteException {
        for(int i = 0; i < coordinatesSaver.length;i++){
            getTable().setSingleSlot(selectedSlots[i], coordinatesSaver[i].getPosX(), coordinatesSaver[i].getPosY());
            getTable().getSingleSlot(coordinatesSaver[i].getPosX(), coordinatesSaver[i].getPosY()).setCatchable(true);
            System.err.println("Ho fatto una undo: "+coordinatesSaver[i].getTile()+"in posizione: "+coordinatesSaver[i].getPosX()+coordinatesSaver[i].getPosY());
        }
        notifyForcedTurnEnding();
    }


    /**
     * Notifies the listeners the end of the current player's turn due to the crash of the player
     *
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    @Override
    public void notifyForcedTurnEnding() throws NotEnoughSpaceChoiceException, RemoteException {
        for(GameEventListener listener: listeners){
            listener.notifyForcedTurnEnding();
        }
    }
}