package app.handler;

import app.persistence.OrderMapper;
import io.javalin.Javalin;

import static app.Main.connectionPool;

public class OrdersHandler {
    public static void removeOrder(Javalin app) {
        app.post("/orders/remove", ctx -> {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));

            OrderMapper.removeOrder(connectionPool, orderId);

            ctx.redirect("/all-orders");
        });
    }
}
