package app.entities.userRoles;

import app.entities.Order;

import java.util.Date;
import java.util.List;

public class Customer extends User {
    private float balance;
    private List<Order> orders;
    private Date birthday;

    public Customer(String firstname, String lastname, String email, String password, float balance, List<Order> orders) {
        super(firstname, lastname, email, password);
        this.balance = balance;
        this.orders = orders;
    }

    public Customer(String firstname, String email, String password, float balance, List<Order> orders) {
        super(firstname, email, password);
        this.balance = balance;
        this.orders = orders;
    }

    public Customer(String firstname, String email, String password, float balance) {
        super(firstname, email, password);
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
