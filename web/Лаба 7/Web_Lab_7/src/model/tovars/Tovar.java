package model.tovars;

import java.io.Serializable;

public abstract class Tovar implements Serializable {
    private String factoryName;
    private String name;
    private Double price;
    private String classname;


    Tovar(String factoryName,String name, Double price, String classname){
        this.factoryName = factoryName;
        this.price = price;
        this.name = name;
        this.classname = classname;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public String getClassName();

    abstract public String getInfo();

}
