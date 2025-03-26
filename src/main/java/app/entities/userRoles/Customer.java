package app.entities.userRoles;

import app.entities.Basket;
import app.entities.Order;

import java.sql.Date;
import java.util.List;

public class Customer extends User {
    private String customerID;
    private Date birthday;
    private Basket basket;
    private List<Order> orders;
    private Float Balance;

    public Customer(String firstname, String lastname, String email, String password, List<Order> orders) {
        super(firstname, lastname, email, password);
    }

    public Customer(String firstname, String email, String password, List<Order> orders) {
        super(firstname, email, password);
    }

    public String getCustomerID() {
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


    public float getBalance(){ return Balance;
    }
}

