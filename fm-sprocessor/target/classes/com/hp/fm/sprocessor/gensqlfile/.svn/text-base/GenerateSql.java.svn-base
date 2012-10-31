package com.hp.fm.sprocessor.gensqlfile;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class GenerateSql
{
    private static Logger _log = Logger.getLogger(GenerateSql.class);

    /**
     * @param args
     * @throws Exception 
     */
    public static void transXmlToSql(String sourceFolderPath, String targetFolderPath, String sqlFileName)
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
        List<XmlFile> mergedXmlFile = new ArrayList<XmlFile>();
        for (int i = 0; i < allXmlFiles.length; i++)
        {
            XmlFile xmlFile = TransforXmlFile.changeId(allXmlFiles[i]);
            xmlFile = TransforXmlFile.replaceId(xmlFile);
            xmlFile = TransforXmlFile.replaceTs(xmlFile, ts);
            TransforXmlFile.merge(xmlFile, mergedXmlFile);
        }
        TransforXmlFile.replaceRepeat(mergedXmlFile);
        TransforXmlFile.generateSql(mergedXmlFile, targetFolder, sqlFileName);
        _log.info("generate sql from xml have done");
        Date dateEnd = new Date();
        _log.info("spend time(seconds) : " + ((dateEnd.getTime() - dateBegin.getTime()) / 1000));
    }
}
