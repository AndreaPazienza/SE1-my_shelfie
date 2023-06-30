package VIEW;

import MODEL.Color;

/**
 * Class that represents the image on a tile.
 */
public class Image {

    /**
     * Retrieves the image on the tile with the input color.
     *
     * @param color The input color.
     * @return The image on the input color tile.
     */
    public static String colorToImage(Color color) {
        switch (color) {
            case GREEN -> {
                return "  CAT  ";
            }
            case PINK -> {
                return " PLANT ";
            }
            case BLUE -> {
                return " FRAME ";
            }
            case LBLUE -> {
                return "TROPHY ";
            }
            case WHITE -> {
                return " BOOK  ";
            }
            case YELLOW -> {
                return " GAME  ";
            }
        }
        return null;
    }
}
