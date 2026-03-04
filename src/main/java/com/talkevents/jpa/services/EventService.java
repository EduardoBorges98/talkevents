package com.talkevents.jpa.services;

import com.talkevents.jpa.entities.Event;
import com.talkevents.jpa.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(UUID id, Event updated) {
        Event existing = findById(id);
        existing.setName(updated.getName());
        existing.setDate(updated.getDate());
        return eventRepository.save(existing);
    }

    public void delete(UUID id) {
        Event existing = findById(id);
        eventRepository.delete(existing);
    }
}