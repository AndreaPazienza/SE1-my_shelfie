public class Target {
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
}
