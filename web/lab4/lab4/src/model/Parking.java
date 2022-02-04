package model;

import java.util.ArrayList;

public class Parking {
    private int maxcar;
    private int parked;
    private boolean full;
    private ArrayList<Car> cars = new ArrayList<Car>();

    public Parking(int maxcar){
        this.setMaxcar(maxcar);
        this.setParked(0);
        this.full = false;
    }

    public void park(Car car){
        this.parked += 1;
        car.setParked(true);
        if(parked == maxcar)
            full = true;
        System.out.println("Car " + car.getId() + " is parked at " + java.util.Calendar.getInstance().getTime());
        this.cars.add(car);
    }

    public void unpark(Car car){
        car.setParked(false);
        this.parked -= 1;
        this.full = false;
        System.out.println("Car " + car.getId() + " went away at " + java.util.Calendar.getInstance().getTime());
    }

    public int getMaxcar() {
        return maxcar;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getParked() {
        return parked;
    }

    public boolean isFull() {
        return full;
    }

    public void setMaxcar(int maxcar) {
        this.maxcar = maxcar;
    }

    public void setParked(int parked) {
        this.parked = parked;
    }
}
