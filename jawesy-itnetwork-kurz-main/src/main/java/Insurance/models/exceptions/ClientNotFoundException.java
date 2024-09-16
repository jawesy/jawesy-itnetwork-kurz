package Insurance.models.exceptions;

/**
 * Exception thrown when a client is not found in the system.
 * This is a runtime exception used to indicate that the requested client does not exist.
 */
public class ClientNotFoundException extends RuntimeException {

    /**
     * Default constructor for ClientNotFoundException.
     * It provides a default message indicating that the client was not found.
     */
    public ClientNotFoundException() {
        super("Klient nebyl nalezen."); // Default message in Czech
    }

    /**
     * Constructor for ClientNotFoundException with a custom message.
     *
     * @param message the custom message for the exception.
     */
    public ClientNotFoundException(String message) {
        super(message);
    }
}
