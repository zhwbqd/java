(function($){
	
	$.fn.fieldCheck = function(){
		var container = $(this);
		
		var fields = container.find("*[checkRule]");
		
		var passed = true;
		
		fields.each(function(index, el){
			
			var field = $(el);
			
			var rule = field.attr("checkRule");
			
			var fieldName = field.attr("filedName");
			
			var msg = field.attr("msg");
			
			var msgField = $("#"+field.attr("msgField"));
			
			field.focus(function(){
				msgField.html("");
			});
			
			
			if(rule && rule.length>0){
				
				var rules = rule.split(",");
				
				//msgField.html("");
				
				var value = field.val();
				
				for(var i =0 ; i< rules.length; i++){
				
					if("double" == rules[i]){
						if(!(/^\d+(\.\d+)?$/).test(value) || !(parseFloat(value) > 0)){
							msgField.html(msg);
							passed = false;
						}
					}
					
					if("required" == rules[i]){
						if(!value || value.length < 1){
							msgField.html(msg);
							passed = false;
						}
					}
				
				}
			}
			
		});
		
		return passed;
	};
	
})(jq);