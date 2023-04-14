package MODEL;

import CONTROLLER.GameController;

import java.util.Observable;

public class Game extends Observable {

    protected static int Nplayers;
    private int playerInGame;
    private boolean gameOn;
    private Player[] player;
    private Dashboard table;
    private Bag bag;
    private PersonalGoalDeck deck;
    private CommonGoal commonGoal1, commonGoal2;

    //Costruttore della partita che a sua volta costruisce la Dashboard passando il numero di giocatori che si inseriranno (in seguito)
    public Game (int numberOfPlayers) {

        Nplayers = numberOfPlayers;
        playerInGame = 0;                                   //Giocatore attualmente di turno
        gameOn = false;
        player = new Player[numberOfPlayers];
        table = new Dashboard(numberOfPlayers);
        bag = new Bag();
        deck = new PersonalGoalDeck();
        commonGoal1 = new CommonGoal(numberOfPlayers);
        commonGoal2 = new CommonGoal(numberOfPlayers);

        //Primo popolamento della plancia
        /* table.refill(bag);
        table.catchAfterRefill();*/
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
        playerInGame ++;
        if (playerInGame == Nplayers) {
            playerInGame = 0;
        }
    }

    /*
    //Selezione, ordinamento ed inserimento delle Slot (e setting di grigio per gli Slot presi dalla Dashboard)
    public void playerMoves(Moves move) {

        Slot[] slots = new Slot[3];

        switch (move) {
            case SELECT ->
            case ORDER ->
            case INSERT ->
        }
    }*/

    //Restituisce il giocatore di turno
    public Player playerOnStage() {

        return player[playerInGame];
    }

    //Chiamata a refill se necessario e setting di catchable, passaggio del turno al giocatore successivo
    public void updateTurn() {

        //Controllo dei CommonGoal completati ed incremento
        this.commonGoal1.getGoal().control(player[playerInGame]);
        this.commonGoal1.getGoal().incrementCG();

        this.commonGoal2.getGoal().control(player[playerInGame]);
        this.commonGoal2.getGoal().incrementCG();

        notifyObservers(commonGoal1.getGoal().getMaxPoint());
        notifyObservers(commonGoal2.getGoal().getMaxPoint());


        //Chiamata a refill (se necessario)
        if (table.checkRefill()) {
            table.refill(bag);
        }
        table.catchAfterRefill();

        /* notify della dashboard aggiornata */
        notifyObservers(table);


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

    public Player[] getPlayer() {
        return player;
    }

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
}