package com.asv.edu.multithread;


/**
 * Created by work on 26.06.2016.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        TL1.threadLocal.set("Test");

        Object o2 = TL2.threadLocal.get();
        Object o1 = TL1.threadLocal.get();

        System.out.println("o2 = " + o2);
        System.out.println("o1 = " + o1);

    }


    public static class TL1 {
        public static ThreadLocal threadLocal = new ThreadLocal();
    }

    public static class TL2 {
        public static ThreadLocal threadLocal = new ThreadLocal();
    }


}
