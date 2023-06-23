package Errors;

//Class that represents a customized exception thrown when a player selects an empty (grey) slot
public class NotPlayableException extends Exception{
    public NotPlayableException(String message) {
        super(message);
    }
}