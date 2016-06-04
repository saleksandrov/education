package com.asv.edu.patterns.observer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * Created by work on 04.06.2016.
 */
public class TimeObservable {

    private final Set<TimeObserver> observers = new HashSet<>();

    public void addObserver(TimeObserver to) {
        observers.add(to);
    }

    public void tenSeconds(int minutes) {
        Iterator<TimeObserver> it = observers.iterator();
        while (it.hasNext()) {
            TimeObserver observer =  it.next();
            observer.tenSecondsEvent(minutes);
        }
    }


}
