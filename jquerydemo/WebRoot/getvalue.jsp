<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>获取常见DOM值</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
    	文本框：
    	<input type="text" id="t1"/>
    	<input type="button" id="b1" value="点击获取文本框值"/>
    	<p/>
    	单选框：
    		<input type="radio" name="sex" value="man"/>雄性
    		<input type="radio" name="sex" value="woman"/>雌性
    	<input type="button" id="b2" value="点击获取单选框值"/>
    	<p/>
    	复选框:
    		<input type="checkbox" id="h" value="basketball">篮球
    		<input type="checkbox" id="h" value="football">足球
    		<input type="checkbox" id="h" value="badminton">羽毛球
    		<input type="checkbox" id="h" value="ping-pong">乒乓球
    		<input type="button" id="b3" value="点击获取复选框值"/>
    	<p/>
    	下拉框：
			<select id="selectTest" name="items">
				<option value="orange">橘子</option>
				<option value="banana">香蕉</option>
				<option value="watermelon">西瓜</option>
			</select> 
		<input type="button" id="b4" value="点击获取下拉框值"/>
		<p/><p/>
		
		<select id="mulSelect" multiple="multiple" style="width: 120px; height: 100px;">
			<option value="x">谢霆锋</option>
			<option value="c">陈冠希</option>
			<option value="z">张柏芝</option>
			<option value="j">阿娇</option>
		</select>
		<input type="button" value=">>" id="add" />
		<input type="button" value="<<" id="del"/>
		<select id="mulValue" name="items" multiple="multiple" style="width: 120px; height: 100px;">
		</select> 
    	
  </body>
  
  <script type="text/javascript" src="jslib/jquery-1.7.1.js"></script>
	 <script type="text/javascript">
	 	$(document).ready(function(){
	 	
	 		$("#b1").click(function(){
	 		    var v=$("#t1").val();
	 			alert(v);
	 		});
	 		
	 		$("#b2").click(function(){
	 			var sex = $(":radio:checked").val();
	 		    //var sex = $("input[name='sex']:checked").val();
	 			alert(sex);
	 		});
	 		
	 		$("#b3").click(function(){
	 		  	/*$("#h:checked").each(function(){   
					//str+=$(this).val()+"\r\n";   
					alert($(this).val()); 
				}); */
				var res='';
				$(":checkbox:checked").each(function(){
					res=res+$(this).val()+" ";
				});
				alert(res);
	 		});
	 		
	 		$("#b4").click(function(){
	 		 	 var item = $("#selectTest").val();
	 		 	 //var item = $("#selectTest :selected").val();
	 		 	 alert(item);
	 		 	 
	 		});
	 		
	 		$("#add").click(function(){
	 			var valueNode=$("#mulValue");
	 			$("#mulSelect :selected").each(function(){
	 				//get value
	 				var tempNode=$(this);
	 				var optionNode="<option val='"+tempNode.val()+"' >"+tempNode.text()+"</option>";
	 				//delete
	 				tempNode.remove();
	 				//add
	 				valueNode.append(optionNode);
				});
	 		
	 		});
	 		
	 		$("#del").click(function(){
	 			var selectNode=$("#mulSelect");
	 			$("#mulValue :selected").each(function(){
	 				//get value
	 				var tempNode=$(this);
	 				var optionNode="<option val='"+tempNode.val()+"' >"+tempNode.text()+"</option>"
	 				//delete
	 				tempNode.remove();
	 				//add
	 				selectNode.append(optionNode);
				});
	 		
	 		});
	 		
	 	});
	 </script>
</html>
