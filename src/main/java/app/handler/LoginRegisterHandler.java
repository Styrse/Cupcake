package app.handler;

import app.Main;
import app.entities.userRoles.Customer;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.UserMapper;
import io.javalin.Javalin;

public class LoginRegisterHandler {
    public static void login(Javalin app) {
        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            User user = UserMapper.getUserByEmail(Main.connectionPool, email, password);

            if (user == null) {
                ctx.redirect("/login");
            } else {
                ctx.sessionAttribute("user", user);
                ctx.sessionAttribute("email", email);
                ctx.sessionAttribute("balance", user.getBalance());
                if (user instanceof Employee) {
                    ctx.redirect("/dashboard");
                } else {
                    ctx.redirect("/");
                }
            }
        });
    }

    public static void register(Javalin app) {
        app.post("/register", ctx -> {
            String firstname = ctx.formParam("firstname");
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            User user = new Customer(firstname, email, password);

            UserMapper.addUser(user);

            ctx.redirect("/");
        });
    }
}
