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

    public FastCache() {

    }

    public void put(K key, V value) {
        //Lock writeLock = this.lock.writeLock();
        wLock.lock();
        try {
            cache.put(key, value);
        } finally {
            wLock.unlock();
        }
    }

    public V get(K key) {
        //Lock readLock = this.lock.readLock();
        rLock.lock();
        try {
            return cache.get(key);
        } finally {
            rLock.unlock();
        }
    }

    public V get(K key, V value) {
        //Lock readLock = this.lock.readLock();
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

    public static void main(String[] args) {

    }


}
