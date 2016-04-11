package com.asv.edu.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alexandrov
 * @since 11.04.2016
 */
public class NonReifiableTypes {

    public static void main(String[] args) {
        List<String> stringListA = new ArrayList<String>();
        List<String> stringListB = new ArrayList<String>();

        NonReifiableTypes.addToList(stringListA, "Seven", "Eight", "Nine");
        NonReifiableTypes.addToList(stringListB, "Ten", "Eleven", "Twelve");
        List<List<String>> listOfStringLists =
                new ArrayList<List<String>>();
        NonReifiableTypes.addToList(listOfStringLists,
                stringListA, stringListB);

        NonReifiableTypes.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
    }

    public static void faultyMethod(List<String>... l) {
        Object[] objectArray = l;     // Valid

        objectArray[0] = Arrays.asList("ui");
        String s = l[0].get(0);       // ClassCastException thrown here
    }

    public static <T> void addToList (List<T> listArg, T... elements) {
        for (T x : elements) {
            listArg.add(x);
        }
    }


}
