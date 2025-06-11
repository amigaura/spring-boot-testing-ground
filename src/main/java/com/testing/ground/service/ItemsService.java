package com.testing.ground.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.testing.ground.entity.Item;
import com.testing.ground.model.ProcessingResult;
import com.testing.ground.repository.ItemRepository;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class ItemsService {

    private final ExecutorService executor = Executors.newFixedThreadPool(20);
    private final Cache<String, ProcessingResult> resultCache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(10))
            .maximumSize(1000)
            .build();

    @Autowired
    private ItemRepository itemRepository;

//    @Autowired
//    private MeterRegistry meterRegistry;

    @Transactional
    public List<ProcessingResult> processItems(List<Item> items) throws InterruptedException {
        System.out.println("Processing items: " + items.size());
        if (items.isEmpty()) {
            System.out.println("No items to process");
            return new ArrayList<>();
        }
        List<Callable<ProcessingResult>> tasks = items.stream()
                .map(item -> (Callable<ProcessingResult>) () -> {
                    // Persist the item before processing
                    Item savedItem = itemRepository.save(item);
                    return processWithTimeout(savedItem);
                })
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

    private ProcessingResult processWithTimeout(Item item) {
        // Check cache first
        ProcessingResult cached = resultCache.getIfPresent(item.getName());
        if (cached != null) return cached;

        try {
            // Simulate processing
            Thread.sleep(2000 + new Random().nextInt(1000));

            ProcessingResult result = new ProcessingResult("SUCCESS", null);
            resultCache.put(item.getName(), result);

            // Save to DB
            Item savedItem = itemRepository.save(item);
            System.out.println("Saved item: " + savedItem);

            // Metrics
//            meterRegistry.counter("item.processed", "status", "SUCCESS").increment();

            return result;
        } catch (Exception e) {
//            meterRegistry.counter("item.processed", "status", "FAILED").increment();
            return new ProcessingResult("FAILED", e.getMessage());
        }
    }
}

