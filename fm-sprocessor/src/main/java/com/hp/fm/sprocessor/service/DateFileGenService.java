/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.fm.sprocessor.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class DateFileGenService
{
    private static Logger _logger = Logger.getLogger(DateFileGenService.class);

    public static Set<String> generateDataFile(String className, String searchEnumName, String sqlFolder,
            String location)
        throws Exception
    {
        //filter the non-value column such as system table
        Set<String> nonValCol = new HashSet<String>();

        // load the Enum class and get enum value list
        String enumClass = "com.hp.fm.searchenum." + searchEnumName;
        List<String> searchColumnList = prepareSearchEnum(enumClass);

        // find the sql file , get all the sql statements
        String fileName = selectSQLFileByClass(sqlFolder, className);
        List<String> allInsertLines = getAllLinesInSqlFile(sqlFolder, fileName);

        int length = allInsertLines.size();
        if (length > 0)
        {
            // for each enum value , generate a dat file in the location
            for (int len = 0; len < searchColumnList.size(); len++)
            {
                Set<String> col_val = new HashSet<String>();
                for (int i = 0; i < length; i++)
                {
                    String result = getColumnValue(allInsertLines.get(i), searchColumnList.get(len));
                    if (null != result)
                    {
                        col_val.add(result);
                    }
                }
                if (col_val.isEmpty())
                {
                    nonValCol.add(searchColumnList.get(len));
                }
                else
                {
                    generateDatFile(location, searchColumnList.get(len), col_val);
                }
            }

            // for userAccount , generate the user_account_id.dat file
            Set<String> user_account_ids = new HashSet<String>();
            for (int i = 0; i < length; i++)
            {
                String result = getColumnValue(allInsertLines.get(i), "USER_ACCOUNT_ID");
                if (null != result)
                {
                    user_account_ids.add(result);
                }
            }
            generateDatFile(location, "USER_ACCOUNT_ID", user_account_ids);
        }
        return nonValCol;
    }

    private static String selectSQLFileByClass(String sqlFolder, String className)
        throws Exception

    {
        File directory = new File(sqlFolder);
        if (!directory.exists())
        {
            _logger.error("the sql folder is not found.");
        }
        FileFilter fileFilter = new FileFilter()
        {
            private boolean isSQL(String file)
            {
                if (file.toLowerCase().endsWith(".sql"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

            private boolean isNotDir(File file)
            {
                if (file.isDirectory())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            public boolean accept(File file)
            {
                return (isSQL(file.getName()) && isNotDir(file));
            }
        };
        File[] allFile = directory.listFiles(fileFilter);
        String sqlFileName = null;
        for (int i = 0; i < allFile.length; i++)
        {
            String fileName = allFile[i].getName();
            String[] parts = fileName.split("\\.");
            if (parts.length == 2 && parts[0].contains(className))
            {
                sqlFileName = fileName;
                break;
            }
        }
        if (null == sqlFileName)
        {
            _logger.error("the SQL file not found!");
            throw new Exception("Stop generate this method script because data leakage");
        }
        else
        {
            return sqlFileName;
        }

    }

    @SuppressWarnings({"rawtypes"})
    private static List<String> prepareSearchEnum(String searchEnum)
        throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
        IllegalAccessException, InvocationTargetException
    {
        List<String> allEnums = new ArrayList<String>();
        Class search = Class.forName(searchEnum);
        Method values = search.getMethod("values", null);
        Object[] r = (Object[])values.invoke(null);
        for (Object object : r)
        {
            Method aa = object.getClass().getMethod("getName", null);
            String result = ((String)aa.invoke(object)).toUpperCase();
            if (!result.endsWith("_TS") && !result.endsWith("_DT"))
            {
                allEnums.add(result.toUpperCase());
            }
        }
        return allEnums;
    }

    // search the sql file by line
    private static List<String> getAllLinesInSqlFile(String folder, String fileName)
        throws Exception
    {
        List<String> lstStatement = new ArrayList<String>();
        if (fileName != null)
        {
            LineNumberReader reader = null;
            File sqlFile = new File(folder + File.separator + fileName);
            FileInputStream input = new FileInputStream(sqlFile);
            reader = new LineNumberReader(new InputStreamReader(input, "UTF-8"));
            String line = null;
            StringBuilder statement = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                line = line.trim();
                if (!line.startsWith("--"))
                {
                    if (line.endsWith(";"))
                    {
                        statement.append(line.substring(0, line.length() - 1));
                        lstStatement.add(statement.toString());
                        statement = new StringBuilder();
                    }
                    else
                    {
                        statement.append(line);
                    }
                }
            }
        }
        return lstStatement;
    }

    // search the insert statement for column
    private static String getColumnValue(String statement, String searchColumnName)
        throws Exception
    {
        String tableValues = null;

        String[] statementPart = statement.split("values");
        String columnStatement = statementPart[0].substring(statementPart[0].indexOf('(') + 1,
                statementPart[0].lastIndexOf(')'));
        String valueStatement = statementPart[1].substring(statementPart[1].indexOf('(') + 1,
                statementPart[1].lastIndexOf(')'));

        String[] columns = columnStatement.split(",");
        valueStatement = valueStatement.replaceAll("to_date *\\(", "").replaceAll(", *'YYYY-MM-DD HH24:MI:SS'\\)", "")
                .replaceAll(", *'YYYY-MM-DD'\\)", "");
        // remove the first '
        valueStatement = valueStatement.substring(1, valueStatement.length());
        // remove the last '
        valueStatement = valueStatement.substring(0, valueStatement.length() - 1);
        // split with ',' and get values
        String[] values = valueStatement.split("','");
        // check the columns and values length
        if (columns.length == values.length)
        {
            for (int i = 0; i < columns.length; i++)
            {
                //filter the column
                if (searchColumnName.equals(columns[i].trim()))
                {
                    tableValues = ("".equals(values[i].trim()) || "null".equals(values[i].trim())) ? null : values[i]
                            .trim();
                }
            }
        }
        return tableValues;
    }

    private static void generateDatFile(String location, String columnName, Set<String> values)
        throws IOException
    {
        File newDatFile = new File(location + File.separator + columnName + ".dat");
        BufferedWriter datWriter = new BufferedWriter(new FileWriter(newDatFile));
        datWriter.append(columnName);
        datWriter.newLine();
        for (String value : values)
        {
            datWriter.append(value);
            datWriter.newLine();
        }
        datWriter.close();
    }
}
