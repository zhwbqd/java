Today is : DATE:${nowDate?date} TIME:${nowDate?time}

--list example--
Sorted by name.last:
<#list nameList?sort as name>
  ${name}
</#list>
	
	
--map example--
<#list priceMap?keys as key>
${key} = ${priceMap[key]};
</#list>
	
--set example--
NotSorted:
<#list nameList as name>
  ${name}
</#list>
