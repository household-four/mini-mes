package barr.execution_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ExecutionServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(ExecutionServiceApplication.class);

	@Value("${spring.profiles.active:default}")
	private String activeProfile;

	public static void main(String[] args) {
		SpringApplication.run(ExecutionServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner showProfile() {
		return args -> log.info("🚀 Running planning-service with Spring profile: {}", activeProfile);
	}

}
