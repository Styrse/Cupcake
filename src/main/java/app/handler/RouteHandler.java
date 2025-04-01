package app.handler;

import app.entities.BasketItem;
import app.entities.Order;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;

import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.Main.connectionPool;
import static app.persistence.CupcakeMapper.getCupcakeBottoms;
import static app.persistence.CupcakeMapper.getCupcakeTops;

public class RouteHandler {
    public static void routes(Javalin app, ConnectionPool connectionPool){
        app.get("/", ctx -> showCupcakes(ctx));

        app.get("/basket", ctx -> showBasket(ctx));

        app.get("/index", ctx -> {
            ctx.render("index.html");
        });

        app.get("/profile", ctx -> {
            ctx.render("settings.html");
        });

        BasketHandler.addToBasket(app);

        BasketHandler.removeFromBasket(app);

        app.get("/login", ctx -> {
            ctx.render("login.html");
        });

        LoginHandler.login(app);


        app.get("/process-payment", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user == null) {
                ctx.redirect("/login");
                return;
            }

            double totalPrice = BasketHandler.getTotalPrice();

            ctx.attribute("basketTotal", totalPrice);
            ctx.attribute("user", user);
            ctx.render("process-payment.html");
        });

        BasketHandler.handlePayment(app);

        app.get("/dashboard", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Employee) {
                ctx.render("dashboard.html");
            } else {
                ctx.redirect("/");
            }
        });

        app.get("/all-orders", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Employee) {
                List<Order> orders = OrdersHandler.getAllOrders(app);
                ctx.attribute("orders", orders);
                ctx.render("all-orders.html");
            } else {
                ctx.redirect("/");
            }
        });

        app.get("/all-profiles", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Employee) {
                ctx.render("all-profiles");
            } else {
                ctx.redirect("/");
            }
        });

        OrdersHandler.removeOrder(app);
    }

    public static void showCupcakes(io.javalin.http.Context ctx) {
        try {
            List<CupcakeBottom> cupcakeBottomList = getCupcakeBottoms(connectionPool);
            List<CupcakeTop> cupcakeTopList = getCupcakeTops(connectionPool);

            Map<String, Object> model = new HashMap<>();
            model.put("cupcakeBottomList", cupcakeBottomList);
            model.put("cupcakeTopList", cupcakeTopList);

            ctx.render("index.html", model);
        } catch (DatabaseException e) {
            ctx.status(500).result("Database error: " + e.getMessage());
        }
    }

    public static void showBasket(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("basket", BasketHandler.basket);

        List<BasketItem> basket = BasketHandler.basket;

        float totalPrice = 0;
        for (BasketItem item : basket) {
            totalPrice += item.getPrice();
        }

        ctx.attribute("basket", basket);
        ctx.attribute("totalPrice", totalPrice);

        ctx.render("basket.html", model);

    }
}
