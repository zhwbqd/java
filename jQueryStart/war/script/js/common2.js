var SP = new Object();

(
	function(shop,jq){
		
		function updateHTML(item,data){
			 var rs = data;
			  if(rs.itemQTY){
				  jq("#"+item+" .item_qty").val(rs.itemQTY);
			  }
			  
			  if(rs.itemAmount){
				  jq("#"+item+" .item_amount").html(rs.itemAmount);
			  }
			  
			  if(rs.subTotal){
				  jq(".order_sub_total").html(rs.subTotal);
			  }
			  
			  if(rs.coupon){
				  jq(".order_coupon_total").html(rs.coupon);
			  }
			  
			  if(rs.grandTotal){
				  jq(".order_grand_total").html(rs.grandTotal);
			  }
			  
			  if(rs.couponFeedbackSuc){
				  jq("#coupon_code_noti").html(rs.couponFeedbackSuc);
			  }
			  
			  
			  if(rs.couponFeedbackErr){
				  jq("#coupon_code_noti").html(rs.couponFeedbackErr);
			  }
			  
			  if(rs.itemID){
				  jq("#"+rs.itemID).hide("normal");
				  jq("#"+rs.itemID).remove();
			  }
			  
			  if(rs.itemCount && "0"!=rs.itemCount){
				  jq("span.items_num").html("(" + rs.itemCount+")");
			  }else{
				  window.location=window.location.toString();
			  }
		}
		
		shop.increaseCartItem = function(item){
			if(item){
				jq.ajax({
					 url: "/uc/updateShoppingCart?action=increaseItemToCart&item="+item+"&v="+ Math.random(),
					 dataType:"json",
					  success: function(data){
						  updateHTML(item,data);
					  }
				});
			}
		};
		
		
		shop.decreaseCartItem = function(item){
			if(item){
				jq.ajax({
					 url: "/uc/updateShoppingCart?action=decreaseItemToCart&item="+item+"&v="+ Math.random(),
					 dataType:"json",
					  success: function(data){
						  updateHTML(item,data);
					  }
				});
			}
		};
		
		shop.removeCartItem = function(item){
			if(item){
				jq.ajax({
					 url: "/uc/updateShoppingCart?action=removeItemToCart&item="+item+"&v="+ Math.random(),
					 dataType:"json",
					  success: function(data){
						  updateHTML(item,data);
					  }
				});
			}
		};
		

		shop.applyCoupon = function(item){
			if(item){
				jq("#submit_coupon").ajLoad();
				jq.ajax({
					 url: "/uc/updateShoppingCart?action=applyCoupon&couponID="+item+"&v="+ Math.random(),
					 dataType:"json",
					  success: function(data){
						  updateHTML(item,data);
						  jq("#submit_coupon").ajUnload();
					  }
				});
			}
		};
		
		shop.goToAddress = function(item){
			if(item){
				jq.ajax({
					 url: "/uc/updateShoppingCart?action=updateCustomerMsg&v="+ Math.random(),
					 dataType:"json",
					 type:'post',
					 data: "order_msg="+item,
					  success: function(rs){
						  jq("#GO_TO_Fill_Address").ajLoad();
						  
						  if(rs.orderMsg){
							  jq("#order_msg_noti").html(rs.orderMsg);
						  }else{
							  window.location="/uc/shoppingCart_address";
						  }
						  jq("#GO_TO_Fill_Address").ajUnload();
						  
					  }
				});
			}else{
				  window.location="/uc/shoppingCart_address";
			}
		};
		
		shop.changePayType = function(){
			jq(".shipping_desc").hide();
			
			var el = jq(".shipping_title input[checked=checked]");
			
			el.parent(".shipping_title").next(".shipping_desc").show();
		};
		
		shop.getShippingPrice = function(item,type){
			var el = jq(item);
			if(el.length>0){
				jq(el).ajLoad();
				var tp = 'standard';
				
				if(type && jq(type).length>0){
					tp = jq(type).val();
				}
				
				jq.ajax({
					 url: "/uc/retrieveShippingPrice?cc="+el.val()+"&shippingMethod="+tp+"&v="+ Math.random(),
					 dataType:"json",
					 type:'post',
					 data: "order_msg="+item,
					  success: function(rs){
						  if(rs.grandTotal){
							  jq(".order_grand_total").html(rs.grandTotal);
						  }
						  
						  if(rs.standard){
							  jq(".order_shipping_standard").html(rs.standard);
						  }
						  
						  if(rs.expedited){
							  jq(".order_shipping_expedited").html(rs.expedited);
						  }
						  
						  if(rs.shippingCost){
							  jq(".order_shipping_total").html(rs.shippingCost);
						  }

						  jq(el).ajUnload();
						  
					  }
				});
			}
		};
	}		

)(SP,jq);


(function(jq){
	
		jq.fn.ajLoad = function(){
			var el = jq(this);
			if(el && el.length > 0 ){
				el.next(".ajax_load").remove();
				el.after("<img src='/style/image/ajax_loader.gif' class='ajax_load'>");
			}
		};
		
		jq.fn.ajUnload = function(){
			var el = jq(this);
			if(el && el.length > 0 ){
				el.next(".ajax_load").remove();
			}
		};
		
		function createMaskElment(){
			
			var masker = jq("#mask_content");
			
			if(masker.length < 1){
				var content = "<div id=\"mask_content\" style=\"position:absolute;background:#000;filter:alpha(opacity=10);opacity:0.2;width:100%;left:0;top:0;z-index:400;border:none;cursor: wait;\"></div>"
				jq(content).appendTo("body");
				masker = jq("#mask_content");
			}
			
			var el = jq(masker);
			el.position(0,0);
			el.css({
				"position":"absolute",
				"background":"#000"
			});
			el.width(jq(document).width());
			el.height(jq(document).height());
			return masker;
		}
		
		jq.fn.mask = function(){
			var mask = createMaskElment();
			var el = jq(this);
			el.css({"position":"absolute"});
			el.css({"z-index":"411"});
			el.css({"float":"left"});
			el.position(0,0);
			el.show();
		};
		
		jq.fn.unmask = function(){
			jq("#mask_content").remove();
			var el = jq(this);
			el.hide();
		};
		
		jq.fn.slider = function(){
			var slider = jq(this);
			slider.show();
			slider.currentSlider = 0;
			slider.sliders = slider.find(".measure_panel");
			slider.controllers = slider.find(".measure_controller img");
			slider.buttons = slider.find(".measure_navigation .measure_buttons .button_01");
			slider.tips = slider.find(".measure_controller .circle_hint");
			
			function initButtons(){
				
				slider.buttons.show();
				
				slider.buttons.unbind();
				
				var currentSlider = slider.find(".measure_panel:visible");
				
				var currentSliderIndex = 0;
				
				if(currentSlider.length > 0){
					currentSliderIndex = slider.sliders.index(currentSlider);
				}
					
				if(slider.currentSlider < 1){
					slider.currentSlider = currentSliderIndex;
				}
				
				if(slider.currentSlider == 0){
					jq(slider.buttons.get(0)).hide();
				} 
				
				if(slider.currentSlider < slider.sliders.length-1){
					jq(slider.buttons.get(2)).hide();
				}
				
				if(slider.currentSlider >= slider.sliders.length-1){
					jq(slider.buttons.get(1)).hide();
				}
				
				
				jq(slider.buttons.get(0)).click(function(){
					slider.go(slider.currentSlider - 1);
				});
				
				jq(slider.buttons.get(1)).click(function(){
					slider.go(slider.currentSlider + 1);
				});
				
				jq(slider.buttons.get(3)).click(function(){
					slider.hide();
					jq.documentUMask();
				});
			}
			
			initButtons();
			
			slider.go = function(index){
				if(index != slider.currentSlider){
					if(jq(slider.sliders.get(slider.currentSlider)).fieldCheck()){
						slider.currentSlider = index;
						slider.sliders.hide();
						jq(slider.sliders.get(slider.currentSlider)).fadeIn("slow");
						slider.controllers.removeClass("pagination_active_m");
						slider.controllers.addClass("pagination_m");
						jq(slider.controllers.get(slider.currentSlider)).removeClass("pagination_m");
						jq(slider.controllers.get(slider.currentSlider)).addClass("pagination_active_m");
					}
				}
				initButtons();
			};
			
			slider.cycle = function(){
				setInterval(function(){
					if(slider.sliders.length - 1 > slider.currentSlider){
						slider.go(slider.currentSlider +1);
					}else{
						slider.go(0);
					}
				},5000);
			}
			
			slider.controllers.each(function(index){
				jq(slider.controllers.get(index)).click(function(){
					slider.go(index);
				});
				
				/*jq(slider.controllers.get(index)).mouseover(function(e){
					slider.tips.html(jq(this).attr("title"));
					slider.tips.show();
					slider.tips.offset({top:e.pageY - 37 ,left:e.pageX - 90});
				});
				
				jq(slider.controllers.get(index)).mouseout(function(){
					slider.tips.hide();
				});*/
			});
			
			return slider;
		};

		/*jq.extend(jq.fn,{
            mask: function(msg,maskDivClass){},
            
            unmask: function(){
                     var original=jq(document.body);
                         if(this[0] && this[0]!==window.document){
                            original=jq(this[0]);
                      }
                      original.find("> div.maskdivgen").fadeOut('slow',0,function(){
                          $(this).remove();
                      });
            }
        });*/
		
		

		jq.extend({
			documentMask : function(options) {
				var op = jq.extend({
					opacity : 0.8,
					z : 10000,
					bgcolor : '#000'
				}, options);
	
				jq('<div class="jquery_addmask"> </div>').appendTo(document.body)
						.css({
							position : 'absolute',
							top : '0px',
							left : '0px',
							'z-index' : op.z,
							width : jq(document).width(),
							height : jq(document).height(),
							'background-color' : op.bgcolor,
							opacity : 0,
							cursor: "wait"
						}).fadeIn('slow', function() {
							jq(this).fadeTo('slow', op.opacity);
						})/*.click(function() {
							jq(this).fadeTo('slow', 0, function() {
								jq(this).remove();
							});
						})*/;
	
				return this;
			}
		}); 
		
		jq.extend({
			documentUMask : function(){
				jq(".jquery_addmask").remove();
			}
		});
		
		jq.fn.checkableGroup = function(){
			var el = jq(this);
			var group = el.attr("checkableGroup");
			var value = el.attr("value");
			var target = el.attr("target");
			
			if(target.val == el.attr("value")){
				if(el.next("h3").length > 0){
					jq("[checkableGroup="+group+"]").next("h3").removeClass("current");
					el.next("h3").addClass("current");
				}else{
					jq("[checkableGroup="+group+"]").removeClass("current");
					el.addClass("current");
				}
			}
			
			el.mouseover(function(){
					if(jq("[checkableGroup="+group+"]").next("h3").length > 0){
						jq("[checkableGroup="+group+"]").next("h3").removeClass("active");
					}else{
						jq("[checkableGroup="+group+"]").removeClass("active");
					}
					
					if(el.next("h3").length > 0){
						jq("[checkableGroup="+group+"]").next("h3").removeClass("active");
						el.next("h3").addClass("active");
					}else{
						el.removeClass("active");
						el.addClass("active");
					}
			});
			
			el.mouseout(function(){
				if(el.next("h3").length > 0){
					jq("[checkableGroup="+group+"]").next("h3").removeClass("active");
				}else{
					el.removeClass("active");
				}
			});
			
			el.click(function(){
				jq("#"+target).val(value);
				if(el.next("h3").length > 0){
					jq("[checkableGroup="+group+"]").next("h3").removeClass("current");
					el.next("h3").addClass("current");
				}else{
					jq("[checkableGroup="+group+"]").removeClass("current");
					el.addClass("current");
				}
			});
		}
		
	}
)(jq);

jq("#main_box").ready(function() {
	jq(this).find("input,select,textarea").each(function(idx,el){
		var target = jq(el);
		target.focus(function(){
			var name = target.attr("name");
			if(name){
				jq("#"+name+"_noti").html("");
			}
		});
		
		target.blur(function(){
			var name = target.attr("name");
			var validation = target.attr("validation");
			
			var value = target.val();
			
			if(value){
				value = (value + "").trim();
				 target.val(value);
			}
			
			if(name && validation){
				
				if(!value || value.length <1){
					jq("#"+name+"_noti").html("Missing required field");
				}
				
				if(!value || value.length > 200){
					jq("#"+name+"_noti").html("Missing required field");
				}
				
				if("country" == validation){
					if(!value || value.length <1 || value == '0'){
						jq("#"+name+"_noti").html("Please select a country");
					}
				}
				
				if("is_email_exist" == validation){
					if(!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/).test(value)){
						jq("#"+name+"_noti").html("Invalid email");
					}else{
						jq.ajax({
							 url: "/uc/checkUserEmail?RegEmail="+value+"&v="+ Math.random(),
							 dataType:"text",
							  success: function(data){
								  if("1"==data){
									  jq("#"+name+"_noti").html("Account exist");
								  }
							  }
						});
					}
				}
			}
		});
	});
	
	
	var billingAsPrimary = jq("#billingAddress_check_box").attr('checked');
	
	if("checked" == billingAsPrimary){
		jq("#billingAddress_content").hide();
		jq("#billingAddress_content_2").show();
	}else{
		jq("#billingAddress_content, #billingAddress_content_2").show();
		jq("#billingAddress_content_2").hide();
	}
	
	jq("#billingAddress_check_box").click(function(){
		var value = jq(this).attr('checked');
		if("checked" == value){
			jq("#billingAddress_content").hide();
			jq("#billingAddress_content_2").show();
		}else{
			jq("#billingAddress_content, #billingAddress_content_2").show();
			jq("#billingAddress_content_2").hide();
		}
	});
	
	
	SP.changePayType();
	
	jq(".shipping_title input").click(function(){
		jq(".shipping_desc").hide();
		jq(this).parent(".shipping_title").next(".shipping_desc").show();
	});
	
});



jq("#suitOpts").ready(function(){
	jq(this).find("[checkableGroup]").each(function(index,el){
		jq(el).checkableGroup();
	});
});






