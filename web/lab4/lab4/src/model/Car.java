package model;

public class Car {
    private int id;
    private boolean parked;
    private int waitTime;
    private Parking parking;

    public Car(int id, int waitTime){
        this.setId(id);
        this.setParked(false);
        this.setWaitTime(waitTime);
    }

    public synchronized void park(Parking p) throws InterruptedException {
        if (p.isFull()){
            wait(waitTime);
            if (p.isFull()){
                System.out.println("Car " + this.getId() + " is unable to park at " + java.util.Calendar.getInstance().getTime());
                return;
            }
            this.setParking(p);
            p.park(this);
        }
        else{
            this.setParking(p);
            p.park(this);
        }
    }

    public synchronized void goAway(){
        this.getParking().unpark(this);
        this.setParking(null);
        notify();
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
