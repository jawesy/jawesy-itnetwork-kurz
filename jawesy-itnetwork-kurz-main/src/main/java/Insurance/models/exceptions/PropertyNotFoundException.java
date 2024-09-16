package Insurance.models.exceptions;

/**
 * Exception thrown when the requested property insurance is not found in the system.
 * This is a runtime exception used to handle cases where a property insurance record does not exist.
 */
public class PropertyNotFoundException extends RuntimeException {

    /**
     * Default constructor for PropertyNotFoundException.
     * It provides a default message indicating that the property insurance was not found.
     */
    public PropertyNotFoundException() {
        super("Property insurance not found."); // Default message
    }

    /**
     * Constructor for PropertyNotFoundException with a custom message.
     *
     * @param message the custom message for the exception.
     */
    public PropertyNotFoundException(String message) {
        super(message);
    }
}
