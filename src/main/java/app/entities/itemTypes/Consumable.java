package app.entities.itemTypes;

public abstract class Consumable extends Item{
    private int calories;
    private String description;
    private boolean glutenFree;

    public Consumable(float costPrice, float salesPrice, int calories, String description, boolean glutenFree) {
        super(costPrice, salesPrice);
        this.calories = calories;
        this.description = description;
        this.glutenFree = glutenFree;
    }

    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }
}
