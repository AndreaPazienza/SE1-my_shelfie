package Errors;

//quando il giocatore tenta di iscriversi con un nome già preso

public class SameNicknameException extends Exception{
    public SameNicknameException(String message) {
        super(message);
    }
}
