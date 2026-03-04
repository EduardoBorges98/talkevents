package com.talkevents.jpa.controllers;

import com.talkevents.jpa.entities.Attendee;
import com.talkevents.jpa.repositories.AttendeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    private final AttendeeRepository repository;

    public AttendeeController(AttendeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Attendee> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> get(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Attendee> create(@RequestBody Attendee attendee) {
        Attendee saved = repository.save(attendee);
        return ResponseEntity.created(URI.create("/api/attendees/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendee> update(@PathVariable UUID id, @RequestBody Attendee body) {
        return repository.findById(id).map(existing -> {
            body.setId(id);              // garante que atualiza o registro certo
            Attendee saved = repository.save(body);
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