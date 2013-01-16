/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.study.xmlparse;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;



public class CatalogConfigurationHandler extends DefaultHandler
{
    private HashMap<String, Cd> _catalog = new HashMap<String, Cd>();

    private Cd _cd = null;

    private String _currentElement = null;

    public void startDocument()
        throws SAXException
    {
        System.out.println("***Start Parser***");
    }

    public void startElement(String namespaceURL, String localName, String qname, Attributes attributes)
        throws SAXException
    {
        // log.debug("Starting startElement...");

        this._currentElement = localName;
        if (localName.equals("cd"))
        {
            this._cd = new Cd();
        }

    }

    public void characters(char[] buffer, int start, int length)
        throws SAXException
    {
        String content = String.valueOf(buffer);

        String characters = content.substring(start, start + length);


        if (characters == null || characters.trim().equals("") || characters.startsWith("\n"))
        {
            return;
        }
        else if (this._currentElement.equals("title"))
        {
            this._cd.setTitle(characters);
        }
        else if (this._currentElement.equals("artist"))
        {
            this._cd.setArtist(characters);
        }
        else if (this._currentElement.equals("country"))
        {
            this._cd.setCountry(characters);
        }
        else if (this._currentElement.equals("company"))
        {
            this._cd.setCompany(characters);
        }
        else if (this._currentElement.equals("price"))
        {
            this._cd.setPrice(characters);
        }
        else if (this._currentElement.equals("year"))
        {
            this._cd.setYear(characters);
        }

    }

    public void endElement(String namespaceURL, String localName, String qname)
        throws SAXException
    {

        if (localName.equals("cd"))
        {
            String key = this._cd.getTitle() + this._cd.getArtist() + this._cd.getYear();
            this._catalog.put(key, this._cd);
            this._cd = null;
        }
    }


    public void endDocument()
        throws SAXException
    {
        System.out.println("***End Parser***");
    }


    public void warning(SAXParseException exception)
    {
        System.out.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
        //        System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }


    public void error(SAXParseException exception)
    {
        System.out.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
        //        System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }


    public void fatalError(SAXParseException exception)
        throws SAXException
    {
        System.out.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
        //        System.err.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
        throw (exception);
    }


    public HashMap<String, Cd> getCatalog()
    {
        return this._catalog;
    }

}
