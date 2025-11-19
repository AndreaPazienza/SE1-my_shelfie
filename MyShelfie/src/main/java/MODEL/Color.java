package MODEL;

import java.util.Random;

/**
 * Enum that represents the color of a slot.
 */
public enum Color {

    /**
     * Color of the cat slot.
     */
    GREEN,

    /**
     * Color of the plant slot.
     */
    PINK,

    /**
     * Color of the frame slot.
     */
    BLUE,

    /**
     * Color of the trophy slot.
     */
    LBLUE,

    /**
     * Color of the book slot.
     */
    WHITE,
    /**
     * Color of the game slot.
     */
    YELLOW,

    /**
     * Color of the empty slot.
     */
    GREY,

    /**
     * Color of the not playable slot.
     */
    BLACK;

    /**
     * Retrieves a random color extracted from the colours that actually represents a slot (grey and black are excluded).
     *
     * @return The casual extracted color.
     */
    public static Color randomColor() {
        //The grey and black values are excluded
        Color[] values = Color.values();
        int length = (values.length - 2);
        int randIndex = new Random().nextInt(length);
        return values[randIndex];

    }

    /**
     * Retrieves the color instance of a string.
     *
     * @param color The string to convert into a color.
     * @return The color that the string indicates.
     * @throws IllegalArgumentException If the input string doesn't indicate a color.
     */
    public static Color colorToString(String color) throws IllegalArgumentException {
        switch (color) {
            case "GREEN":
                return GREEN;
            case "PINK":
                return PINK;
            case "BLUE":
                return BLUE;
            case "LBLUE":
                return LBLUE;
            case "WHITE":
                return WHITE;
            case "YELLOW":
                return YELLOW;
            case "GREY":
                return GREY;
            case "BLACK":
                return BLACK;
            default:
                throw new IllegalArgumentException("Non c'è un colore: " + color);
        }
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
}
