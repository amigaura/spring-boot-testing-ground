package com.testing.ground.misc;

import java.util.ArrayList;
import java.util.List;

public class BucketDivider {

    public static <T> List<List<T>> divideIntoBuckets(List<T> items, int bucketCount) {
        List<List<T>> buckets = new ArrayList<>();
        int totalItems = items.size();
        int baseSize = totalItems / bucketCount;
        int remainder = totalItems % bucketCount;

        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            int currentBucketSize = baseSize + (i < remainder ? 1 : 0);
            List<T> bucket = new ArrayList<>();

            for (int j = 0; j < currentBucketSize && index < totalItems; j++) {
                bucket.add(items.get(index++));
            }

            buckets.add(bucket);
        }

        return buckets;
    }

    public static void main(String[] args) {
        // Sample list of 103 items (e.g., strings "Item 1", "Item 2", ...)
        List<String> items = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            items.add("Item " + i);
        }

        int bucketCount = 10;
        List<List<String>> buckets = divideIntoBuckets(items, bucketCount);

        // Display buckets
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println("Bucket " + (i + 1) + " (" + buckets.get(i).size() + " items): " + buckets.get(i));
        }
    }
}
