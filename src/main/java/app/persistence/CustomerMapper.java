package app.persistence;

import app.entities.userRoles.Admin;
import app.entities.userRoles.Customer;
import app.entities.userRoles.Employee;
import app.entities.userRoles.User;
import app.exceptions.DatabaseException;

import java.sql.*;

public class CustomerMapper {
    public static User findUser(ConnectionPool connectionPool, String login_email, String login_password) throws DatabaseException {
        User user = null;

        String sql = "SELECT * FROM \"Customer\" WHERE customer_email=? AND customer_password=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                String firstname;
                String lastname;
                String email;
                Date birthday;
                String password;
                float balance;
                boolean isEmployee;
                boolean isAdmin;

                while (rs.next()){
                    int customerID = rs.getInt("customer_id");
                    firstname = rs.getString("customer_firstname");
                    lastname = rs.getString("customer_lastname");
                    email = rs.getString("customer_email");
                    birthday = rs.getDate("customer_birthday");
                    password = rs.getString("customer_password");
                    balance = rs.getFloat("customer_balance");
                    isEmployee = rs.getBoolean("isEmployee");
                    isAdmin = rs.getBoolean("isAdmin");

                }

                if (isEmployee){
                    if (isAdmin){
                        user = new Admin(firstname, email, password);
                    } else {
                        user = new Employee(firstname, email, password);
                    }
                } else {
                    user = new Customer(firstname, email, password, customerID, getOrders(customerID), balance)
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return user;
    }
}
