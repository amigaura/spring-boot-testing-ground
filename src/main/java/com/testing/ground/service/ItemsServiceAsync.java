package com.testing.ground.service;

import com.google.gson.Gson;
import com.testing.ground.entity.ApiRequest;
import com.testing.ground.entity.Item;
import com.testing.ground.repository.RequestRepository;
import com.testing.ground.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ItemsServiceAsync {

    @Autowired
    private AsyncProcessor asyncProcessor;

    @Autowired
    private RequestRepository requestRepository;

    @Async
    public String processItemsAsync(List<Item> items) {

        String requestId = UUID.randomUUID().toString();
        ApiRequest request = new ApiRequest();
        request.setRequestId(requestId);
        request.setRequestData(JsonUtil.toJson(items));
        request.setStatus("PENDING");
        request.setCreatedAt(new Date(System.currentTimeMillis()));
        request.setUpdatedAt(new Date(System.currentTimeMillis()));

        requestRepository.save(request);
        asyncProcessor.processAsync(requestId, items);

        return requestId;
    }

    private void updateStatus(String requestId, String status) {
        ApiRequest req = requestRepository.findById(requestId).orElseThrow();
        req.setStatus(status);
        req.setUpdatedAt(new Date(System.currentTimeMillis()));
        requestRepository.save(req);
    }

}
