package app.handler;

import io.javalin.Javalin;

public class RouteHandler {
    public static void routes(Javalin app) {
        IndexHandler.indexReroutes(app);
        BasketHandler.basketReroutes(app);
        OrdersHandler.ordersReroutes(app);
        LoginRegisterHandler.loginRegisterReroutes(app);
        UserHandler.userReroutes(app);
        DashboardHandler.dashboardReroutes(app);
    }
}
