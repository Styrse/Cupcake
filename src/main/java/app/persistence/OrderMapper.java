package app.persistence;

import app.entities.Order;

public class OrderMapper {
    public static void insertOrder(Order order) {
        String email = order.getCustomerEmail();
        String sql = "INSERT INTO \"Order\" (user_email, order_status, payment_type) VALUES (?, ?)";
    }
}
