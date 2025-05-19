package com.testing.ground.sync.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.testing.ground.sync.entity.ItemRecord;
import com.testing.ground.sync.model.ProcessingResult;
import com.testing.ground.sync.repository.ItemRecordRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class ItemProcessorService {

    private final ExecutorService executor = Executors.newFixedThreadPool(20);
    private final Cache<String, ProcessingResult> resultCache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(10))
            .maximumSize(1000)
            .build();

    @Autowired
    private ItemRecordRepository recordRepository;
    @Autowired private MeterRegistry meterRegistry;

    public List<ProcessingResult> processItems(List<String> items) throws InterruptedException {
        List<Callable<ProcessingResult>> tasks = items.stream()
                .map(item -> (Callable<ProcessingResult>) () -> processWithTimeout(item))
                .collect(Collectors.toList());

        List<Future<ProcessingResult>> futures = executor.invokeAll(tasks);

        List<ProcessingResult> results = new ArrayList<>();
        for (Future<ProcessingResult> future : futures) {
            try {
                ProcessingResult result = future.get(3, TimeUnit.SECONDS); // per-item timeout
                results.add(result);
            } catch (TimeoutException | ExecutionException e) {
                results.add(new ProcessingResult("FAILED", e.getMessage()));
            }
        }

        return results;
    }

    private ProcessingResult processWithTimeout(String item) {
        // Check cache first
        ProcessingResult cached = resultCache.getIfPresent(item);
        if (cached != null) return cached;

        try {
            // Simulate processing
            Thread.sleep(2000 + new Random().nextInt(1000));

            ProcessingResult result = new ProcessingResult("SUCCESS", null);
            resultCache.put(item, result);

            // Save to DB
            recordRepository.save(new ItemRecord(item, result.getStatus()));

            // Metrics
            meterRegistry.counter("item.processed", "status", "SUCCESS").increment();

            return result;
        } catch (Exception e) {
            meterRegistry.counter("item.processed", "status", "FAILED").increment();
            return new ProcessingResult("FAILED", e.getMessage());
        }
    }
}

