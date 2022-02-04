package model.tovars;

import java.io.Serializable;

public abstract class Tovar implements Serializable {
    private String factoryName;
    private String name;
    private Double price;
    private String className;

    Tovar(String factoryName,String name, Double price, String className){
        this.factoryName = factoryName;
        this.price = price;
        this.name = name;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    abstract public String getInfo();

}
