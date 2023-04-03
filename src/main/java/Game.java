//import COMMONGOAL.CommonGoal;

public class Game {

    protected static int Nplayers;
    private int playerInGame;
    private boolean gameOn;
    private Player[] player;
    private Dashboard table;
    private Bag bag;
    private CommonGoal commonGoal1, commonGoal2;

    //Costruttore della partita che a sua volta costruisce la Dashboard passando il numero di giocatori che si inseriranno (in seguito)
    public Game (int numberOfPlayers) {

        Nplayers = numberOfPlayers;
        playerInGame = 0;                                   //Giocatore attualmente di turno
        gameOn = true;
        player = new Player[numberOfPlayers];
        table = new Dashboard(numberOfPlayers);
        bag = new Bag();
        commonGoal1 = new CommonGoal(numberOfPlayers);
        commonGoal2 = new CommonGoal(numberOfPlayers);

        //Primo popolamento della plancia
        table.refill(bag);
    }

    //Inserimento del giocatore nell'array dei player e incremento di Nplayers
    public void signPlayer(Player player) {

        this.player[playerInGame] = player;

        playerInGame ++;
        if (playerInGame == Nplayers) {
            playerInGame = 0;
        }
    }

    //Selezione, ordinamento ed inserimento delle Slot (e setting di grigio per gli Slot presi dalla Dashboard)
    public void playerMoves(int nChoices) {

        Slot[] choice = new Slot[nChoices];

        /*
        for (int i = 0; i < nChoices; i ++) {

            choice[i] = player[playerInGame].selectCard(this.table, , );

        }

        if (nChoices == 3)
            player[playerInGame].orderCards(choice, , , );
        else if (nChoices == 2 && /*chiamata al controller per sapere se switchare) player[playerInGame].orderCards(choice); */
    }

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


        //Chiamata a refill (se necessario)
        if (table.checkRefill()) {
            table.refill(bag);
        }

        //Setting di catchable per gli Slot che si sono "sbloccati"
   /*     for (int i = 0; i < Dashboard.getSide(); i ++) {
            for(int j = 0; j < Dashboard.getSide(); j ++) {

                //Catchable per tutti gli Slot (con colore diverso da grigio e nero) con meno di quattro adiacenze (quindi con almeno un lato libero)
               // if (table.catchableSetter (i,j)) {
                 //   table.getSingleSlot(i,j).setCatchable(true);
                }
            }
        }*/

        //Passaggio del turno
        playerInGame ++;
        if (playerInGame == Nplayers) {
            playerInGame = 0;
        }
    }

    //Viene decretato il vincitore (cerca massimo)
    public void finalScore() {

        String winnerNickname;
        int winnerScore = 0;
        int winnerOrderInTurn = 0;

        for (Player p : this.player) {
            if ((p.getScore() > winnerScore) || (p.getScore() == winnerScore && p.getOrderInTurn() > winnerOrderInTurn)) {
                winnerScore = p.getScore();
                winnerOrderInTurn = p.getOrderInTurn();
                winnerNickname = p.getNickname();
            }
        }
    }
}