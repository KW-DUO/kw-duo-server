package kwduo.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI =
        OpenAPI()
            .addSecurityItem(SecurityRequirement().addList("Bearer Authentication"))
            .components(components())
            .info(apiInfo())

    fun apiInfo(): Info =
        Info()
            .title("KW DUO")
            .description("KW DUO Swagger")
            .version("1.0.0")

    fun components(): Components =
        Components()
            .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())

    private fun createAPIKeyScheme() =
        SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
}
