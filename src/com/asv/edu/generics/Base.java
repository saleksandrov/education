package com.asv.edu.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alexandrov on 24.03.2016.
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
        int countVehiclesWithBounded = api.countWithBoundedType(vehicles);
        int countCarsWithBounded = api.countWithBoundedType(cars);

        // cannot compile
        //int countCars = api.count(cars);

        System.out.println("countWithWildcard = " + countWithWildcard);
        System.out.println("countWithBoundedType = " + countWithWildcard2);
        System.out.println("countVehicles = " + countVehicles);
        System.out.println("countVehiclesWithBounded = " + countVehiclesWithBounded);
        System.out.println("countCarsWithBounded = " + countCarsWithBounded);

    }

}

class MyAPI<T> {

    //private T vehicle;

    int countWithWildcard(Collection<? extends T> vehicles) {
        int count = 0;
        for (T vehicle : vehicles) {
            count++;
        }
        return count;
    }

    @SuppressWarnings("TypeParameterHidesVisibleType")
    <T extends Vehicle> int countWithBoundedType(Collection<T> vehicles) {
        int count = 0;
        for (Vehicle vehicle : vehicles) {
            count++;
        }
        return count;
    }

    @SuppressWarnings("TypeParameterHidesVisibleType")
    <T extends Vehicle> void inspect(T t){
        //System.out.println("T: " + t.getClass().getName());
        //System.out.println("U: " + u.getClass().getName());
    }

    int count(Collection<T> vehicles) {
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
