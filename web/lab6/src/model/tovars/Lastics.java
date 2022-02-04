package model.tovars;

public class Lastics extends Tovar {
    private String material;

    public Lastics(String factoryName, String name, double price, String material) {
        super(factoryName, name,price, "Lastic");
        this.material = material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String getInfo() {
        String s = "Lastic " + this.getName() + " made by: " + this.getFactoryName() + " price: " + this.getPrice();
        return s;
    }
}
