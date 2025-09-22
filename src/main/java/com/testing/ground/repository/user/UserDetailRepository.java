package com.testing.ground.repository.user;

import com.testing.ground.entity.user.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    Optional<UserDetail> findByEmail(String email);
    Optional<UserDetail> findByPhoneNumber(String phoneNumber);
    Page<UserDetail> findByNameContainingIgnoreCase(String name, Pageable pageable);

}

