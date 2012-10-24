$(document).ready(function (){
/*	$("input").each(function(){
		var a=$(this);
		alert(a.val());
	});*/

	
	//找到所有的td
	var tds=$("td");
	//注册点击事件
	tds.click(tdclick);
});

//td点击事件
function tdclick(){
	var td=$(this);
	//1.取当前td中的文本内容
	var text=td.text();
	//获取值
	//var text=$(this).val();
	
	//2.清空
	//td.empty();
	td.html("");
	
	//3.建立文本框,也就是输入框
	var input=$("<input>");
	
	//4.设置文本框值
	//input.val(text);
	input.attr("value",text);
	//4.5相应键盘事件
	input.keyup(function(event){
		//0.获取用户按键键值
		//解决不同浏览器获取事件对象的差异
		var myEvent=event || window.event;
		var kycode=myEvent.keyCode;
		//1.是否是回车按下
		if(kycode==13){
			var inputNode=$(this);
			//1.保存当前文本值
			var inputtext=inputNode.val();
			//2.清空td内容
			var tdNode=inputNode.parent();
			tdNode.empty();
			//3.设置td文本
			tdNode.html(inputtext);
			//4.让td重新拥有点击事件
			tdNode.click(tdclick);
		}
	});
	//5.将文本框添加到td中
	//也可以用input.appendTo(td);
	td.append(input);
	
	//5.5让文本框里面的文字高亮显示
	//需要将jquery对象转化为dom对象
	var inputdom=input.get(0);
	inputdom.select();
	//6.清除td上的点击事件
	td.unbind();
}
