package Errors;

//Class that represents a customized exception thrown when selects the same slot more than once
public class AlreadyTakenException extends Exception{
    public AlreadyTakenException (String message) {
        super(message);
    }
}
