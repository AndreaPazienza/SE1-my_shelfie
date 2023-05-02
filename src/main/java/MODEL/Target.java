package MODEL;

import java.io.Serializable;

public class Target implements Serializable {
    private Color tile;
    private int PosX;
    private int PosY;

    public int getPosX() {
        return PosX;
    }

    public int getPosY() {
        return PosY;
    }

    public Color getTile() {
        return tile;
    }

    public void setPosX(int x) {
        this.PosX = x;
    }

    public void setPosY(int y) {
        this.PosY = y;
    }

    public void setTile(Color color) {
        this.tile = color;
    }

    public Target (Color c, int x, int y){
        this.PosX = x;
        this.PosY = y;
        this.tile = c;
    }
}
