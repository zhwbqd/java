/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.threadpool;

public class AccessDBThread implements Runnable
{
    private String msg;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(final String msg)
    {
        this.msg = msg;
    }

    public AccessDBThread()
    {
        super();
    }

    public AccessDBThread(final String msg)
    {
        this.msg = msg;
    }

    @Override
    public void run()
    {
        // 向数据库中添加Msg变量值 
        System.out.println("Added the message: " + msg + " into the Database");
    }
}
