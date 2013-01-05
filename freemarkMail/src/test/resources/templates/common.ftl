<#macro html title>
<html>
<head>
<title>${title?html}</title>
</head>

<body>
<#nested/>
</body>

</html>
</#macro>
