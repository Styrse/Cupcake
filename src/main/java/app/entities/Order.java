package app.entities;

import app.entities.itemTypes.Item;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderID;
//    private LocalDateTime orderDate;
//    private LocalDateTime pickupDate;
    private String customerEmail;
    private String orderStatus;
    private String paymentType;
    private List<Item> items;

    public Order(int orderID, String customerEmail, String orderStatus, String paymentType, List<Item> items) {
        this.orderID = orderID;
        this.customerEmail = customerEmail;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.items = items;
    }

    public Order(String customerEmail, String orderStatus, String paymentType, List<Item> items) {
        this.customerEmail = customerEmail;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.items = items;
    }

    public Order(int orderID, String customerEmail, String orderStatus, String paymentType) {
        this.orderID = orderID;
        this.customerEmail = customerEmail;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public List<Item> getItems() {
        return items;
    }
}
