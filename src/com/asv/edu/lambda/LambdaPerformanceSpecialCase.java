package com.asv.edu.lambda;

import com.asv.util.PerformEstimator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author alexandrov
 * @since 15.06.2016
 */
public class LambdaPerformanceSpecialCase {

    static long count = 0;

    public static void main(String[] args) {
        int count = 100_000;

        List<Integer> etalonList = new ArrayList<>(count);
        while (count-- > 0) {
            etalonList.add(count);
        }
        int count2 = 30_000;

        List<Integer> subList = new ArrayList<>(count2);
        while (count2-- > 0) {
            subList.add(count2+15_056);
        }

        System.out.println("Start work");
        System.out.println("etalonList.size() = " + etalonList.size());
        System.out.println("subList.size() = " + subList.size());

        PerformEstimator.startEstimation();
        case1(etalonList, subList);
        PerformEstimator.printEstimationInMillis();
    }


    /**
     * O(n*m) performance
     *
     * @param etalonList
     * @param subList
     */
    public static void case1(List<Integer> etalonList, List<Integer> subList) {
        //Predicate<Integer> hasElem = c -> etalonList.stream().anyMatch(u -> u.equals(c));
        Predicate<Integer> hasElem = c -> etalonList.stream().anyMatch(u -> equals(u,c));

        List<Integer> res = subList.stream().filter(hasElem).collect(Collectors.toList());
        System.out.println(res.size());
        System.out.println("Count = " + count);
    }

    /**
     * This is MORE fast than case1
     *
     * @param etalonList
     * @param subList
     */
    public static void case2(List<Integer> etalonList, List<Integer> subList) {
        Set<Integer> acceptableInt = etalonList.stream().collect(Collectors.toSet());

        List<Integer> res = subList.stream().filter(c -> acceptableInt.contains(c)).collect(Collectors.toList());
        System.out.println(res.size());
    }

    /**
     * Технический метод. Используется для подсчета количества итераций в алогитме.
     *
     * @param u
     * @param c
     * @return
     */
    public static boolean equals(Integer u, Integer c) {
        count++;
        return u.equals(c);
    }
}
