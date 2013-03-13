/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.study.freemark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTemplate
{
    public static void main(final String[] args)
        throws IOException, TemplateException
    {
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File("src/test/resources/templates"));
        Template temp = cfg.getTemplate("templateExample.ftl");

        Map<String, Object> root = new HashMap<String, Object>();
        List<String> nameList = new ArrayList<String>();
        nameList.add("Jack");
        nameList.add("Rajesh");
        nameList.add("Alex");
        root.put("nameList", nameList);

        Map<String, String> price = new HashMap<String, String>();
        price.put("apple", "3");
        price.put("orange", "5");
        price.put("berry", "10");
        root.put("priceMap", price);

        List<String> nameSet = new ArrayList<String>();
        nameSet.add("Jack");
        nameSet.add("Rajesh");
        nameSet.add("Alex");
        root.put("nameSet", nameSet);

        root.put("nowDate", new Date());
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("src/test/resources/templates/templateExample.txt")));
        temp.process(root, out);
        out.flush();
        out.close();
    }
}
