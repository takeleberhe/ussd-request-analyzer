package com.matrixtech.ussd.ussd_analyzer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up OpenAPI (Swagger) documentation for the USSD Request Analyzer API.
 * This configuration will expose API documentation through a Swagger UI endpoint.
 */
@Configuration
public class OpenApiConfig {

        /**
         * Bean that defines the OpenAPI documentation for the USSD Request Analyzer API.
         * This method customizes the API information like title, version, and description.
         * The information defined here will be used in the Swagger UI and OpenAPI specifications.
         *
         * @return an OpenAPI instance with the configured API info
         */
        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .info(new Info()
                                .title("USSD Request Analyzer API") // Title of the API
                                .version("1.0.0") // Version of the API
                                .description("API documentation for the USSD request and response parsing microservice.") // Brief description of the API's purpose
                        );
        }
}
