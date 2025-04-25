package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.User;

public interface UserRepository extends JpaRepository<User, BigInteger> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}