package company;

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
}
