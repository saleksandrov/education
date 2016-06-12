package com.asv.edu.lambda;

import com.asv.util.PerformEstimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by work on 12.06.2016.
 */
public class LambdaPerformance {

    public static void main(String[] args) {
        int count = 1_000_000;

        List<Integer> etalonList = new ArrayList<>();
        while (count-- > 0) {
            etalonList.add(count);
        }

        PerformEstimator.startEstimation();

        iterateList(etalonList);
        //iterateListByLambda(etalonList);

        System.out.println("PerformEstimator.getEstimationInMillis() = " + PerformEstimator.getEstimationInMillis());
    }

    public static void iterateList(List<?> data) {
        int count = 0;
        for (Object obj: data) {
            count++;
        }
        System.out.println("count = " + count);
    }

    /**
     * Производительность итераций по Stream намного ниже обычного foreach
     *
     */
    public static void iterateListByLambda(List<?> data) {
        int count = 0;
        long count1 = data.stream().count();
        System.out.println("count1 = " + count1);
    }

}
