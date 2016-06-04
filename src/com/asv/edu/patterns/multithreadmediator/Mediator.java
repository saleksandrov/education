package com.asv.edu.patterns.multithreadmediator;

/**
 * Created by work on 04.06.2016.
 */
public interface Mediator {

    void sendEvent(Event event);

    void addTask(MediatorTask task);

    void stop();

}
