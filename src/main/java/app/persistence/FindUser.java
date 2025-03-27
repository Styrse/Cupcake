package app.persistence;

import app.entities.itemTypes.Item;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class FindUser {
    public static User findUser(ConnectionPool connectionPool, String email, String password) throws DatabaseException {
        User user = null;

        String sql = "SELECT * FROM \"Customer\" WHERE customer_email=? AND customer_password=?";

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
        return user;
    }
}
