package com.asv.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandrov
 * @since 10.06.2016
 */
public class Base {

    public static void main(String[] args) {
        long l1 = 0xFFFFFFFF00000000L;
        long l2 = 0x00000000FFFFFFFFL;
        long l3 = 0x0FFFFFFFF0000000L;
        long l4 = 0x0FFFFFFFFFFFFFFFL;

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println(Long.MAX_VALUE);
        System.out.println("Binary=" + Long.toBinaryString(Long.MAX_VALUE));
        System.out.println(Long.toBinaryString(Long.MAX_VALUE).length());

        ClassF classF = new ClassF();
    }

    static class ClassF {

        private List<String> listF = new ArrayList<>();

        public ClassF() {
            System.out.println(""+listF);
        }
    }

}
