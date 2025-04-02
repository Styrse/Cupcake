package app.entities;

import app.entities.itemTypes.Item;

import java.sql.Date;
import java.util.List;

public class Order {
    private int orderID;
    private Date orderDate;
//    private LocalDateTime pickupDate;
    private String customerEmail;
    private String orderStatus;
    private String paymentType;
    private List<Item> items;

    public Order(int orderID, String customerEmail, String orderStatus, String paymentType) {
        this.orderID = orderID;
        this.customerEmail = customerEmail;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
    }

    public Order(int orderID, Date orderDate, String customerEmail, String orderStatus, String paymentType) {
        this.orderID = orderID;
        this.orderDate = orderDate;
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

    public Date getOrderDate() {
        return orderDate;
    }
}
