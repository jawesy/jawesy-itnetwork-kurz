package Insurance.models.exceptions;

/**
 * Exception thrown when an email already exists in the system.
 * This is a runtime exception used to handle duplicate email cases during user registration.
 */
public class DuplicateEmailException extends RuntimeException {

    /**
     * Default constructor for DuplicateEmailException.
     * It provides a default message indicating that the email is already in use.
     */
    public DuplicateEmailException() {
        super("This email is already in use."); // Default message
    }

    /**
     * Constructor for DuplicateEmailException with a custom message.
     *
     * @param message the custom message for the exception.
     */
    public DuplicateEmailException(String message) {
        super(message);
    }
}
