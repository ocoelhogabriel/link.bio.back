package br.com.ocoelhogabriel.link.bio.application.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.model.TokenDataDTO;

@Service
public class TokenService {

    private static final DateTimeFormatter dtfEditado = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Value("${link.bio.security.expiration.time.minutes}")
    private long expirationTime;

    @Value("${link.bio.security.token.secret}")
    private String secret;

    public TokenDataDTO generateToken(Access user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            Instant now = Instant.now();
            var dateExpiration = getExpirationDate(now);
            String token = JWT.create().withIssuer("auth-api").withSubject(user.getLogin()).withExpiresAt(dateExpiration).sign(algorithm);
            return new TokenDataDTO(token, convertInstantToString(now), convertInstantToString(dateExpiration));
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    public TokenDataDTO refreshToken(String oldToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algorithm).withIssuer("auth-api").build();

            var decodedJWT = verifier.verify(oldToken);
            String subject = decodedJWT.getSubject();

            if (subject == null || subject.isBlank()) {
                throw new JWTVerificationException("Token subject is missing");
            }
            if (decodedJWT.getExpiresAt().before(new Date())) {
                throw new JWTVerificationException("Token already expired, refresh not allowed");
            }

            Instant now = Instant.now();
            var dateExpiration = getExpirationDate(now);

            String token = JWT.create().withIssuer("auth-api").withSubject(subject).withExpiresAt(dateExpiration).sign(algorithm);
            return new TokenDataDTO(token, convertInstantToString(now), convertInstantToString(dateExpiration));
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Refresh failed: token is invalid or expired", e);
        }
    }

    private Instant getExpirationDate(Instant date) {
        return date.plusSeconds(expirationTime * 60);
    }

    public static String convertDateToString(Date date) {
        Objects.requireNonNull(date, "A Data de entrada para conversão de Date para String está nula.");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return dtfEditado.format(localDateTime);
    }

    public static String convertInstantToString(Instant date) {
        Objects.requireNonNull(date, "A Data de entrada para conversão de Date para String está nula.");
        return dtfEditado.format(date.atZone(ZoneId.systemDefault()));
    }
}
