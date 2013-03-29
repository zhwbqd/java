<html>
 <head></head>
 <body>
 <div id="emailBody" style="font-family:Arial;font-size:14px">
 Dear Customer;	<br/><br/>									
Thank you for your interest in HP.  This is to inform you that one or more unused Service Credits are within ${expiringWithIn} days of expiration.<br/><br/>	
HP Service Credits cannot be used past their expiration date or rolled over to the renewal service agreement.<br/><br/>			
Support agreement
<table>
<tr><td>SAR/SAID/OBID</td>						<td>Description<td>		<td>Service Credits</td>	<td>Expiration Date</td></tr>
<#list supportAgreement as SA>
<tr><td>${SA.sar}/${SA.said}/${SA.obid}</td><td>${SA.packageDescription}</td><td>${SA.creditsQty}</td>	<td>${SA.expiredDate}</td></tr>
</#list>
<table>
<br/>
Please visit the Shop HP Services page of the Service Credits feature within HP Support Center for a list of services that may benefit your IT environment.
<div style="margin-left:20px">${SChomePageUrl}</div>						
Thank You<br/>										
HP standard signature	
</div>


 </body>
</html>