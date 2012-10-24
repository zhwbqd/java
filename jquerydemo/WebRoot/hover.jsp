<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'hover.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link  href="css/validate2.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="jslib/jquery-1.7.1.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			var xOffset = -20; // x distance from mouse
   			var yOffset = 20; // y distance from mouse  
    
			var msgNode=$("#msg");
			var tipNode=$("p#vtip");
			
			msgNode.mouseover(function(event){
				//处理文本框的键盘事件
				var myEvent=event || window.event;
				
				var top = (myEvent.pageY + yOffset);
				var left = (myEvent.pageX + xOffset);
				tipNode.css("top", top+"px").css("left", left+"px");
				tipNode.fadeIn("slow");
				tipNode.css("top", top+"px").css("left", left+"px");
				
			});
			
			msgNode.mouseout(function(){
				if($("#vtip")!= undefined){
					$("p#vtip").fadeOut("slow");
				}
			});
					
		});
	</script>

  </head>
  
  <body>
    <div ><h1><span id="msg">Hover test</span></h1></div>
    <p id="vtip"><img id="vtipArrow" src="images/vtip_arrow.png"/>lalalalalal</p>
  </body>
</html>
