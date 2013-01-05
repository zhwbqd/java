<#--import example-->
<#import "common.ftl" as c> 
<@c.html title="Welcome!"> 
<h1>Welcome ${user}!</h1>
<p>Our latest product:
<a href="${latestProduct.url}">${latestProduct.name}</a>!
</@c.html> 

</body>
</html>

<#macro repeat count>
<#list 1..count as x>
<#nested x, x/2, x==count>  
</#list>
</#macro>

<#macro list title items>
<p>${title?cap_first}:
<ul>
<#list items as x>
<li>${x?cap_first}
</#list>
</ul>
</#macro>

<#function avg nums...>
	<#if nums?size==0>
		<#return "input can not be empty">
	</#if>
	<#local sum=0>
	<#list nums as num>
		<#local sum = sum + num>
	</#list>
	<#return sum/nums?size>
</#function>

<#-- func example -->
${avg(10, 20)}
${avg(10, 20, 30, 40)}
${avg()!"N/A"}

<#-- macro example -->
	<@repeat count=4 ; c, halfc, last>
	${c}. ${halfc}<#if last> Last!</#if>
	</@repeat>
	
	<@list items=["mouse", "elephant", "python"] title="Animals"/>
	
<#-- if else example-->
	<#assign x=2>
	<#if (x>1)> 
	x greater than 1
	<#elseif (x>0)>
	x between 0 and 1
	</#if>
	
<#-- list example -->
	<#assign members = [
	{"name": {"first": "Joe", "last": "Smith"}, "age": 40},
	{"name": {"first": "Fred", "last": "Crooger"}, "age": 35},
	{"name": {"first": "Amanda", "last": "Fox"}, "age": 25}]>
	Sorted by name.last:
	<#list members?sort_by(['name', 'last']) as m>
	- ${m.name.last}, ${m.name.first}: ${m.age} years old
	</#list>
	
<#-- map example -->
	<#assign h = {"name":"mouse", "price":50}>
	<#assign keys = h?keys>
	<#list keys as key>
	${key} = ${h[key]};
	</#list>
