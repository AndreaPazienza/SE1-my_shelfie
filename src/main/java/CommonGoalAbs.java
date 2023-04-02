import java.util.ArrayList;
import java.util.Random;

public abstract class CommonGoalAbs {

    protected int playing=0;
    private int maxPoint = 8;
    protected Boolean[] playerAchived;
    CommonGoalAbs goal;

    public abstract void control(Player player);

    public CommonGoalAbs(int totalPlayer){

        playerAchived = new Boolean[totalPlayer];
       for(int i=0; i<totalPlayer; i++){
           playerAchived[i]=false;
       }
      // goal = getACommonGoal(totalPlayer);
    }

  /* public CommonGoalAbs getACommonGoal(int players){

       ArrayList<CommonGoalAbs> deck = new ArrayList<>();
        deck.add(new CGFourCorners(players));
        deck.add(new CGDecreaseTiles(players));
        deck.add(new CGEightTilesSameType(players));
        deck.add(new CGFiveTilesDiagonal(players));
        deck.add(new CGFiveTilesX(players));
        deck.add(new CGSixGroupsTwoTiles(players));
        deck.add(new CGDecreaseTiles(players));
        deck.add(new CGSameTypeSquare(players));
        //definizione colonne
        deck.add(new CGThreeColumnsSixTiles(players));
        deck.add(new CGTwoColumnsSixTiles(players));
        //definizione righe
        deck.add(new CGTwoLinesFiveTiles(players));
        deck.add(new CGFourLinesFiveTiles(players));

        return deck.get(shuffle());
    }*/
    public CommonGoalAbs getGoal(){
        return  goal;
    }

   public int shuffle(){
       int randIndex = new Random().nextInt(13);
       return  randIndex;
   }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }
    public void setPlaying(int playing){this.playing=playing;}

    public int getPlaying() {return playing;}

    private void maxDecrease() {
        setMaxPoint(maxPoint - ( 8 / playerAchived.length));
    }


    /*Metodo per sommare i punti al player, restituito come void che opera direttamente sui punteggi
     * per gestire il fatto che ogni che un primo player ha ottenuto il punteggio questo viene salvato*/

    public boolean commonGoalAchived(){
        return playerAchived[playing];}

    private void goalAchived(){
        this.playerAchived[playing]=true;
    }

    protected void givePoints(Player player) {
            player.sumPoints(maxPoint);
            goalAchived();
            this.maxDecrease();
    }


    //Funzione da chiamare in un NextTurn per portare di pari passo il valore giocatore del commonGoal
    public void incrementCG() {
            if (getPlaying() == playerAchived.length-1)
                  { setPlaying(0); }
            else
                 {   setPlaying(getPlaying()+1); }
                }
}
