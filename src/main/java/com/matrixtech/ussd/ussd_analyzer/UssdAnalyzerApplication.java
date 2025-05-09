package com.matrixtech.ussd.ussd_analyzer;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main class for the USSD Analyzer application.
 * It serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
@EnableScheduling // Enable scheduling functionality in Spring Boot
public class UssdAnalyzerApplication {

	public static void main(String[] args) {
		// Load environment variables from the .env file before Spring Boot starts
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()  // Ignore if .env is missing to avoid errors
				.load();  // Load the .env file

		// Set each key-value pair from the .env file as system properties
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		// Start the Spring Boot application
		SpringApplication.run(UssdAnalyzerApplication.class, args);
	}
}
