package com.hp.sbs.command.loadtest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.hp.sbs.command.IExecutor;

public class LoadTestExecutor implements IExecutor
{

    public String exec()
        throws Exception
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date currentDate = new Date();
        String archiveName = format.format(currentDate);

        CommandBuilder builder = new CommandBuilder(archiveName);
        
        // delete the expire result files
        CommandParams params = builder.getCommandParams();
        String expireMonth = params.getExpireMonth();
        String resultLocation = params.getResultLocation();
        String reportAddress = params.getReportAddress();

        int intExpireMonth = 2;
        if (null != expireMonth && !expireMonth.equals(""))
        {
            intExpireMonth = Integer.parseInt(expireMonth);
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(GregorianCalendar.MONTH, -intExpireMonth);
        Date expireDate = cal.getTime();

        File folder = new File(resultLocation);
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            String folderName = files[i].getName();
            Date createDate = format.parse(folderName);
            if (isDateExpire(createDate, expireDate))
            {
                deleteFolder(resultLocation + "\\" + files[i].getName());
            }
        }

        // execute the command
        String controllerCommand = builder.buildControllerCommand();
        String analysisCommand = builder.buildAnalysisCommand();

        Runtime.getRuntime().exec(controllerCommand).waitFor();
        Runtime.getRuntime().exec(analysisCommand).waitFor();

        String reportURL = reportAddress + archiveName + "/An_Report1.html";

        return new ReportHelper().generateReport(reportURL);

    }

    private boolean deleteFolder(String sPath)
    {
        boolean flag = false;
        File file = new File(sPath);
        if (!file.exists())
        {
            return flag;
        }
        else
        {
            if (file.isFile())
            {
                return deleteFile(sPath);
            }
            else
            {
                return deleteDirectory(sPath);
            }
        }
    }

    private boolean deleteFile(String sPath)
    {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }

    private boolean deleteDirectory(String sPath)
    {
        if (!sPath.endsWith(File.separator))
        {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        if (!dirFile.exists() || !dirFile.isDirectory())
        {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isFile())
            {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            else
            {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        if (dirFile.delete())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isDateExpire(Date createDate, Date expireDate)
    {
        long createTime = createDate.getTime();
        long expireTime = expireDate.getTime();
        return (createTime < expireTime);
    }
}
