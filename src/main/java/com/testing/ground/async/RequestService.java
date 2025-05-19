package com.testing.ground.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RequestService {

    @Autowired
    private ApiRequestRepository requestRepository;

    @Autowired
    private AsyncProcessor asyncProcessor;

    public String handleIncomingRequest(List<String> items) {
        String requestId = UUID.randomUUID().toString();
        ApiRequest request = new ApiRequest();
        request.setRequestId(requestId);
        request.setRequestData(new Gson().toJson(items));
        request.setStatus("PENDING");
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());

        requestRepository.save(request);
        asyncProcessor.processAsync(requestId, items);

        return requestId;
    }
}
