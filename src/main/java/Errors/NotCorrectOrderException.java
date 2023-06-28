package Errors;

//Se nell'ordinamento vengono  messi numeri != 1 && 2 && 3

public class NotCorrectOrderException extends Exception{
    public NotCorrectOrderException(String message) {
        super(message);
    }
}
