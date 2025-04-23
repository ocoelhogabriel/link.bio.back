package br.com.ocoelhogabriel.link.bio.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateUserDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.UserResponseDTO;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
        // Auto-generated constructor stub
    }

    public ResponseEntity<Object> findById(UUID id) throws Exception {
        UserResponseDTO service = repository.findById(id).map(UserResponseDTO::new).orElseThrow(() -> new EntityNotFoundException("Service not found"));
        return ResponseEntity.ok(service);
    }

    public ResponseEntity<Object> findAll() {
        try {
            List<UserResponseDTO> services = repository.findAll().stream().map(UserResponseDTO::new).toList();
            return ResponseEntity.ok(services);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> create(CreateUpdateUserDTO service) {
        try {
            UserResponseDTO createdService = new UserResponseDTO(repository.save(service.toEntity()));
            return ResponseEntity.ok(createdService);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> update(UUID id, CreateUpdateUserDTO service) {
        try {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            UserResponseDTO updatedService = new UserResponseDTO(repository.save(service.toEntity(id)));
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
