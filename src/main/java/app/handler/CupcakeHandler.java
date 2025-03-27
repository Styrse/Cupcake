package app.handler;

import app.Main;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import io.javalin.Javalin;

import java.util.Map;

public class CupcakeHandler {
    public static void routes(Javalin app, ConnectionPool connectionPool){
        app.get("/index", ctx -> showCupcakes(ctx));
    }

    public static void showCupcakes(io.javalin.http.Context ctx) {
        try {
            ctx.render("index.html", Map.of("cupcakeList", CupcakeMapper.getCupcakeBottoms(Main.connectionPool)));
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
