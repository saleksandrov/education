package com.asv.edu.multithread;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJonTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        ConcurrentLinkedDeque<Integer> data =
                new ConcurrentLinkedDeque<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));


        ForkJoinTask task1 = new RecursiveAction() {

            @Override
            protected void compute() {
                System.out.println("element = " + data.poll() );
            }
        };

        ForkJoinTask task2 = new RecursiveAction() {

            @Override
            protected void compute() {
                System.out.println("element = " + data.poll() );
            }
        };

        pool.invoke(task1);
        pool.invoke(task2);
    }

}
