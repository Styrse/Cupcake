package app.handler;

import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;

import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.Main.connectionPool;
import static app.handler.BasketHandler.basket;
import static app.persistence.CupcakeMapper.getCupcakeBottoms;
import static app.persistence.CupcakeMapper.getCupcakeTops;

public class RouteHandler {
    public static void routes(Javalin app, ConnectionPool connectionPool) {
        app.get("/", ctx -> showCupcakes(ctx));

        app.get("/basket", ctx -> BasketHandler.showBasket(ctx));

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

        LoginRegisterHandler.login(app);

        LoginRegisterHandler.register(app);

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

            double totalPrice = BasketHandler.getTotalPrice(basket);

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

        OrdersHandler.showAllOrders(app);

        app.get("/all-profiles", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Employee) {
                List<User> users = UserMapper.getAllUsers(connectionPool);
                ctx.attribute("users", users);
                ctx.render("all-profiles.html");
            } else {
                ctx.redirect("/");
            }
        });

        OrdersHandler.removeOrder(app);

//        UserHandler.addFunds(app);

        UserHandler.userReroutes(app);
        UserHandler.removeUser(app);

        UserHandler.logout(app);

        OrdersHandler.showOrder(app);

        OrdersHandler.showCustomerOrders(app);
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

    public static CupcakeBottom getCupcakeBottomById(int bottomId) throws DatabaseException {
        List<CupcakeBottom> cupcakeBottoms = CupcakeMapper.getCupcakeBottoms(connectionPool);

        CupcakeBottom cupcakeBottom = null;

        for (CupcakeBottom bottom : cupcakeBottoms) {
            if (bottom.getId() == bottomId) {
                cupcakeBottom = bottom;
                break;
            }
        }
        return cupcakeBottom;
    }

    public static CupcakeTop getCupcakeTopById(int inputTopId) throws DatabaseException {
        List<CupcakeTop> cupcakeTops = CupcakeMapper.getCupcakeTops(connectionPool);

        CupcakeTop cupcakeTop = null;

        for (CupcakeTop top : cupcakeTops) {
            if (top.getId() == inputTopId) {
                cupcakeTop = top;
                break;
            }
        }
        return cupcakeTop;
    }
}
