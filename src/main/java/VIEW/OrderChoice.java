package VIEW;

import java.io.Serializable;

/**
 * Class that represents the order of insertion of the tiles selected compared to the order of selection in a triple selection,in a double selection the attributes are all initialized as 1.
 */
public class OrderChoice implements Serializable{

    /**
     * The serial version of the class.
     */
    static final long serialVersionUID = 1L;

    /**
     * The position of the first tile selected.
     */
    private final int p;

    /**
     * The position of the second tile selected.
     */
    private final int s;

    /**
     * The position of the third tile selected.
     */
    private final int t;

    /**
     * Constructor of OrderChoice class.
     *
     * @param p The position of the first tile selected.
     * @param s The position of the second tile selected.
     * @param t The position of the third tile selected.
     */
    public OrderChoice (int p, int s, int t) {
        this.p = p;
        this.s = s;
        this.t = t;
    }

    /**
     * Retrieves the position of the first tile selected.
     *
     * @return The position of the first tile selected.
     */
    public int getP() {
        return p;
    }

    /**
     * Retrieves the position of the second tile selected.
     *
     * @return The position of the second tile selected.
     */
    public int getS() {
        return s;
    }

    /**
     * Retrieves the position of the third tile selected.
     *
     * @return The position of the third tile selected.
     */
    public int getT() {
        return t;
    }
}
