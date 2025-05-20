package com.testing.ground.repository;

import com.testing.ground.entity.ApiRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<ApiRequest, String> {
    List<ApiRequest> findByStatus(String status);
}
