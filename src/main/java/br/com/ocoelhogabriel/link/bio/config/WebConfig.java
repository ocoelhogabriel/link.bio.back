package br.com.ocoelhogabriel.link.bio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(@NonNull CorsRegistry registry) {
		registry.addMapping("/**")
				// .allowedOriginPatterns("https://example.com", "https://subdomain.example.com") // Permitir apenas domínios específicos
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir apenas os métodos necessários
				.allowedHeaders("Content-Type", "Authorization") // Permitir apenas cabeçalhos específicos
				.allowCredentials(true) // Permitir envio de cookies/sessões
				.maxAge(3600); // Cache da resposta preflight por 1 hora (em segundos)
	}

	@Override
	public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addViewControllers(@NonNull ViewControllerRegistry registry) {
		// Redireciona a URL base para a documentação Swagger
		registry.addRedirectViewController("/", "/swagger-ui/index.html");
	}
}