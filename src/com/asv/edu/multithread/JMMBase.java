package com.asv.edu.multithread;

/**
 * @author alexandrov
 * @since 01.04.2016
 */
public class JMMBase {

    private boolean flag = true;

    public static void main(String[] args) {

        JMMBase base = new JMMBase();
        base.startTask();

    }

    private void startTask() {
        new Thread(new MyTask()).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new StopTask()).start();
    }


    class MyTask implements Runnable {

        @Override
        public void run() {
            while (flag) {
                System.out.println("My Task");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class StopTask implements Runnable {

        @Override
        public void run() {
            flag = false;
        }
    }

}
