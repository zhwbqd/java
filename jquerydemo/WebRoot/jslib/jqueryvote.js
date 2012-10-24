$(document).ready( function() {
	
	//响应提交按钮点击事件
	$("#tijiao").click(function(){
		//获取客户端选中值
		var selectNode=$("#vote:checked").val();
		//异步提交请求
		$.get("vote?who="+selectNode,null,function(data){
			alert(data);
		});
	});
	
});