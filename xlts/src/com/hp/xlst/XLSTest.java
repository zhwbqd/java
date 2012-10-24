/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.xlst;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XLSTest
{

    /**
     * Description goes here.
     *
     * @param args
     * @throws TransformerException 
     * @throws IOException 
     */
    public static void main(String[] args)
        throws TransformerException, IOException
    {
        InputStream inputTemplate = XLSTest.class.getResourceAsStream("cdcatalog.xsl");
        InputStream inputContent = XLSTest.class.getResourceAsStream("cdcatalog.xml");
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.html"));
        Result outputTarget = new StreamResult(writer);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(inputTemplate));
        transformer.transform(new StreamSource(inputContent), outputTarget);
        writer.close();

    }

}
