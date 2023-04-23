package Errors;

//quando un giocatore tenta di effettuare inserimento ma ha la shelf piena

public class ShelfFullException extends Exception{
    public ShelfFullException(String message) {
        super(message);
    }
}
