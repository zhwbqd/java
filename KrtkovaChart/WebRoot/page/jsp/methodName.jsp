<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hp.sbs.krtkova.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<% 
	String serviceName = request.getParameter("title");
if(serviceName==null){
	serviceName = "Powered by Krtkova";
}
%>
<h1><%=serviceName%></h1>
<hr/>
<div>
<ul>
<li><a href="chartPic.jsp?title=<%=serviceName%>&picServlet=/KrtkovaChart/chartsServlet?type=pieChart" >PieChartDemo</a></li>
<li><a href="chartPic.jsp?title=<%=serviceName%>&picServlet=/KrtkovaChart/chartsServlet?type=3dPieChart" >3D-PieChartDemo</a></li>
<li><a href="chartPic.jsp?title=<%=serviceName%>&picServlet=/KrtkovaChart/chartsServlet?type=barChart" >BarChartDemo</a></li>
<li><a href="chartPic.jsp?title=<%=serviceName%>&picServlet=/KrtkovaChart/chartsServlet?type=3dBarChart" >3D-BarChartDemo</a></li>
<li><a href="chartPic.jsp?title=<%=serviceName%>&picServlet=/KrtkovaChart/chartsServlet?type=timeSeriesChart" >TimeSeriesChart</a></li>
<li><a href="chartPic.jsp?title=<%=serviceName%>&picServlet=/KrtkovaChart/chartsServlet?type=lineChart" >LineChartDemo</a></li>
<li><a href="chartPic.jsp?title=<%=serviceName%>&picServlet=/KrtkovaChart/chartsServlet?type=3dLineChart" >3D-LineChartDemo</a></li>
</ul>
</div>
</body>
</html>
