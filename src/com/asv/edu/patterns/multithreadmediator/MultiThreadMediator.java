package com.asv.edu.patterns.multithreadmediator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by work on 04.06.2016.
 */
public class MultiThreadMediator implements Mediator {

    private List<MediatorTask> tasks = new ArrayList<>();
    private ExecutorService es = Executors.newFixedThreadPool(1000);

    public void addTask(MediatorTask task) {
        tasks.add(task);
    }

    public void sendEvent(Event event) {

        tasks.stream().forEach((mediatorTask) -> es.execute(
                () -> mediatorTask.handleEvent(event))
        );

        /* Old Code
        Iterator<MediatorTask> it = tasks.iterator();
        while (it.hasNext()) {
            MediatorTask mediatorTask = it.next();
            es.execute(new Runnable() {
                @Override
                public void run() {
                    mediatorTask.handleEvent(event);
                }
            });
        }
        */
    }

    public void stop() {
        es.shutdown();
    }


}
