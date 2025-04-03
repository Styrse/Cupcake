package app.entities;

import app.entities.itemTypes.Item;

public class BasketItem {
    private int quantity;
    private Item item;
    private float totalPrice;

    public BasketItem(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
        this.totalPrice = quantity * item.getSalesPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getItemPrice() {
        return item.getSalesPrice();
    }

    public void addToBasket(int quantity) {
        this.quantity += quantity;
        this.totalPrice = getQuantity() * item.getSalesPrice();
    }
}
