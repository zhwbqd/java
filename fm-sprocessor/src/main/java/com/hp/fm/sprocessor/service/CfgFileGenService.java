/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package com.hp.fm.sprocessor.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hp.fm.sprocessor.VelocityTransformer;

/**
 * Generate configuration file Service
 */
public class CfgFileGenService
{

    public static void generateCfgFile(VelocityTransformer vt, String methodPath, Method method, String className,
            String dependecyJarLocation, String searchEnumName, String version, Set<String> nonValCol)
        throws IOException, IllegalArgumentException, SecurityException, ClassNotFoundException,
        IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        vt.clearContext();

        File cfgFile = new File(methodPath + "default.cfg");
        BufferedWriter cfgWriter = new BufferedWriter(new FileWriter(cfgFile));
        vt.addValueToContext("DEPclassPath", dependecyJarLocation);
        vt.addValueToContext("LDclassPath", configClassPath(dependecyJarLocation, version));
        vt.renderTemplate("defaultCfg.vm", cfgWriter);
        cfgWriter.close();

        vt.clearContext();
        File uspFile = new File(methodPath + "default.usp");
        BufferedWriter uspWriter = new BufferedWriter(new FileWriter(uspFile));
        vt.renderTemplate("defaultUsp.vm", uspWriter);
        uspWriter.close();

        vt.clearContext();
        File usrFile = new File(methodPath + "FMS_" + className + "_" + method.getName() + ".usr");
        BufferedWriter usrWriter = new BufferedWriter(new FileWriter(usrFile));
        vt.addValueToContext("serviceName", className);
        vt.addValueToContext("methodName", method.getName());
        vt.renderTemplate("usr.vm", usrWriter);
        usrWriter.close();

        vt.clearContext();
        File prmFile = new File(methodPath + "FMS_" + className + "_" + method.getName() + ".prm");
        BufferedWriter prmWriter = new BufferedWriter(new FileWriter(prmFile));
        generatePrmFile(method, vt, prmWriter, JavaFileGenService.getAllEnumByName(searchEnumName, nonValCol));
        prmWriter.close();
    }

    private static Object configClassPath(String dependecyJarLocation, String version)
    {
        StringBuilder ldClasspath = new StringBuilder();
        ldClasspath.append(dependecyJarLocation + "fm-shared-business-" + version + ".jar").append(";");
        ldClasspath.append(dependecyJarLocation + "fm-shared-db-" + version + ".jar").append(";");
        ldClasspath.append(dependecyJarLocation + "fm-shared-utility-" + version + ".jar").append(";");
        return ldClasspath.toString();
    }

    private static void generatePrmFile(Method method, VelocityTransformer vt, BufferedWriter prmWriter,
            Map<String, String> map)
        throws IOException
    {
        if (JavaFileGenService.jpaDaoFlagJudge(method))
        {
            map.put("UserAccount", "USER_ACCOUNT_ID");
        }
        for (Entry<String, String> temp : map.entrySet())
        {
            vt.clearContext();
            vt.addValueToContext("parameter", temp.getValue().toUpperCase());
            vt.renderTemplate("prm.vm", prmWriter);
        }
        vt.clearContext();
    }
}
