package app.entities.itemTypes;

public abstract class Drinkable extends Consumable{
    public Drinkable(float costPrice, float salesPrice, int calories, String description, boolean glutenFree) {
        super(costPrice, salesPrice, calories, description, glutenFree);
    }
}
