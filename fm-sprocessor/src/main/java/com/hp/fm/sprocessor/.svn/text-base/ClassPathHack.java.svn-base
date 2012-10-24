package com.hp.fm.sprocessor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 
 * @author FMS
 * 
 * This class is used to add file URLs of jars to the current class loader so that
 * it's possible to dynamically add jars to the running app's class path.  The interface
 * processor utilizes this to be able to load in jars so that it can obtain type information
 * and other metadata of classes, so it can generate transport code.
 *
 */
public class ClassPathHack {
    private static final Class<?>[] parameters = new Class[] {URL.class};

    /**
     * Function that adds a file to the current class path.
     * It does this by using a String representation of the path to the file.
     * 
     * @param s full path to the file.
     * @throws IOException File I/O
     */
    public static void addFile(String s) throws IOException
    {
        File f = new File(s);
        addFile(f);
        String name = f.getName();
        name = name.substring(0, name.length()-4);

        		
    }

    /**
     * Function similar to the addFile(String s) that adds a file to 
     * the class path by using a File object to do so.
     * 
     * @param f file object representing file to add to class path.
     * @throws IOException File I/O
     */
    public static void addFile(File f) throws IOException
    {
        addURL(f.toURL());
    }

    /**
     * Private method that does all of the heavy lifting of adding a file to the
     * class path utilizing a URL object.  It uses an exposed method that is somewhat
     * of a hack, thus the name of this class.
     * 
     * @param u url object that should point to resource to add to class loader.
     * @throws IOException
     */
    private static void addURL(URL u) throws IOException
    {
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] {u});
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }
}
