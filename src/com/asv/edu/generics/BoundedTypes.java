package com.asv.edu.generics;

import java.util.List;

/**
 * @author alexandrov
 * @since 11.04.2016
 */
public class BoundedTypes {

    public static void main(String[] args) {

    }

    public static void in(List<? extends Number> src) {
        //src.add(new Integer(1));   // compile time error
    }

    public static void out(List<? super Integer> dest) {
        dest.add(new Integer(1));   // It is possible
    }


}
