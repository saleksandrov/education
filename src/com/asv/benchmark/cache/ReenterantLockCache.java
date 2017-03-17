package com.asv.benchmark.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by SBT-Aleksandrov-SV on 17.03.2017.
 */
public class ReenterantLockCache <K, V> {

    private Map<K, V> cache = new HashMap<>();

    ReentrantLock lock = new ReentrantLock();

    public void put(K key, V value) {

        lock.lock();
        try {
            cache.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    public V get(K key) {

        lock.lock();
        try {
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }

    public V get(K key, V value) {

        lock.lock();
        try {
            if (!cache.containsKey(key)) {
                cache.put(key, value);
                return value;
            }
            return cache.get(key);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

    }
}
