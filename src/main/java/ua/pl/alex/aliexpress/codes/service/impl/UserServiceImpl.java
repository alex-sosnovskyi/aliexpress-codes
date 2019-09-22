package ua.pl.alex.aliexpress.codes.service.impl;

import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.Entity.User;
import ua.pl.alex.aliexpress.codes.datasource.DataSourceHikariImpl;
import ua.pl.alex.aliexpress.codes.service.api.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aleksandr on 20.09.2019.
 */
public class UserServiceImpl implements Service<Integer, User> {
    private final  String READ_ALL_BY_LOGIN="SELECT* FROM users WHERE login=?";
    private static UserServiceImpl service;

    public static synchronized UserServiceImpl getInstance() {
        if (service == null) {
            service = new UserServiceImpl();
        }

        return service;
    }

    @Override
    public List<User> getAll() {return null;}
    @Override
    public User getById(Integer key) {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(User entity) {

    }

    public List<User> getByLogin(String login){
        List<User> userList = new ArrayList<>();
        DataSourceHikariImpl dataSource = DataSourceHikariImpl.getInstance();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_BY_LOGIN)){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String  dbLogin = resultSet.getString("login");
                String dbPassword = resultSet.getString("password");
                String permissions = resultSet.getString("permissions");

                userList.add(new User(dbLogin, dbPassword, permissions));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
