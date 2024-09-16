package Insurance.models.exceptions;

/**
 * Exception thrown when a mandatory insurance record is not found.
 * This is a runtime exception used to handle cases where a mandatory insurance entity cannot be located.
 */
public class MandatoryNotFoundException extends RuntimeException {

    /**
     * Default constructor for MandatoryNotFoundException.
     * It provides a default message indicating that the mandatory insurance was not found.
     */
    public MandatoryNotFoundException() {
        super("Mandatory insurance record not found."); // Default message
    }

    /**
     * Constructor for MandatoryNotFoundException with a custom message.
     *
     * @param message the custom message for the exception.
     */
    public MandatoryNotFoundException(String message) {
        super(message);
    }
}
