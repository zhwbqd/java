/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.study.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import com.hp.xlst.XLSTest;

public class XMLtoInput
{

    /**
     * Description goes here.
     *
     * @param args
     * @throws SAXException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args)
        throws SAXException, FileNotFoundException, IOException
    {
        org.xml.sax.XMLReader parser = XMLReaderFactory.createXMLReader();
        InputStream inputTemplate = XLSTest.class.getResourceAsStream("cdcatalog.xml");
        parser.setFeature("http://xml.org/sax/features/validation", false);
        CatalogConfigurationHandler handler = new CatalogConfigurationHandler();
        parser.setContentHandler(handler);
        parser.setErrorHandler(handler);
        InputSource input = new InputSource(inputTemplate);
        parser.parse(input);
        HashMap<String, Cd> catalog = handler.getCatalog();
        System.out.println(catalog);
    }

}
