package view;

import model.Car;
import model.Parking;
import Thread.MyRunnable;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Parking parking = new Parking();
        Car car1 = new Car(1, 1);
        Car car2 = new Car(2, 2);
        Car car3 = new Car(3, 3);

        Runnable r = new MyRunnable(car1, parking);
        Runnable r2 = new MyRunnable(car2, parking);
        Runnable r3 = new MyRunnable(car3, parking);
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r2);
        Thread thread3 = new Thread(r3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }
}
