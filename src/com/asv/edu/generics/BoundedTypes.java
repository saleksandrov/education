package com.asv.edu.generics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandrov
 * @since 11.04.2016
 */
public class BoundedTypes {

    public static void main(String[] args) {
        out(new ArrayList<Integer>());   // possible
        out(new ArrayList<Number>());    // possible

    }

    public static void in(List<? extends Number> src) {
        //src.add(new Integer(1));   // compile time error
    }

    public static void out(List<? super Integer> dest) {
        dest.add(new Integer(1));   // It is possible

        Number number = new BigInteger("2");
        //dest.add(number);                      // cannot do that (Неоднозначность т.к. актуальный тип может быть Integer)
    }


}
