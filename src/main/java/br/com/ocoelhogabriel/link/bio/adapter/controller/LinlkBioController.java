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

import br.com.ocoelhogabriel.link.bio.application.services.LinkBioService;
import br.com.ocoelhogabriel.link.bio.domain.dto.request.CreateUpdateLinkBioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/link-bio")
@Tag(name = "LinkBio", description = "LinkBio management")
public class LinlkBioController extends SecurityRestController {

    private final LinkBioService service;

    protected LinlkBioController(LinkBioService service) {
        super();
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAllLinkBios() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getServiceById(@PathVariable UUID id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createService(@Valid @RequestBody CreateUpdateLinkBioDTO createService) {
        return service.create(createService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateService(@PathVariable UUID id, @Valid @RequestBody CreateUpdateLinkBioDTO updatedService) {
        return service.update(id, updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable UUID id) {
        return service.delete(id);
    }
}
