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
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream.PutField;
import java.io.Serializable;

public class SerializableTest implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String password = "pass";

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    private void writeObject(ObjectOutputStream out)
    {
        try
        {
            PutField putFields = out.putFields();
            System.out.println("原密码:" + password);
            String encryption = encrypt(password);//模拟加密
            putFields.put("password", encryption);
            System.out.println("加密后的密码" + encryption);
            out.writeFields();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String encrypt(String password)
    {
       StringBuilder encryption = new StringBuilder();
        for (char c : password.toCharArray())
    {
            encryption.append((char)(c << 2));
    }
        return encryption.toString();
    }

    private String deEncrypt(String password)
    {
        StringBuilder encryption = new StringBuilder();
        for (char c : password.toCharArray())
        {
            encryption.append((char)(c >> 2));
        }
        return encryption.toString();
    }

    private void readObject(ObjectInputStream in)
    {
        try
        {
            GetField readFields = in.readFields();
            Object object = readFields.get("password", "");
            System.out.println("要解密的字符串:" + object.toString());
            password = deEncrypt(object.toString());//模拟解密,需要获得本地的密钥
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

    public static void main(String[] args)
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
            out.writeObject(new SerializableTest());
            out.close();

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));
            SerializableTest t = (SerializableTest)oin.readObject();
            System.out.println("解密后的字符串:" + t.getPassword());
            oin.close();
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
