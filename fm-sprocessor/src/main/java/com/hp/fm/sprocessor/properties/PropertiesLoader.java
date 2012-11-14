package com.hp.fm.sprocessor.properties;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesLoader
{
    private static Logger _logger = Logger.getLogger(PropertiesLoader.class);

    private static Properties properties;

    private static PropertiesLoader propertiesLoader = new PropertiesLoader();

    private PropertiesLoader()
    {
        properties = new Properties();
        InputStream is = PropertiesLoader.class.getResourceAsStream("/dbconfig/database.properties");
        try
        {
            properties.load(is);
        }
        catch (Exception e)
        {
            _logger.error("failed to load database.properties", e);
        }
    }

    public static String getProperty(String propertyName)
    {
        return propertiesLoader.properties.getProperty(propertyName);
    }
}
