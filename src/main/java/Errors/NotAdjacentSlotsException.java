package Errors;

/**
 * Class that represents a customized exception thrown when a player selects not adjacent slots.
 */
public class NotAdjacentSlotsException extends Exception{

    /**
     * Retrieves the message associated to the instance of NotAdjacentSlotsException.
     *
     * @param message The error message.
     */
    public NotAdjacentSlotsException (String message) {
        super(message);
    }
}
