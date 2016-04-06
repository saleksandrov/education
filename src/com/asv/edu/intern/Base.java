package com.asv.edu.intern;

/**
 * @author alexandrov
 * @since 06.04.2016
 */
public class Base {

    public static void main(String[] args) {

        System.out.println("data1 == data1 = " + ("data1" == "data1"));
        System.out.println("data2 == data2.intern = " + ("data2" == "data2".intern()));

        String data3_1 = "data3";
        String data3_2 = "data3";

        System.out.println("data3_1 == data3_2 = " + (data3_1 == data3_2));


    }

}
