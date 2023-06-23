package MODEL;

import java.io.Serializable;

public class Target implements Serializable {
    private Color tile;
    private int posX;
    private int posY;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Color getTile() {
        return tile;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public void setTile(Color color) {
        this.tile = color;
    }

    public Target (Color c, int x, int y){
        this.posX = x;
        this.posY = y;
        this.tile = c;
    }
}
