package Insurance.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfiguration {

    /**
     * Defines a bean for the HiddenHttpMethodFilter.
     * This filter allows handling HTTP methods like PUT and DELETE sent via POST requests
     * with a hidden field (_method) specifying the desired HTTP method.
     *
     * @return a HiddenHttpMethodFilter instance.
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}

