package com.hp.fm.sprocessor.gensqlfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlFile
{
    public File file;

    public String text;

    public String xmlContent;

    public String getXmlContent()
    {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent)
    {
        this.xmlContent = xmlContent;
    }

    public String serviceClassName;

    public String version;

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
        throws Exception
    {
        this.file = file;
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        String tempStr = "";
        String tempText = "";
        this.xmlContent = "";
        tempStr = br.readLine();
        //        if (!"<?xml version='1.0' encoding='UTF-8'?>".equals(tempStr.trim()))
        //        {
        //            System.out.println(tempStr.trim());
        //            throw new Exception("first line is not <?xml version='1.0' encoding='UTF-8'?> in file" + file.getName());
        //        }
        this.xmlContent += tempStr + "\n";
        tempStr = br.readLine();
        //        if (!"<dataset>".equals(tempStr.trim()))
        //        {
        //            throw new Exception("second line is not <dataset>");
        //        }
        this.xmlContent += tempStr + "\n";
        while ((tempStr = br.readLine()) != null)
        {
            if (!"</dataset>".equals(tempStr))
            {
                tempText += tempStr + "\n";
            }
        }
        setText(tempText);
        this.xmlContent += tempText;
        this.xmlContent += "</dataset>";
        try
        {
            Matcher matchar = Pattern.compile("prepare_(\\w+)Test_(\\d{2})\\.xml").matcher(file.getName());
            matchar.find();
            setServiceClassName(matchar.group(1));
            setVersion(matchar.group(2));
        }
        catch (Exception e)
        {
            //file name doesn't mathch
            setServiceClassName(null);
            setVersion(null);
        }

    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getServiceClassName()
    {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName)
    {
        this.serviceClassName = serviceClassName;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public void addContentForXml(String content)
    {
        this.setText(this.getText() + content);
        this.setXmlContent("<?xml version='1.0' encoding='UTF-8'?>" + "\n" + "<dataset>" + this.getText()
                + "</dataset>");
    }
}
