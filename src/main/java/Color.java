import java.util.Random;

public enum Color {
    GREEN,
    PINK,
    BLUE,
    LIGHTBLUE,
    WHITE,
    YELLOW,
    GREY, //Grey caselle corrispondenti a tessere prese
    BLACK; //Caselle Black irraggiungibili della plancia iniziale

    public static Color RandomColor() { //Generatore di colore casuale, esculdendo GREY e BLACK che non sono colori effettivi
        Color[] values = Color.values();
        int length = (values.length - 2);
        int randIndex = new Random().nextInt(length);
        return values[randIndex];

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
