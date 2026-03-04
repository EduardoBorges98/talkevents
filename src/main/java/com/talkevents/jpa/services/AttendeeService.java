package com.talkevents.jpa.services;

import com.talkevents.jpa.entities.Attendee;
import com.talkevents.jpa.repositories.AttendeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    public Attendee findById(UUID id) {
        return attendeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendee not found with id: " + id));
    }

    public Attendee save(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public Attendee update(UUID id, Attendee updated) {
        Attendee existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        return attendeeRepository.save(existing);
    }

    public void delete(UUID id) {
        Attendee existing = findById(id);
        attendeeRepository.delete(existing);
    }
}