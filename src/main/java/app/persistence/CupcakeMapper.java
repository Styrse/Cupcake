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

        String sqlBottom = "SELECT * FROM \"Cupcake_bottom\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sqlBottom)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int bottom_id = rs.getInt("cupcake_bottom_id");
                    String bottom_flavor = rs.getString("cupcake_bottom_flavor");
                    float bottom_cost_price = rs.getFloat("cupcake_bottom_cost_price");
                    float bottom_sales_price = rs.getFloat("cupcake_bottom_sales_price");
                    boolean bottom_gluten_free = rs.getBoolean("cupcake_bottom_gluten_free");
                    int bottom_calories = rs.getInt("cupcake_bottom_calories");
                    String bottom_description = rs.getString("cupcake_bottom_description");
                    String bottom_path = rs.getString("cupcake_bottom_path");

                    CupcakeBottom bottom = new CupcakeBottom(
                            bottom_cost_price,
                            bottom_sales_price,
                            bottom_calories, bottom_description,
                            bottom_gluten_free,
                            bottom_id,
                            bottom_flavor,
                            bottom_path
                    );
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

        String sqlTop = "SELECT * FROM \"Cupcake_top\"";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sqlTop)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int top_id = rs.getInt("cupcake_top_id");
                    String top_flavor = rs.getString("cupcake_top_flavor");
                    float top_cost_price = rs.getFloat("cupcake_top_cost_price");
                    float top_sales_price = rs.getFloat("cupcake_top_sales_price");
                    boolean top_gluten_free = rs.getBoolean("cupcake_top_gluten_free");
                    int top_calories = rs.getInt("cupcake_top_calories");
                    String top_description = rs.getString("cupcake_top_description");
                    String top_path = rs.getString("cupcake_top_path");

                    CupcakeTop top = new CupcakeTop(
                            top_cost_price,
                            top_sales_price,
                            top_calories,
                            top_description,
                            top_gluten_free,
                            top_id,
                            top_flavor,
                            top_path
                    );
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

    public static Cupcake getCupcakeId(int bottomId, int topId){
        Cupcake cupcake = null;

        String sql = "SELECT cupcake_id " +
                "FROM \"Cupcake\" " +
                "WHERE bottom_id=? " +
                "AND top_id=?";

        return cupcake;
    }
}