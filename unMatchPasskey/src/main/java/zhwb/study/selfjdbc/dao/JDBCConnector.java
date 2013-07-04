package zhwb.study.selfjdbc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnector
{
    private static JDBCConnector instance = new JDBCConnector();

    public static JDBCConnector getInstance()
    {
        return instance;
    }

    private Properties p;

    private JDBCConnector()
    {
        p = new Properties();
        try
        {
            p.load(JDBCConnector.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public synchronized Connection getSQLServerConnection()
        throws ClassNotFoundException, SQLException
    {
            String driver = p.getProperty("jdbc.driver.sqlserver");
            String url = p.getProperty("jdbc.url.sqlserver");
            String user = p.getProperty("jdbc.username.sqlserver");
            String password = p.getProperty("jdbc.password.sqlserver");

            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
    }

    public synchronized Connection getOracleConnection()
        throws ClassNotFoundException, SQLException
    {
            String driver = p.getProperty("jdbc.driver.oracle");
            String url = p.getProperty("jdbc.url.oracle");
            String user = p.getProperty("jdbc.username.oracle");
            String password = p.getProperty("jdbc.password.oracle");

            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
    }
}
