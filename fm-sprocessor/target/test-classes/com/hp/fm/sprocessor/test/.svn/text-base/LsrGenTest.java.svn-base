/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.fm.sprocessor.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.Map;
import java.util.TreeMap;

import com.hp.fm.sprocessor.VelocityTransformer;

public class LsrGenTest
{

    private static void generateLsrFile()
        throws Exception
    {
        String rootFolder = "C:\\tmp\\target\\script\\";
        Integer vUserPerMethod = Integer.valueOf("10");

        Map<String, String> usrFileMap = getMethodInfoFromFolder(rootFolder);

        VelocityTransformer vt = new VelocityTransformer();
        File cfgFile = new File(rootFolder + "Scenario.lrs");
        BufferedWriter cfgWriter = new BufferedWriter(new FileWriter(cfgFile));
        vt.addValueToContext("scriptNum", usrFileMap.size()); //loop folder
        vt.addValueToContext("totalVUserNum", usrFileMap.size() * vUserPerMethod); //scriptnum*vUserPerMethdo

        vt.addValueToContext("usrFileMap", usrFileMap); //key = methodName value = methodFullPath

        vt.addValueToContext("vUserPerMethod", new Object[vUserPerMethod]); //load from properties

        vt.renderTemplate("lsrGen/lsr.vm", cfgWriter);
        vt.renderTemplate("lsrGen/testChief.vm", cfgWriter);
        vt.renderTemplate("lsrGen/groupChief.vm", cfgWriter);
        cfgWriter.close();
    }

    private static Map<String, String> getMethodInfoFromFolder(String root)
    {
        Map<String, String> usrFileMap = new TreeMap<String, String>();
        File rootFolder = new File(root);
        if (rootFolder.isDirectory())
        {
            for (File secondFolder : rootFolder.listFiles())
            {
                if (secondFolder.isDirectory())
                {
                    for (File file : secondFolder.listFiles(new FilenameFilter()
                    {
                        public boolean accept(File dir, String name)
                        {
                            if (name.endsWith(".usr"))
                                return true;
                            else
                                return false;
                        }
                    }))
                    {
                        usrFileMap.put(file.getName(), file.getAbsolutePath().replace(root, "C:\\ld\\script\\"));
                        System.out.println(file.getAbsolutePath().replace(root, "C:\\ld\\script\\"));
                    }
                }
                else
                {
                    System.err.println("impossible");
                }
            }
        }
        else
        {
            System.err.println("impossible");
        }
        return usrFileMap;
    }

    public static void main(String[] args)
        throws Exception
    {
        generateLsrFile();
    }

}
