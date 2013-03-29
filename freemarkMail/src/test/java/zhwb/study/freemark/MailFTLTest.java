/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.freemark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MailFTLTest
{

    public static void main(final String[] args)
        throws Exception
    {
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File("src/test/resources/mailTemplate"));
        Template temp = cfg.getTemplate("email.ftl");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("expiringWithIn", "90");
        root.put("SChomePageUrl", "www.hp.com");

        List<Map<String, Object>> supportAgreements = new ArrayList<Map<String, Object>>();
        Map<String, Object> sa1 = new HashMap<String, Object>();
        sa1.put("sar", "HP Server");
        sa1.put("said", "12345");
        sa1.put("obid", "54321");
        sa1.put("packageDescription", "desc");
        sa1.put("creditsQty", "30");
        sa1.put("expiredDate", "45");

        Map<String, Object> sa2 = new HashMap<String, Object>();
        sa2.put("sar", "HP Printer");
        sa2.put("said", "11-11");
        sa2.put("obid", "22-22");
        sa2.put("packageDescription", "desc");
        sa2.put("creditsQty", "90");
        sa2.put("expiredDate", "70");

        supportAgreements.add(sa1);
        supportAgreements.add(sa2);
        root.put("supportAgreement", supportAgreements);
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("src/test/resources/templates/email.html")));
        temp.process(root, out);
        out.flush();

    }

}
