package com.talkevents.jpa.controllers;

import com.talkevents.jpa.entities.Location;
import com.talkevents.jpa.repositories.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationRepository repository;

    public LocationController(LocationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Location> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> get(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> create(@RequestBody Location location) {
        Location saved = repository.save(location);
        return ResponseEntity.created(URI.create("/api/locations/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@PathVariable UUID id, @RequestBody Location body) {
        return repository.findById(id).map(existing -> {
            body.setId(id);
            Location saved = repository.save(body);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}