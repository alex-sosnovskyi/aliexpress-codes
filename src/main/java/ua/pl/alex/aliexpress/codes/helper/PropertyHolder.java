package ua.pl.alex.aliexpress.codes.helper;

import java.util.Properties;

/**
 * Created by Admin on 01.04.2017.
 */
public class PropertyHolder {
    private static PropertyHolder propertyHolder;
    private boolean isInMemory;
    private String dbUrl;
    private String dbUserLogin;
    private String dbUserPassword;
    private String dbDriver;
    private String mainId;

    private PropertyHolder() {
        loadProperties();
    }

    public static PropertyHolder getInstance() {
        if (propertyHolder == null) {
            propertyHolder = new PropertyHolder();
        }
        return propertyHolder;
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(PropertyHolder.class.getClassLoader().getResourceAsStream("application.properties"));
            this.mainId = properties.getProperty("mainId");
            this.isInMemory = Boolean.valueOf(properties.getProperty("isInMemory"));
            this.dbDriver = properties.getProperty("dbDriver");
            this.dbUrl = properties.getProperty("jdbcUrl");
            this.dbUserLogin = properties.getProperty("dbUserLogin");
            this.dbUserPassword = properties.getProperty("dbUserPassword");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMainId() {
        return mainId;
    }

    public boolean isInMemory() {
        return isInMemory;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUserLogin() {
        return dbUserLogin;
    }

    public String getDbUserPassword() {
        return dbUserPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }
}
