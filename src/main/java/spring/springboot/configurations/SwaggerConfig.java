package spring.springboot.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import spring.springboot.constants.Swagger;

@Configuration
@OpenAPIDefinition(security = { @SecurityRequirement(name = Swagger.swgSecMethodName) })
@SecuritySchemes({
        @SecurityScheme(name = Swagger.swgSecMethodName, type = SecuritySchemeType.HTTP, scheme = Swagger.swgScheme, bearerFormat = Swagger.swgBearerFormat)
})
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(Swagger.swgTitle).description(Swagger.swgDes).version(Swagger.swgVer));
    }
}
