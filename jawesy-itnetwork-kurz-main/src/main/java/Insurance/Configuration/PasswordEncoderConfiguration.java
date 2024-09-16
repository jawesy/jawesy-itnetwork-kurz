package Insurance.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfiguration {

    /**
     * Defines a bean for the PasswordEncoder, specifically using BCryptPasswordEncoder.
     * This bean is used to encrypt passwords within the application.
     *
     * @return a PasswordEncoder instance using BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
