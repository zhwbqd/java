/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.fm.sprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ProcessorCommand
{
    public static void main(String[] args)
    {
        //load all configuration information from properties
        Properties properties = new Properties();
        InputStream inStream = null;

        inStream = ProcessorCommand.class.getResourceAsStream("/loadtest.properties");
        try
        {
            properties.load(inStream);
            inStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        SqlProcessor.execute(properties);

        ScriptProcessor.execute(properties);

    }


}
