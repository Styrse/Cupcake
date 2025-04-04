package app.handler;

import app.entities.userRoles.User;
import app.persistence.UserMapper;
import app.utils.EmailUtils;
import io.javalin.Javalin;

public class UserHandler {

    public static void userReroutes(Javalin app) {
        addFunds(app);
        removeUser(app);
        settings(app);
        updateEmail(app);
        updatePassword(app);
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
            String email = ctx.formParam("email");

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

    private static void updateEmail(Javalin app) {
        app.post("/update-email", ctx -> {
            String newEmail = ctx.formParam("email");

            if (EmailUtils.checkDuplicateEmail(newEmail)) {
                ctx.redirect("/settings");
            }

            User user = ctx.sessionAttribute("user");

            String oldEmail = user.getEmail();
            user.setEmail(newEmail);

            UserMapper.updateUserEmail(user, oldEmail);

            ctx.sessionAttribute("email", user.getEmail());

            ctx.redirect("/");
        });
    }

    private static void updatePassword(Javalin app) {
        app.post("/update-password", ctx -> {
            String currentPassword = ctx.formParam("currentPassword");
            String newPassword = ctx.formParam("newPassword");
            String confirmPassword = ctx.formParam("confirmPassword");

            User user = ctx.sessionAttribute("user");

            if (currentPassword.equals(user.getPassword())) {

                ctx.sessionAttribute("email", user.getEmail());

                if (newPassword.equals(confirmPassword)) {
                    user.setPassword(newPassword);
                    UserMapper.updateUserPassword(user, newPassword);
                    ctx.redirect("/");
                    return;
                }
                ctx.redirect("/");
            } else {
                ctx.redirect("/settings");
            }
            ctx.redirect("/");
        });
    }
}


