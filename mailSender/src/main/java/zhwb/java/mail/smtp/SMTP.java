/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.java.mail.smtp;

import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class SMTP
{
    public static void main(String[] args)
        throws Exception
    {
        // DNS服务器，看看本机的DNS配置
        String dns = "dns://16.110.135.52";
        // 邮箱后缀：
        String domain = "hp.com";
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        env.put("java.naming.provider.url", dns);
        DirContext ctx = new InitialDirContext(env);
        Attributes attr = ctx.getAttributes(domain, new String[] {"MX"});
        NamingEnumeration<? extends Attribute> servers = attr.getAll();
        // 列出所有邮件服务器：
        while (servers.hasMore())
        {
            System.out.println(servers.next());
        }
    }
}
