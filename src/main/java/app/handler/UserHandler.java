package app.handler;

import app.entities.userRoles.User;
import app.persistence.UserMapper;
import io.javalin.Javalin;

import static app.Main.connectionPool;

public class UserHandler {

    public static void userReroutes(Javalin app) {
        addFunds(app);
        removeUser(app);
        settings(app);
    }
    private static void addFunds(Javalin app) {
        app.post("/profile/add-funds", ctx -> {

            float addedAmount = Float.parseFloat(ctx.formParam("add-funds"));
            float currentBalance = Float.parseFloat(ctx.formParam("balance"));
            String email = ctx.formParam("email");

            float newBalance = currentBalance + addedAmount;

            UserMapper.updateUserBalance(email, newBalance);

           ctx.redirect("/all-profiles");
        });
    }

    private static void removeUser(Javalin app) {
        app.post("/profile/remove", ctx -> {
            String email  = ctx.formParam("email");

            UserMapper.removeUser(email);

            ctx.redirect("/all-profiles");
        });
    }

    private static void settings(Javalin app) {
        app.post("/settings", ctx -> {
            String email = ctx.formParam("email");

            User user = UserMapper.getUserByEmail(email);

            if (user == null) {
                ctx.redirect("/");
            } else {
                ctx.render("/settings");
            }
        });
    }
}

