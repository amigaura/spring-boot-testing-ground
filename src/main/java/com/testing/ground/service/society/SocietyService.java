package com.testing.ground.service.society;

import com.testing.ground.constant.society.SocietyStatus;
import com.testing.ground.entity.society.Society;
import com.testing.ground.repository.society.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SocietyService {

    @Autowired
    private SocietyRepository repository;

    public Society addSociety(Society society) {
        society.setCreatedDate(LocalDateTime.now());
        society.setStatus(SocietyStatus.ACTIVE);
        return repository.save(society);
    }

    public Optional<Society> getSocietyById(Long id) {
        return repository.findById(id);
    }

    public List<Society> searchSocietiesByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Society updateSociety(Long id, Society updated) {
        return repository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setAddress(updated.getAddress());
            existing.setCity(updated.getCity());
            existing.setState(updated.getState());
            existing.setCountry(updated.getCountry());
            existing.setPincode(updated.getPincode());
            existing.setContactNumber(updated.getContactNumber());
            existing.setEmail(updated.getEmail());
            existing.setWebsite(updated.getWebsite());
            existing.setLogoUrl(updated.getLogoUrl());
            existing.setDescription(updated.getDescription());
            existing.setLastUpdated(LocalDateTime.now());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Society not found"));
    }

    public Society suspendSociety(Long id) {
        return repository.findById(id).map(society -> {
            society.setStatus(SocietyStatus.SUSPENDED);
            society.setLastUpdated(LocalDateTime.now());
            return repository.save(society);
        }).orElseThrow(() -> new RuntimeException("Society not found"));
    }

    public void deleteSociety(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Society not found");
        }
        repository.deleteById(id);
    }
}

