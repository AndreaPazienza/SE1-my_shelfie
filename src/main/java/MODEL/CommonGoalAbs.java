package MODEL;

import javax.swing.*;
import java.io.Serializable;

/**
 * Class that represents a generic common goal card that each player can achieve.
 */
public abstract class CommonGoalAbs implements Serializable {

    /**
     * The index that marks the current player.
     */
    protected int playing = 0;

    /**
     * The current maximum number of points achievable associated to the common goal card.
     */
    private int maxPoint = 8;

    /**
     * The list of values that mark if a player achieved the common goal.
     */
    protected Boolean[] playerAchived;

    /**
     * The graphic image associated to the common goal card.
     */
    private ImageIcon image;

    CommonGoalAbs goal;

    /**
     * Checks if a player's shelf comply with the requirements of the common goal card.
     * The method is overridden to allow each class that extends this to check following its specific conditions.
     *
     * @param player The player whose shelf has to be checked.
     */
    public abstract void control(Player player);

    /**
     * Shows the textual representation of the common goal card.
     * The method is overridden to allow each class that extends this to show its particular textual representation.
     */
    public abstract void show();

    /**
     * Constructor for CommonGoalAbs class.
     *
     * @param totalPlayer The number of players in the match.
     */
    public CommonGoalAbs(int totalPlayer) {

        playerAchived = new Boolean[totalPlayer];
        for (int i = 0; i < totalPlayer; i++) {
            playerAchived[i] = false;
        }
    }

    /**
     * Retrieves
     *
     * @return
     */
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
