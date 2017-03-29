package com.asv.benchmark.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by SBT-Aleksandrov-SV on 29.03.2017.
 */
public class NewFastCache<K, V> {

    private Map<K, V> cache = new HashMap<>();

    StampedLock lock = new StampedLock();

    public void put(K key, V value) {
        lock.asWriteLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lock.asWriteLock().unlock();
        }
    }

    public V get(K key) {
        lock.asReadLock().lock();
        try {
            return cache.get(key);
        } finally {
            lock.asReadLock().unlock();
        }
    }

    /**
     * Fast implementation of cache
     *
     * @param key
     * @param value
     * @return
     */
    public V get(K key, V value) {
        lock.asReadLock().lock();
        try {
            if (!cache.containsKey(key)) {
                put(key, value);
                return value;
            }
            return cache.get(key);
        } finally {
            lock.asReadLock().unlock();
        }
    }


}
