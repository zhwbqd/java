/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.jdbc;

import java.sql.ResultSet;

public class DatabaseUpdateTest
{
    public static void main(final String[] args)
    {
        new Threads().start();
        new Threads().start();
        new Threads().start();
    }
}

class Threads extends Thread
{
    @Override
    public void run()
    {
        ResultSet result;
        try
        {
            result = new UpdateDAO().selectForUpdate();

            while (result.next())
            {
                System.out.println(result.getString(1));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
