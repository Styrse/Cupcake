package app.entities.itemTypes.eatables;

public class CupcakeTop {
    private String flavour;
    private float costPrice;
    private float salesPrice;
    private boolean glutenFree;
    private int calories;
    private String description;
    private String path;

    public CupcakeTop(String flavour, float costPrice, float salesPrice, boolean glutenFree, int calories, String description, String path) {
        this.flavour = flavour;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.glutenFree = glutenFree;
        this.calories = calories;
        this.description = description;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "CupcakeTop{" +
                "flavour='" + flavour + '\'' +
                ", costPrice=" + costPrice +
                ", salesPrice=" + salesPrice +
                ", glutenFree=" + glutenFree +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                '}';
    }
}
