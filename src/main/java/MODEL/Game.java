package MODEL;


import Listeners.GameEventListener;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public class Game implements GameEventListener {
    private final List<GameEventListener> listeners = new ArrayList<>();
    protected static int Nplayers;
    private int playerInGame;
    private int firstPlayerFinished = -1;
    private boolean gameOn;
    private Player[] player;
    private Dashboard table;
    private Bag bag;
    private PersonalGoalDeck deck;
    private CommonGoal commonGoal1, commonGoal2;
    private GameState state;

    //Constructor of the game that in turn builds the Dashboard by passing the number of players that will be added later.
    public Game (int numberOfPlayers) {

        Nplayers = numberOfPlayers;
        playerInGame = 0;                                   //Player in turn
        gameOn = false;
        player = new Player[numberOfPlayers];
        table = new Dashboard(numberOfPlayers);
        bag = new Bag();
        deck = new PersonalGoalDeck();
        commonGoal1 = new CommonGoal(numberOfPlayers);
        commonGoal2 = new CommonGoal(numberOfPlayers);
        state = GameState.LOGIN;

    }


    public void setFirstPlayerFinished(int firstPlayerFinished) {
        this.firstPlayerFinished = firstPlayerFinished;
    }

    public int getFirstPlayerFinished() {
        return firstPlayerFinished;
    }



    public void assignPGoal(){
        for(int i = 0; i < player.length; i++){
            player[i].setPgoal(deck.extractionPGoal());
        }
    }

    //Insertion of the player in the player's array and increment of Nplayers.
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

    //Returns the player in turn
    public Player playerOnStage() {
        return player[playerInGame];
    }

    public void startGame() throws RemoteException {
        state = GameState.PLAYING_IN_ORDERING;
        getTable().refill(getBag());
        getTable().catchAfterRefill();
        assignPGoal();
        this.gameStateChanged();
    }


    //Call to refill if necessary and setting catchable, passing the turn to the next player.
    public void updateTurn() throws RemoteException {
        //Check finished common goal and increment
        this.commonGoal1.getGoal().control(player[playerInGame]);
        this.commonGoal1.getGoal().incrementCG();
        //Second PGoal
        this.commonGoal2.getGoal().control(player[playerInGame]);
        this.commonGoal2.getGoal().incrementCG();
        //Call to refill (if necessary)

        if (table.checkRefill()) {
            table.refill(bag);
        }
        table.catchAfterRefill();

        /* notify of the updated dashboard */
        this.turnIsOver();

        //Passaggio del turno
        playerInGame ++;
        if (playerInGame == Nplayers) {
            playerInGame = 0;
        }
    }


    //The winner is declared (searches for the maximum)
    public Player finalScore() {

        Player winner;
        String winnerNickname = null;
        int winnerScore = 0;
        int winnerOrderInTurn = 0;

        for (int i = 0; i < player.length; i ++) {
            if ((player[i].getScore() > winnerScore) || (player[i].getScore() == winnerScore && player[i].getOrderInTurn() > winnerOrderInTurn)) {
                winnerScore = player[i].getScore();
                winnerOrderInTurn = player[i].getOrderInTurn();
                winnerNickname = player[i].getNickname();
            }
        }

        //Creation of the winning player.
        winner = new Player(winnerNickname);
        winner.setScore(winnerScore);
        winner.setOrderInTurn(winnerOrderInTurn);

        return winner;

    }


    public int getNplayers(){ return Nplayers;}

    public Dashboard getTable() {
        return table;
    }
   // public boolean isStarted(){ return state == GameState.NOTSTARTED; }
    public GameState getCurrentState(){return state;}
    public Player[] getPlayer() {return player;}

    public CommonGoal getCommonGoal1() {
        return commonGoal1;
    }

    public CommonGoal getCommonGoal2() {
        return commonGoal2;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn){this.gameOn=gameOn;}

    public int getPlayerInGame() {
        return playerInGame;
    }

    public PersonalGoalDeck getDeck() {
        return deck;
    }

    public Bag getBag() {
        return this.bag;
    }


    //Adds a listener to the model.
    @Override
    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    //Notifies the first notification of the game: Setting and first view.
    @Override
    public void gameStateChanged() throws RemoteException {
    for(GameEventListener listener: listeners){
        listener.gameStateChanged();
        }
    }
    //Notifies the start of the first player's turn.
    public void readyToStart() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.readyToStart();
        }

    }

    //Notifies the transition to the next client during the game.
    @Override
    public void turnIsOver() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.turnIsOver();
        }
    }


}