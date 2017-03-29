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

    public V get2(K key, V value) {

        boolean wasWritten = false;

        long stamp;
        V v = null;
        //OPTIMISTIC READ
        if ((stamp = lock.tryOptimisticRead()) != 0L) { // optimistic
            if (!cache.containsKey(key)) {
                wasWritten = true;
            }
            else {

                v = cache.get(key);
                if (lock.validate(stamp))
                    return v;

            }
        }
        // READ PESSIMISTIC
        if(!wasWritten) {
            stamp = lock.readLock(); // fall back to read lock
            try {
                return cache.get(key);
            } finally {
                lock.unlockRead(stamp);
            }
        }//WRITE
        else{

            stamp = lock.writeLock();
            try {
                put(key, value);

                return value;//cache.get(key);
            } finally {
                lock.unlockWrite(stamp);
            }
        }

    }



}
