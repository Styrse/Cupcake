package app.persistence;

import app.entities.userRoles.Admin;
import app.entities.userRoles.Customer;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMapper {
    public static List<User> getUserByEmail(ConnectionPool connectionPool, String inputEmail, String inputPassword) throws DatabaseException {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM \"User\" WHERE \"user_email\" = ? AND \"user_password\" = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, inputEmail);
                ps.setString(2, inputPassword);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String firstname = rs.getString("user_firstname");
                    String lastname = rs.getString("user_lastname");
                    String email = rs.getString("user_email");
                    Date birthday = rs.getDate("user_birthday");
                    String password = rs.getString("user_password");
                    float balance = rs.getFloat("user_balance");
                    boolean isEmployee = rs.getBoolean("isEmployee");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    if (isEmployee) {
                        if (isAdmin) {
                            users.add(new Admin(firstname, email, password));
                        } else {
                            users.add(new Employee(firstname, email, password));
                        }
                    } else {
                        users.add(new Customer(firstname, email, password, balance));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return users;
    }
}
