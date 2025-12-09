package com.example.striderbackend.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing the generated OpenAPI/Swagger documentation.
 *
 * This class sets the title, version, description, contact details, and license
 * information that appears in Swagger UI.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates and configures the OpenAPI bean.
     *
     * Spring automatically loads this into the OpenAPI auto-configuration,
     * allowing you to customize how your API documentation appears.
     *
     * @return Configured OpenAPI object shown in Swagger UI
     */
    @Bean
    public OpenAPI api() {

        // Create an Info block that defines core metadata for the API documentation
        return new OpenAPI().info(new Info()
                .title("Strider Backend API")          // The title displayed in Swagger UI
                .version("1.0.0")                      // API version
                .description("REST API for Strider.")  // Short description
                .contact(new Contact()                 // Contact information shown in documentation
                        .name("Strider")
                        .email("support@strider.dev"))
                .license(new License()                 // License information
                        .name("Apache 2.0")));
    }
}
