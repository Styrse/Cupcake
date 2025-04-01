package app.handler;

import app.entities.BasketItem;
import app.entities.itemTypes.eatables.Cupcake;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.entities.userRoles.User;
import app.persistence.CupcakeMapper;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

import static app.Main.connectionPool;
import static app.persistence.UserMapper.updateUserBalance;

public class BasketHandler {

    public static List<BasketItem> basket = new ArrayList<>();

    public static void addToBasket(Javalin app) {

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

    public static double getTotalPrice() {
        double totalPrice = 0.0;

        for (BasketItem item : basket) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }


    public static void removeFromBasket(Javalin app) {
        app.post("/basket/remove", ctx -> {
            int index = Integer.parseInt(ctx.formParam("index"));

            if (index >= 0 && index < basket.size()) {
                basket.remove(index);
            }

            ctx.redirect("/basket");
        });
    }

    public static void handlePayment(Javalin app) {
        app.post("/process-payment", ctx -> {
            User user = ctx.sessionAttribute("user");
            assert user != null;
            String userEmail = user.getEmail();
            float basketTotal = (float) getTotalPrice();
            float currentBalance = user.getBalance();

            if (currentBalance >= basketTotal) {
                updateUserBalance(userEmail , currentBalance, basketTotal);

                user.setBalance(currentBalance - basketTotal);  // Set the new balance in the user object
                ctx.sessionAttribute("user", user);
                basket.clear();
                ctx.redirect("/");
            } else {
                ctx.result("Insufficient funds! Please check your balance.");
            }
        });
    }
}
