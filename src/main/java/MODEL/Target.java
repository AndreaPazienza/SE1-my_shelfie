package MODEL;

import java.io.Serializable;

/**
 * Class that represents one of the six slots to fill to pursue a personal goal.
 */
public class Target implements Serializable {

    /**
     * The color to fill with.
     */
    private final Color tile;

    /**
     * The index of shelf's slot's row to fill.
     */
    private final int posX;

    /**
     * The index of shelf's slot's column to fill.
     */
    private final int posY;

    /**
     * Retrieves the index of shelf's slot's row to fill.
     *
     * @return The index of shelf's slot's row to fill.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Retrieves the index of shelf's slot's column to fill.
     *
     * @return The index of shelf's slot's column to fill.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Retrieves the color to fill with.
     *
     * @return The color to fill with.
     */
    public Color getTile() {
        return tile;
    }

    /**
     * Constructor for the Target class.
     *
     * @param c Color to fill with.
     * @param x Index of shelf's slot's row to fill.
     * @param y Index of shelf's slot's column to fill.
     */
    public Target (Color c, int x, int y){
        this.posX = x;
        this.posY = y;
        this.tile = c;
    }
}
