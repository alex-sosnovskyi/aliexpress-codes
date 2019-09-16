package ua.pl.alex.aliexpress.codes.dao.impl;

import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.dao.CrudDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr on 12.09.2019.
 */
public class CouponDaoImpl  extends CrudDao<Coupon> {
    private final String READ_ALL = "Select * from coupon";
    private CouponDaoImpl(Class type) {
        super(type);
    }
    private static CouponDaoImpl crudDao;


    public static CouponDaoImpl getInstance() {
        if (crudDao == null) {
            crudDao = new CouponDaoImpl(Coupon.class);
        }
        return crudDao;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Coupon entity) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Coupon entity) throws SQLException {
        return null;
    }

    @Override
    protected List<Coupon> readAll(ResultSet resultSet) throws SQLException {
        List<Coupon> coupons = new ArrayList<>();
        Coupon couponTmp = null;
//        List<Coupon> result = null;
//        try (Connection connection = DataSourceHikariImpl.getInstance().getConnection();
//             //PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)
//
//        ) {
//            preparedStatement.setInt(1, key);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            result = readAll(resultSet);
//        } catch (SQLException e) {
//        }
//
//        //hall_name, hall_rows, hall_row_seats, hall_seats_total, movie_house
//
        while (resultSet.next()) {
            couponTmp = new Coupon(resultSet.getString("name"), resultSet.getString("description"),
                    resultSet.getString("code"), resultSet.getString("link"),
                    resultSet.getString("region"), resultSet.getDate("dayOn"), resultSet.getDate("dayOf"));
            coupons.add(couponTmp);
        }
        return coupons;
    }
}
