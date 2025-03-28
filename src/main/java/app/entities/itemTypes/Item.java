package app.entities.itemTypes;

public abstract class Item {
    private int id;
    private float costPrice;
    private float salesPrice;

    public Item(float costPrice, float salesPrice) {
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
    }

    public int getId() {
        return id;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public float getSalesPrice() {
        return salesPrice;
    }
}
