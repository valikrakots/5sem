package model.catalog;

import model.tovars.Tovar;

import java.util.List;
import java.util.ArrayList;

/**
 * this is disc director that controls the disc
 *
 *
 * @version 1.0
 */
public class CatalogManager {

    public CatalogManager() {
    }

    /**
     * count total amount
     *
     * @param tovars list
     * @return totalTovarAmount
     */
    public int countTotalAmount(List<Tovar> tovars) {
        return tovars.size();
    }

    /**
     *
     *
     * @param tovars
     * @param minDur
     * @param maxDur
     * @return tovarList
     */
    public List<Tovar> selectByPrice(List<Tovar> tovars, Double minDur, Double maxDur) {
        ArrayList<Tovar> tovarList = new ArrayList<>();
        for (Tovar tovar :
                tovars) {
            if(tovar.getPrice() <= maxDur
                    && tovar.getPrice() >= minDur) {
                tovarList.add(tovar);
            }
        }
        return tovarList;
    }

    /**
     * select tovar from certain
     *
     * @param tovars tovar list
     * @param factory name of the required factory
     * @return tovarList list
     */
    public List<Tovar> selectByFactory(List<Tovar> tovars, String factory) {
        ArrayList<Tovar> tovarList = new ArrayList<>();
        for (Tovar song :
                tovars) {
            if(song.getFactoryName().equals(factory)) {
                tovarList.add(song);
            }
        }
        return tovarList;
    }

    /**
     * select specific tovar
     *
     * @param tovars list of tovars
     * @param name name of tovar
     * @return tovarList
     */
    public List<Tovar> selectSpecificTovar(List<Tovar> tovars, String name) {
        ArrayList<Tovar> songList = new ArrayList<>();
        for (Tovar tovar :
                tovars) {
            if(tovar.getName().equals(name)) {
                songList.add(tovar);
            }
        }
        return songList;
    }


}