
	//創建對象
	var xmlHttp;
	
	function createXMLHttpRequest(){
	    if (window.XMLHttpRequest)
	      {// code for IE7+, Firefox, Chrome, Opera, Safari
	    	xmlHttp=new XMLHttpRequest();
	      }
	    else
	      {// code for IE6, IE5
	    	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	      }
	}
	
	//向服務器發送請求
	function startRequest(){
	    createXMLHttpRequest();
	    
	    var selectNode=checkRadio(document.getElementById("fvote"));
	    xmlHttp.open("GET","vote?who="+selectNode,true);
	    
	    //响应处理
	    xmlHttp.onreadystatechange = function(){
	        if(xmlHttp.readyState == 4 && xmlHttp.status == 200)
	           alert(xmlHttp.responseText);
	    };
	    
	    xmlHttp.send(null);
	}
	
	
	function checkRadio(formObj){
		for(var i=0;i<formObj.vote.length;i++){   
		   if(formObj.vote[i].checked){
		     return formObj.vote[i].value;
		    }
		} 
		return null;
	}
