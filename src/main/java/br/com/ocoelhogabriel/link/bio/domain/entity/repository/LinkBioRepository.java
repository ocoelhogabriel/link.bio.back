package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.LinkBio;

public interface LinkBioRepository extends JpaRepository<LinkBio, UUID> {
    List<LinkBio> findAllByUserId(UUID userId);
}
