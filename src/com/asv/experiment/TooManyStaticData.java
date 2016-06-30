package com.asv.experiment;

import com.asv.util.PerformEstimator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandrov
 * @since 29.06.2016
 */
public class TooManyStaticData {

    public static final int count = 10_000_000;

    private static long[] data1;
    private static long[] data2;
    private static long[] data3;

    private static StringBuilder dataString = new StringBuilder();


    public static void main(String[] args) {
        PerformEstimator.startEstimation();
        experimentThree();
        PerformEstimator.printEstimationInMillis();
    }

    private static void experimentThree() {
        // OutOfMemoryError Java Heap Space (count = 10_000_000)
        BigDecimal bigDecimal;
        List<BigDecimal> data = new ArrayList<>();
        for (int i = 0; i < count; i ++) {
            data.add(new BigDecimal(i));
        }

        System.out.println("data.size: " + data.size());
    }

    private static void experimentTwo() {
        // OutOfMemoryError Java Heap Space (count = 200_000)
        for (int i = 0; i < count; i ++) {
            dataString.append(" This is a test data This is a test data This is a test data This is a test data This is a " +
                    "test data " +
                    "This is a test data This is a test data This is a test data This is a test data This is a test data " +
                    "This is a test data This is a test data This is a test data This is a test data This is a test data " +
                    "This is a test data This is a test data This is a test data This is a test data This is a test data ");
        }

        System.out.println("dataString.length in kb = " + dataString.length()/1024);
    }

    private static void experimentOne() {
        // OutOfMemoryError Java Heap Space (count = 9_000_000)
        data1 = new long[count];
        fillArray(data1);

        data2 = new long[10*count];
        fillArray(data2);

        data3 = new long[count];
        fillArray(data3);
    }

    private static void fillArray(long[] data) {
        for (int i = 0; i < count; i ++) {
            data[i] = i;
        }
    }


}
