<html>

	<head>


		<title>Measurements</title>

		<link rel="stylesheet" type="text/css" href="/style/css/main.css">

		<script src="/script/js/jquery-1.7.1.js" type="text/javascript">

		</script>


	</head>

<body>

<script type="text/javascript">
name = "tony";
var Sp4ts_device = {};

	(function(jq){
		
		jq.fn.fieldCheck = function(){
			var target = jq(this).find("[checkRule]");
			
			target.each(function(index, el){
				var field = jq(el);
				
				field.blur(function(){
					var field = jq(this);
					var rules = field.attr("checkRule");
					var msg = field.attr("msg");
					var errorField = field.attr("msgField");
					
					if(rules == "required" && field.val().length < 1){
						
						jq("#"+errorField).html(msg);
						
					}
					
				});
				
			
			});
			
		}

		/*function hello(){
			alert(this.name);
		}
		
		var c= {};
		
		c.name = "xx";
		
		hello.apply(c);*/
		
	})($)
	
	
	
</script>

<div class="main_box" id="main_box">
<h1>JQuery start</h1>

	<div>
		<input  type="text"  name="measure_height" class="txt_input" checkRule="required" filedName="height" msg="Hight must be a number!" msgField="xxxError">&nbsp;IN
	<span id="xxxError"></span>
	</div>
	
	<input type="button" onclick="$("div").fieldCheck()">
</div>