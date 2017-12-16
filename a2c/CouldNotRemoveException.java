package ca.bcit.comp2526.a2c;

/**
 * Exception for a double linked list class.
 *
 * @author Danny Di Iorio
 * @version 1.0
 */
public class CouldNotRemoveException extends Exception {

    private static final long serialVersionUID = 155804856641436340L;

    /**
     * Creates an object of type CouldNotRemoveException.
     */
    public CouldNotRemoveException() {
    }

    /**
     * Creates an object of type CouldNotRemoveException.
     * @param message the message stored in the exception
     */
    public CouldNotRemoveException(String message) {
        super(message);
    }

    /**
     * Creates an object of type CouldNotRemoveException.
     * @param cause the cause of the exception
     */
    public CouldNotRemoveException(Throwable cause) {
        super(cause);
    }
}
