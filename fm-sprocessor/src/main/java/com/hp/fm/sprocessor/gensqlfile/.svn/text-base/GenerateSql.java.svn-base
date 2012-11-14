package com.hp.fm.sprocessor.gensqlfile;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class GenerateSql
{
    private static Logger _log = Logger.getLogger(GenerateSql.class);

    /**
     * @param args
     * @throws Exception 
     */
    public static void transXmlToSql(String sourceFolderPath, String targetFolderPath, String sqlFileName, int multiple)
        throws Exception
    {
        Date dateBegin = new Date();
        _log.info("begin execute generate sql....");
        File sourceFolder = new File(sourceFolderPath);
        File targetFolder = new File(targetFolderPath);
        if (!sourceFolder.exists())
        {
            throw new Exception("sourceFolder:" + sourceFolder.getAbsolutePath() + " doesn't exit");
        }
        if (!targetFolder.exists())
        {
            targetFolder.mkdirs();
        }

        //        if (sqlFile.exists())
        //        {
        //            throw new Exception("destSqlFile exist");
        //        }
        //        sqlFile.createNewFile();
        FileFilter fileFilter = new FileFilter()
        {
            public boolean accept(File file)
            {
                String tmp = file.getName();
                if (Pattern.compile("prepare_\\w+Test_\\d{2}\\.xml").matcher(tmp).matches())
                {
                    return true;
                }
                return false;
            }
        };
        Map<String, String> ts = new HashMap<String, String>();
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            ts.put("[now]", sdf.format(now));
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.MINUTE, -32);
            ts.put("[Timeout]", sdf.format(c.getTime()));
            c.setTime(now);
            c.add(Calendar.MINUTE, -30);
            ts.put("[thirtyMinutesAgo]", sdf.format(c.getTime()));
            c.setTime(now);
            c.add(Calendar.HOUR_OF_DAY, -1);
            ts.put("[oneHourAgo]", sdf.format(c.getTime()));
        }
        File[] allXmlFiles = sourceFolder.listFiles(fileFilter);
        _log.info("find " + allXmlFiles.length + " xml source files");
        //for example prepare_CustomerServiceTest_01.xml,prepare_CustomerServiceTest_02.xml
        //will merge to CustomerServiceTest.xml
        List<XmlFile> mergedXmlFile = new ArrayList<XmlFile>();
        for (int i = 0; i < allXmlFiles.length; i++)
        {
            XmlFile xmlFile = TransforXmlFile.changeId(allXmlFiles[i]);
            xmlFile = TransforXmlFile.replaceId(xmlFile);
            xmlFile = TransforXmlFile.replaceTs(xmlFile, ts);
            TransforXmlFile.merge(xmlFile, mergedXmlFile);
        }
        _log.info("merged to  " + mergedXmlFile.size() + " xml  files");
        Set<String> allTableNames = new HashSet<String>();
        for (XmlFile temp : mergedXmlFile)
        {
            Util.getAllTables(temp, allTableNames);
        }
        Map<String, Map<String, UniqueColumns>> allAks = new HashMap<String, Map<String, UniqueColumns>>();
        Map<String, Map<String, String>> allFks = new HashMap<String, Map<String, String>>();
        _log.info("begin get aks and fks");
        for (String s : allTableNames)
        {
            _log.info("begin get aks and fks for table " + s);
            Util.addAksForTable(s, allAks);
            Util.addFksForTable(s, allFks);
            _log.info("end get aks and fks for table " + s);
        }
        _log.info("end get aks and fks");
        for (int m = 1; m <= multiple; m++)
        {
            _log.info("begin replaceId with uuid in multiple " + m);
            Date onceDateBegin = new Date();
            for (int i = 0; i < mergedXmlFile.size(); i++)
            {
                XmlFile xmlFile = TransforXmlFile.replaceId(mergedXmlFile.get(i));
                mergedXmlFile.set(i, xmlFile);
            }
            _log.info("end replaceId with uuid in multiple " + m);
            _log.info("begin replace repeat in multiple " + m);
            TransforXmlFile.replaceRepeat(mergedXmlFile, allAks, allFks);
            _log.info("end replace repeat in multiple " + m);
            TransforXmlFile.generateSql(mergedXmlFile, targetFolder, sqlFileName, m);
            Date onceDateEnd = new Date();
            _log.info("have completed generateSql for " + m + "st time ");
            _log.info("spend time(seconds) for " + m + "st time: "
                    + ((onceDateEnd.getTime() - onceDateBegin.getTime()) / 1000));
        }
        _log.info("generate sql from xml have done");
        Date dateEnd = new Date();
        _log.info("spend time(seconds) : " + ((dateEnd.getTime() - dateBegin.getTime()) / 1000));
    }

    public static void main(String args[])
    {
        GenerateSql generateSql = new GenerateSql();
        try
        {
            generateSql.transXmlToSql(
                    "C:/projects/fms/fm-shared-integration/src/test/resources/_test_migration/service_api/prepare/",
                    "C:/tmp/sqls", "allservice", 5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
