package app.persistence;

import app.entities.Order;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.entities.userRoles.Admin;
import app.entities.userRoles.Customer;
import app.entities.userRoles.Employee;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
                    String email = rs.getString("user_email");
                    String orderStatus = rs.getString("order_status");
                    String paymentType = rs.getString("payment_type");

                    orders.add(new Order(orderId, email, orderStatus, paymentType));
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

    public static Order getOrderByOrderID(ConnectionPool connectionPool, int orderId) throws DatabaseException {
        Order order = null;

        String sql = "SELECT * " +
                "FROM \"User\" AS users " +
                "JOIN \"Order\" AS orders USING (user_email) " +
                "JOIN \"Product\" AS products USING (order_id) " +
                "JOIN \"Cupcake\" AS cupcake USING (product_id) " +
                "WHERE order_id=8";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return order;
    }
}
