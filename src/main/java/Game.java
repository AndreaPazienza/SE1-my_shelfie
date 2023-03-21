public class Game {

    private int Nplayers;
    private boolean gameOn;
    private Player[] player;
    private Dashboard table;                            //Manca dashboard tra le classi
    private Bag bag;
    private CommonGoal commonGoal;

    //Costruttore della partita che a sua volta costruisce la Dashboard passando
    public Game () {

        Nplayers = 0;
        gameOn = true;
        player = new Player[4];
        table = new Dashboard();                        //Problema relativo al costruttore di Dashboard (numero dei giocatori)
        bag = new Bag();
        commonGoal = new CommonGoal();
    }

    //Inserimento del giocatore nell'array dei player e incremento di Nplayers
    public void signPlayer(Player player) {
        this.player[this.Nplayers] = player;
        this.Nplayers ++;
    }

    //
    public void PlayerOnStage() {
        //Da definire
    }

    //
    public void updateTurn() {
        //Da definire
    }

    //
    public void FinalScore() {
        //Da definire
    }

    //Get e Set degli attributi privati
    public int getNplayers() {
        return Nplayers;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public Player[] getPlayer() {
        return player;
    }

    public Dashboard getTable() {
        return table;
    }

    public Bag getBag() {
        return bag;
    }

    public CommonGoal getCommonGoal() {
        return commonGoal;
    }

    public void setNplayers(int nplayers) {
        Nplayers = nplayers;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public void setPlayer(Player[] player) {
        this.player = player;
    }

    public void setTable(Dashboard table) {
        this.table = table;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public void setCommonGoal(CommonGoal commonGoal) {
        this.commonGoal = commonGoal;
    }
}