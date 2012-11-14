package com.hp.fm.sprocessor.gensqlfile;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Util
{
    private static Logger _logger = Logger.getLogger(Util.class);

    private static Map<String, Integer> sequence = new HashMap<String, Integer>();
    public static String getNewId(XmlFile xmlFile, Element element, String sourceId)
    {
        return xmlFile.getServiceClassName() + "_" + xmlFile.getVersion() + "_" + element.getName() + "_" + sourceId;
    }

    public static String getIdVluae(Element element)
    {
        String name = element.getName();
        String idValue = null;
        Attribute id = element.attribute(name + "_ID");
        if (id == null)
        {
            return null;
        }
        else
        {
            idValue = id.getStringValue();
            return idValue;
        }
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void addAksForTable(String tableName, Map<String, Map<String, UniqueColumns>> allAks)
        throws Exception
    {
        String sql = "SELECT * FROM ALL_CONS_COLUMNS WHERE table_name = '" + tableName
                + "'  AND CONSTRAINT_NAME LIKE 'AK%' order by CONSTRAINT_NAME";
        List<Map<String, String>> rSet = DBOperation.executeQuery(sql);
        Map<String, String> allconstraint = new HashMap<String, String>();
        String constraint_name = "";
        String column_name = "";
        Iterator<Map<String, String>> iterator = rSet.iterator();
        while (iterator.hasNext())
        {
            Map<String, String> row = iterator.next();
            String constraint = row.get("CONSTRAINT_NAME");
            String column = row.get("COLUMN_NAME");
            if ("".equals(constraint_name))
            {
                constraint_name = constraint;
                column_name += column;
            }
            else
            {
                if (constraint.equals(constraint_name))
                {
                    column_name += "," + column;
                }
                else
                {
                    allconstraint.put(constraint_name, column_name);
                    constraint_name = constraint;
                    column_name = column;
                }
            }
        }
        if (constraint_name.equals(""))
        {
            return;
        }
        allconstraint.put(constraint_name, column_name);
        Map<String, UniqueColumns> allconstraintUniqueColumns = new HashMap<String, UniqueColumns>();
        for (Map.Entry<String, String> oneconstraint : allconstraint.entrySet())
        {
            Map<String, List<String>> uniqueColumnsValues = new HashMap<String, List<String>>();
            UniqueColumns uniqueColumns = new UniqueColumns();
            String oneconstraintName = oneconstraint.getKey();
            String oneconstraintValue = oneconstraint.getValue();
            String[] columns = oneconstraintValue.split(",");
            for (int i = 0; i < columns.length; i++)
            {
                uniqueColumnsValues.put(columns[i], new ArrayList<String>());
            }
            uniqueColumns.setUniqueColumnsValues(uniqueColumnsValues);
            allconstraintUniqueColumns.put(oneconstraintName, uniqueColumns);
        }

        allAks.put(tableName, allconstraintUniqueColumns);
    }

    public static void addFksForTable(String tableName, Map<String, Map<String, String>> allFks)
        throws Exception
    {
        String sql = "SELECT * FROM ALL_CONS_COLUMNS WHERE table_name = '" + tableName
                + "'  AND CONSTRAINT_NAME LIKE 'FK%' order by CONSTRAINT_NAME";
        List<Map<String, String>> rSet = DBOperation.executeQuery(sql);
        Iterator<Map<String, String>> iterator = rSet.iterator();
        Map<String, String> aTableFks = new HashMap<String, String>();
        while (iterator.hasNext())
        {
            Map<String, String> row = iterator.next();
            String fkName = row.get("CONSTRAINT_NAME");
            String fkValue = row.get("COLUMN_NAME");
            aTableFks.put(fkName, fkValue);
        }
        allFks.put(tableName, aTableFks);
    }

    public static String judgeWhichColumnReplace(UniqueColumns uniqueColumns, Map<String, String> aTableFks,
            String exclude)
        throws Exception
    {
        Map<String, List<String>> uniqueColumnsValues = uniqueColumns.getUniqueColumnsValues();
        Set<String> akColumns = uniqueColumnsValues.keySet();
        Collection<String> fkColumns = aTableFks.values();
        String columnWillReplace = null;
        for (String s : akColumns)
        {
            if (!s.equals(exclude))
            {
                if (!fkColumns.contains(s))
                {
                    columnWillReplace = s;
                    return columnWillReplace;
                }
            }
        }
        if (exclude != null)
        {
            return exclude;
        }
        _logger.info("cann't judge which column will replace,akColumns:" + akColumns);
        throw new Exception("cann't judge which column will replace");
    }

    public static void getAllTables(XmlFile xmlFile, Set<String> allTables)
        throws Exception
    {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new StringReader(xmlFile.getXmlContent()));
        List<Element> list = doc.selectNodes("//dataset");
        Element dataNode = list.get(0);
        List<Element> alltalbes = dataNode.elements();
        for (int j = 0; j < alltalbes.size(); j++)
        {
            allTables.add(alltalbes.get(j).getName());
        }
    }

    public static String getXmlContentFormXml(String xml)
        throws Exception
    {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new StringReader(xml));
        List<Element> list = doc.selectNodes("//dataset");
        Element dataNode = list.get(0);
        List<Element> alltalbes = dataNode.elements();
        String xmlcontent = "";
        for (int j = 0; j < alltalbes.size(); j++)
        {
            xmlcontent += alltalbes.get(j).asXML();
        }
        return xmlcontent;
    }

    /**
     * Map<String, Map<String, UniqueColumns>> allAks
     *      |           |          |
     *      tableName  akName      |
     *                             |
     *                        Map<String, List<String>>
     *                             |           |
     *                         columns Of ak   |
     *                                      column value collection
     * @param temp
     * @param allAks
     * @param allFks
     * @throws Exception
     */
    public static void replaceRepeat(XmlFile temp, Map<String, Map<String, UniqueColumns>> allAks,
            Map<String, Map<String, String>> allFks)
        throws Exception
    {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new StringReader(temp.getXmlContent()));
        List<Element> list = doc.selectNodes("//dataset");
        Element dataNode = list.get(0);
        List<Element> alltalbes = dataNode.elements();
        for (int j = 0; j < alltalbes.size(); j++)
        {
            Element element = alltalbes.get(j);
            String aTableName = alltalbes.get(j).getName();
            Map<String, String> aTableFks = allFks.get(aTableName);
            if (allAks.keySet().contains(aTableName))
            {
                Map<String, UniqueColumns> contraints = allAks.get(aTableName);
                for (Map.Entry<String, UniqueColumns> entry : contraints.entrySet())
                {
                    String contraintName = entry.getKey();
                    UniqueColumns uniqueColumns = entry.getValue();
                    Set<String> allColumns = uniqueColumns.getUniqueColumnsValues().keySet();
                    Map<String, String> addValue = new HashMap<String, String>();
                    for (String s : allColumns)
                    {
                        Attribute attribute = element.attribute(s);
                        String attributeValue = attribute == null ? "" : attribute.getStringValue();
                        addValue.put(s, attributeValue);
                    }
                    //need replace
                    if (uniqueColumns.contains(addValue, aTableName))
                    {
                        //replace which column
                        String replaceColumn = "";
                        try
                        {
                            replaceColumn = Util.judgeWhichColumnReplace(uniqueColumns, aTableFks, null);
                        }
                        catch (Exception e)
                        {
                            dataNode.remove(element);
                            continue;
                        }
                        Attribute attribute = element.attribute(replaceColumn);
                        if ((attribute == null) || ("".equals(attribute.getStringValue())))
                        {
                            try
                            {
                                replaceColumn = Util.judgeWhichColumnReplace(uniqueColumns, aTableFks, replaceColumn);
                            }
                            catch (Exception e)
                            {
                                dataNode.remove(element);
                                continue;
                            }
                            attribute = element.attribute(replaceColumn);
                        }
                        
                        if (attribute == null)
                        {
                            element.addAttribute(replaceColumn, "0000");
                            attribute = element.attribute(replaceColumn);
                        }
                        _logger.info("replace source:" + element.asXML());
                        String replacedValue = Util.getAndIncrementSequence(aTableName, replaceColumn,
                                attribute.getStringValue(),
                                aTableName
                                + contraintName + replaceColumn + attribute.getStringValue());
                        element.setAttributeValue(replaceColumn, replacedValue);
                        addValue.put(replaceColumn, element.attributeValue(replaceColumn));
                        uniqueColumns.add(addValue);
                        _logger.info("replace with:  " + element.asXML());
                    }
                    //no need replace
                    else
                    {
                        uniqueColumns.add(addValue);
                    }

                }
            }
        }
        temp.setText(Util.getXmlContentFormXml(doc.asXML()));
        temp.setXmlContent(doc.asXML());

    }

    public static void generateSQL(XmlFile temp, File targetFolder, int m)
        throws Exception
    {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new StringReader(temp.getXmlContent()));
        String serviceName = temp.getServiceClassName();
        File file = new File(targetFolder.getAbsolutePath() + "/" + serviceName + "_" + m + ".sql");
        if (!file.exists())
        {
            file.createNewFile();
        }
        PrintWriter pWriter = new PrintWriter(file);
        List<Element> list = doc.selectNodes("//dataset");
        Element dataNode = list.get(0);
        List<Element> alltalbes = dataNode.elements();
        for (int j = 0; j < alltalbes.size(); j++)
        {
            String sql = generateAStatement(alltalbes.get(j));
            pWriter.println(sql);
        }
        pWriter.flush();
        pWriter.close();
        _logger.info("generate a sql file for " + serviceName);

    }

    private static String generateAStatement(Element element)
    {
        String tableName = element.getName();
        List<String> attributeNames = new ArrayList<String>();
        List<String> attributeValues = new ArrayList<String>();
        Iterator<Attribute> attributeIterator = element.attributeIterator();
        for (; attributeIterator.hasNext();)
        {
            Attribute attribute = attributeIterator.next();
            attributeNames.add(attribute.getName());
            String value = attribute.getValue() == null ? "" : attribute.getValue();
            if (value.contains("'"))
            {
                value = value.replace("'", "''");
            }
            String[] dfAndValue = getDateFormat(value);
            if (dfAndValue != null)
            {
                attributeValues.add("to_date('" + dfAndValue[1] + "','" + dfAndValue[0] + "')");
            }
            else
            {
                attributeValues.add("'" + value + "'");
            }

        }
        String sql = "insert into " + tableName + " (";
        String columns = "";
        String colValues = " values (";
        for (int i = 0; i < attributeNames.size(); i++)
        {
            String attName = attributeNames.get(i);
            String attValue = attributeValues.get(i);
            if (i == (attributeNames.size() - 1))
            {
                columns += attName + ")";
                colValues += attValue + ");";
            }
            else
            {
                columns += attName + ",";
                colValues += attValue + ",";
            }
        }
        return sql + columns + colValues;
    }

    private static String[] getDateFormat(String value)
    {
        if (value.matches("\\d{4}-\\d{2}-\\d{2}"))
        {
            return new String[] {"YYYY-MM-DD", value};
        }
        else if (value.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}"))
        {
            return new String[] {"YYYY-MM-DD HH24:MI:SS", value};
        }
        else if (value.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}.\\d+"))
        {
            return new String[] {"YYYY-MM-DD HH24:MI:SS", value.substring(0, 19)};
        }
        else
        {
            return null;
        }

    }

    public static String subIdRight(String tableName, String columnName, String newidValue)
        throws Exception
    {
        String sql = "select A.column_name clumnName,A.data_length length from user_tab_columns A where A.Table_Name = '"
                + tableName + "' and A.column_name='" + columnName + "'";
        int dblength = 0;
        List<Map<String, String>> rSet = DBOperation.executeQuery(sql);
        Iterator<Map<String, String>> iterator = rSet.iterator();
        if (iterator.hasNext())
        {
            Map<String, String> row = iterator.next();
            try
            {
                dblength = Integer.parseInt(row.get("LENGTH"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(row);
            }

        }
        return subRight(newidValue, dblength);
    }

    public static String subIdLeft(String tableName, String columnName, String newidValue)
        throws Exception
    {
        String sql = "select A.column_name clumnName,A.data_length length from user_tab_columns A where A.Table_Name = '"
                + tableName + "' and A.column_name='" + columnName + "'";
        int dblength = 0;
        List<Map<String, String>> rSet = DBOperation.executeQuery(sql);
        Iterator<Map<String, String>> iterator = rSet.iterator();
        if (iterator.hasNext())
        {
            Map<String, String> row = iterator.next();
            try
            {
                dblength = Integer.parseInt(row.get("LENGTH"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(row);
            }

        }
        return subLeft(newidValue, dblength);
    }
    public static String subLeft(String value,int dblength)
    {
        if (value.length() > dblength)
        {
            return value.substring(value.length()-dblength, value.length());
        }
        else
        {
            return value;
        }
    }
    public static String subRight(String value,int dblength)
    {
        if (value.length() > dblength)
        {
            return value.substring(0, dblength);
        }
        else
        {
            return value;
        }
    }

    public static boolean dbCheckHas(Map<String, String> oneRow, String aTableName)
        throws Exception
    {
        String sql = "select * from " + aTableName + " where ";
        for (Map.Entry<String, String> entry : oneRow.entrySet())
        {
            String colName = entry.getKey();
            String colValue = entry.getValue();
            sql += colName + "=?  and ";

        }
        sql = sql.substring(0, sql.length() - 5);
        Connection connection = DBOperation.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = 1;
        for (Map.Entry<String, String> entry : oneRow.entrySet())
        {
            String colValue = entry.getValue();
            preparedStatement.setString(i, colValue);
            i++;
        }
        ResultSet rSet = preparedStatement.executeQuery();
        if (rSet.next())
        {
            return true;
        }
        rSet.close();
        connection.close();
        return false;
    }

    /**
     * a number use to add to the end of a column to replace repeat
     * @param name   source column value 
     * @param string tableName+akName+replaceColumnName
     * @return
     * @throws Exception 
     */
    public static String getAndIncrementSequence(String tableName, String columnName, String name, String compond)
        throws Exception
    {
        if (name.endsWith("0000"))
        {
            Integer integer = sequence.get(compond);
            if (integer != null)
            {
                int temp = integer.intValue() + 1;
                sequence.put(compond, new Integer(temp));
                return subIdLeft(tableName, columnName, name + integer.intValue());
            }

            Integer integer1 = new Integer(1);
            int temp = integer1.intValue() + 1;
            sequence.put(compond, new Integer(temp));
            return subIdLeft(tableName, columnName, name + integer1.intValue());

        }
        if (name.indexOf("0000") != -1)
        {
            int index = name.indexOf("0000");
            String prex = name.substring(0, index + 4);
            String endx = name.substring(index + 4, name.length());

            int indexcompond = compond.indexOf("0000");
            String prexcompond = compond.substring(0, indexcompond + 4);
            String endxcompond = compond.substring(indexcompond + 4, compond.length());

            Integer integer = sequence.get(prexcompond);
            if (integer == null)
            {
                integer = new Integer(1);
            }
            int temp = integer.intValue() + 1;
            sequence.put(prexcompond, new Integer(temp));
            return subIdLeft(tableName, columnName, prex + integer.intValue());
        }
        Integer integer = sequence.get(compond);
        if (integer == null)
        {
            integer = new Integer(1);
        }
        int temp = integer.intValue() + 1;
        sequence.put(compond + "0000", new Integer(temp));
        sequence.put(compond, new Integer(temp));
        return subIdLeft(tableName, columnName, name + "0000" + integer.intValue());
    }
    
}
