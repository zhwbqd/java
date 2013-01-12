package zhwb.java.mail.smtp;

import java.io.File;

import zhwb.java.mail.smtp.mail.SmtpMailSender;
import zhwb.java.mail.smtp.util.LogManager;

/**
 * 测试类。
 */
public class TestSmtpMail
{
    public static void main(String[] args)
    {
        SmtpMailSender sms = SmtpMailSender.createSmtpMailSender("\"Zhang\"<wen-bin.zhang@hp.com>");
        //        SmtpMailSender sms = SmtpMailSender.createESmtpMailSender("smtp.hp.com", "\"Zhang\"<wen-bin.zhang@hp.com>", "", "");
        
        sms.addLogManager(new LogPrinter());//添加日志管理器
        
        if (sms.sendTextMail("\"Jack\"<wen-bin.zhang@hp.com>", "STMP邮件测试", "这是一封测试邮件。", new File[] {new File("java.gif")}) == SmtpMailSender.SUCCESSFUL)
        {
            System.out.println("邮件发送成功。");
        }
        else
        {
            System.out.println("邮件发送失败。");
        }
    }
}

/**
 * 一个简单的日志管理器。
 */
class LogPrinter implements LogManager
{
    public void output(String info)
    {
        System.out.println(info);//将日志打印到控制台
    }
}
