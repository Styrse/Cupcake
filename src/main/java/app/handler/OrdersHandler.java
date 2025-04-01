package app.handler;

import app.entities.Order;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class OrdersHandler {
    public static List<Order> orders = new ArrayList<>();

    public static List<Order> getAllOrders(Javalin app) {
            orders.add(new Order(2, "customer@gmail.com", "Preparing", "Paid", new ArrayList<>()));
            orders.add(new Order(14, "esben@gmail.com","Done", "Unpaid", new ArrayList<>()));
            orders.add(new Order(21, "styr@gmail.com", "Done", "Prepaid", new ArrayList<>()));

        return orders;
    }

    public static void removeOrder(Javalin app) {
        app.post("/orders/remove", ctx -> {
            int index = Integer.parseInt(ctx.formParam("index"));

            if (index >= 0 && index < orders.size()) {
                orders.remove(index);
            }

            ctx.redirect("/all-orders");
        });
    }
}
