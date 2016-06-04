package com.asv.edu.patterns.observer;

/**
 * Created by work on 04.06.2016.
 */
public class TimeObserverImpl implements TimeObserver {



    public void tenSecondsEvent(int minutes) {
        System.out.println("minites = " + minutes);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        //only for test
        return (int)System.currentTimeMillis();
    }

}
