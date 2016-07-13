package com.asv.edu.lambda;

import com.asv.util.PerformEstimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * @author alexandrov
 * @since 13.07.2016
 */
public class Square {

    public static void main(String[] args) {
        //Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //LinkedList<Integer> linkedList = integerStream.collect(toCollection(LinkedList<Integer>::new));

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        int count = 1_000_000;
        while (--count > 0) {
            linkedList.add(count);
        }

        PerformEstimator.startEstimation();

        int val = versionThree(linkedList);

        PerformEstimator.printEstimationInMillis();

        System.out.println("Result(385) = " + val);

    }

    private static int versionOne(LinkedList<Integer> linkedList) {
        return linkedList.parallelStream().map(x -> x * x).reduce(0, (acc, x) -> acc + x);
    }

    private static int versionTwo(LinkedList<Integer> linkedList) {
        List<Integer> list = new ArrayList<>(linkedList);
        return list.stream().map(x -> x * x).reduce(0, (acc, x) -> acc + x);
    }

    // This method is more efficient
    private static int versionThree(LinkedList<Integer> linkedList) {
        return linkedList.stream().mapToInt(x -> x * x).parallel().reduce(0, (acc, x) -> acc + x);
    }


}
