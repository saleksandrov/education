package com.asv.benchmark.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by SBT-Aleksandrov-SV on 16.03.2017.
 */
public class FastCache<K, V> {

    private Map<K, V> cache = new HashMap<>();

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock rLock = lock.readLock();
    Lock wLock = lock.writeLock();


    public void put(K key, V value) {
        wLock.lock();
        try {
            cache.put(key, value);
        } finally {
            wLock.unlock();
        }
    }

    public V get(K key) {
        rLock.lock();
        try {
            return cache.get(key);
        } finally {
            rLock.unlock();
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
        rLock.lock();
        try {
            if (!cache.containsKey(key)) {
                put(key, value);
                return value;
            }
            return cache.get(key);
        } finally {
            rLock.unlock();
        }
    }

    /**
     * Etalon implementation of cache method
     *
     * @param key
     * @param value
     * @return
     */
    public V getEtalon(K key, V value) {
        rLock.lock();
        if (!cache.containsKey(key)) {
            rLock.unlock();
            wLock.lock();
            try {
                if (!cache.containsKey(key)) {
                    cache.put(key, value);
                    return value;
                } else {
                    rLock.lock();
                }
            } finally {
                wLock.unlock();
            }
            return value;
        }

        try {
            return cache.get(key);
        } finally {
            rLock.unlock();
        }
    }

}
