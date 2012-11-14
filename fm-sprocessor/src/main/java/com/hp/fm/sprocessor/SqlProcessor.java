/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.fm.sprocessor;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.hp.fm.sprocessor.gensqlfile.GenerateSql;

public class SqlProcessor
{
    private static Logger _logger = Logger.getLogger(SqlProcessor.class);

    private static final String PROJECT_LOCATION = "projectBaseDir";

    private static final String SQL_TARGET_LOCATION = "sqlTargetDir";

    private static final String SQL_SOURCE_LOCATION = "prepareXmlDir";

    private static final String MULTIPLE = "multiple";

    private static final String DEFAULT_TARGET_LOCATION = "rm-perf-test" + File.separator + "scripts" + File.separator
            + "database" + File.separator + "service_limit_test_sql";

    private static final String DEFAULT_SOURCE_LOCATION = "fm-shared-integration" + File.separator + "src"
            + File.separator + "test" + File.separator + "resources" + File.separator + "_test_migration"
            + File.separator + "service_api" + File.separator + "prepare";

    private static final String SQL_FILE_NAME = "sqlFileName";

    private static final String DEFAULT_SQL_FILE_NAME = "allservice";

    public static void execute(Properties properties)
    {
        if (System.getProperty("skipGenerateSqls") != null
                && System.getProperty("skipGenerateSqls").equalsIgnoreCase("true"))
        {
            _logger.info("Skip Sql-Processor flag detected so scripts will not be regenerated.");
            return;
        }
        try
        {
            String baseDir = properties.getProperty(PROJECT_LOCATION);

            //base dir the auto-gen loadrunner sql file should be located in 
            String sqlTargetDir = properties.getProperty(SQL_TARGET_LOCATION).toString().trim().length() == 0 ? (baseDir
                    + File.separator + DEFAULT_TARGET_LOCATION + File.separator)
                    : properties.getProperty(SQL_TARGET_LOCATION);

            String prepareXmlDir = properties.getProperty(SQL_SOURCE_LOCATION).toString().trim().length() == 0 ? (baseDir
                    + File.separator + DEFAULT_SOURCE_LOCATION + File.separator)
                    : properties.getProperty(SQL_SOURCE_LOCATION);

            String sqlFileName = properties.getProperty(SQL_FILE_NAME).toString().trim().length() == 0 ? DEFAULT_SQL_FILE_NAME
                    : properties.getProperty(SQL_FILE_NAME);
            String multiple = properties.getProperty(MULTIPLE);
            if (multiple == null)
            {
                multiple = "1";
                _logger.warn("multiple must be specificed,default 1 is used");
            }
            int multipleInt = Integer.parseInt(multiple);

            _logger.info("*************Sql Processor*************");
            _logger.info("Sql file name-->" + sqlFileName);
            _logger.info("Base Dir-->" + baseDir);
            _logger.info("sql Target Dir-->" + sqlTargetDir);
            _logger.info("Prepare data xml-->" + prepareXmlDir);
            _logger.info("sql data multiple-->" + multipleInt);


            processXmlDataSet(prepareXmlDir, sqlTargetDir, sqlFileName, multipleInt);

        }
        catch (Exception e)
        {
            _logger.error("\nException:" + e);
        }

    }

    private static void processXmlDataSet(String prepareXmlDir, String sqlTargetDir, String sqlFileName, int multiple)
        throws Exception
    {
        GenerateSql.transXmlToSql(prepareXmlDir, sqlTargetDir, sqlFileName, multiple);

    }

    //    public static void main(String args[])
    //        throws Exception
    //    {
    //        GenerateSql.transXmlToSql(
    //                "C:/projects/fms/fm-shared-integration/src/test/resources/_test_migration/service_api/prepare",
    //                "c:/tmp/sql", "allservice", 10);
    //    }
}
