package Errors;

//quando seleziono tessera nera posizione ingiocabile

public class NotPlayableException extends Exception{
    public NotPlayableException(String message) {
        super(message);
    }
}
