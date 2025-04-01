package app.handler;

import app.persistence.UserMapper;
import io.javalin.Javalin;

import static app.Main.connectionPool;

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

    public static void removeUser(Javalin app) {
        app.post("/profile/remove", ctx -> {
            String email  = ctx.formParam("email");

            UserMapper.removeUser(connectionPool, email);

            ctx.redirect("/all-profiles");
        });
    }
}

