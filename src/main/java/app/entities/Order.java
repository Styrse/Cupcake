package app.entities;

import app.entities.itemTypes.Item;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderID;
    private LocalDateTime orderDate;
    private LocalDateTime pickupDate;
    private String customerEmail;
    private String status;
    private List<Item> items;

    public Order(int orderID, LocalDateTime orderDate, LocalDateTime pickupDate, String customerEmail, String status, List<Item> items) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.pickupDate = pickupDate;
        this.customerEmail = customerEmail;
        this.status = status;
        this.items = items;
    }

    public Order(int orderID, String customerEmail, String status, List<Item> items, LocalDateTime orderDate) {
        this.orderID = orderID;
        this.customerEmail = customerEmail;
        this.status = status;
        this.items = items;
        this.orderDate = orderDate;
    }

    public Order(int orderID, String customerEmail, String status, List<Item> items) {
        this.orderID = orderID;
        this.customerEmail = customerEmail;
        this.status = status;
        this.items = items;
    }

    public int getOrderID() {
        return orderID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getStatus() {
        return status;
    }

    public List<Item> getItems() {
        return items;
    }
}
