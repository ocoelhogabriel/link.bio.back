package br.com.ocoelhogabriel.link.bio.adapter.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.LoginRequestDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.AuthResponse;
import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.AccessRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccessRepository accessRepository;

    public AuthController(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO request) {
        Optional<Access> accessOpt = accessRepository.findByLogin(request.getLogin());

        if (accessOpt.isEmpty() || !accessOpt.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Login ou senha inválidos");
        }

        Access access = accessOpt.get();
        if (access.getToken() == null || access.getToken().isBlank()) {
            access.setToken(UUID.randomUUID().toString());
            accessRepository.save(access);
        }

        return ResponseEntity.ok(new AuthResponse(access.getToken(), access.getUserId()));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody CreateUpdateAccessDTO request) {
        if (accessRepository.existsByLogin(request.getLogin())) {
            return ResponseEntity.badRequest().body("Login já em uso");
        }

        Access access = new Access(null, request.getUserId(), request.getLogin(), request.getPassword(), UUID.randomUUID().toString(), request.getRole());
        access = accessRepository.save(access);

        return ResponseEntity.ok(new AuthResponse(access.getToken(), access.getUserId()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(@RequestBody String oldToken) {
        Optional<Access> accessOpt = accessRepository.findByToken(oldToken);

        if (accessOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Token inválido");
        }

        Access access = accessOpt.get();
        String newToken = UUID.randomUUID().toString();
        access.setToken(newToken);
        accessRepository.save(access);

        return ResponseEntity.ok(new AuthResponse(newToken, access.getUserId()));
    }
}
