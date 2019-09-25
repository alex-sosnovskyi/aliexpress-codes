package ua.pl.alex.aliexpress.codes.service.impl;

import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.datasource.DataSourceHikariImpl;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aleksandr on 20.09.2019.
 */
public class CouponAdminServiceImpl {
    // private static final String DELETE_BY_ID="DELETE FROM coupon WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id=%d";
    private static CouponAdminServiceImpl service;
    private DataSourceHikariImpl dataSource;

    private CouponAdminServiceImpl() {
        dataSource = DataSourceHikariImpl.getInstance();
    }

    public static synchronized CouponAdminServiceImpl getInstance() {
        if (service == null) {
            service = new CouponAdminServiceImpl();
        }

        return service;
    }

    public List<Coupon> getAll() {
        List<Coupon> coupons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM coupon ORDER BY rating");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String code = rs.getString("code");
                String link = rs.getString("link");
                String region = rs.getString("region");
                Date dayOn = rs.getDate("dayon");
                Date dayOf = rs.getDate("dayof");
                int rating = rs.getInt("rating");
                Coupon coupon = new Coupon(name, description, code, link, region, dayOn, dayOf);
                coupon.setId(id);
                coupon.setRating(rating);
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }

    public void removeById(String trim) {
        String sql = "DELETE FROM coupon WHERE id=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // preparedStatement.setString(1, "coupon");
            int integer = Integer.parseInt(trim);
            preparedStatement.setInt(1, integer);

            //  boolean execute = preparedStatement.execute();
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editById(Coupon coupon) {
        String sql = "UPDATE coupon SET name=?, description=?, code=?, link=?, region=?," +
                "dayOn=?, dayOf=?, rating=? WHERE id=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, coupon.getName());
            preparedStatement.setString(2, coupon.getDescription());
            preparedStatement.setString(3, coupon.getCode());
            preparedStatement.setString(4, coupon.getLink());
            preparedStatement.setString(5, coupon.getRegion());
            preparedStatement.setDate(6, java.sql.Date.valueOf(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(coupon.getStartOn()))));
            preparedStatement.setDate(7, java.sql.Date.valueOf(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(coupon.getEndOf()))));
            preparedStatement.setInt(8, coupon.getRating());
            preparedStatement.setInt(9, coupon.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Coupon coupon) {
        String sql = "INSERT INTO coupon (name, description, code, link, region, dayOn, dayOf, rating) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, coupon.getName());
            preparedStatement.setString(2, coupon.getDescription());
            preparedStatement.setString(3, coupon.getCode());
            preparedStatement.setString(4, coupon.getLink());
            preparedStatement.setString(5, coupon.getRegion());
            preparedStatement.setDate(6, java.sql.Date.valueOf(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(coupon.getStartOn()))));
            preparedStatement.setDate(7, java.sql.Date.valueOf(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(coupon.getEndOf()))));
            preparedStatement.setInt(8, coupon.getRating());
            int i = preparedStatement.executeUpdate();
            i = 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
