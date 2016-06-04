package com.asv.edu.patterns.multithreadmediator;

/**
 * Created by work on 04.06.2016.
 */
public class Base {

    public static void main(String[] args) {

        Thread adminThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        int ac = Thread.activeCount();
                        System.out.println("activeCount = " + ac);
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        Mediator mediator = new MultiThreadMediator();
        mediator.addTask(new SimpleMediatorTask());

        Client client = new Client();
        client.mediator = mediator;

        adminThread.start();
        int attemps = 1000;
        while (attemps-- > 0) {
            client.doSomethimg();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mediator.stop();
        adminThread.interrupt();

    }
    
    static class Client {
        
        public Mediator mediator;
        
        public void doSomethimg() {
            mediator.sendEvent(new Event());
        }
        
        
    }

}
