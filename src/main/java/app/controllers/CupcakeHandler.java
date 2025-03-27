package app.controllers;

import app.Main;
import app.exceptions.DatabaseException;
import app.persistence.CupcakeMapper;

import java.util.Map;

public class CupcakeHandler {
    public static void showCupcakes(io.javalin.http.Context ctx) {
        try {
            ctx.render("index.html", Map.of("cupcakeList", CupcakeMapper.getCupcakeBottoms(Main.connectionPool)));
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
