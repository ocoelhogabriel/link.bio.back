package br.com.ocoelhogabriel.link.bio.config.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ocoelhogabriel.link.bio.application.services.AuthService;
import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.shared.custom.CustomAccessDeniedHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();

    public SecurityFilter(AuthService authService) {
        super();
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {

            String token = recoverToken(request);
            if (token != null) {
                Optional<String> login = authService.validateToken(token);
                if (login.isEmpty()) {
                    accessDeniedHandler.handle(request, response, new AccessDeniedException("Access Denied"));
                    return;
                }

                Optional<Access> access = authService.findByLogin(login.get());

                if (!access.isPresent()) {
                    accessDeniedHandler.handle(request, response, new AccessDeniedException("Access Denied"));
                    return;
                }
                UserDetails user = access.get();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            accessDeniedHandler.handle(request, response, new AccessDeniedException(e.getMessage()));
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }
}
