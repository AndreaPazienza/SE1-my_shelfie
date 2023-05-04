package MODEL;


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
    private Player[] player;
    private Dashboard table;
    private Bag bag;
    private PersonalGoalDeck deck;
    private CommonGoalDeck commonGoalDeck;
    private CommonGoalAbs commonGoal1, commonGoal2;
    private GameState state;

    //Costruttore della partita che a sua volta costruisce la Dashboard passando il numero di giocatori che si inseriranno (in seguito)
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

    //Inserimento del giocatore nell'array dei player e incremento di Nplayers
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

    //Restituisce il giocatore di turno
    public Player playerOnStage() {
        return player[playerInGame];
    }

    public void startGame() throws RemoteException {
        state = GameState.PLAYING_IN_ORDERING;
        getTable().refill(getBag());
        getTable().catchAfterRefill();
        assignPGoal();
        commonGoal1 = commonGoalDeck.getACommonGoal();
        commonGoal2 = commonGoalDeck.getACommonGoal();
        this.gameStateChanged();
        this.readyToStart();
    }

    //Chiamata a refill se necessario e setting di catchable, passaggio del turno al giocatore successivo
    public void updateTurn() throws RemoteException {
        //Controllo dei CommonGoal completati ed incremento
        this.commonGoalDeck1.getGoal().control(player[playerInGame]);
        this.commonGoalDeck1.getGoal().incrementCG();
        //Secondo PGoal
        this.commonGoalDeck2.getGoal().control(player[playerInGame]);
        this.commonGoalDeck2.getGoal().incrementCG();

        if(this.player[playerInGame].getShelf().checkLastLine() && !firstPlayerFinished){
            firstPlayerFinished=true;
            notifyEndGame();
        }
        if(firstPlayerFinished && player[playerInGame].getOrderInTurn()==Nplayers){
            notifyGameFinished();
        }

         //Chiamata a refill (se necessario)
        if (table.checkRefill()) {
            table.refill(bag);
        }
        table.catchAfterRefill();
        //Notify
        this.turnIsOver();

        //Passaggio del turno
        playerInGame ++;
        if (playerInGame == Nplayers) {
            playerInGame = 0;
        }
    }

    //Viene decretato il vincitore (cerca massimo)
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

        //Creazione del player vincitore
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

    public CommonGoalDeck getCommonGoal1() {
        return commonGoalDeck1;
    }

    public CommonGoalDeck getCommonGoal2() {
        return commonGoalDeck2;
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

    public Bag getBag() {
        return this.bag;
    }


    //Agginge un Listener al modello.
    @Override
    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    //Notifica la prima notifica del gioco: Setting e prima view.
    @Override
    public void gameStateChanged() throws RemoteException {
    for(GameEventListener listener: listeners){
        listener.gameStateChanged();
        }
    }
    //Notifica l'inizio del turno del primo giocatore.
    public void readyToStart() throws RemoteException {
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

    //Notifica il passaggio al prossimo client durante la partita.
    @Override
    public void turnIsOver() throws RemoteException {
        for(GameEventListener listener: listeners){
            listener.turnIsOver();
        }
    }



}