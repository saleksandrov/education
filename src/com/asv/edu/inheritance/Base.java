package com.asv.edu.inheritance;

/**
 * @author alexandrov
 * @since 29.09.2016
 */
public class Base {

    public static void main(String[] args) {

        // для B выведет 2, 2
        B b = new B();
        System.out.println("b.f1 = " + b.f1);
        System.out.println("b.get() = " + b.get());

        // для A выведет 1, 2
        A a = new B();
        System.out.println("a.f1 = " + a.f1);
        System.out.println("a.get() = " + a.get());

    }

    static class A {
        int f1 = 1;

        int get() {
            return 1;
        }
    }

    static class B extends A {
        int f1 = 2;

        int get() {
            return 2;
        }
    }

}
