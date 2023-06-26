package MODEL;

import java.io.Serializable;

/**
 * Class that represents a tile or an empty cell of the dashboard or a personal shelf.
 */
public class Slot implements Serializable {

    /**
     * The color of the slot (gray for empty, black for not playable).
     */
    private Color color;

    /**
     * The type of the image of the slot (if not gray or black).
     */
    private Type type;

    /**
     * The value that marks if a slot can be selected from the dashboard .
     */
    private boolean catchable;

    /**
     * Constructor for Slot class.
     *
     * @param color The color of the slot (gray for empty, black for not playable).
     */
    public Slot(Color color){
        this.color=color;
    }

    /**
     * Retrieves if a slot can be selected rom the dashboard.
     *
     * @return True if the slot is catchable, False otherwise.
     */
    public boolean isCatchable() {
        return catchable;
    }


    /**
     * Retrieves the type of the image
     *
     * @return The type of the image.
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of the image.
     *
     * @param type The type of the image.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Sets the type of the slot according to the conversion from an integer value.
     *
     * @param numberType The number to be converted in type.
     */
    public void fillType(int numberType){
        //The rest of the division by three allows to fill the type in a circular way if the input are consecutive numbers
        switch(numberType % 3) {
            case 0-> setType(Type.TYPE1);
            case 1-> setType(Type.TYPE2);
            case 2-> setType(Type.TYPE3);
        }
    }

    /**
     * Sets if a slot can be selected rom the dashboard.
     *
     * @param catchable True if the slot is catchable, False otherwise.
     */
    public void setCatchable(boolean catchable) {
        this.catchable = catchable;
    }

    /**
     * Sets the color of a slot to grey.
     */
    public void setGrey(){
        this.color=Color.GREY;
    }

    /*
    public void setBlack() {
        this.color = Color.BLACK;
    }
    */

    /**
     * Retrieves the color of the slot.
     *
     * @return The color of the slot.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the slot.
     *
     * @param c The color of the slot.
     */
    public void setColor(Color c) {
        this.color = c;
    }
}

