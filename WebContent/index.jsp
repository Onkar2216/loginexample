<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>

 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<title>Login</title>
</head>
<body  style="background-color:powderblue;">
<header >
<center>
<%
	String msg=(String)request.getAttribute("invalid");
	String success=(String)request.getAttribute("success");
	if((msg!=null))
	{
%>	
<font color="red">
		<%=msg %>
<%}
	if(success!=null)
	{
	%>
		<%=success %>
</font>	 
<% 
	}
%>
</center>
</header>
<br>
<form action="loginServlet" method="post" >
<center>
<table class="card-body">
<tr>
	<th>
		User Name
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="uname" size=25 >
	</th>	
</tr>

<tr>
	<th>
		Password
	</th>
	<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="password" name="password" size=25 >
	</th>	
</tr>

<tr>
	<tr></tr>
	<tr></tr>
	<tr>
	<div >
	
	</div>
		<th>
			<input type="submit" value="login" name="in" class="btn btn-primary">
		</th>
		
		<th>
			<input type="submit" value="Registration" name="register"  class="btn btn-secondary">
		</th>
	</tr>	
</tr>

</table>
</center>
</form>

</body>
</html>