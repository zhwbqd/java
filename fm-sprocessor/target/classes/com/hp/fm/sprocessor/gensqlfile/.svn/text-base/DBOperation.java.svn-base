package com.hp.fm.sprocessor.gensqlfile;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.hp.fm.sprocessor.properties.PropertiesLoader;

public class DBOperation
{
    public static String driverClassName;

    public static String url;

    public static String username;

    public static String password;

    public DBOperation()
        throws Exception
    {

    }

    public static Connection getConnection()
        throws Exception
    {
        if (driverClassName == null || username == null || password == null)
        {
            PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
            InputStream is = DBOperation.class.getResourceAsStream("/dbconfig/database.properties");
            propertiesLoader.loadProperties(is);
            driverClassName = propertiesLoader.getProperty("database.driverClassName");
            url = propertiesLoader.getProperty("database.url");
            username = propertiesLoader.getProperty("database.username");
            password = propertiesLoader.getProperty("database.password");
        }
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, username, password);
    }

    public static ResultSet executeQuery(String sql)
        throws Exception
    {
        Statement statement = getConnection().createStatement();
        return statement.executeQuery(sql);
    }
}
