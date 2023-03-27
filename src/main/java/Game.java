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
        this.player[Nplayers] = player;
        Nplayers ++;
    }

    //Selezione, ordinamento ed inserimento delle Slot
    public void playerOnStage() {
        //Da definire
    }

    //Set di grigio sulle tessere prese e controlla se ci sono altre mosse, passaggio di turno al giocatore successivo
    public void updateTurn() {
        //Da definire
    }

    //Viene decretato il vincitore (cerca massimo)
    public void finalScore() {

        String winnerNickname;
        int winnerScore = 0;

        //Non è preso in considerazione il parimerito (da rivedere)
        for (Player p : this.player) {
            if (p.getScore() > winnerScore) {
                winnerScore = p.getScore();
                winnerNickname = p.getNickname();
            }
        }
    }

}