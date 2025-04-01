package app.persistence;

import app.entities.Order;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static void insertOrder(Order order) {
        String email = order.getCustomerEmail();
        String sql = "INSERT INTO \"Order\" (user_email, order_status, payment_type) VALUES (?, ?)";
    }

    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM \"Order\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String email = rs.getString("user_email");
                    String orderStatus = rs.getString("order_status");
                    String paymentType = rs.getString("payment_type");

                    orders.add(new Order(email, orderStatus, paymentType));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return orders;
    }
}
