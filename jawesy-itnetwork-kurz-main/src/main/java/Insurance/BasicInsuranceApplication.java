package Insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main class for the Basic Insurance application.
 * This class is responsible for starting the Spring Boot application.
 */
@SpringBootApplication
@EnableJpaRepositories
public class BasicInsuranceApplication {

    /**
     * The main method that launches the Spring Boot application.
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(BasicInsuranceApplication.class, args);
    }

}

