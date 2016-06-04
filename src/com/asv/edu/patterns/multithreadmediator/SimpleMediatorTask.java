package com.asv.edu.patterns.multithreadmediator;

/**
 * Created by work on 04.06.2016.
 */
public class SimpleMediatorTask implements MediatorTask {
    
    public void handleEvent(Event event) {
        System.out.println("event.hashCode() = " + event.hashCode());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
