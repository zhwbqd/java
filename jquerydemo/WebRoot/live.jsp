<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>live</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="jslib/jquery-1.7.1.js"></script>
	<script type="text/javascript">
	$(function(){
		$('.clickme').live('click', function() {
		  alert("Bound handler called.");
		  $('body').append('<div class="clickme">Another target</div>');
		});
	})
	</script>
  </head>
  
  <body>
    <div class="clickme">Click here</div>
  </body>
</html>
