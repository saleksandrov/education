package com.asv.edu.patterns.observer;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * @author Sergey Aleksandrov
 */
public class Base {

    public static void main(String[] args) {

        AtomicInteger totalAttempt = new AtomicInteger(3);

        TimeObservable timeObservable = new TimeObservable();
        timeObservable.addObserver(new TimeObserverImpl());

        Thread timeThread = new Thread(() -> {
            while (totalAttempt.get() > 0) {
                LocalTime time = LocalTime.now();
                int sec = time.getSecond();
                if (sec % 10 == 0) {
                    timeObservable.tenSeconds(sec);
                    totalAttempt.getAndDecrement();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        timeThread.start();

        System.out.println("END");
    }


}
