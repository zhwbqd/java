//定义全局变量,表示当前高亮的节点
var highlightindex=-1;
var timeoutId;

$(document).ready(function(){
	var wordInput=$("#word");
	
	//控制显示框的样式
	var wordInputOffset=wordInput.offset();
	$("#auto").hide().css("border","1px black solid").css("position","absolute")
		.css("top",wordInputOffset.top+wordInput.height()+ 5 +"px")
		.css("left",wordInputOffset.left+"px").width(wordInput.width()+ 2);
	
	//响应键盘按下事件
	wordInput.keyup(function(event){
		//处理文本框的键盘事件
		var myEvent=event || window.event;
		var keyCode=myEvent.keyCode;
		//如果输入的是字母,应将最新的文本框值输送到服务器
		//按下退格键或删除键也应获得最新响应数据
		if(keyCode>=65 && keyCode<=90 || keyCode==8 || keyCode==46){
			//1.首先获取文本框的值
			var wordVal=$("#word").val();
			var autoNode=$("#auto");
			if(wordVal!==""){
				//对上次未完成的延时操作进行取消
				clearTimeout(timeoutId);
				//延时操作,避免用户打字过快相关误操作,减轻服务端压力,采用延时加载的方法
				timeoutId=setTimeout(function(){
					//2.异步交互
					$.post("autocomplete?random="+Math.random(),{word:wordVal},function(data){
						//alert(data);
						//将dom对象data转换成jquery的对象
						var words=eval(data);
						//清空上次的请求结果
						autoNode.html("");
						for(i in words){
							//新建div节点  插入到结果div框中
							var newDivNode=$("<div>").attr("id",i);
							newDivNode.html(words[i]).appendTo(autoNode);
							
							//增加鼠标进入事件,高亮节点
							newDivNode.mouseover(function(){
								//将原来高亮的节点取消
								if(highlightindex!=-1){
									autoNode.children("div").eq(highlightindex).css("background-color","white");
								}
								//记录新的高亮节点索引
								highlightindex=$(this).attr("id");
								$(this).css("background-color","red");
							});
							
							//增加鼠标移出事件,失去高亮
							newDivNode.mouseout(function(){
								$(this).css("background-color","white");
							});
							
							//双击鼠标自动补全
							newDivNode.click(function(){
								$("#word").val($(this).html());
								autoNode.hide();
								highlightindex=-1;
							});
						}
						if(words.length>0){
							autoNode.show();
						}else{
							autoNode.hide();
							//弹出框隐藏的同时,高亮节点的值-1
							highlightindex=-1;
						}
					});
				},500);
			}else{
				autoNode.hide();
				//弹出框隐藏的同时,高亮节点的值-1
				highlightindex=-1;
			}
		}else if(keyCode==38 || keyCode==40){
			//按上下键时对应操作
			if(keyCode==38 ){
				//向上
				var auotoNodes=$("#auto").children("div");
				if(highlightindex!=-1){
					//如果原来存在高亮节点,则改为白色
					auotoNodes.eq(highlightindex).css("background-color","white");
					highlightindex --;
				}else{
					highlightindex=auotoNodes.length-1;
				}
				if(highlightindex ==-1){
					//如果改完索引以后变为-1,则指向最后一个
					highlightindex=auotoNodes.length-1;
				}
				//让现在高亮的内容变为红色
				auotoNodes.eq(highlightindex).css("background-color","red");
			}
			if(keyCode==40 ){
				//向下
				var auotoNodes=$("#auto").children("div");
				if(highlightindex!=-1){
					//如果原来存在高亮节点,则改为白色
					auotoNodes.eq(highlightindex).css("background-color","white");
				}
				highlightindex ++;
				if(highlightindex == auotoNodes.length){
					//如果改完索引以后变为-1,则指向最后一个
					highlightindex=0;
				}
				//让现在高亮的内容变为红色
				auotoNodes.eq(highlightindex).css("background-color","red");
			}
		}else if(keyCode==13){
			//按下回车响应事件
			//下拉框有高亮
			if(highlightindex!=-1){
				//取下拉框值
				var comText=$("#auto").hide().children("div").eq(highlightindex).text();
				highlightindex=-1;
				//修改文本框值
				$("#word").val(comText);
				alert("你选择的"+comText+"的已经提交了");
			}else{
				//下拉框无高亮
				alert("你选择的"+$("#word").val()+"的已经提交了");
				$("#auto").hide();
				//让文本框失去焦点
				$("#word").get(0).blur();
			}
		}
	});
	
	//按钮点击事件
	$("input[type='button']").click(function(){
		alert("你选择的"+$("#word").val()+"的已经提交了");
	});
});