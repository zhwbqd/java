package com.hp.fm.sprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * Velocity Transformer
 * @since 1.0.0
 */
public class VelocityTransformer 
{
	private VelocityContext context;
	private VelocityEngine ve;

    /**
     * Constructor
     * @throws Exception 
     */
	public VelocityTransformer()
        throws Exception
	{
		initializeEngine();
		context = new VelocityContext();
	}
	
    private void initializeEngine()
        throws Exception
	{
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();
	}
	
	/**
	 * Add Value to Context
	 * @param name Context Variable Name
	 * @param value Context Variable Value
	 */
    public void addValueToContext(String name, Object value)
	{
		context.put( name, value );
	}
	
	/**
	 * Clear Context
	 */
	public void clearContext()
	{
		context = new VelocityContext();
	}
	
	/**
	 * Render Template
	 * @param templateName Template File Name
	 * @param w File Output Stream
	 */
	public void renderTemplate( final String templateName, final Writer w )
	{
        try
        {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(templateName);
            if (input == null)
            {
                throw new IOException("Template file doesn't exist");
            }
            
            Template template = ve.getTemplate(templateName, "UTF-8");
            template.merge(context, w);

        }
        catch (Exception e )
        {
            System.out.println("Problem merging template : " + e );
        }
	}
	
	/**
	 * Render Dynamic Template
	 * @param template Template File Name
	 * @param w File Output Stream
	 */
	public void renderDynamicTemplate( final String template, final Writer w )
	{
        
		// We will use the string passed to be a "dynamic"
		// template and will evaluate via that.
        try
        {
            ve.evaluate( context, w, "rendDynTemp", template );
        }
        catch( ParseErrorException pee )
        {
            /*
             * thrown if something is wrong with the
             * syntax of our template string
             */
            System.out.println("ParseErrorException : " + pee );
        }
        catch( MethodInvocationException mee )
        {
            /*
             *  thrown if a method of a reference
             *  called by the template
             *  throws an exception. That won't happen here
             *  as we aren't calling any methods in this
             *  example, but we have to catch them anyway
             */
            System.out.println("MethodInvocationException : " + mee );
        }
        catch( Exception e )
        {
            System.out.println("Exception : " + e );
        }
    }
}
