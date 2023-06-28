package Errors;

// quando voglio fare un refill con borsa vuota o con borsa con non abbastanza tessere

public class RefillException extends Exception {
    public RefillException(String message) {
        super(message);
    }
}
