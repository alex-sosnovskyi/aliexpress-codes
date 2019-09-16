package ua.pl.alex.aliexpress.codes.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ua.pl.alex.aliexpress.codes.helper.PropertyHolder;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Admin on 01.04.2017.
 */
public class DataSource {
    private static DataSource dataSource;
    private static ComboPooledDataSource poolConnections;

    private DataSource(){
        initPoolConnections();
    }

    public static synchronized DataSource getInstance(){
        if(dataSource==null){
            dataSource=new DataSource();
        }
        return dataSource;
    }

    public Connection getConnection(){
        Connection connection=null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coupons?useSSL=false&useUnicode=true&characterEncoding=utf-8",
                     "root", "");
        }catch (SQLException e){
            e.printStackTrace();
        }
//        try {
//            poolConnections.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return connection;
    }

    private static void initPoolConnections(){
        poolConnections=new ComboPooledDataSource();
       // PropertyHolder propertyHolder= PropertyHolder.getInstance();
        try {
            //poolConnections.setDriverClass(propertyHolder.getDbDriver());
            poolConnections.setDriverClass("com.mysql.jdbc.Driver");
            //poolConnections.setJdbcUrl(propertyHolder.getDbUrl());
            poolConnections.setJdbcUrl("jdbc:mysql://localhost:3306/coupons?useSSL=false&useUnicode=true&characterEncoding=utf-8");
           // poolConnections.setUser(propertyHolder.getDbUserLogin());
            poolConnections.setUser("root");
            poolConnections.setPassword("");
            poolConnections.setMinPoolSize(5);
            poolConnections.setAcquireIncrement(1);
            poolConnections.setMaxPoolSize(100);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }
}
