package br.com.ocoelhogabriel.link.bio.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.AccessResponseDTO;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.AccessRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AccessService {

    private final AccessRepository repository;

    public AccessService(AccessRepository repository) {
        this.repository = repository;
        // Auto-generated constructor stub
    }

    public ResponseEntity<Object> findById(UUID id) throws Exception {
        AccessResponseDTO service = repository.findById(id).map(AccessResponseDTO::new).orElseThrow(() -> new EntityNotFoundException("Service not found"));
        return ResponseEntity.ok(service);
    }

    public ResponseEntity<Object> findAll() {
        try {
            List<AccessResponseDTO> services = repository.findAll().stream().map(AccessResponseDTO::new).toList();
            return ResponseEntity.ok(services);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> create(CreateUpdateAccessDTO service) {
        try {
            AccessResponseDTO createdService = new AccessResponseDTO(repository.save(service.toEntity()));
            return ResponseEntity.ok(createdService);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> update(UUID id, CreateUpdateAccessDTO service) {
        try {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            AccessResponseDTO updatedService = new AccessResponseDTO(repository.save(service.toEntity(id)));
            return ResponseEntity.ok(updatedService);
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
