package Insurance.models.exceptions;

/**
 * Exception thrown when crash insurance is not found in the system.
 * This is a runtime exception used to indicate that the requested crash insurance does not exist.
 */
public class CrashNotFoundException extends RuntimeException {

    /**
     * Default constructor for CrashNotFoundException.
     * It provides a default message indicating that crash insurance was not found.
     */
    public CrashNotFoundException() {
        super("Crash insurance was not found."); // Default message
    }

    /**
     * Constructor for CrashNotFoundException with a custom message.
     *
     * @param message the custom message for the exception.
     */
    public CrashNotFoundException(String message) {
        super(message);
    }
}
