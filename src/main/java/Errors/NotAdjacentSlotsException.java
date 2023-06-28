package Errors;

//quando vengono selezionate tessere che non sono adiacenti

public class NotAdjacentSlotsException extends Exception{
    public NotAdjacentSlotsException (String message) {
        super(message);
    }
}
