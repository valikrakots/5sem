package model.tovars;

public class Pens extends Tovar {
    private String color;

    public Pens(String factoryName, String name, double price, String color) {
        super(factoryName, name, price, "Pen");
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String getInfo() {
        String s = "Pen " + this.getName() + " made by: " + this.getFactoryName() + " price: "+ this.getPrice();
        return s;
    }
}
