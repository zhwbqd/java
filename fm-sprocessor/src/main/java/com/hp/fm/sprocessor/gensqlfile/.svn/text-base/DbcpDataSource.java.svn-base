package com.hp.fm.sprocessor.gensqlfile;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * @author space
 * @date Aug 12, 2008 3:25:49 PM
 *
 * dbcp 实用类，提供了dbcp连接，不允许继承；
 * 
 * 该类需要有个地方来初始化 DS ，通过调用initDS 方法来完成，可以在通过调用带参数的构造函数完成调用， * 可以在其它类中调用，也可以在本类中加一个static{}来完成；
 */
public final class DbcpDataSource
{
    private static Logger _logger = Logger.getLogger(DbcpDataSource.class);
    /** 数据源，static */
    private static DataSource DS;

    /** 从数据源获得一个连接 */
    public static Connection getConn()
    {
        Connection con = null;
        if (DS != null)
        {
            try
            {
                con = DS.getConnection();
            }
            catch (Exception e)
            {
                _logger.error("failed to get connection from db", e);
            }

            try
            {
                con.setAutoCommit(true);
            }
            catch (SQLException e)
            {
                _logger.error("failed to setAutoCommit(true)", e);
            }
        }
        else
        {

        }
        return con;
    }



    /** 构造函数，初始化了 DS ，指定 所有参数 */
    public DbcpDataSource(String connectURI, String username, String pswd, String driverClass)
    {
            initDS(connectURI, username, pswd, driverClass);
    }

    /** 
     * 指定所有参数连接数据源
     * 
     * @param connectURI 数据库
     * @param username 用户名
     * @param pswd 密码
     * @param driverClass 数据库连接驱动名
     * @param initialSize 初始连接池连接个数
     * @param maxActive 最大激活连接数
     * @param maxIdle 最大闲置连接数
     * @param maxWait 获得连接的最大等待毫秒数
     * @return
     * @throws PropertyVetoException 
     */
    public static void initDS(String connectURI, String username, String pswd, String driverClass)
    {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUsername(username);
        ds.setPassword(pswd);
        ds.setUrl(connectURI);
        ds.setInitialSize(15);
        ds.setMaxActive(2000);
        ds.setMaxIdle(20);
        ds.setMaxWait(10000);
        DS = ds;
    }

    /** 获得数据源连接状态 */
    public static Map<String, Integer> getDataSourceStats()
        throws SQLException
    {
        BasicDataSource bds = (BasicDataSource)DS;
        Map<String, Integer> map = new HashMap<String, Integer>(2);
        map.put("active_number", bds.getNumActive());
        map.put("idle_number", bds.getMinIdle());
        return map;
    }

    /** 关闭数据源 */
    protected static void shutdownDataSource()
        throws SQLException
    {
        BasicDataSource bds = (BasicDataSource)DS;
        bds.close();
    }

}
