package company;

import java.util.ArrayList;

public class PriceList {
    ArrayList<Double> prices = new ArrayList<Double>();

    PriceList(ArrayList<Tovar> tovars){
        for (Tovar tovar : tovars) {
            prices.add(tovar.getPrice());
        }
    }
}
