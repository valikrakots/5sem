package Thread;

import model.Car;
import model.Parking;

public class MyRunnable implements Runnable {
    private Car car;
    private Parking parking;

    public MyRunnable(Object car, Object parking) {
        this.car = (Car) car;
        this.parking = (Parking) parking;
    }

    public void run() {
        try {
            car.park(parking);
            Thread.sleep(2000);
            if (car.isParked()){
                car.goAway();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}