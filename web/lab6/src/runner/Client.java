package runner;

import model.disc.RemoteDisc;
import model.tovars.Tovar;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Client class which work with rmi methods of disc
 *
 * @version 1.0
 */
public class Client {

    /**
     * Client main method
     *
     * @param args command line params
     */
    public static void main(String[] args) {
        Registry reg;
        RemoteDisc stub;

        try {
            reg = LocateRegistry.getRegistry(9001);
            stub = (RemoteDisc)reg.lookup("Disc");
        } catch(RemoteException | NotBoundException e) {
            System.out.println(e);
            return;
        }

        ArrayList<Tovar> t = new ArrayList<Tovar>();
        try {
            System.out.println("I opened the disc");
            stub.comeIn();
            List<Tovar> tovars = stub.getTovars();
            boolean flag = true;
            Scanner sc = new Scanner(System.in);
            while (flag) {
                System.out.println("Select what do you want(0 - factory, 1 - tovar, 2 - price, 3 - total num, 4 - all tovars, 5 - exit): ");
                int opNum = Integer.parseInt(sc.nextLine());
                switch (opNum) {
                    case 0:
                        System.out.print("Enter factory: ");
                        String n = sc.nextLine();
                        t = (ArrayList<Tovar>) stub.selectByFactory(n);
                        printSongsList("Tovars made by " + n, t);
                        break;
                    case 1:
                        System.out.print("Enter name: ");
                        String n2 = sc.nextLine();
                        t = (ArrayList<Tovar>) stub.selectSpecificTovar(n2);
                        printSongsList("Tovars named " + n2, t);
                        break;
                    case 2:
                        t = (ArrayList<Tovar>) stub.selectByPrice(1., 10.);
                        printSongsList("Tovars with price between 1 and 10", t);
                        break;
                    case 3:
                        int songsAmount = stub.countTotalSongsAmount();
                        System.out.println("Total amount of tovars in disc\n" + songsAmount);
                        break;
                    case 4:
                        printSongsList("All tovars\n", tovars);
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        break;
                }
            }



        } catch (RemoteException e) {
            System.out.println(e);
        } finally {
            try {
                stub.goOut();
            } catch(RemoteException e) {
                System.out.println(e);
            }
            System.out.println("I go out from the disc");
        }
    }

    /**
     * method to display info about all songs in disc
     * @param header that determines info for user
     * @param tovarList list of described tovars
     */
    public static void printSongsList(String header, List<Tovar> tovarList) {
        System.out.println("\n =========== " + header + " ===========");
        if(tovarList.size() > 0) {
            for(Tovar tovars : tovarList) {
                System.out.println(tovars.getInfo());
            }
            System.out.println();
        }
        else {
            System.out.println("No tovars");
        }
    }
}
