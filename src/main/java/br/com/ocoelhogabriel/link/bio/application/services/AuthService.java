package br.com.ocoelhogabriel.link.bio.application.services;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.LoginRequestDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.AuthAccessResponse;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.AuthResponse;
import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.AccessRepository;
import br.com.ocoelhogabriel.link.bio.domain.model.TokenDataDTO;
import jakarta.validation.Valid;

@Service
public class AuthService {

    @Lazy
    private final TokenService tokenService;
    @Lazy
    private final AccessRepository accessRepository;
    @Lazy
    private final AuthenticationManager authenticationManager;

    public AuthService(AccessRepository accessRepository, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.accessRepository = accessRepository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO request) {
        Optional<Access> accessOpt = accessRepository.findByLogin(request.getLogin());

        var userAutheticationToken = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
        authenticationManager.authenticate(userAutheticationToken);
        if (accessOpt.isEmpty() || !accessOpt.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Login ou senha inválidos");
        }

        Access access = accessOpt.get();
        TokenDataDTO tokenData = tokenService.generateToken(access);
        access.setToken(tokenData.token());
        accessRepository.save(access);

        return ResponseEntity.ok(new AuthAccessResponse().accessGranted("Access granted!", tokenData.token(), access.getUserId(), tokenData.date(), tokenData.expiryIn()));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody CreateUpdateAccessDTO request) {
        if (accessRepository.existsByLogin(request.getLogin())) {
            return ResponseEntity.badRequest().body("Login já em uso");
        }

        Access access = new Access(null, request.getUserId(), request.getLogin(), request.getPassword(), UUID.randomUUID().toString(), request.getRole());
        access = accessRepository.save(access);

        return ResponseEntity.ok(new AuthResponse(true, "Access granted!", access.getUserId()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(@RequestBody String oldToken) {
        Optional<Access> accessOpt = accessRepository.findByToken(oldToken);

        if (accessOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Token inválido");
        }

        Access access = accessOpt.get();
        TokenDataDTO tokenData = tokenService.refreshToken(access.getToken());
        access.setToken(tokenData.token());
        accessRepository.save(access);

        return ResponseEntity.ok(new AuthAccessResponse().accessGranted("Access granted!", tokenData.token(), access.getUserId(), tokenData.date(), tokenData.expiryIn()));
    }

}
