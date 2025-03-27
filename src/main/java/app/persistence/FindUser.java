package app.persistence;

import app.entities.itemTypes.Item;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;

import java.sql.*;

public class FindUser {
    public static User findUser(ConnectionPool connectionPool, String email, String password) throws DatabaseException {
        User user = null;

        String sql = "SELECT * FROM \"Customer\" WHERE customer_email=? AND customer_password=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    int
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return user;
    }

    private static Item getItem
}
