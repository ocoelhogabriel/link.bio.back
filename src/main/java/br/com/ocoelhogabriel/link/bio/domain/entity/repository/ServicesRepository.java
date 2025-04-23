package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.Services;

public interface ServicesRepository extends JpaRepository<Services, UUID> {
    List<Services> findAllByUserId(UUID userId);
}