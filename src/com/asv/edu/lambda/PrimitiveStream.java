package com.asv.edu.lambda;

import com.asv.util.PerformEstimator;

import java.util.ArrayList;
import java.util.Arrays;
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
        PerformEstimator.startEstimation();

        // This code is much more faster (include array construction loop) than lambda
        int[] intArray = new int[count];
        while (--count >= 0) {
            intArray[count] = count;
        }
        long sum = 0;
        int min = 0;
        int max = 0;
        int countStat = 0;
        for (int d : intArray) {
            sum += d;
            if (min > d) {
                min = d;
            }
            if (max < d) {
                max = d;
            }
            count++;
        }
        double average = sum/intArray.length;
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("Average = " + average);
        System.out.println("Sum = " + sum);

        PerformEstimator.printEstimationInMillis();

        PerformEstimator.startEstimation();

        IntSummaryStatistics intSummaryStatistics = Arrays.stream(intArray).summaryStatistics();
        System.out.println("intStream.getMax = " + intSummaryStatistics.getMax());
        System.out.println("intStream.getMin = " + intSummaryStatistics.getMin());
        System.out.println("intStream.getAverage = " + intSummaryStatistics.getAverage());
        System.out.println("intStream.getSum = " + intSummaryStatistics.getSum());

        PerformEstimator.printEstimationInMillis();
    }
}
