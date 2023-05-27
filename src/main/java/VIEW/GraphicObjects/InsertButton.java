package VIEW.GraphicObjects;

import javax.swing.*;

public class InsertButton extends JButton {

    private JButton cButton;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
