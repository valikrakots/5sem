package company;

import java.util.ArrayList;

public class Agency {
    private String name;
    private ArrayList<Tovar> tovars;
    private PriceList priceList;

    public Agency(String name, ArrayList<Tovar> tovars){
        this.name = name;
        this.tovars = tovars;
        priceList = new PriceList(tovars);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setTovars(ArrayList<Tovar> tovars) {
        this.tovars = tovars;
    }

    public ArrayList<Tovar> getTovars() {
        return tovars;
    }
}
