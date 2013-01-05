<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*" %>
<%@ page import="com.roywmiller.contacts.model.*" %>
<html>
<head>
<title>Contacts List 1.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<style type="text/css">
	body, table, hr {
		color: black;
		background: silver;
		font-family: Verdana, sans-serif;
		font-size: x-small;
	}
</style>
</head>

<body>
<h2>Contact List 1.0</h2>
<hr size="2"/>
<fieldset>
<legend><b>Please Login</b></legend>
<form method="post" action="loginAction.perform">
	<table>
		<tr>
			<td>Username:<td>
			<td><input type="text" size="30" name="username"></td>
		</tr>
		<tr>
			<td>Password:<td>
			<td><input type="password" size="30" name="password"></td>
		</tr>
	</table>
	<br/>
	<input type="submit" name="login" value="  Login  ">
</form>
</fieldset>
</body>

</html>