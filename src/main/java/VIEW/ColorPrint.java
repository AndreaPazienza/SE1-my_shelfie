package VIEW;

import MODEL.Color;

/**
 * Class that represents the code strings to print colorized details.
 */
public class ColorPrint {

    /**
     * The string to reset the text to his default color.
     */
    public static final String RESET = "\033[0m";

    /**
     * The string to set the text color to green.
     */
    public static final String D_GREEN = "\033[0;32m";

    /**
     * The string to set the text color to pink.
     */
    public static final String D_PINK = "\033[0;35m";

    /**
     * The string to set the text color to blue.
     */
    public static final String D_BLUE = "\033[0;34m";

    /**
     * The string to set the text color to light blue.
     */
    public static final String D_LBLUE = "\033[0;36m";

    /**
     * The string to set the text color to white.
     */
    public static final String D_WHITE = "";

    /**
     * The string to set the text color to yellow.
     */
    public static final String D_YELLOW = "\033[0;33m";

    /**
     * Retrieves the color code string associated to the input color.
     *
     * @param color The color to print the text of.
     * @return The code string associated to the input.
     */
    public static String convertColor(Color color) {
        switch (color) {
            case GREEN -> {
                return D_GREEN;
            }
            case PINK -> {
                return D_PINK;
            }
            case BLUE -> {
                return D_BLUE;
            }
            case LBLUE -> {
                return D_LBLUE;
            }
            case WHITE -> {
                return D_WHITE;
            }
            case YELLOW -> {
                return D_YELLOW;
            }
        }
        return " ";
    }
}
