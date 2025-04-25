package br.com.ocoelhogabriel.link.bio.adapter.controller;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ocoelhogabriel.link.bio.application.services.AccessService;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateAccessDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/access")
@Tag(name = "Access", description = "Access management")
public class AccessController extends SecurityRestController {

    private final AccessService service;

    protected AccessController(AccessService service) {
        super();
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAllAccesss() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getServiceById(@PathVariable BigInteger id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createService(@Valid @RequestBody CreateUpdateAccessDTO createService) {
        return service.create(createService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateService(@PathVariable BigInteger id, @Valid @RequestBody CreateUpdateAccessDTO updatedService) {
        return service.update(id, updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable BigInteger id) {
        return service.delete(id);
    }
}
