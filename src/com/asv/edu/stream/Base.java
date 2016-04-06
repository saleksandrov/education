package com.asv.edu.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandrov
 * @since 01.04.2016
 */
public class Base {

    public static void main(String[] args) {

        List<Integer> calls = new ArrayList<>();

        //for (final String word : "Count characters using callable".split(" ")) {
        //    calls.add(word::length);
        //}

        for (int i = 0; i < 1_000_000; i++) {
            calls.add(i);
        }

        long startTime = System.currentTimeMillis();
        //long sum = getSumFromStream(calls);
        long sum = getSumFromLoop(calls);
        long endTime = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("(endTime - startTime) = " + (endTime - startTime));
    }

    private static long getSumFromStream(List<Integer> calls) {
        return calls.stream().mapToLong(Integer::intValue).sum();
    }

    private static long getSumFromLoop(List<Integer> calls) {
        long result = 0;
        for (Integer digit : calls) {
            result += digit.intValue();
        }
        return result;
    }

}
