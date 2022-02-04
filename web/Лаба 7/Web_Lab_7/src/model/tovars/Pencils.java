package model.tovars;

public class Pencils extends Tovar{
    private int width;


    public Pencils(String factoryName, String name, double price, int width) {
        super(factoryName, name, price, "Pencil");
        this.width = width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }


    @Override
    public String getClassName() {
        return "Pencil";
    }

    @Override
    public String getInfo() {
        String s = "Pencil " + this.getName() + " made by: " + this.getFactoryName() + " price: " + this.getPrice();
        return s;
    }
}
