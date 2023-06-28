package Errors;

//Class that represents a customized exception thrown when the same slot is selected more time in the same selection
public class AlreadyTakenException extends Exception{
    public AlreadyTakenException (String message) {
        super(message);
    }
}
