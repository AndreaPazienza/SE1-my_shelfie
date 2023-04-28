package VIEW;

import MODEL.Color;

public class ColorPrint {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String D_GREEN = "\033[0;32m";   // GREEN
    public static final String D_PINK = "\033[0;35m";  // PINK in realtà è viola
    public static final String D_BLUE = "\033[0;34m";    // BLUE
    public static final String D_LBLUE = "\033[0;36m";    // LBLUE
    public static final String D_WHITE = "";   // WHITE
    public static final String D_YELLOW = "\033[0;33m";  // YELLOW

    //Metodo per restituire la codifica del colore per ogni colore
    public static String convertColor(Color c) {
        switch (c) {
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

