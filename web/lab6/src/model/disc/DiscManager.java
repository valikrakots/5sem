package model.disc;

import model.tovars.Tovar;
import model.tovars.Tovar;
import util.comparator.TovarPriceComparator;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * this is disc director that controls the disc
 *
 *
 * @version 1.0
 */
public class DiscManager {

    /**
     * count total amount
     *
     * @param tovars list
     * @return totalSongsAmount total amount of song in disc
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
     * @return songList list of song with appropriate duration
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
     * @param tovars song list
     * @param factory name of the required musician
     * @return songList list of song with appropriate musician name
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
     * select specific song of disc
     *
     * @param tovars list of tovars
     * @param name name of tovar
     * @return songList required song list
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