package com.testing.ground.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Custom input class
class Item {
    int id;
    String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// Custom output class
class ProcessedItem {
    int id;
    String processedName;

    public ProcessedItem(int id, String processedName) {
        this.id = id;
        this.processedName = processedName;
    }

    @Override
    public String toString() {
        return "ProcessedItem{id=" + id + ", processedName='" + processedName + "'}";
    }
}

public class ItemProcessorV2 {

    // Step 1: Collect a list of items
    public static List<Item> collectItems() {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            items.add(new Item(i, "Item" + i));
        }
        return items;
    }

    // Step 2: Partition the list
    public static <T> List<List<T>> partitionList(List<T> items, int chunkSize) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < items.size(); i += chunkSize) {
            partitions.add(items.subList(i, Math.min(i + chunkSize, items.size())));
        }
        return partitions;
    }

    // Custom logic to process each item (simulate complex business logic)
    public static ProcessedItem processItem(Item item) throws InterruptedException {
        Thread.sleep(50); // simulate processing delay
        return new ProcessedItem(item.id, "Processed-" + item.name);
    }

    // Steps 3-10
    public static List<ProcessedItem> processItems(List<Item> items) throws InterruptedException {
        int chunkSize = 10;
        int poolSize = 10;
        int taskTimeoutSeconds = 5;

        List<List<Item>> partitions = partitionList(items, chunkSize);
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<Future<List<ProcessedItem>>> futures = new ArrayList<>();

        for (List<Item> group : partitions) {
            Callable<List<ProcessedItem>> task = () -> {
                List<ProcessedItem> result = new ArrayList<>();
                for (Item item : group) {
                    try {
                        result.add(processItem(item));
                    } catch (Exception e) {
                        System.err.println("Error processing item " + item.id + ": " + e.getMessage());
                    }
                }
                return result;
            };
            futures.add(executor.submit(task));
        }

        List<ProcessedItem> finalResults = new ArrayList<>();
        for (Future<List<ProcessedItem>> future : futures) {
            try {
                finalResults.addAll(future.get(taskTimeoutSeconds, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                System.err.println("Task timed out: " + e.getMessage());
            } catch (ExecutionException e) {
                System.err.println("Task execution failed: " + e.getCause());
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }
        }

        executor.shutdown();
        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
            executor.shutdownNow();
            System.err.println("Executor did not shut down cleanly.");
        }

        return finalResults;
    }

//    public static void main(String[] args) {
//        try {
//            List<Item> items = collectItems();
//            List<ProcessedItem> results = processItems(items);
//            System.out.println("Final Results:");
//            results.forEach(System.out::println);
//        } catch (Exception e) {
//            System.err.println("Fatal error: " + e.getMessage());
//        }
//    }
}
