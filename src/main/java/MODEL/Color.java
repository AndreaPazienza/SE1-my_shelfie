package MODEL;

import java.util.Random;

/**
 * Enum that represents the color of a slot (it also represents the image of the real slots).
 */
public enum Color {
    GREEN,              //Color of the cat slot
    PINK,               //Color of the plant slot
    BLUE,               //Color of the frame slot
    LBLUE,              //Color of the trophy slot
    WHITE,              //Color of the book slot
    YELLOW,             //Color of the game slot
    GREY,               //Color of the empty slot
    BLACK;              //Color of the not playable slot

    /**
     * Retrieves a random color extracted from the colours that actually represents a slot (grey and black are excluded).
     *
     * @return The casual extracted color.
     */
    public static Color randomColor() { // random color generator excluding GREY and BLACK (not true colors)
        Color[] values = Color.values();
        //The gry and black values are excluded
        int length = (values.length - 2);
        int randIndex = new Random().nextInt(length);
        return values[randIndex];

    }

    /**
     * Establishes if two colors are the same.
     *
     * @param obj The color compared.
     * @return True if the colors are the same, False otherwise.
     */
    public boolean Equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return false;
        }
    }

    /*
    public boolean notEquals(Object obj) {
        if (obj != this) {
            return true;
        } else {
            return false;
        }
    }*/

    /**
     * Retrieves the color instance of a string.
     *
     * @param color The string to convert into a color.
     * @return The color that the string indicate
     * @throws IllegalArgumentException If the input string doesn't indicate a color.
     */
    public static Color colorToString(String color) throws IllegalArgumentException {
        switch (color.toLowerCase()) {
            case "green":
                return GREEN;
            case "pink":
                return PINK;
            case "blue":
                return BLUE;
            case "lblue":
                return LBLUE;
            case "white":
                return WHITE;
            case "yellow":
                return YELLOW;
            case "grey":
                return GREY;
            case "black":
                return BLACK;
            default:
                throw new IllegalArgumentException("Invalid color string: " + color);
        }
    }
}
