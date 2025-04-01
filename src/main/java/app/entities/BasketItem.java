package app.entities;

import app.entities.itemTypes.Item;

public class BasketItem {
    private int quantity;
    private Item item;
    private float price;

    public BasketItem(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
        this.price = quantity * item.getSalesPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public float getPrice() {
        return price;
    }

    public float getItemPrice() {
        return item.getSalesPrice();
    }

    public void addToBasket(int quantity) {
        quantity += quantity;
    }
}
