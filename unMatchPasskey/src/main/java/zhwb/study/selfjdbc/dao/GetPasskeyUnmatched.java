package zhwb.study.selfjdbc.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetPasskeyUnmatched
{
    private static final int ORACLE = 0;

    private static final int SQL_SERVER = 1;

    public void getUnmatchedPasskeyLowCode()
    {
        ExecutorService threadpool = Executors.newFixedThreadPool(2);

        String oracle = "select distinct t.std_hr_low_cd_id from isda.pspract t where t.std_hr_low_cd_id is not null union select distinct v.off_hr_low_cd_id from isda.pspract v where v.off_hr_low_cd_id is not null";
        Future<List<String>> future_Oracle = threadpool.submit(new WorkThread(this, oracle, ORACLE));
        String sqlserver = "select distinct  d.Deliverable FROM passkey_75.dbo.Deliverable d union select distinct  d.Deliverable FROM passkey_8.dbo.Deliverable d union select distinct  d.Deliverable FROM passkey_9.dbo.Deliverable d";
        Future<List<String>> future_SQLServer = threadpool.submit(new WorkThread(this, sqlserver, SQL_SERVER));

        try
        {
            List<String> oralceList = future_Oracle.get();
            List<String> sqlserverList = future_SQLServer.get();

            System.out.println("begin to compare......." + oralceList.size() + " " + sqlserverList.size());

            List<String> oralceList1 = new ArrayList<String>(oralceList);
            List<String> sqlserverList1 = new ArrayList<String>(sqlserverList);

            /*Passkey unmatched*/
            sqlserverList1.removeAll(oralceList);

            /*Oracle unmatched*/
            oralceList1.removeAll(sqlserverList);

            BufferedWriter passkey_f = new BufferedWriter(new FileWriter("passkey_unmatch_lowCode.txt"));

            for (String string : sqlserverList1)
            {
                passkey_f.write(string);
                passkey_f.newLine();
            }
            passkey_f.flush();
            passkey_f.close();

            BufferedWriter isda_f = new BufferedWriter(new FileWriter("isda_unmatch_lowCode.txt"));
            for (String string : oralceList1)
            {
                isda_f.write(string);
                isda_f.newLine();
            }
            isda_f.flush();
            isda_f.close();

            System.out.println("finished........");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            threadpool.shutdown();
        }
    }

    public List<String> getListFromInputSqlStatement(final String sql, final int type)
    {
        List<String> result = new ArrayList<String>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try
        {
            if (type == 0)
            {
                conn = JDBCConnector.getInstance().getOracleConnection();
            }
            else if (type == 1)
            {
                conn = JDBCConnector.getInstance().getSQLServerConnection();
            }
            else
            {
                throw new IllegalArgumentException("invalid type");
            }
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next())
            {
                result.add(rs.getString(1));
            }
            System.out.println("retrieving ending......");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                statement.close();
                conn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}

class WorkThread implements Callable<List<String>>
{

    private GetPasskeyUnmatched service;

    private String sql;

    private int type;

    public WorkThread(final GetPasskeyUnmatched getPasskeyUnmatched, final String sql, final int type)
    {
        service = getPasskeyUnmatched;
        this.sql = sql;
        this.type = type;
    }

    @Override
    public List<String> call()
        throws Exception
    {
        System.out.println("retrieving data.....");
        return service.getListFromInputSqlStatement(sql, type);
    }

}
