<%@include file="prevent.jsp" %>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.database.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update User</title>
</head>
<style>
h1
{
	font-size:45px;
	text-align:center;
	color:green;
}
</style>
<body>

<h1>Update User</h1>

<br>

<a href="userPage.jsp">
<font size=5 color="orange" alignment="left">
>>Admin
</font>
</a>

<a href="allUsersList.jsp">
<font size=5 color="orange" alignment="left">
>>All User List
</font>
</a>
<br>
<br>

<form action="userUpdate" method="get">
<center>
<%
	//HttpSession s=request.getSession();
System.out.println("user Upadate jsp session id >> "+session.getId());
	String	update=(String)request.getAttribute("update");
	if(update!=null)
	{
%>
<%=update %>
<%} %>
	</center>
<br>
<br>

<center>
<table>
<% 	
		HashMap<String,ArrayList<String>> users=(HashMap<String,ArrayList<String>>)session.getAttribute("userdata");

		Set<String> keys=users.keySet();
		for(String key: keys)
		{
			ArrayList<String> userdata=users.get(key);	
			String uname=userdata.get(0);
			String pass=userdata.get(1);
			String email=userdata.get(2);
			
		%>
<tr>
	<th>
		User Name
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" value="<%=uname %>" name="uname" size="25">
	</th>	
</tr>

<tr>
	<th>
		Password
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="password" value="<%=pass %>" name="password" size=25>
	</th>	
</tr>

<tr>
	<th>
		Email
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="email" value="<%=email %>" name="email" size=25>
	</th>	
</tr>

<tr>
	<tr>	
		<th></th>
		<th>
			<input type="submit" value="Update User" name="updateUser">
		</th>
	</tr>	
</center>
<%} %>
</table>

</form>
</body>
</html>