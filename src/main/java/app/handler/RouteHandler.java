package app.handler;

import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.Main.connectionPool;
import static app.persistence.CupcakeMapper.getCupcakeBottoms;
import static app.persistence.CupcakeMapper.getCupcakeTops;

public class RouteHandler {
    public static void routes(Javalin app, ConnectionPool connectionPool){
        app.get("/", ctx -> showCupcakes(ctx));

        app.get("/basket", ctx -> {
            ctx.render("basket.html");
        });

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
}
