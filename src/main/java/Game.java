import COMMONGOAL.CommonGoal;

public class Game {

    protected static int Nplayers;
    private boolean gameOn;
    private Player[] player;
    private Dashboard table;
    private Bag bag;
    private CommonGoal commonGoal;

    //Costruttore della partita che a sua volta costruisce la Dashboard passando il numero di giocatori che si inseriranno (in seguito)
    public Game (int n) {

        Nplayers = 1;
        gameOn = true;
        player = new Player[4];
        table = new Dashboard(n);
        bag = new Bag();
    }

    //Inserimento del giocatore nell'array dei player e incremento di Nplayers
    public void signPlayer(Player player) {
        this.player[this.Nplayers] = player;
        this.Nplayers ++;
    }

    //Selezione, ordinamento ed inserimento delle Slot
    public void PlayerOnStage() {
        //Da definire
    }

    //Set di grigio sulle tessere prese e controlla se ci sono altre mosse, passaggio di turno al giocatore successivo
    public void updateTurn() {
        //Da definire
    }

    //Viene decretato il vincitore (cerca massimo)
    public void FinalScore() {
        //Da definire
    }

}