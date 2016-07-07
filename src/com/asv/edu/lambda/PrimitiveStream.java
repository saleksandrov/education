package com.asv.edu.lambda;

import com.asv.util.PerformEstimator;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author alexandrov
 * @since 07.07.2016
 */
public class PrimitiveStream {

    public static void main(String[] args) {
        testIntStream();
    }

    private static void testIntSummaryStatictics() {
        List<Integer> listInteger = new ArrayList<>();
        listInteger.add(1);
        listInteger.add(2);
        listInteger.add(3);
        listInteger.add(4);
        listInteger.add(5);

        IntStream intStream = listInteger.stream().mapToInt(x -> x);

        IntSummaryStatistics intSummaryStatistics = intStream.summaryStatistics();
        System.out.println("intStream.getMax = " + intSummaryStatistics.getMax());
        System.out.println("intStream.getMin = " + intSummaryStatistics.getMin());
    }

    private static void testIntStream() {
        int count = 100_000_000;
        int[] intArray = new int[count];
        while (--count >= 0) {
            intArray[count] = count;
        }

        PerformEstimator.startEstimation();

        // This code is more performant than lambda
/*
        long sum = 0;
        int min = 0;
        int max = 0;
        double average = 0.0;
        for (int d : intArray) {
            sum += d;
            if (min > d) {
                min = d;
            }
            if (max < d) {
                max = d;
            }
        }
        average = sum/intArray.length;
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("Average = " + average);
        System.out.println("Sum = " + sum);
*/


        IntSummaryStatistics intSummaryStatistics = IntStream.of(intArray).summaryStatistics();
        System.out.println("intStream.getMax = " + intSummaryStatistics.getMax());
        System.out.println("intStream.getMin = " + intSummaryStatistics.getMin());
        System.out.println("intStream.getAverage = " + intSummaryStatistics.getAverage());
        System.out.println("intStream.getSum = " + intSummaryStatistics.getSum());


        PerformEstimator.printEstimationInMillis();
    }
}
