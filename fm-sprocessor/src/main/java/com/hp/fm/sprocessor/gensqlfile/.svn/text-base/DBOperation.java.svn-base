package com.hp.fm.sprocessor.gensqlfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.fm.sprocessor.properties.PropertiesLoader;

public class DBOperation
{

    public static String driverClassName = PropertiesLoader.getProperty("database.driverClassName");

    public static String url = PropertiesLoader.getProperty("database.url");

    public static String username = PropertiesLoader.getProperty("database.username");

    public static String password = PropertiesLoader.getProperty("database.password");

    //public static int count = 0;

    public static DbcpDataSource dbcpDataSource = new DbcpDataSource(url, username, password, driverClassName);

    //    static
    //    {
    //        try
    //        {
    //            Class.forName(driverClassName);
    //        }
    //        catch (ClassNotFoundException e)
    //        {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //    }

    public DBOperation()
        throws Exception
    {

    }

    public static Connection getConnection()
    {

        //Connection conn = dbcpDataSource.getConn();

        Connection conn = null;
        try
        {

            conn = dbcpDataSource.getConn();
            //conn = DriverManager.getConnection(url, username, password);

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;

        
    }

    public static List<Map<String, String>> executeQuery(String sql)
        throws Exception
    {
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCounts = metaData.getColumnCount();
        while (rs.next())
        {
            Map<String, String> aresult = new HashMap<String, String>();
            for (int i = 1; i <= columnCounts; i++)
            {
                aresult.put(metaData.getColumnLabel(i).toUpperCase(), rs.getString(i));
            }
            results.add(aresult);
        }
        statement.close();
        conn.close();
        return results;
    }
}
