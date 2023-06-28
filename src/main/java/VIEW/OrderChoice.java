package VIEW;
import java.io.Serializable;

public class OrderChoice implements Serializable{

    static final long serialVersionUID = 1L;
    private final int p;
    private final int s;
    private final int t;

    public OrderChoice (int p, int s, int t) {
        this.p = p;
        this.s = s;
        this.t = t;
    }
    public OrderChoice(){
        this.p=1;
        this.s=1;
        this.t=1;
    }

    public int getP() {
        return p;
    }

    public int getS() {
        return s;
    }

    public int getT() {
        return t;
    }
}
