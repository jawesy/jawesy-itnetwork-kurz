package Insurance.models.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to check if two password fields match.
 * This annotation is used at the class level.
 */
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {

    // Default validation message if passwords do not match.
    String message() default "Hesla se neshodují";

    // Allows specifying validation groups.
    Class<?>[] groups() default {};

    // Payload type that can be attached to a given constraint declaration.
    Class<? extends Payload>[] payload() default {};
}
