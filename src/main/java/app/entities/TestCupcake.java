package app.entities;

public class TestCupcake {
    private String flavor;
    private double price;
    private String imagePath;

    public TestCupcake(String flavor, double price, String imagePath) {
        this.flavor = flavor;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }
}
