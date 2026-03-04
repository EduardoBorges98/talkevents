package com.talkevents.jpa.services;

import com.talkevents.jpa.entities.Location;
import com.talkevents.jpa.repositories.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findById(UUID id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Location update(UUID id, Location updated) {
        Location existing = findById(id);
        existing.setName(updated.getName());
        existing.setAdress(updated.getAdress());
        existing.setCapacity(updated.getCapacity());
        return locationRepository.save(existing);
    }

    public void delete(UUID id) {
        Location existing = findById(id);
        locationRepository.delete(existing);
    }
}