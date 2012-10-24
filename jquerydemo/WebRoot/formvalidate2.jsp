<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>validate2</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="jslib/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="jslib/validate/validate2/easy_validator.pack.js"></script>
	<script type="text/javascript" src="jslib/validate/validate2/jquery.bgiframe.min.js"></script>
	<link  href="css/validate2.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
<hr />
FORM1表单验证
<hr />
<form name="validateForm1" action="http://wangking717.javaeye.com/" method="post">
<table width="800" border="0" class="ad" cellpadding="0" cellspacing="1" bgcolor="#999999" id="testTable">
 　　<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		用户名 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<input name="flightno" type="text" id="flightno" reg="^\w{2}\d+$" tip="游戏商名称[2个字母简写]+用户ID[数字] 如: sd10059"/>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		中文姓名 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<input name="floatNum" type="text" id="floatNum" reg="^[\u4e00-\u9fa5]+$" tip="只允许中文字符"/>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		电话号码 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<input name="str" type="text" id="str" reg="^\d{3}-\d{8}$|^\d{4}-\d{7}$" tip="国内电话号码，格式: 0832-4405222 或 021-87888822"/>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		邮箱地址 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<input name="groupname" type="text" id="groupname" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="邮箱地址，如wangking717@qq.com" />
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		来自哪里 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<select id="from" name="from" reg="[^0]" tip="一定要选择哟">
		  <option value="0">－－请选择你来自哪里－－</option>
		  <option value="a">北京</option>
		  <option value="b">上海</option>
		  <option value="c">四川</option>
		</select> <span name="easyTip"></span>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
	<td colspan="2" align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
	<input type="submit" name="submit" value=" 提交 " id="submit" />
	</td>
	</tr>
</table>
</form>


<!-- FORM2表单验证
<hr />
<form name="validateForm2" action="http://wangking717.javaeye.com/" method="post">
<table width="800" border="0" class="ad" cellpadding="0" cellspacing="1" bgcolor="#999999" id="testTable">
	<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		网址 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<input name="time1" type="text" id="time1" reg="^(http|https|ftp)\://[a-zA-Z0-9\-\.]+\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\-\._\?\,\'/\\\+&%\$#\=~])*$" tip="URl格式，允许FTP,HTTP,HTTPS"/>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		图片上传 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<input name="imgFile" type="file" id="imgFile" reg=".*gif|png$" tip="允许GIF,PNG图片"/>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td align="right" bgcolor="#EEEEEE" width="150px" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		文本 : 
		</td>
		<td align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
		<textarea name="myeara" reg="^.+$" tip="不能为空" cols="40" rows="5">菜网付11715+预付1月5000</textarea>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
	<td colspan="2" align="left" style="padding-left: 5px; padding-top: 4px; padding-bottom: 4px; padding-right: 18px;">
	<input type="submit" name="submit" value=" 提交 " id="submit" />
	</td>
	</tr>
</table>
</form>	-->	
  </body>
</html>
