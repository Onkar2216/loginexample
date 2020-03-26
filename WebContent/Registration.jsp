<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
<%
	String err=(String)request.getAttribute("error");
	if(err!=null)
	{
%>		
		<%=err %> 
<% 
	}
%>
<form action="registerServlet" method="post">
<center>
<table>
<tr>
	<th>
		User Name
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="uname" size=25 required="required">
	</th>	
</tr>

<tr>
	<th>
		Password
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="password" name="password" size=25 required="required">
	</th>	
</tr>

<tr>
	<th>
		Email
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="email" name="email" size=25 required="required">
	</th>	
</tr>

<tr>
	<tr></tr>
	<tr></tr>
	<tr>
		<th>
		<th>
			<input type="submit" value="Register" name="register">
		</th>
		</th>
	</tr>	
</tr>
<tr>
	<th>
		Already Have Account
	</th>
	
	<th>
	<input type="submit" value="login" name="login" >
	</th>
</tr>
</table>
</center>
</form>
</body>
</html>