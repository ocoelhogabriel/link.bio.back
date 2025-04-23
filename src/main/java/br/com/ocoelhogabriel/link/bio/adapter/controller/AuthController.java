package br.com.ocoelhogabriel.link.bio.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ocoelhogabriel.link.bio.application.services.AuthService;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.LoginRequestDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO request) {
        return service.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody CreateUpdateAccessDTO request) {
        return service.register(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(@RequestBody String oldToken) {
        return service.refreshToken(oldToken);
    }

}
