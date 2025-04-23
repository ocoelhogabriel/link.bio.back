package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.Access;

public interface AccessRepository extends JpaRepository<Access, UUID> {
    Optional<Access> findByLogin(String login);
    Optional<Access> findByToken(String token);

    boolean existsByLogin(String login);
}