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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hp.fm.sprocessor.VelocityTransformer;

/**
 * Generate java file service
 */
public class JavaFileGenService
{
    private static Logger _logger = Logger.getLogger(JavaFileGenService.class);

    public static void generateJaveFile(String className, VelocityTransformer vt, String methodPath, Method method,
            String fullVersionNumber, String searchEnumName, Set<String> nonValCol)
        throws Exception
    {
        vt.clearContext();
        //generate init java 
        File vuser_init = new File(methodPath + "vuser_init.java");
        BufferedWriter vuser_initWriter = new BufferedWriter(new FileWriter(vuser_init));
        vuser_initWriter.newLine();
        vuser_initWriter.append("// Do not change this file");
        vuser_initWriter.close();

        //generate end java 
        File vuser_end = new File(methodPath + "vuser_end.java");
        BufferedWriter vuser_endWriter = new BufferedWriter(new FileWriter(vuser_end));
        vuser_endWriter.newLine();
        vuser_endWriter.append("// Do not change this file");
        vuser_endWriter.close();

        //generate action java 
        File javaFile = new File(methodPath + "Actions.java");
        BufferedWriter javaWriter = new BufferedWriter(new FileWriter(javaFile));

        boolean jpaDaoFlag = jpaDaoFlagJudge(method);
        Map<String, String> searchEnumMap = getAllEnumByName(searchEnumName, nonValCol);

        vt.addValueToContext("fullVersionNumber", fullVersionNumber);

        vt.addValueToContext("transactionName", "\"" + method.getName()
                + (jpaDaoFlag == true ? "_\"+" + "userAccount.getPerson().getPersonType()" : "\""));

        vt.addValueToContext("className", className);

        vt.addValueToContext("methodName", method.getName());

        vt.addValueToContext("fields", getAllFieldByMethod(method));

        vt.addValueToContext("params", getParams(method));

        vt.addValueToContext("signature", getSignature(method));

        vt.addValueToContext("searchEnumName", searchEnumName);

        vt.addValueToContext("searchEnumMap", searchEnumMap);

        vt.addValueToContext("returnType", generateReturnType(method));

        vt.addValueToContext("importPackage",
                buildImports(method, getGeneralImports(searchEnumName, className, searchEnumMap.isEmpty(), method)));

        vt.renderTemplate("actionsJava.vm", javaWriter);

        javaWriter.close();
    }

    private static String getSignature(Method method)
    {
        StringBuilder signatureList = new StringBuilder();

        @SuppressWarnings("rawtypes")
        Class[] paramClasses = method.getParameterTypes();
        int index = 0;
        for (int i = 0; i < paramClasses.length; i++)
        {
            index++;
            String className = paramClasses[i].getSimpleName();
            if (index == paramClasses.length)
            {
                signatureList
                        .append(className + " " + className.substring(0, 1).toLowerCase() + className.substring(1));
            }
            else
            {
                signatureList.append(className + " " + className.substring(0, 1).toLowerCase() + className.substring(1)
                        + ",");
            }
        }
        return signatureList.toString();
    }

    private static String getAllFieldByMethod(Method method)
    {
        StringBuilder fields = new StringBuilder();
        String newline = System.getProperty("line.separator");
        for (@SuppressWarnings("rawtypes")
        Class clazz : method.getParameterTypes())
        {

            String className = clazz.getSimpleName();
            if (className.equals("FmCriterion"))
            {
                fields.append(className + " " + className.substring(0, 1).toLowerCase() + className.substring(1)
                        + " = " + "null;");
            }
            else
            {
                String jpaDao = className + "JpaDao";
                fields.append(jpaDao + " " + jpaDao.substring(0, 1).toLowerCase() + jpaDao.substring(1) + " = "
                        + "new " + jpaDao + "();");
                fields.append(newline + "\t\t");
                fields.append(jpaDao.substring(0, 1).toLowerCase() + jpaDao.substring(1)
                        + ".setEntityManager(_entityManager);");
                fields.append(newline + "\t\t");
                fields.append(className + " " + className.substring(0, 1).toLowerCase() + className.substring(1)
                        + " = " + jpaDao.substring(0, 1).toLowerCase() + jpaDao.substring(1)
                        + ".findById(\"<USER_ACCOUNT_ID>\");");
            }
            fields.append(newline + "\t\t");
        }
        return fields.toString();
    }

    public static Map<String, String> getAllEnumByName(String searchEnumName, Set<String> nonValCol)
        throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
        NoSuchMethodException
    {
        Map<String, String> searchEnumMap = new LinkedHashMap<String, String>();
        Class<?> searchEnum = null;
        try
        {
            searchEnum = Class.forName("com.hp.fm.searchenum." + searchEnumName);
            Class<?> searchEnum2 = searchEnum;
            Object[] criteriaList = (Object[])(searchEnum2.getMethod("values", null).invoke(null));
            for (Object object : criteriaList)
            {
                Method getName = object.getClass().getMethod("getName", null);
                String enumName = object.toString();
                String colName = ((String)getName.invoke(object)).toUpperCase();
                if (!colName.endsWith("_TS") && !colName.endsWith("_DT"))
                {
                    if (!nonValCol.contains(colName))
                    {
                        searchEnumMap.put(enumName, colName);
                    }
                    else
                    {
                        _logger.info("This search enum has no data: " + colName);
                    }
                }
            }
        }
        catch (ClassNotFoundException e)
        {
            _logger.error("Not find vaild Enum!!!" + searchEnumName);
        }
        return searchEnumMap;
    }

    @SuppressWarnings("rawtypes")
    public static boolean jpaDaoFlagJudge(Method method)
    {
        Class[] paramClasses = method.getParameterTypes();
        for (Class<?> clazz : paramClasses)
        {
            if (clazz.getSimpleName().equalsIgnoreCase("UserAccount"))
            {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    private static String getParams(Method method)
    {
        StringBuilder paramList = new StringBuilder();

        Class[] paramClasses = method.getParameterTypes();
        int index = 0;
        for (int i = 0; i < paramClasses.length; i++)
        {
            index++;
            String className = paramClasses[i].getSimpleName();
            if (index == paramClasses.length)
            {
                paramList.append(className.substring(0, 1).toLowerCase() + className.substring(1));
            }
            else
            {
                paramList.append(className.substring(0, 1).toLowerCase() + className.substring(1) + ",");
            }
        }
        return paramList.toString();
    }

    private static HashSet<String> getGeneralImports(String searchEnumName, String className, boolean isNoneSet,
            Method method)
    {
        HashSet<String> importNames = new HashSet<String>();
        importNames.add("com.hp.fm.criteria.FmCriterion");
        importNames.add(method.getDeclaringClass().getName());
        if (!isNoneSet)
        {
            importNames.add("com.hp.fm.criteria.FmOpCriteria");
            importNames.add("com.hp.fm.criteria.FmOpCriteria.Operand");
            importNames.add("com.hp.fm.criteria.FmValue");
            importNames.add("com.hp.fm.searchenum." + searchEnumName);
        }
        if (jpaDaoFlagJudge(method))
        {
            importNames.add("com.hp.fm.db.dao.jpaimpl.UserAccountJpaDao");
        }
        importNames.add("com.hp.fm.service.ServiceException");
        importNames.add("com.hp.fm.performance.FmPerfTestEntityManager");
        importNames.add("javax.persistence.EntityManager");
        return importNames;
    }

    private static String buildImports(Method method, HashSet<String> imports)
        throws Exception
    {
        String returnType = method.getReturnType().getCanonicalName();
        if (typeOk(returnType))
        {
            imports.add(returnType);
        }
        else
            _logger.info("Return Type is invalid");
        Type tempType = method.getGenericReturnType();
        imports.addAll(recurseTypesForImports(tempType));
        for (Class<?> paramTypeClass : method.getParameterTypes())
        {
            if (typeOk(paramTypeClass.getCanonicalName()))
            {
                imports.add(paramTypeClass.getCanonicalName());
            }
            else
            {
                _logger.info("Param Type is invalid");
            }
        }
        imports.addAll(recurseTypesViaSignatureForImports(method));
        //Sort the imports to make the output formatted
        ArrayList<String> tempImports = new ArrayList<String>(imports);
        Collections.sort(tempImports);
        String lastImportName = "";
        StringBuffer importsName = new StringBuffer();
        //Add every import to the class
        for (String importName : tempImports)
        {
            //Don't import java.lang objects
            if (importName.matches("java\\.lang\\.[^\\.]*"))
            {
                continue;
            }

            //Add an extra line whenever this changes
            if (!compareImportPackages(importName, lastImportName, 3))
            {
                if (lastImportName != "")
                {
                    importsName.append("\n");
                }
                lastImportName = importName;
            }

            //Write the import statement for the class
            importsName.append(String.format("\nimport %s;", importName));
        }

        return importsName.toString();
    }

    private static boolean typeOk(final String typeName)
    {
        if (typeName.equals("void"))
        {
            return false;
        }
        else if (typeName.equals("int") || typeName.equals("char") || typeName.equals("byte")
                || typeName.equals("boolean") || typeName.equals("long") || typeName.equals("short")
                || typeName.equals("float") || typeName.equals("double"))
        {
            return false;
        }
        else if (typeName.endsWith("[]"))
        {
            return false;
        }

        return true;
    }

    private static Set<String> recurseTypesForImports(final Type type)
    {
        Class<?> clazz;
        Type genericArgTypes[];
        HashSet<String> result = new HashSet<String>();

        if (type != null)
        {
            clazz = type.getClass();
            if (clazz.getSimpleName().equals("ParameterizedTypeImpl"))
            {
                genericArgTypes = getReturnGenericTypeValues(type);
                for (Type genericArg : genericArgTypes)
                {
                    if (genericArg.getClass().getSimpleName().equals("ParameterizedTypeImpl"))
                    {
                        result.addAll(recurseTypesForImports(genericArg));
                    }
                    else
                    {
                        result.add(genericArg.toString().replace("class ", "").replace("$", "."));
                    }
                }

                result.add(getReturnRawTypeCanonicalName(type).replace("$", "."));
            }
        }
        return result;
    }

    @SuppressWarnings("restriction")
    private static Type[] getReturnGenericTypeValues(Type returnType)
    {
        sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl pti;
        pti = (sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl)returnType;
        Type genericArgTypes[] = pti.getActualTypeArguments();

        return genericArgTypes;
    }

    @SuppressWarnings("restriction")
    private static String getReturnRawTypeCanonicalName(Type returnType)
    {
        sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl pti;
        pti = (sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl)returnType;

        return pti.getRawType().getCanonicalName();
    }

    private static Set<String> recurseTypesViaSignatureForImports(final Method method)
        throws Exception
    {
        HashSet<String> parameters = new HashSet<String>();

        try
        {
            Class<?> cls = Class.forName("java.lang.reflect.Method");
            Field fld = cls.getDeclaredField("signature");
            fld.setAccessible(true);
            _logger.info("signature = " + "Class: " + method.getDeclaringClass().getSimpleName() + " method: "
                    + method.getName() + " " + fld.get(method));
            String signature = (String)fld.get(method);

            // (Ljava/util/ArrayList<Ljava/lang/String;>;Ljava/util/ArrayList<Ljava/lang/Integer;>;Ljava/util/ArrayList<Ljava/lang/Float;>;)Lcom/hp/it/sbs/example/beans/Person;
            if (signature != null)
            {
                String paramlist[] = signature.split("[)]");
                paramlist[0] = paramlist[0].replaceAll(";>;", ">;");
                String param[] = paramlist[0].split(";");
                String split[];
                String genericType;
                int i;
                for (String p : param)
                {
                    split = p.replaceAll("[()]", "").split("[<]");
                    if (split.length > 1)
                    {
                        for (i = 0; i < split.length; i++)
                        {
                            // Have to check to see if this generic contains yet another generic...

                            if (split[i].charAt(split[i].length() - 1) != '>')
                            {
                                genericType = split[i].substring(1, split[i].length()).replaceAll("/", ".");
                                parameters.add(genericType);
                            }
                            else
                            {
                                genericType = split[i].substring(1, split[i].length() - 1).replaceAll("/", ".");
                                parameters.add(genericType);
                            }
                        }
                    }

                    // We won't add anything here if it's not split, since that only happens
                    // in the case where it's just a base (i.e. non generic type) and these should 
                    // have been added in the method that called this, so we can just let it handle
                    // those types.
                }
            }
        }
        catch (Exception e)
        {
            _logger.error(e.getMessage());
            throw new Exception("Exception when manage imports: ", e);
        }

        return parameters;
    }

    /**
     * Compare Import Packages
     * Determines if import1 and import2 are in the same package structure
     * Up to the number of packages specified by parameter depth
     * @param import1 Service Class Import 1
     * @param import2 Service Class Import 2
     * @param depth Package Depth of Comparison
     * @return boolean Whether a Match
     */
    private static boolean compareImportPackages(String import1, String import2, int depth)
    {
        int newindex;

        //Find the package of import 1 up to depth
        int index1 = 0;
        for (int x = 0; x < depth; x++)
        {
            newindex = import1.indexOf('.', index1 + 1);

            //Only set if another package exists
            if (newindex > index1)
            {
                index1 = newindex;
            }
        }

        //Find the package of import 2 up to depth
        int index2 = 0;
        for (int x = 0; x < depth; x++)
        {
            newindex = import2.indexOf('.', index2 + 1);

            //Only set if another package exists
            if (newindex > index2)
            {
                index2 = newindex;
            }
        }

        //If no packages, use the entire string
        if (index1 < 1)
        {
            index1 = import1.length();
        }

        //If no packages, use the entire string
        if (index2 < 1)
        {
            index2 = import2.length();
        }

        //Compare the packages
        if (index1 <= 0 || index2 <= 0)
        {
            return false;
        }
        else if (index1 > index2)
        {
            return import1.substring(0, index1).contains(import2.substring(0, index2));
        }
        else
        {
            return import2.substring(0, index2).contains(import1.substring(0, index1));
        }
    }

    private static String generateReturnType(Method method)
        throws Exception
    {
        //Analyze the service interface
        String result = null;
        StringBuilder buffer;
        Type returnType;

        try
        {
            //Try to determine from the method definition
            returnType = method.getGenericReturnType();
            buffer = new StringBuilder();
            result = recurseReturnType(buffer, returnType);
        }
        catch (Exception e)
        {
            _logger.error(e.getMessage());
            throw new Exception("Exception when generate return type: ", e);
        }

        //Return the result
        return result;
    }

    private static String recurseReturnType(StringBuilder buffer, Type type)
    {
        Type genericArgTypes[];
        String genericArgRawType;
        boolean needComma = false;
        String temp;

        Class<?> clazz = type.getClass();
        if (clazz.getSimpleName().equals("ParameterizedTypeImpl"))
        {
            genericArgRawType = getReturnRawTypeSimpleName(type);
            genericArgTypes = getReturnGenericTypeValues(type);
            buffer.append(genericArgRawType.toString() + "<");
            for (Type genericArg : genericArgTypes)
            {
                if (needComma)
                {
                    buffer.append(",");
                }

                if (genericArg.getClass().getSimpleName().equals("ParameterizedTypeImpl"))
                {
                    temp = recurseReturnType(buffer, genericArg);
                }
                else
                {
                    temp = genericArg.toString().replace("$", ".");
                    buffer.append(temp.substring(temp.lastIndexOf(".") + 1));
                }

                needComma = true;
            }
            buffer.append(">");
        }
        else
        {
            temp = type.toString().replace("$", ".");
            buffer.append(temp.substring(temp.lastIndexOf(".") + 1));
        }

        return buffer.toString();
    }

    @SuppressWarnings("restriction")
    private static String getReturnRawTypeSimpleName(Type returnType)
    {
        sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl pti;
        pti = (sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl)returnType;

        return pti.getRawType().getSimpleName();
    }

    @SuppressWarnings("restriction")
    public static String processTypeFromParameterList(Type type, boolean needComma)
    {
        Class<?> clazz1;
        Class<?> clazz2;
        Type genericArgTypes1[];
        Type genericArgTypes2[];
        String temp1;
        String temp2;
        sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl pti1;
        sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl pti2;
        boolean localComma = needComma;
        boolean subComma = false;
        StringBuilder buffer;

        clazz1 = type.getClass();
        temp1 = type.toString();
        if (clazz1.getSimpleName().equals("ParameterizedTypeImpl"))
        {
            pti1 = (sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl)type;
            genericArgTypes1 = pti1.getActualTypeArguments();
            buffer = new StringBuilder();
            buffer.append(pti1.getRawType().getSimpleName().replace("$", ".") + "<");
            for (Type genericArg : genericArgTypes1)
            {
                if (localComma)
                {
                    buffer.append(",");
                }
                clazz2 = genericArg.getClass();
                if (clazz2.getSimpleName().equals("ParameterizedTypeImpl"))
                {
                    pti2 = (sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl)genericArg;
                    genericArgTypes2 = pti2.getActualTypeArguments();
                    buffer.append(pti2.getRawType().getSimpleName().replace("$", ".") + "<");
                    for (Type genericArg2 : genericArgTypes2)
                    {
                        if (subComma)
                        {
                            buffer.append(",");
                        }
                        temp1 = processTypeFromParameterList(genericArg2, false);
                        buffer.append(temp1);
                        subComma = true;
                    }
                    buffer.append(">");
                }
                else
                {
                    temp1 = genericArg.toString();
                    temp2 = temp1.substring(temp1.lastIndexOf(".") + 1).replace("$", ".");
                    buffer.append(temp2);
                    localComma = true;
                }
            }
            buffer.append(">");
            return buffer.toString();
        }

        temp2 = temp1.substring(temp1.lastIndexOf(".") + 1).replace("$", ".");
        return temp2;
    }

    //    private static Boolean isCollection(String returnType)
    //    {
    //
    //        if (returnType.contains("List<") || returnType.contains("Set<") || returnType.contains("Vector<") || returnType.contains("Stack<"))
    //        {
    //            return true;
    //        }
    //        else
    //        {
    //            return false;
    //        }
    //    }

    //    private static String getVariables(Method method)
    //    {
    //        StringBuffer variables = new StringBuffer();
    //        Type[] paramTypes = method.getGenericParameterTypes();
    //        for (int i = 0; i < paramTypes.length; i++)
    //        {
    //            String variableType = processTypeFromParameterList(paramTypes[i], false);
    //            if (isCollection(variableType))
    //            {
    //                collectionFlag = true;
    //                if (variableType.startsWith("List"))
    //                {
    //                    variables.append(variableType + " value" + i + " = new " + "Array" + variableType + "();" + "\r\n\t\t");
    //                    importNames.add("java.util.ArrayList");
    //                }
    //                else if (variableType.startsWith("Set"))
    //                {
    //                    variables.append(variableType + " value" + i + " = new " + "Hash" + variableType + "();" + "\r\n\t\t");
    //                    importNames.add("java.util.HashSet");
    //                }
    //                else
    //                {
    //                    variables.append(variableType + " value" + i + " = new " + variableType + "();" + "\r\n\t\t");
    //                }
    //                String iterator = "";
    //                String genericName = variableType.substring(variableType.indexOf("<") + 1, variableType.indexOf(">"));
    //                if (genericName.equals("String"))
    //                {
    //                    iterator = "value" + i + ".add(\"\");" + "\t //
    //                }
    //                else
    //                {
    //                    iterator = "value" + i + ".add(" + genericName + ".valueOf(\"\"));" + "\t //
    //                }
    //                variables.append(iterator);
    //                variables.append("for (int i=1; i<Integer.parseInt(\"<ITEM_COUNT>\"); i++) {" + "\r\n\t\t\t");
    //                variables.append("lr.next_row(\"\");" + "\t //
    //                variables.append(iterator + "}");
    //            }
    //            else if (variableType.contains("Map<"))
    //            {
    //                collectionFlag = true;
    //                if (variableType.startsWith("Map"))
    //                {
    //                    variables.append(variableType + " value" + i + " = new " + "Hash" + variableType + "();" + "\r\n\t\t");
    //                    importNames.add("java.util.HashMap");
    //                }
    //                else
    //                {
    //                    variables.append(variableType + " value" + i + " = new " + variableType + "();" + "\r\n\t\t");
    //                }
    //                String iterator = "";
    //                String firstGenericName = variableType.substring(variableType.indexOf("<") + 1, variableType.indexOf(","));
    //                String secondGenericName = variableType.substring(variableType.indexOf(",") + 1, variableType.indexOf(">"));
    //                if (firstGenericName.equals("String") && secondGenericName.equals("String"))
    //                {
    //                    iterator = "value" + i + ".put(\"\", \"\");" + "\t //
    //                }
    //                else if (firstGenericName.equals("String") && !secondGenericName.equals("String"))
    //                {
    //                    iterator = "value" + i + ".put(\"\", " + secondGenericName + ".valueOf(\"\"));" + "\t //
    //                            + "\r\n\t\t";
    //                }
    //                else if (!firstGenericName.equals("String") && secondGenericName.equals("String"))
    //                {
    //                    iterator = "value" + i + ".put(" + firstGenericName + ".valueOf(\"\"), \"\");" + "\t //
    //                            + "\r\n\t\t";
    //                }
    //                else
    //                {
    //                    iterator = "value" + i + ".put(" + firstGenericName + ".valueOf(\"\"), " + secondGenericName + ".valueOf(\"\"));"
    //                            + "\t //
    //                }
    //                variables.append(iterator);
    //                variables.append("for (int i=1; i<Integer.parseInt(\"<ITEM_COUNT>\"); i++) {" + "\r\n\t\t\t");
    //                variables.append("lr.next_row(\"\");" + "\t //
    //                variables.append(iterator + "}");
    //            }
    //            else
    //            {
    //                variables.append("String" + " value" + i + " = \"\";" + "\t" + "//
    //                        + "\r\n\t\t");
    //            }
    //            variables.append("\r\n\t\t");
    //        }
    //        variables.append("\r\n\t\t" + "/* Do not change the below codes if possible */" + "\r\n\t\t");
    //        for (int i = 0; i < paramTypes.length; i++)
    //        {
    //            int index = i + 1;
    //            String variableType = processTypeFromParameterList(paramTypes[i], false);
    //            if (variableType.equalsIgnoreCase("Integer") || variableType.equalsIgnoreCase("Byte") || variableType.equalsIgnoreCase("String")
    //                    || variableType.equalsIgnoreCase("Long") || variableType.equalsIgnoreCase("Double") || variableType.equalsIgnoreCase("Boolean")
    //                    || variableType.equalsIgnoreCase("Float") || variableType.equalsIgnoreCase("Short") || isCollection(variableType)
    //                    || variableType.contains("Map<"))
    //            {
    //                if (variableType.startsWith("List"))
    //                {
    //                    variables.append(variableType + " param" + i + " = new " + "Array" + variableType + "(value" + i + ");");
    //                }
    //                else if (variableType.startsWith("Set"))
    //                {
    //                    variables.append(variableType + " param" + i + " = new " + "Hash" + variableType + "(value" + i + ");");
    //                }
    //                else if (variableType.startsWith("Map"))
    //                {
    //                    variables.append(variableType + " param" + i + " = new " + "Hash" + variableType + "(value" + i + ");");
    //                }
    //                else
    //                {
    //                variables.append(variableType + " param" + i + " = new " + variableType + "(value" + i + ");");
    //                }
    //                if (index == paramTypes.length)
    //                {
    //                    continue;
    //                }
    //                variables.append("\r\n\t\t");
    //            }
    //            else
    //            {
    //                variables.append(variableType + " param" + i + " = " + variableType + ".valueOf" + "(value" + i + ");");
    //                if (index == paramTypes.length)
    //                {
    //                    continue;
    //                }
    //                variables.append("\r\n\t\t");
    //            }
    //        }
    //        return variables.toString();
    //    }
}
