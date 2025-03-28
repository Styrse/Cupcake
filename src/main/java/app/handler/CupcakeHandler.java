package app.handler;

import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

import static app.Main.connectionPool;
import static app.persistence.CupcakeMapper.getCupcakeBottoms;
import static app.persistence.CupcakeMapper.getCupcakeTops;

public class CupcakeHandler {
    public static void routes(Javalin app, ConnectionPool connectionPool){
        app.get("/index", ctx -> showCupcakes(ctx));
    }

    public static void showCupcakes(io.javalin.http.Context ctx) {
        try {
            List<CupcakeBottom> cupcakeBottomList = getCupcakeBottoms(connectionPool);
            List<CupcakeTop> cupcakeTopList = getCupcakeTops(connectionPool);

            ctx.render("index.html", Map.of("cupcakeBottomList", cupcakeBottomList));
            ctx.render("index.html", Map.of("cupcakeTopList", cupcakeTopList));
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
