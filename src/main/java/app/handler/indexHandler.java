package app.handler;

import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.persistence.CupcakeMapper.getCupcakeBottoms;
import static app.persistence.CupcakeMapper.getCupcakeTops;

public class indexHandler {
    public static void indexReroutes(Javalin app) {
        showCupcakes(app);
        showIndex(app);
        showSettings(app);
        showLogin(app);
    }

    private static void showCupcakes(Javalin app) {
        app.get("/", ctx -> {
                List<CupcakeBottom> cupcakeBottomList = getCupcakeBottoms();
                List<CupcakeTop> cupcakeTopList = getCupcakeTops();

                Map<String, Object> model = new HashMap<>();
                model.put("cupcakeBottomList", cupcakeBottomList);
                model.put("cupcakeTopList", cupcakeTopList);

                ctx.render("index.html", model);
        });
    }

    private static void showIndex(Javalin app) {
        app.get("/index", ctx -> {
            ctx.render("index.html");
        });
    }

    private static void showSettings(Javalin app) {
        app.get("/profile", ctx -> {
            ctx.render("settings.html");
        });
    }

    private static void showLogin(Javalin app) {
        app.get("/login", ctx -> {
            ctx.render("login.html");
        });
    }
}
