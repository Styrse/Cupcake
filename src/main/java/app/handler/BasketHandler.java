package app.handler;

import app.entities.BasketItem;
import app.entities.itemTypes.Item;
import app.entities.itemTypes.eatables.Cupcake;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.persistence.CupcakeMapper;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

import static app.Main.connectionPool;
import static app.handler.RouteHandler.showCupcakes;

public class BasketHandler {

    public static List<BasketItem> basket = new ArrayList<>();

    public static void handle(Javalin app) {

        app.post("/", ctx -> {

            //USERS VALG AF BOTTOM OG TOP
            int inputBottomId = Integer.parseInt(ctx.formParam("cupcakeBottom"));
            int inputTopId = Integer.parseInt(ctx.formParam("cupcakeTop"));
            int quantity = Integer.parseInt(ctx.formParam("cupcakeQuantity"));

            try {
                // ALLE BOTTOMS OG TOPS FRA DB
                List<CupcakeBottom> bottoms = CupcakeMapper.getCupcakeBottoms(connectionPool);
                List<CupcakeTop> tops = CupcakeMapper.getCupcakeTops(connectionPool);

                CupcakeBottom cupcakeBottom = null;

                for (CupcakeBottom bottom : bottoms) {
                    if (bottom.getId() == inputBottomId) {
                        cupcakeBottom = bottom;
                        break;
                    }
                }

                CupcakeTop cupcakeTop = null;
                for (CupcakeTop top : tops) {
                    if (top.getId() == inputTopId) {
                        cupcakeTop = top;
                        break;
                    }
                }

                assert cupcakeBottom != null;
                assert cupcakeTop != null;

                basket.add(new BasketItem(quantity, new Cupcake(cupcakeBottom, cupcakeTop)));

                ctx.redirect("/");

            } catch (NumberFormatException e) {
                ctx.result("Invalid input! Please ensure both selections are valid numbers.");
            }
        });

    }
}
