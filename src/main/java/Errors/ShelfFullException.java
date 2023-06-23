package Errors;

//Class that represents a customized exception thrown when a player tries to insert a slot (or more) in a full shelf
public class ShelfFullException extends Exception{
    public ShelfFullException(String message) {
        super(message);
    }
}
