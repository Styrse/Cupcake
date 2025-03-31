package app.handler;

import app.entities.BasketItem;
import app.entities.itemTypes.Item;
import app.entities.itemTypes.eatables.Cupcake;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
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
        /*app.get("/basket", ctx -> {
            ctx.render("basket.html");
        });*/

        app.get("/index", ctx -> {
            ctx.render("index.html");
        });

        app.get("/profile", ctx -> {
            ctx.render("profile.html");
        });
        BasketHandler.handle(app);
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
        try {
            List<BasketItem> basket = new ArrayList<>();
            //TODO: Add get List method
            basket.add(new BasketItem(3, new Cupcake(getCupcakeBottoms(connectionPool).get(2), getCupcakeTops(connectionPool).get(1))));
            basket.add(new BasketItem(1, new Cupcake(getCupcakeBottoms(connectionPool).get(1), getCupcakeTops(connectionPool).get(3))));
            basket.add(new BasketItem(2, new Cupcake(getCupcakeBottoms(connectionPool).get(3), getCupcakeTops(connectionPool).get(3))));
            basket.add(new BasketItem(4, new Cupcake(getCupcakeBottoms(connectionPool).get(3), getCupcakeTops(connectionPool).get(1))));
            basket.add(new BasketItem(1, new Cupcake(getCupcakeBottoms(connectionPool).get(4), getCupcakeTops(connectionPool).get(1))));

            Map<String, Object> model = new HashMap<>();
            model.put("basket", basket);

            ctx.render("basket.html", model);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
