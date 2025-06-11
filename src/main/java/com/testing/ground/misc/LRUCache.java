package com.testing.ground.misc;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache {

    // Concurrent map to ensure thread safety for cache accesses
    private final ConcurrentHashMap<Integer, Integer> cache;
    private final LinkedHashMap<Integer, Long> accessOrder;
    private int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();

    // Constructor to initialize the cache with a specified capacity
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.cache = new ConcurrentHashMap<>();
        this.accessOrder = new LinkedHashMap<>();
        this.maxCapacity = capacity;
    }

    // Adds a key-value pair to the cache
    public void put(int key, int value) {
        lock.lock();
        try {
            if (cache.size() >= maxCapacity) {
                evict();
            }
            cache.put(key, value);
            accessOrder.put(key, System.nanoTime());  // Track access time to evict the least recently used item
        } finally {
            lock.unlock();
        }
    }

    // Retrieves a value by key from the cache
    public int get(int key) {
        lock.lock();
        try {
            if (!cache.containsKey(key)) {
                return -1;  // If key doesn't exist, return -1
            }
            accessOrder.put(key, System.nanoTime());  // Update the access time to mark it as recently used
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }

    // Sets a new capacity for the cache
    public void setCapacity(int newCapacity) {
        lock.lock();
        try {
            if (newCapacity <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than 0");
            }
            if (newCapacity < maxCapacity) {
                while (cache.size() > newCapacity) {
                    evict();
                }
            }
            // Update capacity
            this.maxCapacity = newCapacity;
        } finally {
            lock.unlock();
        }
    }

    // Evicts the least recently used entry from the cache
    private void evict() {
        // Find the key with the earliest access time (LRU)
        long oldest = Long.MAX_VALUE;
        int keyToEvict = -1;
        for (Map.Entry<Integer, Long> entry : accessOrder.entrySet()) {
            if (entry.getValue() < oldest) {
                oldest = entry.getValue();
                keyToEvict = entry.getKey();
            }
        }
        if (keyToEvict != -1) {
            cache.remove(keyToEvict);
            accessOrder.remove(keyToEvict);
        }
    }

    // Returns the current size of the cache
    public int size() {
        return cache.size();
    }

    public static void main(String[] args) {
        // Testing the LRU Cache implementation
        LRUCache cache = new LRUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);

        System.out.println(cache.get(1));  // Returns 1 (LRU access)
        cache.put(4, 4);  // Evicts key 2
        System.out.println(cache.get(2));  // Returns -1 (not found)

        cache.setCapacity(5);  // Resizes the cache
        cache.put(5, 5);

        System.out.println(cache.size());  // Returns 4
    }
}

