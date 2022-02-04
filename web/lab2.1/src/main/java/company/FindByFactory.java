package company;

import java.util.ArrayList;

public class FindByFactory {
    private Agency agency;


    public FindByFactory(Agency agency){
        this.agency = agency;
    }

    public ArrayList<Tovar> find(String name){
        ArrayList<Tovar> tovars = agency.getTovars();
        ArrayList<Tovar> selected = new ArrayList<Tovar>();

        for (Tovar tovar: tovars){
            if (tovar.getFactoryName().equals(name)){
                selected.add(tovar);
            }
        }

        return selected;
    }

    public Agency getAgency() {
        return agency;
    }
}
