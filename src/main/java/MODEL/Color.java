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
    public static Color randomColor() {
        //The grey and black values are excluded
        Color[] values = Color.values();
        int length = (values.length - 2);
        int randIndex = new Random().nextInt(length);
        return values[randIndex];

    }

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
