package Errors;

//Class that represents a customized exception thrown when a player selects a not playable (black) slot
public class NotPlayablePositionException extends Exception {
    public NotPlayablePositionException (String message) {
        super(message);
    }
}
