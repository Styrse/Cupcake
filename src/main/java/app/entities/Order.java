package app.entities;

import app.entities.itemTypes.Item;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderID;
    private LocalDateTime orderDate;
    private LocalDateTime pickupDate;
    private String customerID;
    private String status;
    private List<Item> items;

    public Order(int orderID, LocalDateTime orderDate, LocalDateTime pickupDate, String customerID, String status, List<Item> items) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.pickupDate = pickupDate;
        this.customerID = customerID;
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

    public String getCustomerID() {
        return customerID;
    }

    public String getStatus() {
        return status;
    }

    public List<Item> getItems() {
        return items;
    }
}
