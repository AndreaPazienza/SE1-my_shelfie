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
    }

    public CommonGoalAbs getGoal(){
        return  goal;
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
