package Insurance.models.exceptions;

/**
 * Exception thrown when the passwords provided during a registration or update process do not match.
 * This is a runtime exception used to handle cases where the password and confirm password fields are not equal.
 */
public class PasswordsDoNotEqualException extends RuntimeException {

    /**
     * Default constructor for PasswordsDoNotEqualException.
     * It provides a default message indicating that the passwords do not match.
     */
    public PasswordsDoNotEqualException() {
        super("Passwords do not match."); // Default message
    }

    /**
     * Constructor for PasswordsDoNotEqualException with a custom message.
     *
     * @param message the custom message for the exception.
     */
    public PasswordsDoNotEqualException(String message) {
        super(message);
    }
}
