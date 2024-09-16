package Insurance.Configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for ApplicationSecurityConfiguration. Injects the userDetailsService and passwordEncoder.
     * @param userDetailsService the service that handles user authentication details.
     * @param passwordEncoder the password encoder used for encrypting user passwords.
     */
    @Autowired
    public ApplicationSecurityConfiguration(
            @Qualifier("userServiceImpl") UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Configures the security filter chain. Defines which endpoints are accessible and which require authentication.
     * Also configures login and logout settings.
     * @param http the HttpSecurity object used for configuring security settings.
     * @return the security filter chain for the application.
     * @throws Exception if there is an issue with configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE, "/clients/delete/**").permitAll()
                .anyRequest().permitAll() // Allow all other requests
                .and()
                .formLogin()
                .loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/clients/profile", true)
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") // Redirect to the main page after logout
                .logoutSuccessHandler(logoutSuccessHandler()) // Adds flash message upon logout
                .permitAll()
                .and()
                .build();
    }

    /**
     * Configures the logout success handler, which adds a message to the session and redirects the user after logout.
     * @return the logout success handler.
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleUrlLogoutSuccessHandler() {
            @Override
            protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                request.getSession().setAttribute("logoutMessage", "Došlo k úspěšnému odhlášení.");
                return "/";
            }
        };
    }

    /**
     * Configures the authentication manager with the userDetailsService and passwordEncoder.
     * This method is used to validate user credentials during authentication.
     * @param auth the authentication manager builder used to configure authentication.
     * @throws Exception if there is an issue with configuration.
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
