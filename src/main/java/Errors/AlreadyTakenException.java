package Errors;

// gestione errore di quando si tenta di prendere una tessera che è già stata presa

public class AlreadyTakenException extends Exception{
    public AlreadyTakenException (String message) {
        super(message);
    }
}
