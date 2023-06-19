package Errors;

//Class that represents a customized exception thrown when the dashboard needs to be refilled but the bag is empty
public class RefillException extends Exception {
    public RefillException(String message) {
        super(message);
    }
}
