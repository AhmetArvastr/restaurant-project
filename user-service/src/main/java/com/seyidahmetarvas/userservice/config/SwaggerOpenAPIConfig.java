package com.seyidahmetarvas.userservice.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(name = "Seyid Ahmet ARVAS", email = "ahmetarvastr@gmail.com"),
                description = "OpenAPI documentation for Restaurant App, a Spring Boot REST API as a graduation project",
                title = "Dine Experience App - User Service API",
                version = "1.0"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Source code on GitHub",
                url = "https://www.github.com/ahmetarvastr/dineExperienceApp"
        ),
        servers = {@Server(description = "user-service", url = "http://localhost:8082")}

)
public class SwaggerOpenAPIConfig {
}
