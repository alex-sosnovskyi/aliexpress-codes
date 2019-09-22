package ua.pl.alex.aliexpress.codes.datasource;

//import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 01.04.2017.
 */
public class DataSourceHikariImpl {
    private static DataSourceHikariImpl dataSource;
     private static ComboPooledDataSource poolConnections;
   // private static DataSource hikariDatasource;

    private DataSourceHikariImpl() {
        initPoolConnections();
    }

    public static synchronized DataSourceHikariImpl getInstance() {
        if (dataSource == null) {
            dataSource = new DataSourceHikariImpl();
        }
        return dataSource;
    }

    public Connection getConnection() {
        //-----------------simple connection------------------------------
        Connection connection = null;
//        try {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coupons?useSSL=false&useUnicode=true&characterEncoding=utf-8",
//                    "root", "");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //---------------c3p0 pool connection--------------------------------------------------------
        try {
          connection = poolConnections.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            connection = hikariDatasource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //---------------------------------------------------------------------------

        return connection;
    }

    private static void initPoolConnections() {
        //-------------------c3p0-----------------------------------------
        poolConnections = new ComboPooledDataSource();
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
        //-------------------------------------------------------------------------------------
//        HikariConfig config = new HikariConfig();
//        config.setDriverClassName("com.mysql.jdbc.Driver");
//       // config.setDriverClassName("org.postgresql.Driver");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/coupons?useSSL=false&useUnicode=true&characterEncoding=utf-8");
//        //postgres://lnoyasrqdovtyu:b2bc40396271151843208aa49918591bb24acaa80744c635029b35547a4e2e96@ec2-54-221-238-248.compute-1.amazonaws.com:5432/dd981om64u9ubo
//      //  config.setJdbcUrl("jdbc:postgresql://");
//        config.setUsername("root");
//        config.setPassword("");
//        config.setMaximumPoolSize(10);
//        config.setAutoCommit(false);
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

//        hikariDatasource = new HikariDataSource(config);
    }
}
