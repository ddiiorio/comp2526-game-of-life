package ca.bcit.comp2526.a2c;

/**
 * Exception for a double linked list class.
 *
 * @author Danny Di Iorio
 * @version 1.0
 */
public class CouldNotAddException extends CouldNotRemoveException {

    private static final long serialVersionUID = 4846195485109846544L;

    /**
     * Creates an object of type CouldNotAddException.
     */
    public CouldNotAddException() {
    }

    /**
     * Creates an object of type CouldNotAddException.
     * @param message the message stored in the exception
     */
    public CouldNotAddException(String message) {
        super(message);
    }

    /**
     * Creates an object of type CouldNotAddException.
     * @param cause the cause of the exception
     */
    public CouldNotAddException(Throwable cause) {
        super(cause);
    }
}
