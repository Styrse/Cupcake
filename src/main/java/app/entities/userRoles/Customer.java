package app.entities.userRoles;

import app.entities.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User {
    private List<Order> orders;
    private Date birthday;

    public Customer(String firstname, String lastname, String email, String password, float balance, Date birthday) {
        super(firstname, lastname, email, password, balance);
        this.orders = new ArrayList<>();
        this.birthday = birthday;
    }

    public Customer(String email, String password, float balance, String firstname, Date birthday) {
        super(email, password, balance, firstname);
        this.orders = new ArrayList<>();
        this.birthday = birthday;
    }

    public Customer(String email, String password, float balance, String firstname) {
        super(email, password, balance, firstname);
        this.orders = new ArrayList<>();
    }

    public Customer(String firstname, String email, float balance) {
        super(firstname, email, balance);
        this.orders = new ArrayList<>();
    }

    public Customer(String email, float balance) {
        super(email, balance);
        this.orders = new ArrayList<>();
    }

    public Customer(String firstname, String email, String password) {
        super(firstname, email, password);
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
