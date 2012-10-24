//在页面装载时，给所有的主菜单注册点击事件

//注册页面装在方法
$(document).ready(function(){
	//首先找到所有的主菜单
	//然后给所有的主菜单注册事件
	var as=$("ul > a");
	//var as=$("ul").children("a");
	
	//这里需要找到ul下面所有的li，然后显示出来
	as.click(function(){
		//获取当前被点击的a节点
		var aNode=$(this);
		//找到当前a节点同层li
		//var lis=ulNode.children("li");
		//nextAll查找当前元素之后所有的同辈元素
		var lis=aNode.nextAll("li");
		//显示或隐藏当前的li
		lis.toggle("show");
		//lis.fadeIn("show");
		//lis.slideDown("show");
	});
	
	$("li > a").click(function(){
		$("#page").load($(this).attr("id"));
	});
	
});