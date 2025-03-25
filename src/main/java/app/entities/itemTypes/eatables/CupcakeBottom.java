package app.entities.itemTypes.eatables;

public class CupcakeBottom {
    private String flavour;
    private float costPrice;
    private float salesPrice;
    private boolean glutenFree;
    private int calories;
    private String description;

    public CupcakeBottom(String flavour, float costPrice, float salesPrice, boolean glutenFree, int calories, String description) {
        this.flavour = flavour;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.glutenFree = glutenFree;
        this.calories = calories;
        this.description = description;
    }

    public String getFlavour() {
        return flavour;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public float getSalesPrice() {
        return salesPrice;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }
}
