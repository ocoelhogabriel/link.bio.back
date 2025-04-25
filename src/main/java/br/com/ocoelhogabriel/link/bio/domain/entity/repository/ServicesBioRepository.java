package br.com.ocoelhogabriel.link.bio.domain.entity.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ocoelhogabriel.link.bio.domain.entity.ServicesBio;

public interface ServicesBioRepository extends JpaRepository<ServicesBio, BigInteger> {
    List<ServicesBio> findAllByUserId(BigInteger userId);
}