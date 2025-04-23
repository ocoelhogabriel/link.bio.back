package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}