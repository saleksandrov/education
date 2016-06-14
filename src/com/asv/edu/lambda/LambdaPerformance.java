package com.asv.edu.lambda;

import com.asv.util.PerformEstimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by work on 12.06.2016.
 */
public class LambdaPerformance {

    public static void main(String[] args) {
        int count = 50_000_000;

        List<Integer> etalonList = new ArrayList<>(count);
        while (count-- > 0) {
            etalonList.add(count);
        }

        PerformEstimator.startEstimation();

        //iterateList(etalonList);
        iterateListByLambdaSecond(etalonList);
        //iterateListSecond(etalonList);

        System.out.println("PerformEstimator.getEstimationInMillis() = " + PerformEstimator.getEstimationInMillis());
    }

    public static void iterateList(List<?> data) {
        int count = 0;
        for (Object obj: data) {
            count++;
        }
        System.out.println("count = " + count);
    }

    public static void iterateListSecond(List<Integer> data) {
        for (Integer obj: data) {
            doIt(obj);
        }
    }

    /**
     * Производительность этой итераций по Stream намного ниже обычного foreach
     *
     */
    public static void iterateListByLambda(List<?> data) {
        int count = 0;
        long count1 = data.stream().count();
        System.out.println("count1 = " + count1);
    }

    /**
     * Эта операция выполняется с аналогичной производительностью что и с использованием for-each
     *
     */
    public static void iterateListByLambdaSecond(List<Integer> data) {
        data.stream().forEach(x -> doIt(x));
    }

    private static void doIt(Integer i) {
        i *= 2;
    }

}
