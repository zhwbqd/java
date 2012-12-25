<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/KrtkovaChart/resource/css/mainStyle.css" type="text/css" />
<title>Created chart picture</title>
<% 
	String title = request.getParameter("title");
    String picServlet = request.getParameter("picServlet");   
%>
</head>
<body>
<h1><%=title %></h1>
<a href="methodName.jsp?title=<%=title %>" >Return Menu</a>
<hr/>
<div align ="center">
  <img src="<%=picServlet%>&title=<%=title %>" border="0">      
</div>

</body>
</html>
