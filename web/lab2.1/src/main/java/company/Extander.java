package company;

public class Extander {
    public Extander(){}

    public String getList(Agency agency){
        String s = "";
        for(Tovar tovar : agency.getTovars()){
            s += "Tovar " + tovar.getName() + " is sold for " + tovar.getPrice() + ".\n";
        }
        return s;
    }
}
