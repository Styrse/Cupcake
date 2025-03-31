package app.handler;

import io.javalin.Javalin;

public class LoginHandler {
    public static void login(Javalin app) {
        app.post("/login", ctx-> {
            String email = ctx.formParam("email");
            String password = ctx.formParam("password");


        });
    }
}
