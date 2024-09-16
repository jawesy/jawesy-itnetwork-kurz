package Insurance.models.validations;

import Insurance.models.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator class for the custom annotation @PasswordMatches.
 * This validator checks if the password and confirm password fields in the UserDTO are equal.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDTO> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        // This method can be used for initialization, but it's not required for this validator.
    }

    /**
     * Checks if the password and confirmPassword fields are equal.
     * @param user The UserDTO object that contains the password and confirmPassword fields.
     * @param context The context in which the constraint is evaluated.
     * @return true if passwords match, false otherwise.
     */
    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
