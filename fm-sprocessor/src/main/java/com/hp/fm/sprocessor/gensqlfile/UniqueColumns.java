package com.hp.fm.sprocessor.gensqlfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueColumns
{
    public Map<String, List<String>> getUniqueColumnsValues()
    {
        return uniqueColumnsValues;
    }

    public void setUniqueColumnsValues(Map<String, List<String>> uniqueColumnsValues)
    {
        this.uniqueColumnsValues = uniqueColumnsValues;
    }

    public Map<String, List<String>> uniqueColumnsValues = new HashMap<String, List<String>>();

    public void add(Map<String, String> oneRow)
    {
        for (Map.Entry<String, String> entry : oneRow.entrySet())
        {
            List<String> allColumnValues = uniqueColumnsValues.get(entry.getKey());
            if (allColumnValues == null)
            {
                allColumnValues = new ArrayList<String>();
                allColumnValues.add(entry.getValue());
            }
            else
            {
                allColumnValues.add(entry.getValue());
            }
        }
    }

    public boolean contains(Map<String, String> oneRow, String aTableName)
        throws Exception
    {
        boolean flag = true;
        int rowNum = -1;
        for (Map.Entry<String, String> entry : oneRow.entrySet())
        {
            if (Util.dbCheckHas(oneRow, aTableName))
            {
                return true;
            }
            List<String> allColumnValues = uniqueColumnsValues.get(entry.getKey());
            int index = allColumnValues.lastIndexOf(entry.getValue());

            if (index == -1)
            {
                flag = false;
                break;
            }
            else
            {
                if (rowNum == -1)
                {
                    rowNum = index;
                }
                else
                {
                    if (rowNum != index)
                    {
                        if("".equals(entry.getValue()))
                        {
                           flag = true;
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                    else
                    {
                        flag = true;
                       
                    }
                }

            }
        }
        return flag;

    }

}
