package Errors;

/**
 * Class that represents a customized exception thrown when a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
 */
public class NotEnoughSpaceChoiceException extends Exception{

    /**
     * Retrieves the message associated to the instance of NotEnoughSpaceChoiceException.
     *
     * @param message The error message.
     */
    public NotEnoughSpaceChoiceException(String message){
        super(message);
    }
}