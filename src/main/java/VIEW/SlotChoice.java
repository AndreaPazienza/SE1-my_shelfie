package VIEW;
import java.io.Serializable;

public class SlotChoice implements Serializable{

    static final long serialVersionUID = 1L;
    private final int x;
    private final int y;

    public SlotChoice (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
