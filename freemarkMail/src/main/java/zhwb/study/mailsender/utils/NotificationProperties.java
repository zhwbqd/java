/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.mailsender.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class NotificationProperties.
 */
public final class NotificationProperties
{

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(NotificationProperties.class);

    /** PROPERTIES_MAP. */
    private final Map<String, String> propertiesMap;

    /**
     * returns property value for a given name from Security properties file.
     *
     * @param name string property name
     * @return String value of security property
     */
    public String getProperty(final String name)
    {
        return propertiesMap.get(name);
    }

    /**
     * returns property value for a given name  from Security properties file.
     *
     * @param name string property name
     * @param defaultValue returned if property is not found
     * @return String value of security property
     */
    public String getProperty(final String name, final String defaultValue)
    {
        if (getProperty(name) != null)
        {
            return propertiesMap.get(name);
        }
        else
        {
            return defaultValue;
        }
    }

    /**
     * Constructor private for SecurityProperties.
     *
     * @param propertiesName the properties name
     */
    public NotificationProperties(final String propertiesName)
    {
        InputStream fis = null;
        Map<String, String> propertiesMapTemp = new HashMap<String, String>();
        Properties securityProperties = new Properties();
        try
        {
            fis = NotificationProperties.class.getResourceAsStream(propertiesName);
            securityProperties.load(fis);

            Iterator<Entry<Object, Object>> iter = securityProperties.entrySet().iterator();
            while (iter.hasNext())
            {
                Entry<Object, Object> entry = iter.next();
                propertiesMapTemp.put((String)entry.getKey(), (String)entry.getValue());
            }

            fis.close();
        }
        catch (IOException ioe)
        {
            LOG.error("Error while reading securityService.properties file");
        }
        finally
        {
            propertiesMap = Collections.unmodifiableMap(propertiesMapTemp);
            if (fis != null)
            {
                try
                {
                    fis.close(); //close security properties
                }
                catch (IOException ioe)
                {
                    LOG.error("Error while reading persona.properties file");
                }
            }
        }
    }
}
