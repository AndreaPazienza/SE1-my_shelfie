package VIEW.GraphicObjects;

import javax.swing.*;

public class TilesToOrder {

    JLabel tile;
    int position;

    public TilesToOrder(int index){
        this.tile = new JLabel();
        this.position = index;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public JLabel getLabel() {
        return this.tile;
    }
}
