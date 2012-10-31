package com.hp.it.server.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ProjectContext
{
	private static Map<String, Properties> projectContextMap = new HashMap<String, Properties>();

	public static synchronized Properties getProperties(String identity)
	{
		return projectContextMap.get(identity);
	}

	public static synchronized Map<String, Properties> getContext()
	{
		return projectContextMap;
	}

	public static synchronized void setProperties(String identity, Properties properties)
	{
		projectContextMap.put(identity, properties);
	}

	private ProjectContext()
	{
	}

}
