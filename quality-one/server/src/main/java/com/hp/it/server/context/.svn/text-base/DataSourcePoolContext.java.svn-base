package com.hp.it.server.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.hp.it.encrypt.EncryptUtil;
import com.hp.it.resource.ds.DataSourceFactory;
import com.hp.it.server.configuration.ArtifactReportConstant;

public class DataSourcePoolContext
{
	private static Map<PoolIdentifier, DataSource> pools = new HashMap<PoolIdentifier, DataSource>();

	public static synchronized DataSource getDataSource(String identity)
	{
		Properties properties = ProjectContext.getProperties(identity);
		if (properties == null)
		{
			// If there is no such properties bind to the kee
			// throw exception
			// TODO
		}

		String driverName = properties.getProperty(ArtifactReportConstant.DB_DRIVER);
		String url = properties.getProperty(ArtifactReportConstant.DB_URL);
		String userName = properties.getProperty(ArtifactReportConstant.DB_USER);
		String password = new EncryptUtil().decrypt(properties.getProperty(ArtifactReportConstant.DB_PWD));

		PoolIdentifier key = new PoolIdentifier();
		key.setUrl(url);
		key.setUser(userName);
		key.setPassword(password);

		DataSource ds = pools.get(key);
		if (ds == null)
		{
			ds = DataSourceFactory.getDataSource(driverName, url, userName, password);
			pools.put(key, ds);
		}
		return ds;
	}

	public static synchronized boolean destory()
	{
		return false;
		// TODO
	}

	static class PoolIdentifier
	{
		private String url;

		private String user;

		private String password;

		public String getUrl()
		{
			return url;
		}

		public void setUrl(String url)
		{
			this.url = url;
		}

		public String getUser()
		{
			return user;
		}

		public void setUser(String user)
		{
			this.user = user;
		}

		public String getPassword()
		{
			return password;
		}

		public void setPassword(String password)
		{
			this.password = password;
		}

		@Override
		public int hashCode()
		{
			int i = 0;
			String param = getUrl() + getUser() + getPassword();
			return param.hashCode();
		}

		@Override
		public boolean equals(Object obj)
		{
			if (!(obj instanceof PoolIdentifier))
			{
				return false;
			}
			PoolIdentifier object = (PoolIdentifier) obj;
			if (getUrl().equalsIgnoreCase(object.getUrl()) && getUser().equalsIgnoreCase(object.getUser())
					&& getPassword().equalsIgnoreCase(object.getPassword()))
			{
				return true;
			}
			return false;
		}
	}

	private DataSourcePoolContext()
	{
	}
}
