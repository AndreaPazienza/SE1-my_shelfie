package Errors;

//quando vengono selezionate tessere che non sono prendibili

public class NotCatchableException extends Exception {
    public NotCatchableException(String statement){
        super(statement);
    }
}
