<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Jquery Training</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
	<div style="margin-left: 500px;margin-top: 100px;">
	
	<a href="selector/selectIndex.html">jQuery选择器</a><p/>
	
	<a href="getvalue.jsp">常见表单元素操作</a><p/>
	
	<a href="menu.jsp">滑动菜单</a><p/>
	
	<a href="table.jsp">可编辑表格</a><p/>
	
	<p/>
	<p/>
	<p/>
	<p/>
	
	<a href="vote.html">网上投票示例</a><p/>
	
	<a href="autocomplete.html">防Google自动补全插件</a><p/>
	
	<a href="live.jsp">图片搜索框架</a><p/>
	
	<p/>
	<p/>
	<p/>
	<p/>
	
	<a href="formvalidate1.jsp">Jquery表单验证框架1</a><p/>
	
	<a href="formvalidate2.jsp">Jquery表单验证框架2</a><p/>
	
	<a href="getmessage.jsp">通过后台取得数据</a><p/>
	
	<a href="award.html">抽奖系统</a><p/>
	
	<a href="lazyloadpage.jsp">页面延迟加载</a><p/>
	
	<p/>
	<p/>
	<p/>
	<p/>
		
	<a href="onload.jsp">onload与ready的区别</a><p/>
	
	</div>
	
<!-- <table width="778" cellspacing="6" cellpadding="0" border="0" align="center">
  <tbody><tr>
    <td><div align="right"><a target="_blank" href="http://sh.cyberpolice.cn/"><img width="120" height="45" border="0" alt="110" src="logo/110.jpg"></a></div></td>
    <td height="4"><div align="left"><a target="_blank" href="http://www.zx110.org"><img width="120" height="45" border="0" alt="zx110" src="logo/zx110.jpg"></a></div>
      </td>
  </tr>
</tbody></table> -->
  </body>
</html>