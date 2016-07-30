package com.asv.edu.lambda;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author alexandrov
 * @since 13.07.2016
 */
public class Cumulative {

    public static void main(String[] args) {
        int[] values = {1,2,3};
        Arrays.parallelPrefix(values, Integer::sum);
        System.out.println("Arrays.toString(values) = " + Arrays.toString(values));
    }
    
}
