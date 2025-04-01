package app.handler;

import app.entities.Order;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class OrdersHandler {
    public static List<Order> orders = new ArrayList<>();

    public static void removeOrder(Javalin app) {
        app.post("/orders/remove", ctx -> {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));
            

            ctx.redirect("/all-orders");
        });
    }
}
