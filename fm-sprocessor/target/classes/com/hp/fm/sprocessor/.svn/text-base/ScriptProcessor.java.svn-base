/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package com.hp.fm.sprocessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import com.hp.fm.sprocessor.service.CfgFileGenService;
import com.hp.fm.sprocessor.service.DateFileGenService;
import com.hp.fm.sprocessor.service.JavaFileGenService;

public class ScriptProcessor
{
    private static Logger _logger = Logger.getLogger(ScriptProcessor.class);

    private static final String PROJECT_LOCATION = "projectBaseDir";

    private static final String PROJECT_VERSION = "projectVersion";

    private static final String SCRIPT_TARGET_LOCATION = "scriptBaseDir";

    private static final String SQL_TARGET_LOCATION = "sqlTargetDir";

    private static final String JAR_FILE_LOCATION = "jarFileLocation";

    private static final String SCRIPT_LOADRUNNER_LOCATION = "scriptLoadrunnerLocation";

    private static final String VUSER_PER_METHOD = "vUserPerMethod";

    private static final String DEP_JAR_FILE_LOCATION = "dependencyJarLocation";

    private static final String DEFAULT_TARGET_LOCATION = "rm-perf-test" + File.separator + "scripts" + File.separator
            + "database" + File.separator + "service_limit_test_sql";

    private static final String DEFAULT_SCRIPT_LOCATION = "rm-perf-test" + File.separator + "scripts" + File.separator
            + "loadrunner" + File.separator + "service_limit_test";

    private static final String DEFAULT_JAR_FILE_LOCATION = "rm-xsp-war" + File.separator + "target" + File.separator
            + "rm-xsp" + File.separator + "WEB-INF" + File.separator + "lib";

    private static final String DEFAULT_DEP_JAR_FILE_LOCATION = "C:\\LR\\CriteriaSearch-LoadTest\\lib\\";

    private static final String DEFAULT_SCRIPT_LOADRUNNER_LOCATION = "C:\\LR\\CriteriaSearch-LoadTest\\";

    private static final int DEFAULT_VUSER_PER_METHOD = 5;

    /**
     * Generate LoadRunner Script Interface.
     *
     * @param clazz
     * @param scriptBaseDir
     * @param fullVersionNumber
     * @param dependecyJarLocation
     * @param sqlTargetDir2 
     * @param sqlBaseDir
     * @param searchSet
     * @throws Exception
     */
    public static void processInterface(final Class<?> clazz, final String scriptBaseDir,
            final String fullVersionNumber, String dependecyJarLocation, String sqlTargetDir,
            Set<Entry<Object, Object>> searchSet)
        throws Exception
    {
        //create a root script folder if it not exists
        File loadRunnerScriptDir = new File(scriptBaseDir);
        if (!loadRunnerScriptDir.exists())
        {
            loadRunnerScriptDir.mkdirs();
        }
        Method[] methods = clazz.getMethods();
        String serviceName = clazz.getSimpleName();
        for (Method method : methods)
        {
            //only generate limit test script for search method and parameter contains FmCriterion 
            if (method.getName().startsWith("search"))
            {
                Class[] paramters = method.getParameterTypes();
                for (Class parameter : paramters)
                {
                    if (parameter.getSimpleName().equals("FmCriterion"))
                    {
                        generateScriptByConfiguration(serviceName, method, scriptBaseDir, sqlTargetDir,
                                fullVersionNumber, dependecyJarLocation, searchSet);
                    }
                }
            }
        }
    }

    private static void generateScriptByConfiguration(String className, Method method, String scriptBaseDir,
            String sqlTargetDir, String fullVersionNumber, String dependecyJarLocation,
            Set<Entry<Object, Object>> searchSet)
        throws Exception
    {
        try
        {
            VelocityTransformer vt = new VelocityTransformer();
            String methodName = method.getName();

            //only generate the file with sql data prepared
            if (checkSqlFileExist(sqlTargetDir, className))
            {
                //generate a series loadrunner script for one method 
                String methodPath = scriptBaseDir + className + "_" + methodName + File.separator;
                File methodFolder = new File(methodPath);
                methodFolder.mkdirs();

                String searchEnumName = "";
                //get matched enum from Set
                for (Entry<Object, Object> entry : searchSet)
                {
                    String fullKey = entry.getKey().toString();
                    String _className = fullKey.substring(0, fullKey.indexOf("."));
                    String _methodName = fullKey.substring(fullKey.indexOf(".") + 1);
                    if (_className.equals(className) && _methodName.equals(methodName))
                    {
                        searchEnumName = (String)entry.getValue();
                        break;
                    }
                }

                Set<String> nonValCol = DateFileGenService.generateDataFile(className, searchEnumName, sqlTargetDir,
                        methodPath);

                JavaFileGenService.generateJaveFile(className, vt, methodPath, method, fullVersionNumber,
                        searchEnumName, nonValCol);

                CfgFileGenService.generateCfgFile(vt, methodPath, method, className, dependecyJarLocation,
                        searchEnumName, fullVersionNumber, nonValCol);
            }
            else
            {
                _logger.info("This class and method do not have sql data prepared: " + className + " "
                        + method.getName());
            }
        }

        catch (Exception e)
        {

            throw new Exception("Exception while generate Scripts", e);
        }
    }

    public static void execute(Properties properties)

    {
        if (System.getProperty("skipGenerateScripts") != null
                && System.getProperty("skipGenerateScripts").equalsIgnoreCase("true"))
        {
            _logger.info("Skip S-Processor flag detected so scripts will not be regenerated.");
            return;
        }

        String baseDir = properties.getProperty(PROJECT_LOCATION);

        //the autogen loadrunner java file should be located in 
        String scriptBaseDir = properties.getProperty(SCRIPT_TARGET_LOCATION).toString().trim().length() == 0 ? (baseDir
                + File.separator + DEFAULT_SCRIPT_LOCATION + File.separator)
                : properties.getProperty(SCRIPT_TARGET_LOCATION);

        String jarLocation = properties.getProperty(JAR_FILE_LOCATION).toString().trim().length() == 0 ? (baseDir
                + File.separator + DEFAULT_JAR_FILE_LOCATION + File.separator) : properties
                .getProperty(JAR_FILE_LOCATION);

        //default dependency jar file also location on "rm-xsp-war/target/rm-xsp/WEB-INF/lib"
        String dependencyJarLocation = properties.getProperty(DEP_JAR_FILE_LOCATION).toString().trim().length() == 0 ? (baseDir
                + File.separator + DEFAULT_DEP_JAR_FILE_LOCATION + File.separator)
                : properties.getProperty(DEP_JAR_FILE_LOCATION);

        //base dir the auto-gen loadrunner sql file should be located in which generate dat file will use
        String sqlTargetDir = properties.getProperty(SQL_TARGET_LOCATION).toString().trim().length() == 0 ? (baseDir
                + File.separator + DEFAULT_TARGET_LOCATION + File.separator) : properties
                .getProperty(SQL_TARGET_LOCATION);

        String projectVersion = getProjectVersion(jarLocation, properties);

        int vUserPerMethod = properties.getProperty(VUSER_PER_METHOD).toString().trim().length() == 0 ? DEFAULT_VUSER_PER_METHOD
                : Integer.valueOf(properties.getProperty(VUSER_PER_METHOD));

        String loadrunnerLocation = properties.getProperty(SCRIPT_LOADRUNNER_LOCATION).toString().trim().length() == 0 ? DEFAULT_SCRIPT_LOADRUNNER_LOCATION
                : properties.getProperty(SCRIPT_LOADRUNNER_LOCATION);

        _logger.info("*************Script Processor*************");
        _logger.info("Full Version Number-->" + projectVersion);
        _logger.info("Base Dir-->" + baseDir);
        _logger.info("Script Target Dir-->" + scriptBaseDir);
        _logger.info("Generate Sql Base Dir-->" + sqlTargetDir);
        _logger.info("FMS Jar File Location-->" + jarLocation);

        _logger.info("*************Loadrunner Processor*************");
        _logger.info("Virtual User Per Script-->" + vUserPerMethod);
        _logger.info("Loadrunner User Per Script-->" + loadrunnerLocation);
        _logger.info("FMS Dependecy Jar File Location-->" + dependencyJarLocation);


        //load all search method mapping with search enum from properties
        Properties searchProperties = new Properties();
        InputStream inStream = ScriptProcessor.class.getResourceAsStream("/searchEnumLists.properties");

        try
        {
            searchProperties.load(inStream);
            inStream.close();
            loadJarToClassPath(baseDir, jarLocation);
        }
        catch (IOException e)
        {
            _logger.error(e.getStackTrace());
        }
        Set<Entry<Object, Object>> searchSet = searchProperties.entrySet();

        //load the service class
        Set<Class<?>> classes = getClasses("com.hp.fm.service", "Service");
        for (Class<?> clazz : classes)
        {
            try
            {
                if (!clazz.isInterface())
                {
                    processInterface(clazz, scriptBaseDir, projectVersion, dependencyJarLocation,
                            sqlTargetDir, searchSet);
                }
                else
                    _logger.info("This " + clazz.getName() + " is a Interface.");
            }
            catch (Exception e)
            {
                _logger.error(e.getMessage());
            }
        }
        _logger.info("Finish generate the loadrunner script!");
        try
        {
            _logger.info("begin generate the loadrunner scenario!");
            processScenario(scriptBaseDir, vUserPerMethod, loadrunnerLocation);
            _logger.info("finish generate the loadrunner scenario!");
        }
        catch (Exception e)
        {
            _logger.error(e.getMessage());
        }

    }

    private static void processScenario(String scriptBaseDir, int vUserPerMethod, String loadrunnerLocation)
        throws Exception
    {
        Map<String, String> usrFileMap = getMethodInfoFromFolder(scriptBaseDir, loadrunnerLocation);

        VelocityTransformer vt = new VelocityTransformer();
        File lrsFile = new File(scriptBaseDir + File.separator + "search_scenario.lrs");
        BufferedWriter lrsWriter = new BufferedWriter(new FileWriter(lrsFile));
        vt.addValueToContext("scriptNum", usrFileMap.size()); //loop folder
        vt.addValueToContext("totalVUserNum", usrFileMap.size() * vUserPerMethod); //scriptnum*vUserPerMethdo

        vt.addValueToContext("usrFileMap", usrFileMap); //key = methodName value = methodFullPath

        vt.addValueToContext("vUserPerMethod", new Object[vUserPerMethod]); //load from properties

        vt.renderTemplate("lsrGen/lsr.vm", lrsWriter);
        vt.renderTemplate("lsrGen/testChief.vm", lrsWriter);
        vt.renderTemplate("lsrGen/groupChief.vm", lrsWriter);
        lrsWriter.close();

    }

    private static Map<String, String> getMethodInfoFromFolder(String scriptBaseDir, String loadrunnerLocation)
    {
        Map<String, String> usrFileMap = new TreeMap<String, String>();
        File rootFolder = new File(scriptBaseDir);
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
                        String replacedDir = file.getAbsolutePath().replace(scriptBaseDir, loadrunnerLocation)
                                .replaceAll("/", "\\\\");
                        usrFileMap.put(file.getName(), replacedDir);
                        _logger.info("ori dir: " + scriptBaseDir + " replace dir: " + loadrunnerLocation
                                + " replaced dir: " + replacedDir);
                    }
                }
                else
                {
                    _logger.error("Method Folder not exists, can not generate lrs File");
                }
            }
        }
        else
        {
            _logger.error("Root Folder not exists, can not generate lrs File");
        }
        return usrFileMap;
    }

    private static String getProjectVersion(String jarFileLocation, Properties properties)
    {
        File jarFileFolder = new File(jarFileLocation);
        String version = null;
        if (jarFileFolder.isDirectory())
        {
            for (String filename : jarFileFolder.list(new FilenameFilter()
            {
                public boolean accept(File dir, String name)
                {
                    if (name.startsWith("fm-shared"))
                    {
                        return true;
                    }
                    return false;
                };
            }))
            {
                String tmp = filename.substring(10, filename.length() - 4);
                version = tmp.substring(tmp.indexOf("-") + 1);
                break;
            }
        }
        else
        {
            _logger.error(jarFileLocation + " is not a directory");
            version = properties.getProperty(PROJECT_VERSION);
        }
        return version;
    }

    private static void loadJarToClassPath(String baseDir, String jarLocation)
        throws IOException
    {
        File jarFolder = new File(jarLocation);
        if (jarFolder.isDirectory())
        {
            for (String jar : jarFolder.list())
            {
                ClassPathHack.addFile(jarLocation + jar);
            }
        }
        else
        {
            _logger.error(jarLocation + " is not a directory");
        }
    }

    /**
     * Find all class in jar file and end with "Character" to oriented the specific class 
     * if want to get whole classes ,please leave the Character "" 
     *
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String pack, String endCharacter)
    {

        // defined a Set
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

        boolean recursive = true;

        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try
        {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

            while (dirs.hasMoreElements())
            {

                URL url = dirs.nextElement();

                String protocol = url.getProtocol();

                if ("file".equals(protocol))
                {
                    _logger.info("file");

                    System.err.println("need to get classes from jar");
                }
                else if ("jar".equals(protocol))
                {
                    _logger.info("jar file");
                    JarFile jar;
                    try
                    {

                        jar = ((JarURLConnection)url.openConnection()).getJarFile();

                        Enumeration<JarEntry> entries = jar.entries();

                        while (entries.hasMoreElements())
                        {

                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();

                            if (name.charAt(0) == '/')
                            {

                                name = name.substring(1);
                            }

                            if (name.startsWith(packageDirName))
                            {
                                int idx = name.lastIndexOf('/');

                                if (idx != -1)
                                {

                                    packageName = name.substring(0, idx).replace('/', '.');
                                }

                                if ((idx != -1) || recursive)
                                {

                                    if (name.endsWith(".class") && !entry.isDirectory())
                                    {

                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        if (className.endsWith(endCharacter))
                                        {
                                            try
                                            {
                                                _logger.info("add class into Set: " + className);
                                                classes.add(Thread.currentThread().getContextClassLoader()
                                                        .loadClass(packageName + '.' + className));
                                            }
                                            catch (ClassNotFoundException e)
                                            {
                                                _logger.info("Can not find defined class file" + e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (IOException e)
                    {
                        _logger.error("Can't get file from jar file");
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return classes;
    }

    private static boolean checkSqlFileExist(String sqlFolder, String className)
        throws Exception

    {
        boolean isExist = false;
        File directory = new File(sqlFolder);
        if (!directory.exists())
        {
            _logger.error("the sql folder is not found.");
            return isExist;
        }
        FileFilter fileFilter = new FileFilter()
        {
            private boolean isSQL(String file)
            {
                if (file.toLowerCase().endsWith(".sql"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

            private boolean isNotDir(File file)
            {
                if (file.isDirectory())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            public boolean accept(File file)
            {
                return (isSQL(file.getName()) && isNotDir(file));
            }
        };
        File[] allFile = directory.listFiles(fileFilter);
        String sqlFileName = null;
        for (int i = 0; i < allFile.length; i++)
        {
            String fileName = allFile[i].getName();
            String[] parts = fileName.split("\\.");
            if (parts.length == 2 && className.equals(parts[0]))
            {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

}
