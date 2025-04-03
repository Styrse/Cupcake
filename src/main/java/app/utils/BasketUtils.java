package app.utils;

import app.entities.BasketItem;

import java.util.List;

public class BasketUtils {
    public static double getTotalPrice(List<BasketItem> list) {
        double totalPrice = 0.0;

        for (BasketItem item : list) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
