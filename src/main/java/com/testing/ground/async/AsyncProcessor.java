package com.testing.ground.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AsyncProcessor {

    @Autowired
    private ApiRequestRepository requestRepository;

    @Async
    public void processAsync(String requestId, List<String> items) {
        updateStatus(requestId, "IN_PROGRESS");

        for (String item : items) {
            try {
                // Simulate item processing
                Thread.sleep(100);
                // Save individual item result to another table if needed
            } catch (Exception e) {
                // log failure, optionally store per-item error
            }
        }

        updateStatus(requestId, "COMPLETED");
    }

    private void updateStatus(String requestId, String status) {
        ApiRequest req = requestRepository.findById(requestId).orElseThrow();
        req.setStatus(status);
        req.setUpdatedAt(LocalDateTime.now());
        requestRepository.save(req);
    }
}
