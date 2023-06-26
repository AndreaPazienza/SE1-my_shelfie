package VIEW;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView{

    private final int x;
    private final int y;
    private int order;

    public Tile (String imageUrl, int x, int y) {

        super(new Image(imageUrl));
        this.x = x;
        this.y = y;
        order = 0;
    }

    public int getTileX() {
        return x;
    }

    public int getTileY() {
        return y;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
