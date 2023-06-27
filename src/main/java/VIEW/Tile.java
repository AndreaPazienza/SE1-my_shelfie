package VIEW;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that represents the image of a tile on the dashboard or in the shelf.
 */
public class Tile extends ImageView{

    /**
     * The dashboard's row index of the tile (if the tile is not on the dashboard is initialized with a conventional -1 value).
     */
    private final int x;

    /**
     * The dashboard's column index of the tile (if the tile is not on the dashboard is initialized with a conventional -1 value).
     */
    private final int y;

    /**
     * The number that identifies the order of selection of the tile (if the tile is not selected the value is 0).
     */
    private int order;

    /**
     * Constructor for Tile class.
     *
     * @param imageUrl The url of the tile's image.
     * @param x The dashboard's row index of the tile.
     * @param y The dashboard's column index of the tile.
     */
    public Tile (String imageUrl, int x, int y) {
        //Constructor for ImageView class
        super(new Image(imageUrl));
        this.x = x;
        this.y = y;
        order = 0;
    }

    /**
     * Retrieves the dashboard's row index of the tile
     *
     * @return The dashboard's row index of the tile
     */
    public int getTileX() {
        return x;
    }

    /**
     * Retrieves the dashboard's column index of the tile.
     *
     * @return The dashboard's column index of the tile.
     */
    public int getTileY() {
        return y;
    }

    /**
     * Retrieves the number that identifies the order of selection of the tile.
     *
     * @return The number that identifies the order of selection of the tile.
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * Sets the number that identifies the order of selection of the tile
     *
     * @param order The number that identifies the order of selection of the tile
     */
    public void setOrder(int order) {
        this.order = order;
    }
}
