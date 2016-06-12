package com.asv.util;

import java.util.Deque;
import java.util.concurrent.*;

/**
 * Class for measure performance of the application. Class is thread safe but it requires to invoke methods in specific order.
 * This class uses LIFO principles in method invocation start and get.
 *
 *
 * @author Sergey Aleksandrov
 * @since 12.06.2016.
 */
public class PerformEstimator {

    private static Deque<Long> queue = new ConcurrentLinkedDeque<>();

    /**
     * Start measure of work time. Can be invoked more than one time. Each invocation of method add new start time metric to queue.
     *
     *
     */
    public static void startEstimation() {
        queue.add(System.currentTimeMillis());
    }

    /**
     * Return work time in Milliseconds for last call of @method startEstimation.
     *
     * @return work time in Milliseconds or -1 if no estimation exists (in case this method was invoked more time than @method startEstimation)
     */
    public static int getEstimationInMillis() {
        long end = System.currentTimeMillis();
        Long start = queue.pollLast();
        if (start != null) {
            return (int) (end - start.longValue());
        }
        return - 1;
    }

    public static void main(String[] args) {
        Deque<Long> queue = new ConcurrentLinkedDeque<>();
        queue.add(1L);
        queue.add(2L);
        queue.add(3L);
        //queue.add(4L);

        long l1 = queue.pollLast();
        long l2 = queue.pollLast();
        long l3 = queue.pollLast();
        Long l4 = queue.pollLast();
        //long l1 = queue.pollLast();


        System.out.println("queue.size() = " + queue.size());
    }

}
