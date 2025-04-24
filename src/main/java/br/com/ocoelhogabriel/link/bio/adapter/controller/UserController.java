package br.com.ocoelhogabriel.link.bio.adapter.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ocoelhogabriel.link.bio.application.services.UserService;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateUserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management")
public class UserController extends SecurityRestController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAllServices() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getServiceById(@PathVariable UUID id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createService(@Valid @RequestBody CreateUpdateUserDTO createService) {
        return service.create(createService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateService(@PathVariable UUID id, @Valid @RequestBody CreateUpdateUserDTO updatedService) {
        return service.update(id, updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable UUID id) {
        return service.delete(id);
    }
}
