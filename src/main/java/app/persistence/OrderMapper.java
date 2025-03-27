package app.persistence;

import app.entities.Order;
import app.entities.itemTypes.Item;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM \"Order\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    int orderID = rs.getInt("order_id");
                    LocalDateTime orderDate = rs.getDate("order_date");
                    LocalDateTime pickupDate = rs.getDate("pickup_date");
                    int customerID = rs.getInt("customer_id");
                    String status = rs.getString("status");
                    List<Item> items =
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return items;
    }
}
