<%
//response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
String domainId=request.getParameter("id");
Cookie _cookie=new Cookie("test",domainId);
_cookie.setMaxAge(30*60*100); 
_cookie.setPath("/");
response.addCookie(_cookie);
%>
