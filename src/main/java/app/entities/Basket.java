package app.entities;

import app.entities.itemTypes.Item;

import java.util.List;

public class Basket {
    private List<Item> items;


    public Basket(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int itemId){
        items.removeIf(item -> item.getId() == itemId);
    }
}
