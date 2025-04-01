package app.handler;

import app.entities.BasketItem;
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
            ctx.render("process-payment.html");
        });

        app.get("/dashboard", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Employee) {
                ctx.render("dashboard.html");
            } else {
                ctx.redirect("/");
            }
        });
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
