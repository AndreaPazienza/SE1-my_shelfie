package Errors;

// Nell'ordinamento vengono selezionate delle posizioni uguali

public class SameNumberException extends Exception {
    public SameNumberException(String message) {
        super(message);
    }
}
