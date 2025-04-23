package br.com.ocoelhogabriel.link.bio.config.security;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.ocoelhogabriel.link.bio.shared.custom.CustomAccessDeniedHandler;
import br.com.ocoelhogabriel.link.bio.shared.custom.CustomAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@EnableWebSecurity
@SecurityScheme(name = "bearerAuth", description = "JWT Authentication", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class SecurityConfig {

    // Lista de rotas públicas permitidas
    private static final List<String> PUBLIC_ROUTES = List.of("/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html");

    // Rotas por método + role necessária
    private static final Map<HttpMethod, Map<String, String>> METHOD_ROLE_ROUTES = Map.of(HttpMethod.GET,
            Map.of("/user/**", "USER", "/admin/**", "ADMIN"),
            HttpMethod.POST,
            Map.of("/product", "ADMIN"),
            HttpMethod.PUT,
            Map.of("/user/**", "USER"),
            HttpMethod.DELETE,
            Map.of("/admin/**", "ADMIN"));

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityFilter securityFilter) throws Exception {
        return http.csrf(csrf -> csrf.disable()).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth -> {
            // Rotas públicas
            PUBLIC_ROUTES.forEach(route -> auth.requestMatchers(route).permitAll());

            // Autorização por método + rota + role
            METHOD_ROLE_ROUTES.forEach((method, routes) -> routes.forEach((route, role) -> auth.requestMatchers(method, route).hasRole(role)));

            auth.anyRequest().permitAll();
        })
            .exceptionHandling(ex -> ex.accessDeniedHandler(new CustomAccessDeniedHandler()).authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}