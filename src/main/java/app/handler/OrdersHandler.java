package app.handler;

import app.Main;
import app.entities.Order;
import app.persistence.OrderMapper;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

import static app.Main.connectionPool;

public class OrdersHandler {
    public static List<Order> orders = new ArrayList<>();
    //TODO: Check use

    public static void removeOrder(Javalin app) {
        app.post("/orders/remove", ctx -> {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));

            OrderMapper.removeOrder(connectionPool, orderId);

            ctx.redirect("/all-orders");
        });
    }
}
