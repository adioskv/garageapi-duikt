package in.mhlvs.garageapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springGarageOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Garage API")
                        .description("Документація REST API для системи автосервісу")
                        .version("v1.0.0"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("garage")
                .pathsToMatch("/appointments/**")
                .build();
    }
}