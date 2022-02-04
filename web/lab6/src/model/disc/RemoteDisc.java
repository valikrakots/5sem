package model.disc;


import model.tovars.Tovar;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


/**
 * interface that give RMI methods from disc service
 *
 * @version 1.0
 */
public interface RemoteDisc extends Remote {

    /**
     * come to disc
     *
     */
    void comeIn() throws RemoteException;

    /**
     * go out from disc
     *
     */
    void goOut() throws RemoteException;

    /**
     * return tovars of the disc
     *
     *
     */
    List<Tovar> getTovars() throws RemoteException;



    /**
     * count total amount of songs in disc
     *
     * @return totalSongsAmount total amount of songs in disc
     */
    int countTotalSongsAmount() throws RemoteException;




    /**
     * select songs by both parameters(musician and below certain work duration)
     *
     * @param name maximum work duration
     * @return list of songs with required musician with duration below parameter
     */
    List<Tovar> selectSpecificTovar(String name) throws RemoteException;

    /**
     * select songs of certain musician
     *
     * @param factory name of the factory
     * @return list of tovars
     */
    List<Tovar> selectByFactory(String factory) throws RemoteException;

    /**
     * select tovar with specific price
     *
     * @param minDur minimalprice
     * @param maxDur maximumprice
     * @return list of tovars
     */
    List<Tovar> selectByPrice(Double minDur, Double maxDur) throws RemoteException;
}
