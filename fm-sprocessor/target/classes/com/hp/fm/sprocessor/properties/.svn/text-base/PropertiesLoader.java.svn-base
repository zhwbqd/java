package com.hp.fm.sprocessor.properties;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader
{
    private static PropertiesLoader propertiesLoader = new PropertiesLoader();

    private static Properties properties;

    private PropertiesLoader()
    {
        properties = new Properties();
    }

    public static PropertiesLoader getInstance()
    {
        return propertiesLoader;
    }

    public void loadProperties(InputStream is)
        throws Exception
    {
        properties.load(is);
    }

    public String getProperty(String propertyName)
    {
        return properties.getProperty(propertyName);
    }
}
