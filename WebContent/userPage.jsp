<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@include file="prevent.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<style>
h1{
	text-align:center;
	font-size:55px;
}
h2{
	color:red;
	text-align:center;
	font-size:50px;
}
p{
	text-align:center;
	size:45px;
}
tr,table
{
	border:1px;
	padding:10px;
	border-collapse: collapse;  
}
th
{	
	background:skyblue;
	border:1px;
	padding:10px;
	border-collapse: collapse;  
}
</style>
<body>

<form action="adminServlet" method="post">
<header>
<h1>
Welcome To Admin Page
</h1>
</header>
<br>
<a href="userPage.jsp">
<font size=5 color="orange" alignment="left">
>>Admin
</font>
</a>
<br>
<h2>
			Add User by Admin
</h2>	

<br>
<br>
<br>
<br>
<br>

<%
	String success=(String)request.getAttribute("successAdmin");
	String error=(String)request.getAttribute("Error");	
	if(success!=null)
	{
%>	
<font color="red" alignment="center">
		<%=success %>
</font>	 
<% 
	}
	if(error!=null)
	{
%>		
<font color="blue" alignment="center">
	<%=error%>
	</font>
<%
	}
%>
<br>
<br>
<table id="AddUser" align="center" bgcolor="Orange">
	<thead>
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
			<th>
				email
			</th>
		<th>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="email" size=25 >
		</th>	
		</tr>
	</thead>
	<thead>
		<tr>
			<th >
				<input type="submit" value="Add User" name="addUser"/>
			</th>
			<th >
				<input type="submit" value="View User" name="viewUser"/>
			</th>
		</tr>
		
	</thead>
	
</table>
</form>
</center>
</body>
</html>
