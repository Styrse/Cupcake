package app.entities.itemTypes;

public abstract class Item {
    private float costPrice;
    private float salesPrice;

    public Item(float costPrice, float salesPrice) {
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }
}
