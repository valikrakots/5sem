package model.catalog;

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
public class Catalog  {



    /**
     * list of working tovars
     */
    private List<Tovar> tovars;



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
    private CatalogManager director;

    /**
     * getter of director
     *
     * @return director
     */
    public CatalogManager getDirector() {
        return director;
    }

    /**
     * Constructor that creates with builder
     * @param director disc director
     */
    public Catalog(CatalogManager director) {
        this.director = director;
        tovars = new ArrayList<>();
    }


    /**
     * add list
     *
     * @param tovar to add to list
     */
    public void addTovar(Tovar tovar) {
        this.tovars.add(tovar);
    }

    /**
     * count total amount tovars in disc
     *
     * @return totalTovarsAmount
     */
    public int countTotalSongsAmount() {
        List<Tovar> cur = getTovars();
        return director.countTotalAmount(cur);
    }




}
