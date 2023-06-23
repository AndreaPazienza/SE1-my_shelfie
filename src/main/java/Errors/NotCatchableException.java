package Errors;

/**
 * Class that represents a customized exception thrown when a player selects one (or more) not catchable slots.
 */
public class NotCatchableException extends Exception {

    /**
     * Retrieves the message associated to the instance of NotCatchableException.
     *
     * @param message The error message.
     */
    public NotCatchableException(String message){
        super(message);
    }
}
