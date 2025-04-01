package app.handler;

import app.Main;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.UserMapper;
import io.javalin.Javalin;

public class LoginHandler {
    public static void login(Javalin app) {
        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            User user = UserMapper.getUserByEmail(Main.connectionPool, email, password);

            if (user != null) {
                ctx.sessionAttribute("user", user);
                ctx.sessionAttribute("email", email);
                ctx.sessionAttribute("balance", user.getBalance());
                if (user instanceof Employee) {
                    ctx.redirect("/dashboard");
                }
            }

            ctx.redirect("/");
        });
    }
}
