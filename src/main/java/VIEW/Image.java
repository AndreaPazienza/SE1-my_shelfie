package VIEW;

import MODEL.Color;

public enum Image {

    CAT,
    PLANT,
    FRAME,
    TROPHY,
    BOOK,
    GAME;

    //Conversion from the color to the image on the card
    public static Image ColorToImage(Color c) {
        switch (c) {
            case GREEN -> {
                return CAT;
            }
            case PINK -> {
                return PLANT;
            }
            case BLUE -> {
                return FRAME;
            }
            case LBLUE -> {
                return TROPHY;
            }
            case WHITE -> {
                return BOOK;
            }
            case YELLOW -> {
                return GAME;
            }
        }
        return null;
    }
}
