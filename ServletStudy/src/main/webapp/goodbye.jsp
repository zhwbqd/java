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
<jsp:useBean id="user" scope="session" class="com.roywmiller.contacts.model.ContactsUser"/>

<h2>Contact List 1.0</h2>
<hr size="2"/>
Goodbye <%= user.getUsername() %>!
</body>

</html>