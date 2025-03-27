package app.entities.userRoles;

import app.entities.Basket;
import app.entities.Order;

import java.sql.Date;
import java.util.List;

public class Customer extends User {
    private int customerID;
    private Date birthday;
    private Basket basket;
    private List<Order> orders;
    private float balance;

    public Customer(String firstname, String lastname, String email, String password, int customerID, Date birthday, List<Order> orders, float balance) {
        super(firstname, lastname, email, password);
        this.customerID = customerID;
        this.birthday = birthday;
        this.basket = new Basket();
        this.orders = orders;
        this.balance = balance;
    }

    public Customer(String firstname, String email, String password, int customerID, List<Order> orders, float balance) {
        super(firstname, email, password);
        this.customerID = customerID;
        this.basket = new Basket();
        this.orders = orders;
        this.balance = balance;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Basket getBasket() {
        return basket;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public float getBalance(){ return balance;
    }
}

