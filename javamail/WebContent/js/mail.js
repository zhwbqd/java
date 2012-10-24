var atach_list=[];
function sendEmail()
	{
		var stmp=$("#stmp_host").val();
		var port=$("#stmp_host_port").val();
		var from=$("#from").val();
		var to=$("#to").val();
		var subject=$("#subject").val();
		var content=$("#content").val();
		alert(atach_list[0]);
		var atachs="";
		
		$.each(atach_list,function(n,atach){
			atachs+=atach+";";
		});
		atachs=atachs.substring(0,atachs.length-1);
		alert(atachs);
		$.post("sendemail",{action:"SENDEMAIL",
			stmp_host:stmp,
			mail_from:from,
			mail_to:to,
			mail_subject:subject,
			mail_content:content,
			mail_atach_list:atachs,
			stmp_host_port:port},
			function(data){
				if(data=="success")
				{
					$("#email_result").append("邮件发送成功");
				}
				else
				{
					$("#email_result").append(data);
				}
			});
	}
	function newAtach()
	{
		var atach=$("#atach").val();
		if(atach!="")
		{
			atach_list[atach_list.length]=atach;
			$("#atach_list").append('<div id="atach'+atach_list.length+'">'+atach+'</div>');
			$("#atach").val("");
		}
		else
		{
			return;
		}
	}
