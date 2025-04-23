package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.ServicesBio;

public interface ServicesBioRepository extends JpaRepository<ServicesBio, UUID> {
    List<ServicesBio> findAllByUserId(UUID userId);
}