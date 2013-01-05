/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.sbs.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTest
{
    public static void main(String[] args)
        throws IOException, TemplateException
    {
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File("src/test/resources/templates"));
        Template temp = cfg.getTemplate("example.ftl");
        /* 创建数据模型 */
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("user", "Big Joe");
        Map<String, Object> latest = new HashMap<String, Object>();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        /* 将模板和数据模型合并 */
        //        Writer out = new OutputStreamWriter(System.out);
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("src/test/resources/templates/result.txt")));
        temp.process(root, out);
        out.flush();
    }
}
