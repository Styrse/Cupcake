package app.handler;

import app.entities.Order;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.OrderMapper;
import io.javalin.Javalin;

import java.util.List;

import static app.Main.connectionPool;

public class OrdersHandler {
    public static void removeOrder(Javalin app) {
        app.post("/orders/remove", ctx -> {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));

            OrderMapper.removeOrder(connectionPool, orderId);

            ctx.redirect("/all-orders");
        });
    }

    public static void showAllOrders(Javalin app) {
        app.get("/all-orders", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Employee) {
                List<Order> orders = OrderMapper.getAllOrders(connectionPool);
                ctx.attribute("orders", orders);
                ctx.render("all-orders.html");
            } else {
                ctx.redirect("/");
            }
        });
    }
}
