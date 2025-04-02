package app.handler;

import app.entities.BasketItem;
import app.entities.Order;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.OrderMapper;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void showOrder(Javalin app) {
        app.post("/show-order", ctx -> {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));

            List<BasketItem> items = OrderMapper.getOrderByOrderID(connectionPool, orderId);

            Map<String, Object> model = new HashMap<>();
            model.put("orderId", orderId);
            model.put("customer_order", items);
            model.put("totalPrice", BasketHandler.getTotalPrice(items));

            ctx.render("customer-order.html", model);
        });
    }

    public static void showCustomerOrders(Javalin app) {
        app.post("/profile-orders", ctx -> {
            String customerEmail = ctx.formParam("email");

            List<Order> userOrders = OrderMapper.getUserOrders(connectionPool, customerEmail);

            ctx.attribute("userOrders", userOrders);
            ctx.render("user-orders.html");
        });
    }
}
