package com.asv.edu.multithread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author alexandrov
 * @since 08.08.2016
 */
public class PrimitiveSync {

    public static void main(String[] args) {

        final Counter counter = new CounterVolatile();
        AtomicInteger threadTerminated = new AtomicInteger(0);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(40));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.increment();
                counter.getValue();
                threadTerminated.incrementAndGet();

            }
        };

        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        int count = threadCount;
        while (--count >= 0) {
            executorService.execute(task);
        }

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        System.out.println("counter = " + counter.getValue());
        System.out.println("threadTerminated = " + threadTerminated);


    }

    interface Counter {

        long getValue();
        long increment();
    }

    static class CounterNotSync implements Counter {

        long value = 0;

        @Override
        public long getValue() {
            return value;
        }

        @Override
        public long increment() {
            return ++value;
        }
    }

    static class CounterAtomic implements Counter {

        AtomicLong value = new AtomicLong(0);

        @Override
        public long getValue() {
            return value.longValue();
        }

        @Override
        public long increment() {
            return value.incrementAndGet();
        }
    }

    static class CounterVolatile implements Counter {

        volatile long value = 0;

        @Override
        public long getValue() {
            return value;
        }

        @Override
        public synchronized long increment() {
            return ++value;
        }
    }


}
