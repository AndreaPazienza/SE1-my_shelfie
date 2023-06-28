package Errors;

//Class that represents a customized exception thrown when inserts a wrong combination of numbers in the ordering phase
public class NotCorrectOrderException extends Exception{
    public NotCorrectOrderException(String message) {
        super(message);
    }
}
