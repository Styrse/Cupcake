package app.handler;

import app.entities.userRoles.Customer;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.UserMapper;
import io.javalin.Javalin;

import static app.Main.connectionPool;

public class LoginRegisterHandler {
    public static void loginRegisterReroutes(Javalin app) {
        login(app);
        register(app);
    }

    private static void login(Javalin app) {
        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            User user = UserMapper.getUserByEmail(connectionPool, email, password);

            if (user == null) {
                ctx.sessionAttribute("loginError", "Invalid email or password.");
                ctx.redirect("/login");
            } else {
                ctx.sessionAttribute("user", user);
                ctx.sessionAttribute("email", email);
                ctx.sessionAttribute("isEmployee", user instanceof Employee);

                if (user instanceof Employee) {
                    ctx.redirect("/dashboard");
                } else {
                    ctx.redirect("/");
                }
            }
        });
    }


    private static void register(Javalin app) {
        app.post("/register", ctx -> {
            String firstname = ctx.formParam("firstname");
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            User newUser = new Customer(firstname, email, password);

            for (User user : UserMapper.getAllUsers(connectionPool)) {
                if (user.getEmail().equals(email)) {
                    ctx.redirect("/");
                    return;
                }
            }

            UserMapper.addUser(newUser);

            ctx.redirect("/");
        });
    }

    private static void logout(Javalin app) {
//        app.post();
    }
}
