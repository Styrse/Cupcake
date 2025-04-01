package app.handler;

import app.persistence.UserMapper;
import io.javalin.Javalin;

public class UserHandler {
    public static void addFunds(Javalin app) {
        app.post("/profile/add-funds", ctx -> {

            float addedAmount = Float.parseFloat(ctx.formParam("add-funds"));
            float currentBalance = Float.parseFloat(ctx.formParam("balance"));
            String email = ctx.formParam("email");

            float newBalance = currentBalance + addedAmount;

            UserMapper.updateUserBalance(email, newBalance);

           ctx.redirect("/all-profiles");
        });
    }
}

