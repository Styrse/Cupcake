package app.persistence;

import app.entities.itemTypes.eatables.Cupcake;
import app.entities.itemTypes.eatables.CupcakeBottom;
import app.entities.itemTypes.eatables.CupcakeTop;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CupcakeMapper {
    public static List<CupcakeBottom> getCupcakeBottoms(ConnectionPool connectionPool) throws DatabaseException {
        List<CupcakeBottom> bottoms = new ArrayList<>();

        String sqlBottom = "SELECT * from \"Cupcake_bottom\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sqlBottom)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String bottom_flavour = rs.getString("cupcake_bottom_flavour");
                    float bottom_cost_price = rs.getFloat("cupcake_bottom_cost_price");
                    float bottom_sales_price = rs.getFloat("cupcake_bottom_sales_price");
                    boolean bottom_gluten_free = rs.getBoolean("cupcake_bottom_gluten_free");
                    int bottom_calories = rs.getInt("cupcake_bottom_calories");
                    String bottom_description = rs.getString("cupcake_bottom_description");
                    String bottom_path = rs.getString("cupcake_bottom_path");

                    CupcakeBottom bottom = new CupcakeBottom(bottom_flavour,
                            bottom_cost_price,
                            bottom_sales_price,
                            bottom_gluten_free,
                            bottom_calories,
                            bottom_description,
                            bottom_path);
                    bottoms.add(bottom);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return bottoms;
    }

    public static List<CupcakeTop> getCupcakeTops(ConnectionPool connectionPool) throws DatabaseException {
        List<CupcakeTop> tops = new ArrayList<>();

        String sqlTop = "SELECT * from \"Cupcake_top\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sqlTop)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String top_flavour = rs.getString("cupcake_top_flavour");
                    float top_cost_price = rs.getFloat("cupcake_top_cost_price");
                    float top_sales_price = rs.getFloat("cupcake_top_sales_price");
                    boolean top_gluten_free = rs.getBoolean("cupcake_top_gluten_free");
                    int top_calories = rs.getInt("cupcake_top_calories");
                    String top_description = rs.getString("cupcake_top_description");

                    CupcakeTop top = new CupcakeTop(top_flavour,
                            top_cost_price,
                            top_sales_price,
                            top_gluten_free,
                            top_calories,
                            top_description);
                    tops.add(top);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error executing query");
        }
        return tops;
    }

    public static List<Cupcake> cupcakeBuilder(ConnectionPool connectionPool) throws DatabaseException{
        List<Cupcake> cupcakes = new ArrayList<>();

            for (CupcakeBottom bottom : getCupcakeBottoms(connectionPool)) {
                for (CupcakeTop top : getCupcakeTops(connectionPool)) {
                    cupcakes.add(new Cupcake(bottom, top));
                }
            }
        return cupcakes;
    }
}