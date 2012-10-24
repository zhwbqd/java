<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>selector</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.cla1{
		height: 100px;
		width: 300px;
		border: 2px;
		background: pink;
	}
	.cla2{
		height: 100px;
		width: 100px;
		background: orange;
	}
	.cla3{
		height: 100px;
		width: 200px;
		background: red;
		margin-left: 2px;
	}
	</style>
	
	<script type="text/javascript" src="jslib/jquery-1.7.1.js" ></script>
	<script type="text/javascript">
		$(document).ready(function(){
		
			$("#btn1").click(function(){
				$("body div").css("backgroundColor","black");
			});
			
			$("#btn2").click(function(){
				$("body > div").css("backgroundColor","black");
			});
			
			$("#btn3").click(function(){
				//$('.cla1 + div').css("backgroundColor","blue");
				$(".cla1").next("div").css("backgroundColor","blue");
			});
			
			$("#btn4").click(function(){
				//$('.cla1 ~ div').css("backgroundColor","blue");
				$(".cla1").nextAll("div").css("backgroundColor","blue");
			});
			
			//filter
			$("#btn5").click(function(){
				$("div:first").css("backgroundColor","blue");
			});
			
			$("#btn6").click(function(){
				$("div:even").css("backgroundColor","black");
			});
			
			$("#btn7").click(function(){
				$("div:eq(1)").css("backgroundColor","red");
			});
			
			$("#btn8").click(function(){
				$("div:not(.cla3)").css("backgroundColor","yellow");
			});
			
			$("#btn9").click(function(){
				$("div:gt(2)").css("backgroundColor","#abcdef");
			});
			
			//
			$("#btn10").click(function(){
				$("div:hidden").show(1000).css("backgroundColor","black");
			});
			
			//
			$("#btn11").click(function(){
				$("div[title]").css("backgroundColor","green");
			});
			
			$("#btn12").click(function(){
				//$("div[title=test]").css("backgroundColor","green");
				//$("div[title!=test]").css("backgroundColor","green");
				$("div[title^=t]").css("backgroundColor","white");
			});
			
			$("#btn13").click(function(){
				$("div.cla1 :first-child").css("backgroundColor","white");
			});
		});
	</script>
  </head>
  
  <body>
  	<input type="button" id="btn1" value="btn1"/>
    <input type="button" id="btn2" value="btn2>"/>
    <input type="button" id="btn3" value="btn3+"/>
    <input type="button" id="btn4" value="btn4~"/><p/>
    
    <input type="button" id="btn5" value="btn5"/>
    <input type="button" id="btn6" value="btn6"/>
    <input type="button" id="btn7" value="btn7"/>
    <input type="button" id="btn8" value="btn8"/>
    <input type="button" id="btn9" value="btn9"/><p/>
    
    <input type="button" id="btn10" value="btn10"/>
    <input type="button" id="btn11" value="btn11"/>
    <input type="button" id="btn12" value="btn12"/>
    <input type="button" id="btn13" value="btn13"/>
    
    <div class="cla1" title="test">
    	<div class="cla2"><span>选取子元素</span></div>
    </div>
    
    <div class="cla3">+选取紧跟后的元素</div>
    
    <div class="cla3">~选取之后的所有元素 </div>
    
    <div style="display: none;">test jquery selector</div>
  </body>
</html>
