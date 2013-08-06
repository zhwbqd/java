<%@ page contentType="text/html; charset=utf-8" %>
<%
   Cookie cookies[]=request.getCookies(); // 将适用目录下所有Cookie读入并存入cookies数组中
 
   Cookie sCookie=null; 
   String sname=null;
   String name=null;
   if(cookies==null) // 如果没有任何cookie
     out.print("none any cookie");
   else
   {
     out.print(cookies.length + "<br>");
     for(int i=0;i<cookies.length; i++) // 循环列出所有可用的Cookie
     {
       sCookie=cookies[i];
       sname=sCookie.getName();
       name = sCookie.getValue();
       out.println("comment==>>>"+sCookie.getComment()+"\n");
       out.println("getDomain==>>>"+sCookie.getDomain()+"\n");
       out.println("getSecure==>>"+sCookie.getSecure()+"\n");
       out.println("getVersion==>>"+sCookie.getVersion()+"\n");
       out.println("cookiename==>>"+sname + "->" + "cookievalue==>>>"+name + "<br>");
     }
   } 
%>
