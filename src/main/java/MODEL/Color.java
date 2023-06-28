package MODEL;

import java.util.Random;

public enum Color {
    GREEN,
    PINK,
    BLUE,
    LBLUE,
    WHITE,
    YELLOW,
    GREY, //Grey == taken cards
    BLACK; // not playable positions in dashboard
    public static Color randomColor() { // random color generator excluding GREY and BLACK (not true colors)
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

    public boolean Equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return false;
        }
    }

    public boolean notEquals(Object obj) {
        if (obj != this) {
            return true;
        } else {
            return false;
        }
    }
}
