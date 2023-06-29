package VIEW;

import java.io.Serializable;

/**
 * Class that represents the tiles selected from the dashboard by the player in order of selection.
 */
public class SlotChoice implements Serializable {

    /**
     * The serial version of the class.
     */
    static final long serialVersionUID = 1L;

    /**
     * The dashboard's row index of the tile.
     */
    private final int x;

    /**
     * The dashboard's column index of the tile.
     */
    private final int y;

    /**
     * Constructor for SlotChoice class.
     *
     * @param x The dashboard's row index of the tile.
     * @param y The dashboard's column index of the tile.
     */
    public SlotChoice(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the dashboard's row index of the tile.
     *
     * @return The dashboard's row index of the tile.
     */
    public int getX() {
        return x;
    }

    /***
     * Retrieves the dashboard's column index of the tile.
     *
     * @return The dashboard's column index of the tile.
     */
    public int getY() {
        return y;
    }
}