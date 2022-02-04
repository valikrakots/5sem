package model.disc;

import model.tovars.Tovar;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * this is disc
 *
 *
 * @version 1.0
 */
public class Disc implements RemoteDisc {

    public static int TOTAL_VISITORS_ON_RECEPTION = 3;

    /**
     * lock to synchronize on disc
     */
    private Lock tovarsLock;

    /**
     * semaphore to synchronize reception
     */
    private Semaphore receptionSemaphore;

    /**
     * list of working tovars
     */
    private List<Tovar> tovars;

    public void comeIn() throws RemoteException {
        try {
            Thread.sleep(1000);
            receptionSemaphore.acquire();
        } catch(InterruptedException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public void goOut() {
        receptionSemaphore.release();
    }

    /**
     * getter
     *
     * @return list
     */
    public List<Tovar> getTovars() {
        return tovars;
    }

    /**
     * director of disc
     */
    private DiscManager director;

    /**
     * getter of director
     *
     * @return director
     */
    public DiscManager getDirector() {
        return director;
    }

    /**
     * Constructor that creates with builder
     * @param director disc director
     */
    public Disc(DiscManager director) {
        this.director = director;
        tovars = new ArrayList<>();
        tovarsLock = new ReentrantLock();
        receptionSemaphore = new Semaphore(TOTAL_VISITORS_ON_RECEPTION);
    }


    /**
     * add list
     *
     * @param tovar to add to list
     */
    public void addTovar(Tovar tovar) {
        tovarsLock.lock();
        this.tovars.add(tovar);
        tovarsLock.unlock();
    }

    /**
     * count total amount tovars in disc
     *
     * @return totalTovarsAmount
     */
    public int countTotalSongsAmount() {
        tovarsLock.lock();
        List<Tovar> cur = getTovars();
        tovarsLock.unlock();
        return director.countTotalAmount(cur);
    }

    /**
     * select tovars with certain price
     *
     * @param minDur minimal
     * @param maxDur maximum
     * @return list of tovars with defined duration
     */
    public List<Tovar> selectByPrice(Double minDur, Double maxDur) {
        tovarsLock.lock();
        List<Tovar> cur = getTovars();
        tovarsLock.unlock();
        return director.selectByPrice(cur, minDur, maxDur);
    }

    /**
     * select songs of certain factory
     *
     * @param factory name of factory
     * @return list of tovars
     */
    public List<Tovar> selectByFactory(String factory) {
        tovarsLock.lock();
        List<Tovar> cur = getTovars();
        tovarsLock.unlock();
        return director.selectByFactory(cur, factory);
    }

    /**
     *
     *
     * @param name name of tovar
     *
     * @return list list of tovars
     */
    public List<Tovar> selectSpecificTovar(String name) {
        tovarsLock.lock();
        List<Tovar> curT = getTovars();
        tovarsLock.unlock();
        return director.selectSpecificTovar(curT, name);
    }




}
