package cl.desafio.java.pstaubr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Desafío java Pstaubr.")
                .version("1.0").description("Microservicio correspondiente al desafío java Pstaubr.")
                .contact(new Contact().name("Desafío java Pstaubr.")
                )
                .license(new License().name("Desafío java Pstaubr")));
    }
}