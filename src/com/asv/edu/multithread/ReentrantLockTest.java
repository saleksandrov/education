package com.asv.edu.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Created by work on 31.07.2016.
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        ReentrantLock lock  = new ReentrantLock();

        Runnable r = () -> {
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    //lock.lock();
                    System.out.println("got lock");
                    Thread.sleep(10000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("unlock");
                lock.unlock();
            }
        };

        Runnable r2 = () -> {
            boolean locked = false;
            try {
                if (lock.tryLock(15, TimeUnit.SECONDS)) {
                    System.out.println("got lock2");
                    locked = true;
                } else {
                    System.out.println("Cannot get lock" );
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (locked) {
                    lock.unlock();
                    System.out.println("unlock2");
                }
            }
        };


        new Thread(r).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r2).start();



    }

}
