package MODEL;

import java.io.Serializable;
import javafx.scene.image.Image;

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
    private Image image;

    //CommonGoalAbs goal;

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
    public abstract String description();

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

    /*public CommonGoalAbs getGoal() {
        return goal;
    }*/

    /**
     * Sets the current maximum number of points achievable associated to the common goal card.
     *
     * @param maxPoint The current maximum number of points achievable associated to the common goal card.
     */
    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    /**
     * Sets the index that marks the current player.
     *
     * @param playing The index that marks the current player.
     */
    public void setPlaying(int playing) {
        this.playing = playing;
    }

    /**
     * Retrieves the index that marks the current player.
     *
     * @return The index that marks the current player.
     */
    public int getPlaying() {
        return playing;
    }

    /**
     * Updates the  maximum number of points achievable associated to the common goal card.
     */
    private void maxDecrease() {
        //The decrease depends on the number of players in the game
        setMaxPoint(maxPoint - (8 / playerAchived.length));
    }

    /**
     * Establishes if the current player has achieved the common goal.
     *
     * @return True if the current player has achieved the common goal, False otherwise.
     */
    public boolean commonGoalAchived() {
        return playerAchived[playing];
    }

    /**
     * Marks that the current player achieved the common goal.
     */
    private void goalAchived() {
        this.playerAchived[playing] = true;
    }

    /**
     * Increases the points of the player who achieved a common goal.
     *
     * @param player The player whose points have to be increased.
     */
    protected void givePoints(Player player) {
        player.sumPoints(maxPoint);
        goalAchived();
        this.maxDecrease();
    }

    /**
     * Updates the player value of the common goal at the end of the turn.
     */
    public void incrementCG() {
        if (getPlaying() == playerAchived.length - 1) {
            setPlaying(0);
        } else {
            setPlaying(getPlaying() + 1);
        }
    }

    /**
     * Retrieves the graphic image associated to the common goal card.
     *
     * @return The graphic image associated to the common goal card.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the graphic image associated to the common goal card.
     *
     * @param image The graphic image associated to the common goal card.
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
