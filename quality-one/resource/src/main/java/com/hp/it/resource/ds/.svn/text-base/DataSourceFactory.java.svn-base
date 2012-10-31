package com.hp.it.resource.ds;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public final class DataSourceFactory 
{

	public DataSourceFactory(String driverName, String url, String userName, String password)
	{
	}

	public static DataSource getDataSource(String driverName, String connectionUrl, String userName, String password)
	{
		BasicDataSource bds = new BasicDataSource();
		bds.setMaxActive(50);
		bds.setMaxIdle(20);
		bds.setDriverClassName(driverName);
		bds.setUsername(userName);
		bds.setPassword(password);
		bds.setUrl(connectionUrl);
		return bds;
	}
}