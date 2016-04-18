package com.asv.edu.sort;

import java.util.*;
import java.util.function.BiConsumer;


/**
 * Starter for sorting
 *
 * @author alexandrov
 * @since 15.04.2016
 */
public class Base {

    static final BiConsumer<int[], int[]> forkJoinFunction = (x, y) -> Sort.forkJoinSort(x, y, 0, x.length - 1);
    static final BiConsumer<int[], int[]> mergeSortClassicFunction = (x, y) -> Sort.merge_sort(x,y,0,x.length -1);
    static final BiConsumer<int[], int[]> arraysSortNativeFunction = (x, y)  ->  Arrays.sort(x);
    static final BiConsumer<int[], int[]> arraysSortParallelNativeFunction = (x, y)  ->  Arrays.parallelSort(x);

    // Configuration part
    static final int EXPERIMENT_COUNT = 1;
    static final int LENGTH_OF_ARRAY = 100_000_000;
    static final BiConsumer<int[], int[]> sortFunction = arraysSortParallelNativeFunction;

    public static void main(String[] args) {

        int[] arrayToSort = generateArrayOfLength(LENGTH_OF_ARRAY);
        int[] bufer = new int[arrayToSort.length];

        System.out.println("Start sorting");
        measureTime(arrayToSort, bufer, sortFunction);

        for (int i = 0; i < 10; i++) {
            int element = bufer[i];
            System.out.println(element);
        }

    }

    static void measureTime(int[] arrayToSort, int[] bufer, BiConsumer<int[], int[]> consumer) {
        long s = System.currentTimeMillis();
        int experimentCount = EXPERIMENT_COUNT;
        while (experimentCount-- > 0) {
            consumer.accept(arrayToSort, bufer);
        }
        long workTime = System.currentTimeMillis() - s;
        System.out.println("WorkTime = " + workTime/EXPERIMENT_COUNT);
    }

    static int[] generateArrayOfLength(int length) {
        Random random = new Random();
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(300);
        }
        return result;

    }

}
