package br.com.ocoelhogabriel.link.bio.application.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.AccessResponseDTO;
import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.AccessRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AccessService {

    private static final Logger logger = LoggerFactory.getLogger(AccessService.class);
    private static final String ACCESS_DEFAULT_NOT_CREATED = "Access default not created";

    private final AccessRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AccessService(AccessRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        // Auto-generated constructor stub
    }

    public ResponseEntity<Object> findById(BigInteger id) throws Exception {
        AccessResponseDTO service = repository.findById(id).map(AccessResponseDTO::new).orElseThrow(() -> new EntityNotFoundException("Access not found for id " + id));
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
            Access access = repository.save(service.toEntity(encodePassword(service.getPassword())));
            AccessResponseDTO createdService = new AccessResponseDTO(access);
            return ResponseEntity.ok(createdService);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> update(BigInteger id, CreateUpdateAccessDTO service) {
        try {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            Access access = repository.save(service.toEntity(id, encodePassword(service.getPassword())));
            AccessResponseDTO updatedService = new AccessResponseDTO(access);
            return ResponseEntity.ok(updatedService);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> delete(BigInteger id) {
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

    public Optional<Access> findByLogin(String login) {
        try {
            return repository.findByLogin(login);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createAccessDefault(BigInteger userId) {
        try {
            logger.info("Creating access default for user with id: {}", userId);
            Access newAccessDefault = Access.createAccessDefault(userId, encodePassword());
            newAccessDefault = repository.save(newAccessDefault);

            if (newAccessDefault.getId() == null) {
                throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED);
            }
            logger.info("Access default created with id: {}", newAccessDefault.getId());
        } catch (Exception e) {
            throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED, e);
        }
    }

    public void updateAccessDefault(Access accessDefault, BigInteger userId) {
        try {
            logger.info("Updating access default for user with id: {}", userId);
            Access updateAccessDefault = repository.save(Access.updateAccessDefault(accessDefault, userId, encodePassword()));

            if (updateAccessDefault.getId() == null) {
                throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED);
            }
            logger.info("Access default update with id: {}", updateAccessDefault.getId());
        } catch (Exception e) {
            throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED, e);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String encodePassword() {
        return passwordEncoder.encode("admin");
    }
}
