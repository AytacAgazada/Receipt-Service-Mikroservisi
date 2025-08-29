package com.example.receiptservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Receipt Service API",
                version = "1.0",
                description = "Receipt Service API documentation"
        )
)
@Configuration
public class OpenApi {
}
