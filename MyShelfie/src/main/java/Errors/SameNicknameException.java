package Errors;

/**
 * Class that represents a customized exception thrown when a player choices a nickname already in use.
 */
public class SameNicknameException extends Exception{

    /**
     * Retrieves the message associated to the instance of SameNicknameException.
     *
     * @param message The error message.
     */
    public SameNicknameException(String message) {
        super(message);
    }
}

