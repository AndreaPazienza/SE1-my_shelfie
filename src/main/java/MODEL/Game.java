package MODEL;


import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Listeners.GameEventListener;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public class Game implements GameEventListener {
    private final List<GameEventListener> listeners = new ArrayList<>();
    protected static int Nplayers;
    private int playerInGame;
    private boolean firstPlayerFinished = false;
    private boolean gameOn;
    private final Player[] player;
    private final Dashboard table;
    private final Bag bag;
    private final PersonalGoalDeck deck;
    private final CommonGoalDeck commonGoalDeck;
    private CommonGoalAbs commonGoal1, commonGoal2;
    private GameState state;

    private GameError lastError;


    public GameError getLastError (){
        return lastError;
    }

    public void setLastError(GameError lastError) throws RemoteException {
        this.lastError = lastError;
        notifyLastError();
    }

    //Constructor of the game that in turn builds the Dashboard by passing the number of players that will be added later.
    public Game (int numberOfPlayers) {
        Nplayers = numberOfPlayers;
        playerInGame = 0;                                   //Giocatore attualmente di turno
        gameOn = false;
        player = new Player[numberOfPlayers];
        table = new Dashboard(numberOfPlayers);
        bag = new Bag();
        deck = new PersonalGoalDeck();
        commonGoalDeck = new CommonGoalDeck(numberOfPlayers);
        state = GameState.LOGIN;
    }

    public void assignPGoal(){
        for(int i = 0; i < player.length; i++){
            player[i].setPgoal(deck.extractionPGoal());
        }
    }
    //Metodo che data una string da la posizione del player giocante.
    public int positionInGame(String name){
        for(int i=0; i<Nplayers; i++){
            if(player[i].getNickname().equals(name)){
                return player[i].getOrderInTurn()-1;
            }
        }
        return -1;
    }

    public int getPlayerPosition(String name){
        for (Player playing : player) {
            if (playing.getNickname().equals(name))
                return playing.getOrderInTurn()-1;
        }
        return -1;
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

    public Player previousOnStage(){
        if(playerInGame == 0){
            return player[Nplayers-1];
        } else {
            return player[playerInGame - 1];
        }
    }

    public void startGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        state = GameState.PLAYING_IN_ORDERING;
        getTable().refill(getBag());
        getTable().catchAfterRefill();
        assignPGoal();
        commonGoal1 = commonGoalDeck.getACommonGoal();
        commonGoal2 = commonGoalDeck.getACommonGoal();
        this.gameStateChanged();
        this.readyToStart();
    }


    //Call to refill if necessary and setting catchable, passing the turn to the next player.
    public void updateTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        //Controllo dei CommonGoal completati ed incremento
            this.commonGoal1.control(player[playerInGame]);
            this.commonGoal1.incrementCG();
        //Secondo PGoal
            this.commonGoal2.control(player[playerInGame]);
            this.commonGoal2.incrementCG();
        //Controllati i PersonalGoal viene controllato che non si tratti di fine gioco
            if (this.player[playerInGame].getShelf().checkLastLine() && !firstPlayerFinished) {
                firstPlayerFinished = true;
                notifyEndGame();
            }
            if (firstPlayerFinished && player[playerInGame].getOrderInTurn() == Nplayers) {
                notifyGameFinished();
            }

        //Chiamata a refill (se necessario)
            if (table.checkRefill()) {
                table.refill(bag);
            }
            table.catchAfterRefill();
        //Passaggio del turno
            playerInGame++;
            if (playerInGame == Nplayers) {
                playerInGame = 0;
            }
        //Se il gioco non è stato posto in stato di fine continuerà ad aggiornare i turni
            if(gameOn) {
                this.turnIsOver();
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

    public CommonGoalAbs getCommonGoal1() {
        return commonGoal1;
    }

    public void setCommonGoal1(CommonGoalAbs commonGoal1) {
        this.commonGoal1 = commonGoal1;
    }

    public void setCommonGoal2(CommonGoalAbs commonGoal2) {
        this.commonGoal2 = commonGoal2;
    }

    public CommonGoalDeck getCommonGoalDeck() {
        return commonGoalDeck;
    }

    public CommonGoalAbs getCommonGoal2() {
        return commonGoal2;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn){this.gameOn=gameOn;}

    public int getPlayerInGame() {
        return playerInGame;
    }

    public boolean isFirstPlayerFinished() {
        return firstPlayerFinished;
    }

    public PersonalGoalDeck getDeck() {
        return deck;
    }
    public void nextPlayerInGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        if (playerInGame == Nplayers-1) {
            playerInGame = 0;
        }else{
            playerInGame ++;
        }
        notifySkipTurn();
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
    public void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for(GameEventListener listener: listeners){
            listener.readyToStart();
        }
    }

    @Override
    public void notifyEndGame() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.notifyEndGame();
        }
    }

    @Override
    public void notifyGameFinished() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.notifyGameFinished();
        }
    }

    @Override
    public void notifySkipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for(GameEventListener listener: listeners){
            listener.notifySkipTurn();
        }
    }

    //Notifies the transition to the next client during the game.
        @Override
        public void turnIsOver () throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
            for (GameEventListener listener : listeners) {
                listener.turnIsOver();
            }
        }

    @Override
    public void notifyLastError() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.notifyLastError();
        }
    }


    public void forcedGameOver() {
        this.setGameOn(false);
    }
}