/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.java.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test implements Serializable
{

    private static final long serialVersionUID = 1L;

    public static int staticVar = 5;

    public static void main(String[] args)
    {
        try
        {
            //鍒濆鏃秙taticVar涓�            
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
            out.writeObject(new Test());
            out.close();

            //搴忓垪鍖栧悗淇敼涓�
            Test.staticVar = 10;

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));
            Test t = (Test)oin.readObject();
            oin.close();

            //鍐嶈鍙栵紝閫氳繃t.staticVar鎵撳嵃鏂扮殑鍊�
            System.out.println(t.staticVar);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
