package br.com.ocoelhogabriel.link.bio.application.services;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateUserDTO;
import br.com.ocoelhogabriel.link.bio.domain.dto.response.UserResponseDTO;
import br.com.ocoelhogabriel.link.bio.domain.entity.User;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private static final String USER_DEFAULT_NOT_CREATED = "User default not created";

    public UserService(UserRepository repository) {
        this.repository = repository;
        // Auto-generated constructor stub
    }

    public ResponseEntity<Object> findById(BigInteger id) throws Exception {
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

    public ResponseEntity<Object> update(BigInteger id, CreateUpdateUserDTO service) {
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

    public BigInteger createUserDefault() {
        try {
            logger.info("Creating user default");
            User userDefault = User.createUserDefault();
            userDefault = repository.save(userDefault);

            if (userDefault.getId() == null) {
                throw new RuntimeException(USER_DEFAULT_NOT_CREATED);
            }
            logger.info("User default created with id: {}", userDefault.getId());
            return userDefault.getId();
        } catch (Exception e) {
            throw new RuntimeException(USER_DEFAULT_NOT_CREATED, e);
        }
    }

    public User updateUserDefault(BigInteger userId) {
        try {
            logger.info("Creating user default");
            User userDefault = repository.findById(userId).orElseThrow(() -> new RuntimeException(USER_DEFAULT_NOT_CREATED));
            User updateUuserDefault = repository.save(User.updateUserDefault(userDefault));

            if (updateUuserDefault.getId() == null) {
                throw new RuntimeException(USER_DEFAULT_NOT_CREATED);
            }
            logger.info("User default created with id: {}", updateUuserDefault.getId());
            return updateUuserDefault;
        } catch (Exception e) {
            throw new RuntimeException(USER_DEFAULT_NOT_CREATED, e);
        }
    }

}
