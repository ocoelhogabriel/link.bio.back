package br.com.ocoelhogabriel.link.bio.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateServicesBioDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.ServicesBioResponseDTO;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.ServicesBioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicesBioService {

    private final ServicesBioRepository repository;

    public ServicesBioService(ServicesBioRepository repository) {
        super();
        this.repository = repository;
    }

    public ResponseEntity<Object> findById(UUID id) throws Exception {
        ServicesBioResponseDTO service = repository.findById(id).map(ServicesBioResponseDTO::new).orElseThrow(() -> new EntityNotFoundException("Service not found"));
        return ResponseEntity.ok(service);
    }

    public ResponseEntity<Object> findAll() {
        try {
            List<ServicesBioResponseDTO> services = repository.findAll().stream().map(ServicesBioResponseDTO::new).toList();
            return ResponseEntity.ok(services);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> create(CreateUpdateServicesBioDTO service) {
        try {
            ServicesBioResponseDTO createdService = new ServicesBioResponseDTO(repository.save(service.toEntity()));
            return ResponseEntity.ok(createdService);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> update(UUID id, CreateUpdateServicesBioDTO service) {
        try {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            ServicesBioResponseDTO updatedService = new ServicesBioResponseDTO(repository.save(service.toEntity(id)));
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
