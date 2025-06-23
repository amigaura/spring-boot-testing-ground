package com.testing.ground.job;

import com.google.gson.reflect.TypeToken;
import com.testing.ground.entity.misc.ApiRequest;
import com.testing.ground.entity.misc.Item;
import com.testing.ground.repository.misc.RequestRepository;
import com.testing.ground.service.misc.AsyncProcessor;
import com.testing.ground.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecoveryJob {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AsyncProcessor asyncProcessor;

    @Scheduled(fixedDelay = 60000) // every 60 seconds
    public void retryUnfinishedRequests() {
        List<ApiRequest> unfinished = requestRepository.findByStatus("IN_PROGRESS");

        for (ApiRequest req : unfinished) {
//            List<Item> items = new Gson().fromJson(req.getRequestData(), new TypeToken<List<String>>(){}.getType());
            List<Item> items = JsonUtil.fromJson(req.getRequestData(), (Class<List<Item>>) new TypeToken<List<Item>>(){}.getType());
            asyncProcessor.processAsync(req.getRequestId(), items);
        }
    }
}

