package app.persistence;

import app.entities.Order;
import app.entities.userRoles.Admin;
import app.entities.userRoles.Customer;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static app.Main.connectionPool;

public class UserMapper {
    public static User getUserByEmail(ConnectionPool connectionPool, String inputEmail, String inputPassword) throws DatabaseException {
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
                            return new Admin(email, password, balance, firstname);
                        } else {
                            return new Employee(email, password, balance, firstname);
                        }
                    } else {
                        return new Customer(email, password, balance, firstname);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return null;
    }

    public static void updateUserBalance(String user_email, double newBalance) throws SQLException {
        String query = "UPDATE \"User\" SET \"user_balance\" = ? WHERE \"user_email\" = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, newBalance);
            statement.setString(2, user_email);
            statement.executeUpdate();
        }
    }

    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM \"User\" ORDER BY \"user_email\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String firstname = rs.getString("user_firstname");
                    String email = rs.getString("user_email");
                    float balance = rs.getFloat("user_balance");
                    boolean isEmployee = rs.getBoolean("isEmployee");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    if (isEmployee) {
                        if (isAdmin) {
                            users.add(new Admin(email, balance));
                        } else {
                            users.add(new Employee(email, balance));
                        }
                    } else {
                        if (firstname != null) {
                            users.add(new Customer(firstname, email, balance));
                        } else {
                            users.add(new Customer(email, balance));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return users;
    }

    public static void removeUser(ConnectionPool connectionPool, String email) throws DatabaseException {
        String sql = "DELETE FROM \"User\" WHERE \"user_email\" = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
    }
}
