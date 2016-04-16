package com.asv.edu.sort;

import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author alexandrov
 * @since 15.04.2016
 */
public class Base {

    public static void main(String[] args) {
        //int[] arrayToSort = new int[]{4, 45, 2, 16, 5, 8, 3, 0, 32, 12, 11 , 4, 45, 2, 16, 5, 8, 3, 0, 32, 12, 11, 4, 45, 2, 16, 5, 8, 3, 0, 32, 12, 11,4, 45, 2, 16, 5, 8, 3, 0, 32, 12, 11};
        int[] arrayToSort = generateArrayOfLength(200_000_000);
        int[] bufer = new int[arrayToSort.length];

        BiConsumer<int[], int[]> biConsumer = (x, y) -> {
            Sort.forkJoinSort(x, y, 0, x.length - 1);
            //Sort.merge_sort(x,y,0,x.length -1);
        };
        System.out.println("Start sorting");
        measureTime(arrayToSort, bufer, biConsumer);

        /*for (int i = 0; i < bufer.length; i++) {
            int element = bufer[i];
            System.out.println(element);
        }*/

    }

    private static void measureTime(int[] arrayToSort, int[] bufer, BiConsumer<int[], int[]> consumer) {
        long s = System.currentTimeMillis();
        int experimentCount = 5;
        while (experimentCount-- > 0) {
           // Sort.forkJoinSort(arrayToSort, bufer, 0, arrayToSort.length - 1);
            consumer.accept(arrayToSort, bufer);
        }
        System.out.println("WorkTime = " + (System.currentTimeMillis() - s));
    }

    public static int[] generateArrayOfLength(int length) {
        Random random = new Random();
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(300);
        }
        return result;

    }

}
