package com.testing.ground.async;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecoveryJob {

    @Autowired
    private ApiRequestRepository requestRepository;

    @Autowired
    private AsyncProcessor asyncProcessor;

    @Scheduled(fixedDelay = 60000) // every 60 seconds
    public void retryUnfinishedRequests() {
        List<ApiRequest> unfinished = requestRepository.findByStatus("IN_PROGRESS");

        for (ApiRequest req : unfinished) {
            List<String> items = new Gson().fromJson(req.getRequestData(), new TypeToken<List<String>>(){}.getType());
            asyncProcessor.processAsync(req.getRequestId(), items);
        }
    }
}

