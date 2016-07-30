package com.asv.edu.multithread;

/**
 * Created by work on 30.07.2016.
 */
public class ThreadBlock {

    static Integer data = new Integer(2);

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (data) {
                    while (true) {

                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (data) {
                    System.out.println("data = " + data);
                }
            }
        });

        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t2.state = " + t2.getState());

        t2.interrupt();


        System.out.println("t2.state after interrupt= " + t2.getState());
        System.out.println("t2.interrupt= " + t2.isInterrupted());

        t1.stop();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t1.state" + t1.getState());


    }



}
