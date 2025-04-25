package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.Access;

public interface AccessRepository extends JpaRepository<Access, BigInteger> {
    Optional<Access> findByLogin(String login);

    Optional<Access> findByToken(String token);

    boolean existsByLogin(String login);
}