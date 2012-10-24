import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

public class StudyMap
{

    /**
     * Description goes here.
     *
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args)
        throws InterruptedException
    {
        Properties scopedDataSet = new Properties();
        try
        {
            scopedDataSet.load(StudyMap.class.getClassLoader().getResourceAsStream("predefinedTestScope.properties"));
        }
        catch (IOException e)
        {
            //
        }
        Map<String, ArrayList<String>> predefinedScopeTableMap = new HashMap<String, ArrayList<String>>();
        for (Entry<Object, Object> entry : scopedDataSet.entrySet())
        {
            ArrayList<String> tables = new ArrayList<String>();
            for (String talbeName : ((String)entry.getValue()).split(","))
            {
                tables.add(talbeName);
            }
            predefinedScopeTableMap.put((String)entry.getKey(), tables);
        }
        Iterator it = predefinedScopeTableMap.entrySet().iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
        System.out.println(predefinedScopeTableMap.get("CDO"));
    }

}
