package app.entities.itemTypes.eatables;

import app.entities.itemTypes.Consumable;

public abstract class Eatable extends Consumable {
    public Eatable(float costPrice, float salesPrice, int calories, String description, boolean glutenFree) {
        super(costPrice, salesPrice, calories, description, glutenFree);
    }
}
