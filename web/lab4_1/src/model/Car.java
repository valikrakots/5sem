package model;

import java.util.concurrent.locks.ReentrantLock;

public class Car {
    private int id;
    private boolean parked;
    private int waitTime;
    private Parking parking;
    static private ReentrantLock lock = new ReentrantLock();


    public Car(int id, int waitTime){
        this.setId(id);
        this.setParked(false);
        this.setWaitTime(waitTime);
    }




    public int getId() {
        return id;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public boolean isParked() {
        return parked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }
}
