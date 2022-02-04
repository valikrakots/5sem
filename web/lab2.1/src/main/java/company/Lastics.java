package company;

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
}
