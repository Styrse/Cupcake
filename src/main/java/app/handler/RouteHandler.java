package app.handler;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;

public class RouteHandler {
    public static void routes(Javalin app, ConnectionPool connectionPool) {
        indexHandler.indexReroutes(app);
        BasketHandler.basketReroutes(app);
        OrdersHandler.ordersReroutes(app);
        LoginRegisterHandler.loginRegisterReroutes(app);
        UserHandler.userReroutes(app);
        DashboardHandler.dashboardReroutes(app);
    }
}
