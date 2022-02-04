package runner;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.tovars.Lastics;
import model.tovars.Pencils;
import model.tovars.Pens;

import model.disc.*;


/**
 * Server class that contains disc
 *
 * @version 1.0
 */
public class Server {
    /**
     * Server main method
     *
     * @param args command line params
     */
    public static void main(String[] args) {
        Disc disc = new Disc(new DiscManager());


        disc.addTovar(new Lastics("first", "lastic1",23.0, "silicon"));


        disc.addTovar(new Pencils("second", "pencil1", 1.0, 12));


        disc.addTovar(new Pens("third", "pencil1", 18.0,"red"));


        System.out.println("Create disc");

        try {
            RemoteDisc stub = (RemoteDisc) UnicastRemoteObject.exportObject(disc, 0);
            Registry reg = LocateRegistry.createRegistry(9001);
            reg.bind("Disc", stub);
            System.out.println("Register disc");
        } catch(RemoteException | AlreadyBoundException e) {
            System.out.println(e);
        }
    }
}
