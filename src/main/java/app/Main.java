package app;

import app.config.SessionConfig;
import app.config.ThymeleafConfig;
import app.handler.RouteHandler;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String USER =
            System.getenv().getOrDefault("JDBC_USER", "postgres");

    private static final String PASSWORD =
            System.getenv().getOrDefault("JDBC_PASSWORD", "postgres");

    private static final String URL =
            System.getenv().getOrDefault("JDBC_CONNECTION_STRING",
                    "jdbc:postgresql://db:5432/%s?currentSchema=public");

    private static final String DB =
            System.getenv().getOrDefault("JDBC_DB", "cupcake");


    public static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(SessionConfig.sessionConfig()));
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start("0.0.0.0", 8585);

        RouteHandler.routes(app);
    }
}