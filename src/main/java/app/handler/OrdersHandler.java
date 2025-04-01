package app.handler;

import app.entities.Order;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class OrdersHandler {
    public static List<Order> getAllOrders(Javalin app) {
        List<Order> orders = new ArrayList<>();

            orders.add(new Order(2, "customer@gmail.com", "Paid", new ArrayList<>()));
            orders.add(new Order(23, "esben@gmail.com", "Unpaid", new ArrayList<>()));
            orders.add(new Order(87, "styr@gmail.com", "Prepaid", new ArrayList<>()));

        return orders;
    }
}
