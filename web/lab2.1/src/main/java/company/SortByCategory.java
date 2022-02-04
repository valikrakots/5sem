package company;

import java.util.ArrayList;

public class SortByCategory {
    private Agency agency;

    public SortByCategory(Agency agency){
        this.agency = agency;
    }

    public ArrayList<Tovar> sort(String category){
        ArrayList<Tovar> tovars = agency.getTovars();
        ArrayList<Tovar> selected = new ArrayList<Tovar>();

        for(Tovar tovar: tovars){
            if(tovar.getClassName().equals(category)){
                selected.add(tovar);
            }
        }
        selected.sort(new TovarPriceComparator());

        return selected;
    }

    public Agency getAgency() {
        return agency;
    }
}
