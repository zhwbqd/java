package com.hp.fm.sprocessor.gensqlfile;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TransforXmlFile
{
    private static Logger _logger = Logger.getLogger(TransforXmlFile.class);

    public static XmlFile changeId(File file)
        throws Exception
    {
        XmlFile xmlFile = new XmlFile();
        xmlFile.setFile(file);
        String xmlContent = xmlFile.getXmlContent();
        String text = xmlFile.getText();
        SAXReader saxReader = new SAXReader();
        saxReader.setEncoding("utf-8");
        Document doc = saxReader.read(new StringReader(xmlFile.getXmlContent()));
        List<Element> list = doc.selectNodes("//dataset");
        Element dataNode = list.get(0);
        List<Element> alltalbes = dataNode.elements();
        for (int j = 0; j < alltalbes.size(); j++)
        {
            Element element = alltalbes.get(j);
            String sourceId = Util.getIdVluae(element);
            if (sourceId == null)
            {
                continue;
            }
            String newidValue = element.getName() + "_" + sourceId;
            element.setAttributeValue(element.getName() + "_ID", newidValue);
            xmlContent = xmlContent.replace(element.getName() + "_ID=\"" + sourceId + "\"", element.getName()
                    + "_ID=\"" + newidValue + "\"");
            text = text.replace(element.getName() + "_ID=\"" + sourceId + "\"", element.getName() + "_ID=\""
                    + newidValue + "\"");
        }
        xmlFile.setXmlContent(xmlContent);
        xmlFile.setText(text);
        return xmlFile;
    }

    /**
     * replace id with uuid
     * @param xmlFile
     * @return
     * @throws Exception
     */
    public static XmlFile replaceId(XmlFile xmlFile)
        throws Exception
    {
        String xmlContent = xmlFile.getXmlContent();
        String text = xmlFile.getText();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new StringReader(xmlFile.getXmlContent()));
        List<Element> list = doc.selectNodes("//dataset");
        Element dataNode = list.get(0);
        List<Element> alltalbes = dataNode.elements();
        for (int j = 0; j < alltalbes.size(); j++)
        {
            Element element = alltalbes.get(j);
            String sourceId = Util.getIdVluae(element);
            if (sourceId == null)
            {
                continue;
            }
            String newidValue = Util.getUUID();
            newidValue = Util.subIdRight(element.getName(), element.getName() + "_ID", newidValue);
            element.setAttributeValue(element.getName() + "_ID", newidValue);
            xmlContent = xmlContent.replace(element.getName() + "_ID=\"" + sourceId + "\"", element.getName()
                    + "_ID=\"" + newidValue + "\"");
            text = text.replace(element.getName() + "_ID=\"" + sourceId + "\"", element.getName() + "_ID=\""
                    + newidValue + "\"");
        }
        xmlFile.setXmlContent(xmlContent);
        xmlFile.setText(text);
        return xmlFile;
    }

    public static XmlFile replaceTs(XmlFile xmlFile, Map<String, String> ts)
        throws Exception
    {
        String xmlText = xmlFile.getXmlContent();
        String text = xmlFile.getText();
        for (Map.Entry<String, String> entry : ts.entrySet())
        {
            xmlText = xmlText.replace(entry.getKey(), entry.getValue());
            text = text.replace(entry.getKey(), entry.getValue());
        }
        xmlFile.setXmlContent(xmlText);
        xmlFile.setText(text);
        return xmlFile;
    }

    public static void merge(XmlFile xmlFile, List<XmlFile> mergedXmlFile)
    {
        boolean exit = false;
        String serviceName = xmlFile.getServiceClassName();
        XmlFile serviceXmlfile = null;
        for (XmlFile temp : mergedXmlFile)
        {
            if (temp.getServiceClassName().equals(serviceName))
            {
                exit = true;
                serviceXmlfile = temp;
                break;
            }
        }
        if (exit)
        {
            serviceXmlfile.addContentForXml(xmlFile.getText());
        }
        else
        {
            mergedXmlFile.add(xmlFile);
        }

    }

    public static void replaceRepeat(List<XmlFile> mergedXmlFile, Map<String, Map<String, UniqueColumns>> allAks,
            Map<String, Map<String, String>> allFks)
        throws Exception
    {
        for (XmlFile temp : mergedXmlFile)
        {
            Util.replaceRepeat(temp, allAks, allFks);
        }
    }

    public static void generateSql(List<XmlFile> mergedXmlFile, File targetFolder, String mergeFileName, int m)
        throws Exception
    {
        for (XmlFile temp : mergedXmlFile)
        {
            Util.generateSQL(temp, targetFolder, m);
        }
        File file = new File(targetFolder.getAbsolutePath() + "/" + mergeFileName + ".sql");
        PrintWriter pwPrintWriter = new PrintWriter(new FileWriter(file, true));
        for (XmlFile temp : mergedXmlFile)
        {
            pwPrintWriter.println("@" + temp.getServiceClassName() + "_" + m + ".sql");
            pwPrintWriter.println("commit;");
        }
        pwPrintWriter.flush();
        pwPrintWriter.close();

    }

}
