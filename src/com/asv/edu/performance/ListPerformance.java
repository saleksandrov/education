package com.asv.edu.performance;

import com.asv.util.PerformEstimator;

import java.util.*;

/**
 * @author alexandrov
 * @since 16.09.2016
 */
public class ListPerformance {

    private static int LIST_SIZE = 14_000_000;

    // -Xmx1000M
    public static void main(String[] args) {

        List<Integer> linkedListData = fillList(new LinkedList<Integer>());
        List<Integer> arrayListListData = fillList(new ArrayList<Integer>());

        // ArrayList in such case i much much more slower
        PerformEstimator.startEstimation();
        testList(arrayListListData);
        PerformEstimator.printEstimationInMillis();

    }

    private static void testList(List<Integer> listData) {
        int deleted = 0;
        int totalIteration = 0;
        Iterator<Integer> iterator = listData.iterator();
        while (iterator.hasNext()) {
            Integer currentInt = iterator.next();
            if (currentInt % 100 == 0) {
                deleted++;
                iterator.remove();
            }
            totalIteration++;
        }
        System.out.println("Deleted " + deleted);
        System.out.println("Total " + totalIteration);
    }

    private static List<Integer> fillList(List<Integer> list) {
        int currentCount = 0;
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(currentCount++);
        }
        return list;
    }

}
