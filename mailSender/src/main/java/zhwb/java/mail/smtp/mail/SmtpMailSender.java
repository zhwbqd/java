package zhwb.java.mail.smtp.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import zhwb.java.mail.smtp.security.Base64;
import zhwb.java.mail.smtp.util.LogManager;

/**
 * SMTP邮件发送系统。
 * 
 * 
 * 发件人和收件人的正确格式如下:
 * 
 * 例1: "Sol"<sol@gameeden.org>
 * 例2: Sol<sol@gameeden.org>
 * 例3: <sol@gameeden.org>
 * 例4: sol@gameeden.org
 * 
 * @author Sol
 * @since 1.5
 */
public final class SmtpMailSender
{
    /**
     * 发送成功的常量。
     */
    public final static boolean SUCCESSFUL=true;
    
    /**
     * 发送失败的常量。
     */
    public final static boolean FAILED=false;
    
    private final static int PORT = 25;//服务器端口(SMTP服务器和邮件接收服务器的端口均为25)

    private final static int RETRY = 3;//当连接SMTP服务器失败后尝试重新连接的次数(仅用于发送ESMTP邮件)

    private final static int INTERVAL = 1000;//当连接SMTP服务器失败后重新连接的时间间隔(仅用于发送ESMTP邮件)

    private final static int TIMEOUT = 10000;//网络连接的超时时间
    
    private final static String BOUNDARY;//MIME分格符

    private final static String CHARSET;//虚拟机的默认编码

    private final static Pattern PATTERN;//用于效验邮箱地址的正确性
    
    private static InitialDirContext dirContext;//用于查询DNS记录
    
    private final ArrayList<LogManager> logManager;//日志管理器
    
    private boolean isEsmtp;//发送类型
    
    private String smtp;//SMTP服务器地址(仅用于发送ESMTP邮件)

    private String user;//用户名(仅用于发送ESMTP邮件)

    private String password;//密码(仅用于发送ESMTP邮件)

    private String sender;//发件人名字

    private String senderAddress;//发件人的E-Mail地址
    
    static
    {
        BOUNDARY="Boundary-=_hMbeqwnGNoWeLsRMeKTIPeofyStu";
        CHARSET=Charset.defaultCharset().displayName();
        PATTERN = Pattern.compile(".+@[^.@]+(\\.[^.@]+)+$");//此处放弃了传统匹配方式，这是为了兼容非英文域名的电子邮箱
        
        Hashtable<String,String> hashtable=new Hashtable<String,String>();
        hashtable.put("java.naming.factory.initial","com.sun.jndi.dns.DnsContextFactory");
        
        try
        {
            dirContext=new InitialDirContext(hashtable);
        }
        catch(NamingException e)
        {
        }
    }
    
    private SmtpMailSender(String from)
    {
        if(from==null)
        {
            throw new IllegalArgumentException("参数from不能为null。");
        }
        
        int leftSign=(from=from.trim()).charAt(from.length()-1)=='>'?from.lastIndexOf('<'):-1;
        
        senderAddress=leftSign>-1?from.substring(leftSign+1,from.length()-1).trim():from;
        
        if(!PATTERN.matcher(senderAddress).find())
        {
            throw new IllegalArgumentException("参数from不正确。");
        }
        
        sender=leftSign>-1?from.substring(0,leftSign).trim():null;
        logManager=new ArrayList<LogManager>();
        isEsmtp=false;
        
        if(sender!=null)
        {
            if(sender.length()==0)
            {
                sender=null;
            }
            else if(sender.charAt(0)=='"'&&sender.charAt(sender.length()-1)=='"')
            {
                sender=sender.substring(1,sender.length()-1).trim();
            }
        }
    }
    
    private SmtpMailSender(String address,String from,String user,String password)
    {
        this(from);
        
        isEsmtp=true;
        this.smtp=address;
        this.user=Base64.encode(user.getBytes());
        this.password=Base64.encode(password.getBytes());
    }

    /**
     * 创建SMTP邮件发送系统实例。
     * @param from 发件人
     * @return SMTP邮件发送系统的实例
     * @throws IllegalArgumentException 如果参数from为null或格式不正确
     */
    public static SmtpMailSender createSmtpMailSender(String from) throws IllegalArgumentException
    {
        return new SmtpMailSender(from);
    }
    
    /**
     * 创建ESMTP邮件发送系统实例。
     * @param smtp SMTP服务器地址
     * @param from 发件人
     * @param user 用户名
     * @param password 密码
     * @return SMTP邮件发送系统的实例
     * @throws IllegalArgumentException 如果参数from为null或格式不正确
     */
    public static SmtpMailSender createESmtpMailSender(String smtp,String from,String user,String password) throws IllegalArgumentException
    {
        return new SmtpMailSender(smtp,from,user,password);
    }
    
    /**
     * 发送邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @param attachments 附件
     * @param isHtml 使用网页形式发送
     * @param isUrgent 紧急邮件
     * @return 是否发送成功
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean sendMail(String to,String subject,String content,File[] attachments,boolean isHtml,boolean isUrgent) throws IllegalArgumentException
    {
        if(to==null)
        {
            throw new IllegalArgumentException("参数to不能为null。");
        }
        
        int leftSign=(to=to.trim()).charAt(to.length()-1)=='>'?to.lastIndexOf('<'):-1;
        
        String addresseeAddress = leftSign > -1 ? to.substring(leftSign + 1, to.length() - 1).trim() : to;//收件人的E-Mail地址

        if(!PATTERN.matcher(addresseeAddress).find())
        {
            throw new IllegalArgumentException("参数to不正确。");
        }
        
        String addressee = leftSign > -1 ? to.substring(0, leftSign).trim() : null;//收件人名字
        boolean needBoundary=attachments!=null&&attachments.length>0;
        
        Socket socket=null;
        InputStream in=null;
        OutputStream out=null;
        byte[] data;

        try
        {   
            if(addressee!=null)
            {
                if(addressee.length()==0)
                {
                    addressee=null;
                }
                else if(addressee.charAt(0)=='"'&&addressee.charAt(addressee.length()-1)=='"')
                {
                    addressee=addressee.substring(1,addressee.length()-1).trim();
                }
            }
            
            if(isEsmtp)
            {
                for(int k=1;;k++)
                {
                    try
                    {
                        log("连接: 主机:\"" + smtp + "\" 端口:\"" + PORT + "\"");
                        socket=new Socket(smtp,PORT);
                        break;
                    }
                    catch(IOException e)
                    {
                        log("错误: 连接失败" + k + "次");

                        if(k==RETRY)
                        {
                            return FAILED;
                        }
                        
                        try
                        {
                            Thread.sleep(INTERVAL);
                        }
                        catch(InterruptedException ie)
                        {
                        }
                    }
                }

                in=socket.getInputStream();
                out=socket.getOutputStream();
                
                if(response(in)!=220)
                {
                    return FAILED;
                }
            }
            else
            {
                log("状态: 创建邮件接收服务器列表");
                String[] address=parseDomain(parseUrl(addresseeAddress));
                
                if(address==null)
                {
                    return FAILED;
                }
                
                for(int k=0;k<address.length;k++)
                {
                    try
                    {
                        log("连接: 主机:\"" + address[k] + "\" 端口:\"" + PORT + "\"");

                        socket=new Socket(address[k],PORT);
    
                        in=socket.getInputStream();
                        out=socket.getOutputStream();
                        
                        if(response(in)!=220)
                        {
                            return FAILED;
                        }
                        
                        break;
                    }
                    catch(IOException e)
                    {
                        log("错误: 连接失败");
                    }
                }
            }

            if(in==null||out==null)
            {
                return FAILED;
            }
            
            socket.setSoTimeout(TIMEOUT);
            
            sendString("HELO "+parseUrl(senderAddress),out);
            sendNewline(out);
            
            if(response(in)!=250)
            {
                return FAILED;
            }

            if(isEsmtp)
            {
                sendString("AUTH LOGIN",out);
                sendNewline(out);
                
                if(response(in)!=334)
                {
                    return FAILED;
                }
                
                sendString(user,out);
                sendNewline(out);
                
                if(response(in)!=334)
                {
                    return FAILED;
                }
                
                sendString(password,out);
                sendNewline(out);
                
                if(response(in)!=235)
                {
                    return FAILED;
                }
            }
            
            sendString("MAIL FROM: <"+senderAddress+">",out);
            sendNewline(out);

            if(response(in)!=250)
            {
                return FAILED;
            }
            
            sendString("RCPT TO: <"+addresseeAddress+">",out);
            sendNewline(out);
            
            if(response(in)!=250)
            {
                return FAILED;
            }

            sendString("DATA",out);
            sendNewline(out);

            if(response(in)!=354)
            {
                return FAILED;
            }

            sendString("From: "+(sender==null?senderAddress:getBase64String(sender)+" <"+senderAddress+">"),out);
            sendNewline(out);
            sendString("To: "+(addressee==null?addresseeAddress:getBase64String(addressee)+" <"+addresseeAddress+">"),out);
            sendNewline(out);
            sendString("Subject: "+getBase64String(subject),out);
            sendNewline(out);
            sendString("Date: "+new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z (z)",Locale.US).format(new Date()),out);
            sendNewline(out);
            sendString("MIME-Version: 1.0",out);
            sendNewline(out);
            
            if(needBoundary)
            {
                sendString("Content-Type: multipart/mixed; BOUNDARY=\""+BOUNDARY+"\"",out);
                sendNewline(out);
            }
            else
            {
                if(isHtml)
                {
                    sendString("Content-Type: text/html; charset=\""+CHARSET+"\"",out);
                    sendNewline(out);
                }
                else
                {
                    sendString("Content-Type: text/plain; charset=\""+CHARSET+"\"",out);
                    sendNewline(out);
                }
            }
            
            sendString("Content-Transfer-Encoding: base64",out);
            sendNewline(out);

            if(isUrgent)
            {
                sendString("X-Priority: 1",out);
                sendNewline(out);
            }
            else
            {
                sendString("X-Priority: 3",out);
                sendNewline(out);
            }
            
            sendString("X-Mailer: BlackFox Mail[Copyright(C) 2007 Sol]",out);
            sendNewline(out);
            
            log("发送: ");
            sendNewline(out);

            if(needBoundary)
            {
                sendString("--"+BOUNDARY,out);
                sendNewline(out);
                
                if(isHtml)
                {
                    sendString("Content-Type: text/html; charset=\""+CHARSET+"\"",out);
                    sendNewline(out);
                }
                else
                {
                    sendString("Content-Type: text/plain; charset=\""+CHARSET+"\"",out);
                    sendNewline(out);
                }
                
                sendString("Content-Transfer-Encoding: base64",out);
                sendNewline(out);
                
                log("发送: ");
                sendNewline(out);
            }
            
            data=(content!=null?content:"").getBytes();
            
            for(int k=0;k<data.length;k+=54)
            {
                sendString(Base64.encode(data,k,Math.min(data.length-k,54)),out);
                sendNewline(out);
            }

            if(needBoundary)
            {
                RandomAccessFile attachment=null;
                int fileIndex=0;
                String fileName;
                int k;
                data=new byte[54];
                
                try
                {
                    for(;fileIndex<attachments.length;fileIndex++)
                    {
                        fileName=attachments[fileIndex].getName();
                        attachment=new RandomAccessFile(attachments[fileIndex],"r");

                        sendString("--"+BOUNDARY,out);
                        sendNewline(out);
                        sendString("Content-Type: "+MimeTypeFactory.getMimeType(fileName.indexOf(".")==-1?"*":fileName.substring(fileName.lastIndexOf(".")+1))+"; name=\""+(fileName=getBase64String(fileName))+"\"",out);
                        sendNewline(out);
                        sendString("Content-Transfer-Encoding: base64",out);
                        sendNewline(out);
                        sendString("Content-Disposition: attachment; filename=\""+fileName+"\"",out);
                        sendNewline(out);
                        
                        log("发送: ");
                        sendNewline(out);
                        
                        do
                        {
                            k=attachment.read(data,0,54);
                            
                            if(k==-1)
                            {
                                break;
                            }
                            
                            sendString(Base64.encode(data,0,k),out);
                            sendNewline(out);
                        }while(k==54);
                    }
                }
                catch(FileNotFoundException e)
                {
                    log("错误: 附件\"" + attachments[fileIndex].getAbsolutePath() + "\"不存在");
                    return FAILED;
                }
                catch(IOException e)
                {
                    log("错误: 无法读取附件\"" + attachments[fileIndex].getAbsolutePath() + "\"");
                    return FAILED;
                }
                finally
                {
                    if(attachment!=null)
                    {
                        try
                        {
                            attachment.close();
                        }
                        catch(IOException e)
                        {
                        }
                    }
                }
                
                sendString("--"+BOUNDARY+"--",out);
                sendNewline(out);
            }
            
            sendString(".",out);
            sendNewline(out);

            if(response(in)!=250)
            {
                return FAILED;
            }

            sendString("QUIT",out);
            sendNewline(out);

            if(response(in)!=221)
            {
                return FAILED;
            }
            
            return SUCCESSFUL;
        }
        catch(SocketTimeoutException e)
        {
            log("错误: 连接超时");
            return FAILED;
        }
        catch(IOException e)
        {
            log("错误: 连接出错");
            return FAILED;
        }
        catch(Exception e)
        {
            log("错误: " + e.toString());
            return FAILED;
        }
        finally
        {
            if(in!=null)
            {
                try
                {
                    in.close();
                }
                catch(IOException e)
                {
                }
            }
            
            if(out!=null)
            {
                try
                {
                    out.close();
                }
                catch(IOException e)
                {
                }
            }

            if(socket!=null)
            {
                try
                {
                    socket.close();
                }
                catch(IOException e)
                {
                }
            }
        }
    }
    
    /**
     * 给多个发件人发送邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @param attachments 附件
     * @param isHtml 使用网页形式发送
     * @param isUrgent 紧急邮件
     * @return 任务状况
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean[] sendMail(String[] to,String subject,String content,File[] attachments,boolean isHtml,boolean isUrgent) throws IllegalArgumentException
    {
        boolean[] task=new boolean[to.length];
        
        for(int k=0;k<task.length;k++)
        {
            task[k]=sendMail(to[k],subject,content,attachments,isHtml,isUrgent);
        }
        
        return task;
    }

    /**
     * 发送纯文本邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @return 是否发送成功
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean sendTextMail(String to,String subject,String content) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,null,false,false);
    }
    
    /**
     * 发送HTML邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @return 是否发送成功
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean sendHtmlMail(String to,String subject,String content) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,null,true,false);
    }
    
    /**
     * 给多个发件人发送纯文本邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @return 任务状况
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean[] sendTextMail(String[] to,String subject,String content) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,null,false,false);
    }
    
    /**
     * 给多个发件人发送HTML邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @return 任务状况
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean[] sendHtmlMail(String[] to,String subject,String content) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,null,true,false);
    }
    
    /**
     * 发送带附件的纯文本邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @param attachments 附件
     * @return 是否发送成功
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean sendTextMail(String to,String subject,String content,File[] attachments) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,attachments,false,false);
    }
    
    /**
     * 发送带附件的HTML邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @param attachments 附件
     * @return 是否发送成功
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean sendHtmlMail(String to,String subject,String content,File[] attachments) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,attachments,true,false);
    }
    
    /**
     * 给多个发件人发送带附件的纯文本邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @param attachments 附件
     * @return 任务状况
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean[] sendTextMail(String[] to,String subject,String content,File[] attachments) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,attachments,false,false);
    }
    
    /**
     * 给多个发件人发送带附件的HTML邮件。
     * @param to 收件人
     * @param subject 主题
     * @param content 正文
     * @param attachments 附件
     * @return 任务状况
     * @throws IllegalArgumentException 如果参数to为null或格式不正确
     */
    public boolean[] sendHtmlMail(String[] to,String subject,String content,File[] attachments) throws IllegalArgumentException
    {
        return sendMail(to,subject,content,attachments,true,false);
    }

    /**
     * 添加一个日志管理器。
     * @param manager 日志管理器
     */
    public void addLogManager(LogManager manager)
    {
        logManager.add(manager);
    }
    
    /**
     * 移除日志管理器。
     * @param manager 要移除的日志管理器
     */
    public void removeLogManager(LogManager manager)
    {
        logManager.remove(manager);
    }
    
    /**
     * 通过分析收件人邮箱域名的DNS记录获取邮件接收服务器地址。
     * @param url 收件人邮箱域名
     * @return 主机地址列表
     */
    private String[] parseDomain(String url)
    {
        try
        {
            NamingEnumeration records=dirContext.getAttributes(url,new String[]{"mx"}).getAll();
            
            String[] address;
            String[] tmpMx;
            MX[] tmpMxArray;
            MX tmp;

            if(records.hasMore())
            {
                url=records.next().toString();
                url=url.substring(url.indexOf(": ")+2);
                address=url.split(",");
                tmpMxArray=new MX[address.length];

                for(int k=0;k<address.length;k++)
                {
                    tmpMx=address[k].trim().split(" ");
                    tmpMxArray[k]=new MX(Integer.parseInt(tmpMx[0]),tmpMx[1]);
                }
                
                for(int n=1;n<tmpMxArray.length;n++)
                {
                    for(int m=n;m>0;m--)
                    {
                        if(tmpMxArray[m-1].pri>tmpMxArray[m].pri)
                        {
                            tmp=tmpMxArray[m-1];
                            tmpMxArray[m-1]=tmpMxArray[m];
                            tmpMxArray[m]=tmp;
                        }
                    }
                }
                
                for(int k=0;k<tmpMxArray.length;k++)
                {
                    address[k]=tmpMxArray[k].address;
                }
                
                return address;
            }//分析mx记录

            records=dirContext.getAttributes(url,new String[]{"a"}).getAll();

            if(records.hasMore())
            {
                url=records.next().toString();
                url=url.substring(url.indexOf(": ")+2).replace(" ","");
                address=url.split(",");
                
                return address;
            }//分析a记录
            
            return new String[]{url};
        }
        catch(NamingException e)
        {
            log("错误: 域名\"" + url + "\"无法解析");
            return null;
        }
    }
    
    /**
     * 获得响应码。
     * @param in 输入流
     * @return 响应码
     * @throws IOException 如果发生 I/O 错误。
     */
    private int response(InputStream in) throws IOException
    {
        byte[] buffer=new byte[1024];
        int k=in.read(buffer);
        
        if(k==-1)
        {
            return -1;
        }
        
        String response=new String(buffer,0,k).trim();
        log("响应: " + response);
        return Integer.parseInt(response.substring(0,3));
    }
    
    /**
     * 输出字符串。
     * @param str 字符串
     * @param out 输出流
     * @throws IOException 如果发生 I/O 错误。
     */
    private void sendString(String str,OutputStream out) throws IOException
    {
        log("发送: " + str);

        if(str==null)
        {
            str="";
        }
        
        out.write(str.getBytes());
        out.flush();
    }
    
    /**
     * 写日志。
     * @param info 信息
     */
    private void log(String info)
    {
        for(int n=0,m=logManager.size();n<m;n++)
        {
            logManager.get(n).output(info);
        }
    }

    /**
     * 输出一个换行符。
     * @param out 输出流
     * @throws IOException 如果发生 I/O 错误。
     */
    private static void sendNewline(OutputStream out) throws IOException
    {
        out.write('\r');
        out.write('\n');
        out.flush();
    }
    
    /**
     * 获得字符串的Base64加密形式。
     * @param str 字符串
     * @return 加密后的字符串
     */
    private static String getBase64String(String str)
    {
        if(str==null||str.length()==0)
        {
            return "";
        }
        
        StringBuffer tmpStr=new StringBuffer();
        byte[] bytes=str.getBytes();
        
        for(int k=0;k<bytes.length;)
        {
            if(k!=0)
            {
                tmpStr.append(' ');
            }
            
            tmpStr.append("=?");
            tmpStr.append(CHARSET);
            tmpStr.append("?B?");
            tmpStr.append(Base64.encode(bytes,k,Math.min(bytes.length-k,30)));
            tmpStr.append("?=");
            
            k+=30;
            
            if(k<bytes.length)
            {
                tmpStr.append('\r');
                tmpStr.append('\n');
            }
        }

        return tmpStr.toString();
    }
    
    /**
     * 分析邮箱域名。
     * @param address E-Mail地址
     * @return 邮箱域名
     */
    private static String parseUrl(String address)
    {
        return address.substring(address.lastIndexOf('@')+1);
    }
    
    /**
     * MX记录。
     */
    private class MX
    {
        final int pri;
        final String address;
        
        MX(int pri,String host)
        {
            this.pri=pri;
            this.address=host;
        }
    }
}
