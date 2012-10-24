<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>validate</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/validate.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="jslib/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="jslib/validate/jquery.validate.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//手机号码验证
		jQuery.validator.addMethod("isPhone", function(value,element) {   
		    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
		    var tel = /^\d{3,4}-?\d{7,9}$/;   
		    return this.optional(element) || (tel.test(value) || mobile.test(value));   
		}, "请正确填写联系电话");
		
		//留言验证
		jQuery.validator.addMethod("isdefault", function(value,element) {   
		    return this.optional(element) || (value!="其他需要说明的事项");   
		}, "请填写留言"); 
		  
		validateRegForm();
			
		function validateRegForm(){
			$('#fmessage').validate({
				rules : {
			'message.company' : {
				'required': true
			},
			'message.phone' : {
				'required': true,
				'isPhone':true
			},
			'message.email' : {
				'required': true,
				'email':true
			}
		},
		messages : {
			'message.company' : {
				required: "请填写公司名称"
			},
			'message.phone' : {
				required: "请填写联系电话",
				isphone:"请正确填写联系电话"
			},
			'message.email' : {
				required: "请填写Email地址",
				email:"请正确填写Email地址"
			}
			}
		});
			
		}
			
		});
		
	$("#sub").click(function(){
		$("#fmessage").submit();
	});
	</script>
  </head>
  
  <body>
	
	        <form id="fmessage" action="http://www.baidu.com" method="post">
           	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="hzjm_table">
			  <tr>
			    <td align="right">公司名称：</td>
			    <td><input name="message.company" type="text" class="input_01" /></td>
			  </tr>
			  <tr>
			    <td align="right">联系方式：</td>
			    <td><input name="message.phone" type="text" class="input_01" /></td>
			  </tr>
			  <tr>
			    <td align="right">E-mail：</td>
			    <td><input name="message.email" type="text" class="input_01" /></td>
			  </tr>
			  <tr>
				  <td align="right"><input type="submit" id="sub" value="submit"/></td>
				  <td><input type="reset" id="res" value="reset"/></td>
			  </tr>
			</table>
			
			</form>
  </body>
</html>
