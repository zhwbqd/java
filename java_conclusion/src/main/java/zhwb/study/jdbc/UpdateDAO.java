/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateDAO
{
    public ResultSet selectForUpdate()
        throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection dataSource = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "password");
        //        dataSource.setAutoCommit(false);
        Statement statement = dataSource.createStatement();
        boolean result = statement.execute("select * from test where status ='new' for update");
        System.out.println(result);
        Thread.sleep(10000);
        //        dataSource.commit();
        return statement.getResultSet();
    }
}
