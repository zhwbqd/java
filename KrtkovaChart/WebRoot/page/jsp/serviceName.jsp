<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hp.sbs.krtkova.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<String> serviceNames = LoadFile.loadService();
	Iterator<String> iter = serviceNames.iterator();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/TestJfreeChart/resource/css/mainStyle.css" type="text/css" />
<title>Service and Method Name</title>
</head>
<hr />
<body>
<h3>Service Catagory</h3>
Service catagory list as below:
<ul>
<%while(iter.hasNext()){
	String serviceName = iter.next();%>
<li><a target="methodName" href="methodName.jsp?title=<%=serviceName%>"><%=serviceName%></a></li>
<%} %>
</ul>
<hr />
</body>
</html>
