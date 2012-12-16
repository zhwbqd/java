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

<h2>Contact List 1.0</h2> <a href="logoutAction.perform">Logout</a>
<hr size="2"/>
<b>Welcome, <%= user.getUsername() %>!</b>
<br/>
<br/>
<table frame="below" width="100%">
  <tr>
    <th align="left">&nbsp;</th>
    <th align="left">Name</th>
    <th align="left">Street</th>
    <th align="left">City</th>
    <th align="left">State</th>
    <th align="left">Zip</th>
    <th align="left">Type</th>
  </tr>
<%
  List list = user.getContacts();
  for (Iterator i = list.iterator(); i.hasNext();) {
    Contact contact = (Contact)i.next();
%>
  <tr>
    <td width="100"><a href="removeContactAction.perform?id=<%= contact.getId()%>" >Delete</a></td>  
    <td width="200"><%=contact.getFirstname()%> <%=contact.getLastname()%></td>
    <td width="150"><%=contact.getStreet()%></td>
    <td width="100"><%=contact.getCity()%></td>
    <td width="100"><%=contact.getState()%></td>
    <td width="100"><%=contact.getZip()%></td>
    <td width="100"><%=contact.getType()%></td>
  </tr>
<%
  }
%>  
</table>
<br/>
<br/>
<br/>
<fieldset>
<legend><b>Add Contact</b></legend>
<form method="post" action="addContactAction.perform">
	<table>
		<tr>
			<td>First Name:<td>
			<td><input type="text" size="30" name="firstname"></td>
		</tr>
		<tr>
			<td>Last Name:<td>
			<td><input type="text" size="30" name="lastname"></td>
		</tr>
		<tr>
			<td>Street:<td>
			<td><input type="text" size="30" name="street"></td>
		</tr>
		<tr>
			<td>City:<td>
			<td><input type="text" size="30" name="city"></td>
		</tr>
		<tr>
			<td>State:<td>
			<td><input type="text" size="30" name="state"></td>
		</tr>
		<tr>
			<td>Zip:<td>
			<td><input type="text" size="30" name="zip"></td>
		</tr>
		<tr>
			<td>Type:<td>
			<td><input type="radio" size="30" name="type" value="family"> Family <input type="radio" size="30" name="type" value="acquaintance" checked> Acquaintance</td>
		</tr>
	</table>
	<br/>
	<input type="submit" name="addContact" value="  Add  ">
</form>
</fieldset>

</body>
</html>