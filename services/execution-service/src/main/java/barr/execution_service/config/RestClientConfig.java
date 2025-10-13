package barr.execution_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    RestClient planningRestClient() {
        return RestClient.builder().baseUrl("http://planning-service:8082").build();
    }

    @Bean
    RestClient productRestClient() {
        return RestClient.builder().baseUrl("http://product-service:8081").build();
    }
}
