package com.asv.edu.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandrov on 24.03.2016.
 */
public class Base {

    public static void main(String[] args) {

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());

        List<Car> cars = new ArrayList<Car>();
        cars.add(new Car());
        cars.add(new Car());

        MyAPI<Vehicle> api = new MyAPI<Vehicle>();
        int countWithWildcard = api.countWithWildcard(vehicles);
        int countWithWildcard2 = api.countWithWildcard(cars);
        int countVehicles = api.count(vehicles);
        // cannot compile
        //int countCars = api.count(cars);

        System.out.println("countWithWildcard = " + countWithWildcard);
        System.out.println("countWithWildcard2 = " + countWithWildcard2);
        System.out.println("countVehicles = " + countVehicles);

    }

}

class MyAPI<T> {

    private T vehicle;

    public int countWithWildcard(Collection<? extends T> vehicles) {
        int count = 0;
        for (T vehicle : vehicles) {
            count++;
        }
        return count;
    }

    public int count(Collection<T> vehicles) {
        int count = 0;
        for (T vehicle : vehicles) {
            count++;
        }
        return count;
    }



}

class Vehicle {

}

class Car extends Vehicle {

}

class Boat extends  Vehicle {

}
