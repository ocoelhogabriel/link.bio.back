package br.com.ocoelhogabriel.link.bio.application.services;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.LoginRequestDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.AuthAccessResponse;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.AuthResponse;
import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.AccessRepository;
import br.com.ocoelhogabriel.link.bio.domain.model.TokenDataDTO;

@Service
public class AuthService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AccessRepository accessRepository;
    private final AuthenticationManager authenticationManager;

    public AuthService(AccessRepository accessRepository, TokenService tokenService, @Lazy AuthenticationManager authenticationManager, @Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.accessRepository = accessRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Access> usuario = accessRepository.findByLogin(username);
        if (usuario.isEmpty()) {
            log.info("Usuário não encontrado: {}", username);
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return usuario.get();
    }

    public Optional<Access> findByLogin(String login) {
        return accessRepository.findByLogin(login);
    }

    public Optional<String> validateToken(String login) {
        return Optional.ofNullable(tokenService.validateToken(login));
    }

    public ResponseEntity<Object> login(LoginRequestDTO request) {
        Access access = accessRepository.findByLogin(request.getLogin()).orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(access.getLogin(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);

        TokenDataDTO tokenData = tokenService.generateToken(access);
        access.setToken(tokenData.token());
        accessRepository.save(access);

        return ResponseEntity.ok(new AuthAccessResponse().accessGranted("Access granted!", tokenData.token(), access.getUserId(), tokenData.date(), tokenData.expiryIn()));
    }

    public ResponseEntity<Object> register(CreateUpdateAccessDTO request) {
        if (accessRepository.existsByLogin(request.getLogin())) {
            return ResponseEntity.badRequest().body("Login já em uso");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Access access = new Access(null, request.getUserId(), request.getLogin(), encodedPassword, UUID.randomUUID().toString(), request.getRole());
        access = accessRepository.save(access);

        return ResponseEntity.ok(new AuthResponse(true, "Access granted!", access.getUserId()));
    }

    public ResponseEntity<Object> refreshToken(String oldToken) {
        Optional<Access> accessOpt = accessRepository.findByToken(oldToken);
        if (accessOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }

        Access access = accessOpt.get();
        TokenDataDTO tokenData = tokenService.refreshToken(access.getToken());
        access.setToken(tokenData.token());
        accessRepository.save(access);

        return ResponseEntity.ok(new AuthAccessResponse().accessGranted("Access granted!", tokenData.token(), access.getUserId(), tokenData.date(), tokenData.expiryIn()));
    }
}
