package com.testing.ground.service.user;

import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.AppUserSocietyMapping;
import com.testing.ground.repository.user.AppUserSocietyMappingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserSocietyMappingService {

    private final AppUserSocietyMappingRepository repository;

    @Autowired
    public AppUserSocietyMappingService(AppUserSocietyMappingRepository repository) {
        this.repository = repository;
    }

//    public List<AppUserSocietyMapping> getMappingsForUser(AppUser appUser) {
//        return repository.findByAppUser(appUser);
//    }

    public AppUserSocietyMapping save(AppUserSocietyMapping mapping) {
        return repository.save(mapping);
    }

//    public AppUserSocietyMapping

    public List<AppUserSocietyMapping> getMappingsForUser(AppUser appUser) {
        return repository.findByAppUserId(appUser.getId());
    }

    public AppUserSocietyMapping getMappingById(Long mappingId) {
        return repository.findById(mappingId)
                .orElseThrow(() -> new EntityNotFoundException("Mapping not found"));
    }

    public Optional<AppUserSocietyMapping> findById(Long id) {
        return repository.findById(id);
    }

    public List<AppUserSocietyMapping> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean existsMappingForUserAndSociety(AppUser appUser, Long societyId) {
        return repository.findByAppUser(appUser).stream()
                .anyMatch(mapping -> mapping.getSociety().getId().equals(societyId));
    }
}
