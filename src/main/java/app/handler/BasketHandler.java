package app.handler;

import app.entities.BasketItem;
import app.entities.itemTypes.eatables.Cupcake;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;
import app.persistence.CupcakeMapper;
import app.persistence.OrderMapper;
import app.utils.BasketUtils;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.persistence.UserMapper.updateUserBalance;

public class BasketHandler {

    public static List<BasketItem> basket = new ArrayList<>();

    public static void basketReroutes(Javalin app) {
        addToBasket(app);
        removeFromBasket(app);
        handlePayment(app);
        showBasket(app);
    }

    private static void addToBasket(Javalin app) {
        app.post("/", ctx -> {
            //USERS VALG AF BOTTOM OG TOP
            int inputBottomId = Integer.parseInt(ctx.formParam("cupcakeBottom"));
            int inputTopId = Integer.parseInt(ctx.formParam("cupcakeTop"));
            int quantity = Integer.parseInt(ctx.formParam("cupcakeQuantity"));

            try {
                // ALLE BOTTOMS OG TOPS FRA DB
                CupcakeBottom cupcakeBottom = CupcakeMapper.getCupcakeBottomById(inputBottomId);
                CupcakeTop cupcakeTop = CupcakeMapper.getCupcakeTopById(inputTopId);

                BasketItem basketItem = new BasketItem(quantity, new Cupcake(cupcakeBottom, cupcakeTop));

                if (basket.isEmpty()) {
                    basket.add(basketItem);
                } else {
                    for (BasketItem item : basket) {
                        if (item.getItem().equals(basketItem.getItem())) {
                            item.addToBasket(quantity);
                            ctx.redirect("/");
                            return;
                        }
                    }
                    basket.add(basketItem);
                }

                ctx.redirect("/");
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void removeFromBasket(Javalin app) {
        app.post("/basket/remove", ctx -> {
            int index = Integer.parseInt(ctx.formParam("index"));

            if (index >= 0 && index < basket.size()) {
                basket.remove(index);
            }

            ctx.redirect("/basket");
        });
    }

    private static void handlePayment(Javalin app) {
        app.post("/process-payment", ctx -> {
            User user = ctx.sessionAttribute("user");
            assert user != null;
            String userEmail = user.getEmail();
            float basketTotal = (float) BasketUtils.getTotalPrice(basket);
            float currentBalance = user.getBalance();

            String paymentMethod = ctx.formParam("paymentMethod");

            String orderStatus = "";
            String paymentType = "";

            if (paymentMethod.equals("balance") || paymentMethod.equals("mobilepay")) {
                orderStatus = "Confirmed";

                if (paymentMethod.equals("balance")) {
                    if (currentBalance >= basketTotal) {
                        paymentType = "Balance";

                        double newBalance = currentBalance - basketTotal;
                        updateUserBalance(userEmail, newBalance);

                        user.setBalance(currentBalance - basketTotal);
                    }
                } else if (paymentMethod.equals("mobilepay")) {
                    paymentType = "MobilePay";
                }
            } else if (paymentMethod.equals("cash")) {
                orderStatus = "Pending";
                paymentType = "Cash";
            }

            OrderMapper.addOrder(userEmail, orderStatus, paymentType, basket);
            basket.clear();

            ctx.sessionAttribute("user", user);
            ctx.redirect("/");
        });
    }

    private static void showBasket(Javalin app) {
        app.get("/basket", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("basket", basket);

            ctx.sessionAttribute("basket", basket);
            ctx.attribute("totalPrice", BasketUtils.getTotalPrice(basket));

            ctx.render("basket.html", model);
        });
    }
}
