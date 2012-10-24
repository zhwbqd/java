<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>$(this)与this</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="jslib/jquery-1.7.1.js" ></script>
	<script type="text/javascript">
		function optDelete(_object){
			//alert(_object.tagName);
			var currentli=$(_object).parent().parent();
			currentli.empty();
		}
	</script>
  </head>
  
  <body>
  
  <table>
  	<tr>
  	<td><input type="checkbox" value="49" name="ids"></td>
  	<td><a name="link1" onclick="optDelete(this);" href="javascript:void(0)">删除</a></td>
  	</tr>
  	<tr>
  	<td><input type="checkbox" value="49" name="ids"></td>
  	<td><a name="link2" onclick="optDelete(this);" href="javascript:void(0)">删除</a></td>
  	</tr>
  	<tr>
  	<td><input type="checkbox" value="49" name="ids"></td>
  	<td><a name="link3" onclick="optDelete(this);" href="javascript:void(0)">删除</a></td>
  	</tr>
  </table>

  </body>
</html>
