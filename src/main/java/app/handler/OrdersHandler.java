package app.handler;

import app.entities.BasketItem;
import app.entities.Order;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.OrderMapper;
import app.utils.BasketUtils;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.Main.connectionPool;
import static app.handler.BasketHandler.basket;

public class OrdersHandler {
    public static void ordersReroutes(Javalin app) {
        removeOrder(app);
        showAllOrders(app);
        showOrder(app);
        showCustomerOrders(app);
        processPayment(app);
    }

    private static void removeOrder(Javalin app) {
        app.post("/orders/remove", ctx -> {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));

            OrderMapper.removeOrder(connectionPool, orderId);

            ctx.redirect("/all-orders");
        });
    }

    private static void showAllOrders(Javalin app) {
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

    private static void showOrder(Javalin app) {
        app.post("/show-order", ctx -> {
            int orderId = Integer.parseInt(ctx.formParam("orderId"));

            List<BasketItem> items = OrderMapper.getOrderByOrderID(connectionPool, orderId);

            Map<String, Object> model = new HashMap<>();
            model.put("orderId", orderId);
            model.put("customer_order", items);
            model.put("totalPrice", BasketUtils.getTotalPrice(items));

            ctx.render("customer-order.html", model);
        });
    }

    private static void showCustomerOrders(Javalin app) {
        app.post("/profile-orders", ctx -> {
            String customerEmail = ctx.formParam("email");

            List<Order> userOrders = OrderMapper.getUserOrders(connectionPool, customerEmail);

            ctx.attribute("userOrders", userOrders);
            ctx.render("user-orders.html");
        });
    }

    private static void processPayment(Javalin app) {
        app.get("/process-payment", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user == null) {
                ctx.redirect("/login");
                return;
            }

            if (basket.isEmpty()) {
                ctx.redirect("/");
                return;
            }

            double totalPrice = BasketUtils.getTotalPrice(basket);

            ctx.attribute("basketTotal", totalPrice);
            ctx.attribute("user", user);
            ctx.render("process-payment.html");
        });
    }
}
