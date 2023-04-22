package Errors;

public class NotCatchableException extends Exception {
    public NotCatchableException(String statement){
        super(statement);
    }
}
