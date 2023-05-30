package MODEL;

import javax.swing.*;
import java.io.Serializable;

public abstract class CommonGoalAbs implements Serializable {

    protected int playing = 0;
    private int maxPoint = 8;
    protected Boolean[] playerAchived;

    private ImageIcon image;
    CommonGoalAbs goal;

    public abstract void control(Player player);

    public abstract void show();

    public CommonGoalAbs(int totalPlayer) {

        playerAchived = new Boolean[totalPlayer];
        for (int i = 0; i < totalPlayer; i++) {
            playerAchived[i] = false;
        }
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public CommonGoalAbs getGoal() {
        return goal;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void setPlaying(int playing) {
        this.playing = playing;
    }

    public int getPlaying() {
        return playing;
    }

    private void maxDecrease() {
        setMaxPoint(maxPoint - (8 / playerAchived.length));
    }


    /*Method for adding points to the player, returned as void that directly operates on the scores
     * to handle the fact that once a player has scored, it is saved/*/

    public boolean commonGoalAchived() {
        return playerAchived[playing];
    }

    private void goalAchived() {
        this.playerAchived[playing] = true;
    }

    protected void givePoints(Player player) {
        player.sumPoints(maxPoint);
        goalAchived();
        this.maxDecrease();
    }


    //Function to be called in a NextTurn to keep the player value of the commonGoal in step
    public void incrementCG() {
        if (getPlaying() == playerAchived.length - 1) {
            setPlaying(0);
        } else {
            setPlaying(getPlaying() + 1);
        }
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
