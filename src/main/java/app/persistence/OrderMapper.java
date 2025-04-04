package app.persistence;

import app.entities.BasketItem;
import app.entities.Order;
import app.entities.itemTypes.eatables.Cupcake;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.Main.connectionPool;

public class OrderMapper {
    public static List<Order> getAllOrders() throws DatabaseException {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM \"Order\" ORDER BY order_id DESC";

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

    public static void removeOrder(int orderId) throws DatabaseException {
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

    public static void addOrder(String email, String orderStatus, String paymentType, List<BasketItem> items) throws DatabaseException {
        String orderSQL = "INSERT INTO \"Order\" (order_date, user_email, order_status, payment_type) VALUES (?, ?, ?, ?) RETURNING order_id";
        String productSQL = "INSERT INTO \"Product\" (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement psOrder = connection.prepareStatement(orderSQL)) {

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                timestamp.setNanos(0);

                psOrder.setTimestamp(1, timestamp);
                psOrder.setString(2, email);
                psOrder.setString(3, orderStatus);
                psOrder.setString(4, paymentType);

                ResultSet rs = psOrder.executeQuery();

                int orderId = 0;
                if (rs.next()) {
                    orderId = rs.getInt("order_id");
                }

                for (BasketItem item : items) {
                    try (PreparedStatement psItem = connection.prepareStatement(productSQL)) {

                        int productId = -1;
                        int quantity = item.getQuantity();

                        if (item.getItem() instanceof Cupcake) {
                            productId = CupcakeMapper.getCupcakeId(((Cupcake) item.getItem()).getCupcakeBottom().getId(), ((Cupcake) item.getItem()).getCupcakeTop().getId());
                        }

                        psItem.setInt(1, orderId);
                        psItem.setInt(2, productId);
                        psItem.setInt(3, quantity);
                        psItem.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
    }

    public static List<BasketItem> getOrderByOrderID(int orderId) throws DatabaseException {
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

                    CupcakeBottom cupcakeBottom = CupcakeMapper.getCupcakeBottomById(bottomId);
                    CupcakeTop cupcakeTop = CupcakeMapper.getCupcakeTopById(topId);
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

    public static List<Order> getUserOrders(String customerEmail) throws DatabaseException {
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
