package br.com.ocoelhogabriel.link.bio.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateLinkBioDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.LinkBioResponseDTO;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.LinkBioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LinkBioService {

    private final LinkBioRepository repository;

    public LinkBioService(LinkBioRepository repository) {
        super();
        this.repository = repository;
    }

    public ResponseEntity<Object> findById(UUID id) throws Exception {
        LinkBioResponseDTO service = repository.findById(id).map(LinkBioResponseDTO::new).orElseThrow(() -> new EntityNotFoundException("LinkBio not found"));
        return ResponseEntity.ok(service);
    }

    public ResponseEntity<Object> findAll() {
        try {
            List<LinkBioResponseDTO> services = repository.findAll().stream().map(LinkBioResponseDTO::new).toList();
            return ResponseEntity.ok(services);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> create(CreateUpdateLinkBioDTO service) {
        try {
            LinkBioResponseDTO createdLinkBio = new LinkBioResponseDTO(repository.save(service.toEntity()));
            return ResponseEntity.ok(createdLinkBio);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> update(UUID id, CreateUpdateLinkBioDTO service) {
        try {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            LinkBioResponseDTO updatedLinkBio = new LinkBioResponseDTO(repository.save(service.toEntity(id)));
            return ResponseEntity.ok(updatedLinkBio);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> delete(UUID id) {
        try {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw e;
        }
    }

}
