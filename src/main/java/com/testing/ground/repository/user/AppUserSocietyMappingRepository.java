package com.testing.ground.repository.user;

import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.AppUserSocietyMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserSocietyMappingRepository extends JpaRepository<AppUserSocietyMapping, Long> {

    List<AppUserSocietyMapping> findByAppUser(AppUser appUser);

    List<AppUserSocietyMapping> findByAppUserId(Long id);
}
