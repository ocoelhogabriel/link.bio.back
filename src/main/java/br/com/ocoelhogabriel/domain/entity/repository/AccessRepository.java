package br.com.ocoelhogabriel.domain.entity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.domain.entity.Access;

public interface AccessRepository extends JpaRepository<Access, UUID> {
    Access findByLogin(String login);

    boolean existsByLogin(String login);
}