package com.asv.edu.multithread;

/**
 * @author alexandrov
 * @since 01.04.2016
 */
public class JMMTest2 {

    static int data = 0;
    static boolean run = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                data = 1;
                run = false;
            }
        }).start();

        while (run) {

        }

        System.out.println(data);
    }

}
