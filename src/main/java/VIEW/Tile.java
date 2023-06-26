package VIEW;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView{

    private final int x;
    private final int y;

    public Tile (String imageUrl, int x, int y) {

        super(new Image(imageUrl));
        this.x = x;
        this.y = y;
    }

    public int getTileX() {
        return x;
    }

    public int getTileY() {
        return y;
    }
}
