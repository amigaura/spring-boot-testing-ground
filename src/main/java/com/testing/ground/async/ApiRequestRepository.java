package com.testing.ground.async;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiRequestRepository extends JpaRepository<ApiRequest, String> {
    List<ApiRequest> findByStatus(String status);
}
