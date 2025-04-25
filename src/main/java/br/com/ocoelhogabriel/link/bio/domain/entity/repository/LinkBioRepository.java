package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.LinkBio;

public interface LinkBioRepository extends JpaRepository<LinkBio, BigInteger> {
    List<LinkBio> findAllByUserId(BigInteger userId);
}
