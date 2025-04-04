package app.handler;

import app.entities.userRoles.Admin;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.persistence.UserMapper;
import io.javalin.Javalin;

import java.util.List;

public class DashboardHandler {
    public static void dashboardReroutes(Javalin app) {
        showDashboard(app);
        showProfiles(app);
    }

    private static void showDashboard(Javalin app){
        app.get("/dashboard", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Admin) {
                ctx.render("dashboard.html");
            } else {
                ctx.redirect("/");
            }
        });
    }

    private static void showProfiles(Javalin app) {
        app.get("/all-profiles", ctx -> {
            User user = ctx.sessionAttribute("user");

            if (user instanceof Employee) {
                List<User> users = UserMapper.getAllUsers();
                ctx.attribute("users", users);
                ctx.render("all-profiles.html");
            } else {
                ctx.redirect("/");
            }
        });
    }
}
