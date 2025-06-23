package com.testing.ground.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ItemProcessor {

    // Step 1: Collect a list of items (for demo, using integers)
    public static List<Integer> collectItems() {
        List<Integer> items = new ArrayList<>();
        for (int i = 1; i <= 61; i++) {
            items.add(i); // simulate collection
        }
        return items;
    }

    // Step 2: Break list into chunks of 10
    public static List<List<Integer>> partitionList(List<Integer> items, int chunkSize) {
        List<List<Integer>> partitions = new ArrayList<>();
        for (int i = 0; i < items.size(); i += chunkSize) {
            partitions.add(items.subList(i, Math.min(i + chunkSize, items.size())));
        }
        return partitions;
    }

    // Step 3 to 10: Process each group in a thread, collect and merge results
    public static List<String> processItems(List<Integer> items) throws InterruptedException, ExecutionException {
        int chunkSize = 10;
        int poolSize = 10;

        List<List<Integer>> partitions = partitionList(items, chunkSize);

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<Future<List<String>>> futures = new ArrayList<>();

        for (List<Integer> group : partitions) {
            Callable<List<String>> task = () -> {
                List<String> result = new ArrayList<>();
                for (Integer item : group) {
                    // Simulate processing
                    result.add("Processed-" + item);
                }
                return result;
            };
            futures.add(executor.submit(task));
        }

        List<String> finalResults = new ArrayList<>();
        for (Future<List<String>> future : futures) {
            finalResults.addAll(future.get()); // Step 8 and 9
        }

        executor.shutdown(); // Step 7: Wait until all tasks are done
        executor.awaitTermination(1, TimeUnit.MINUTES);

        return finalResults; // Step 10
    }

//    public static void main(String[] args) {
//        try {
//            List<Integer> items = collectItems(); // Step 1
//            List<String> results = processItems(items);
//            System.out.println("Final Results:");
//            results.forEach(System.out::println);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

