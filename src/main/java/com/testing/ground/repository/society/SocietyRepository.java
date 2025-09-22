package com.testing.ground.repository.society;

import com.testing.ground.entity.society.Society;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {
    List<Society> findByNameContainingIgnoreCase(String name);
}

