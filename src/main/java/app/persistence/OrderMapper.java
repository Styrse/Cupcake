package app.persistence;

import app.entities.BasketItem;
import app.entities.Order;
import app.entities.itemTypes.eatables.Cupcake;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.exceptions.DatabaseException;
import app.handler.RouteHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM \"Order\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Date orderDate = rs.getDate("order_date");
                    String email = rs.getString("user_email");
                    String orderStatus = rs.getString("order_status");
                    String paymentType = rs.getString("payment_type");

                    orders.add(new Order(orderId, orderDate, email, orderStatus, paymentType));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return orders;
    }

    public static void removeOrder(ConnectionPool connectionPool, int orderId) throws DatabaseException {
        String sql = "DELETE FROM \"Order\" WHERE order_id=?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, orderId);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
    }

    public static void addOrder(ConnectionPool connectionPool, String email, String orderStatus, String paymentType) throws DatabaseException {
        String sql = "INSERT INTO \"Order\" (user_email, order_status, payment_type) VALUES (?, ?, ?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, email);
                ps.setString(2, orderStatus);
                ps.setString(3, paymentType);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
    }

    public static List<BasketItem> getOrderByOrderID(ConnectionPool connectionPool, int orderId) throws DatabaseException {
        List<BasketItem> items = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM \"User\" AS users " +
                "JOIN \"Order\" AS orders USING (user_email) " +
                "JOIN \"Product\" AS products USING (order_id) " +
                "JOIN \"Cupcake\" AS cupcake USING (product_id) " +
                "WHERE order_id=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    int bottomId = rs.getInt("bottom_id");
                    int topId = rs.getInt("top_id");

                    CupcakeBottom cupcakeBottom = RouteHandler.getCupcakeBottomById(bottomId);
                    CupcakeTop cupcakeTop = RouteHandler.getCupcakeTopById(topId);
                    Cupcake cupcake = new Cupcake(cupcakeBottom,cupcakeTop);

                    items.add(new BasketItem(quantity, cupcake));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return items;
    }

    public static List<Order> getUserOrders(ConnectionPool connectionPool, String customerEmail) throws DatabaseException {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM \"Order\" " +
                "WHERE user_email LIKE ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, customerEmail);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Date orderDate = rs.getDate("order_date");
                    String email = rs.getString("user_email");
                    String orderStatus = rs.getString("order_status");
                    String paymentType = rs.getString("payment_type");

                    orders.add(new Order(orderId, orderDate, email, orderStatus, paymentType));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return orders;
    }
}
