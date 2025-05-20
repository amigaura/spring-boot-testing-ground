package com.testing.ground.service;

import com.testing.ground.entity.ApiRequest;
import com.testing.ground.entity.Item;
import com.testing.ground.repository.ItemRepository;
import com.testing.ground.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AsyncProcessor {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Async
    public void processAsync(String requestId, List<Item> items) {
        updateStatus(requestId, "IN_PROGRESS");

        for (Item item : items) {
            try {
                // Simulate item processing
                Thread.sleep(100);

                // Save individual item result to another table if needed
                itemRepository.save(item);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        updateStatus(requestId, "COMPLETED");
    }

    private void updateStatus(String requestId, String status) {
        ApiRequest req = requestRepository.findById(requestId).orElseThrow();
        req.setStatus(status);
        req.setUpdatedAt(new Date(System.currentTimeMillis()));
        requestRepository.save(req);
    }
}

