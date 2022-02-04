package util.comparator;

import model.tovars.Tovar;

import java.util.Comparator;

public class TovarPriceComparator implements Comparator<Tovar> {


    public int compare(Tovar a, Tovar b){
        if(a.getPrice()>b.getPrice())
            return 1;
        else if(a.getPrice()<b.getPrice())
            return-1;
        else
            return 0;
    }
}
