package model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private int parked;
    private boolean full;
    private ArrayList<Car> cars = new ArrayList<Car>();
    static private int maxcar = 1;
    static private Semaphore semaphore = new Semaphore(maxcar);
    static private ReentrantLock lock = new ReentrantLock();

    public Parking(){
        this.setParked(0);
        this.full = false;
    }

    public void park(Car car) throws InterruptedException {
        boolean permit = semaphore.tryAcquire(car.getWaitTime(), TimeUnit.SECONDS);
        if(permit) {
            this.parked += 1;
            car.setParked(true);
            car.setParking(this);
            if (parked == maxcar)
                full = true;
            System.out.println("Car " + car.getId() + " is parked at " + java.util.Calendar.getInstance().getTime());
            this.cars.add(car);
        }
        else{
            System.out.println("Car " + car.getId() + " is unable to park at " + java.util.Calendar.getInstance().getTime());
        }
    }

    public void unpark(Car car){
        lock.lock();
        car.setParking(null);
        car.setParked(false);
        this.parked -= 1;
        this.full = false;
        System.out.println("Car " + car.getId() + " went away at " + java.util.Calendar.getInstance().getTime());
        semaphore.release(1);
        lock.unlock();
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

    public void setParked(int parked) {
        this.parked = parked;
    }
}
