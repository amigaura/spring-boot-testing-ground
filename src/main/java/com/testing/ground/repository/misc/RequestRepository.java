package com.testing.ground.repository.misc;

import com.testing.ground.entity.misc.ApiRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<ApiRequest, String> {
    List<ApiRequest> findByStatus(String status);
}
