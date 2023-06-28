package Errors;

//Class that represents a customized exception thrown when inserts more times the same number in the ordering phase
public class SameNumberException extends Exception {
    public SameNumberException(String message) {
        super(message);
    }
}

