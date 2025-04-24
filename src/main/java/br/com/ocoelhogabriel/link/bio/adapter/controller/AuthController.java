package br.com.ocoelhogabriel.link.bio.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ocoelhogabriel.link.bio.application.services.AuthService;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.LoginRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "Controller for authentication and authorization")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO request) {
        return service.login(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(@RequestBody String oldToken) {
        return service.refreshToken(oldToken);
    }

    @PostMapping("/register")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Object> register(@Valid @RequestBody CreateUpdateAccessDTO request) {
        return service.register(request);
    }
}
