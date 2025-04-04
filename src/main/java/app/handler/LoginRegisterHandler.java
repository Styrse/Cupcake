package app.handler;

import app.entities.userRoles.Customer;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.UserMapper;
import app.utils.EmailUtils;
import io.javalin.Javalin;

public class LoginRegisterHandler {
    public static void loginRegisterReroutes(Javalin app) {
        login(app);
        register(app);
        logout(app);
    }

    private static void login(Javalin app) {
        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");

            User user = UserMapper.verifyUser(email, password);

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


            if (EmailUtils.checkDuplicateEmail(email)) {
                ctx.redirect("/");
            }

            UserMapper.addUser(newUser);

            ctx.redirect("/");
        });
    }

    private static void logout(Javalin app) {
        app.post("/logout", ctx -> {
            ctx.req().getSession().invalidate();
            ctx.redirect("/");
        });
    }
}
