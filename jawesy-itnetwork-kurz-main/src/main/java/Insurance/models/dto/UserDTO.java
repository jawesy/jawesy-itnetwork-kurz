package Insurance.models.dto;

import Insurance.models.validations.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for user registration and profile data.
 * This class is used to transfer user data between layers in the application.
 * It includes validation annotations for form input and password matching.
 */
@PasswordMatches(message = "Hesla se neshodují") // Custom validation to ensure passwords match
public class UserDTO {

    private long clientId; // The ID of the associated client

    @Email(message = "Vyplňte validní email")
    @NotBlank(message = "Vyplňte uživatelský email")
    private String email; // User's email address

    @NotBlank(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String password; // User's password

    @NotBlank(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String confirmPassword; // Confirmation of the user's password

    // Getters and Setters

    /**
     * Gets the user's email address.
     *
     * @return email the email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return password the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the confirmation password.
     *
     * @return confirmPassword the confirmation of the user's password.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirmation password.
     *
     * @param confirmPassword the confirmation of the user's password.
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
