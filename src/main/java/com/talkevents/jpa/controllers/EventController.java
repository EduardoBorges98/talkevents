package com.talkevents.jpa.controllers;

import com.talkevents.jpa.entities.Event;
import com.talkevents.jpa.repositories.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Event> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event saved = repository.save(event);
        return ResponseEntity.created(URI.create("/api/events/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable UUID id, @RequestBody Event body) {
        return repository.findById(id).map(existing -> {
            body.setId(id);
            Event saved = repository.save(body);
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