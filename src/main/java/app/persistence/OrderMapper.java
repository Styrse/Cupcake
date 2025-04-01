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
}
