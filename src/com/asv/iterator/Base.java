package com.asv.iterator;

import java.util.*;

/**
 * @author alexandrov on 24.03.2016.
 */
public class Base {
    
    public static void main(String[] args) {
        int[] digits = new int[] {1, 3, 2, 5, 6, 8, 4, 2, 7, 8, 4, 10, 12, 33, 45, 60 };

        List<Integer> digitsList = new ArrayList<>();
        for (Integer digit : digits) {
            digitsList.add(digit);
        }

        // we must get only even values
        MyIterator<Integer> myIt = new MyIterator<>(digitsList.iterator());

        // Case 1
        while (myIt.hasNext()) {
            System.out.println("myIt.next() = " + myIt.next());
        }

        // Case 2
        //System.out.println("myIt.next() = " + myIt.next());   // 2
        //System.out.println("myIt.next() = " + myIt.next());   // 6
        //System.out.println("myIt.next() = " + myIt.next());   // 8

    }

}

class MyIterator<T extends Number> implements Iterator<T> {

    private Iterator<T> it;
    private T currentElement;
    private boolean wasCurrentElementPopulated = false;

    MyIterator(Iterator<T> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        return tryToObtainNextElement();
    }

    private boolean tryToObtainNextElement() {
        if (wasCurrentElementPopulated) {
            offFlag();
            return true;
        }
        offFlag();
        while (it.hasNext()) {
            T element = it.next();
            if (element.intValue() % 2 == 0) {
                currentElement = element;
                wasCurrentElementPopulated = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        tryToObtainNextElement();
        offFlag();
        return currentElement;
    }

    private void offFlag() {
        wasCurrentElementPopulated = false;
    }

    @Override
    public void remove() {

    }

}