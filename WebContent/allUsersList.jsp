<%@page import="javafx.scene.control.Alert.AlertType"%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="prevent.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
<style>  
table, th, td {  
  border: 1px solid black;  
  border-collapse: collapse;  
}  
th, td {  
  padding: 10px;  
}  
table#userlist tr:nth-child(even) {  
  background-color: #eee;  
}  
table#userlist tr:nth-child(odd) {  
  background-color: #fff;  
}  
table#userlist th {  
  color: white;  
  background-color: gray;  
}
button
{

	background-color:pink;
}
</style>  
</head>
<body>
<form action="userDelete" method="post">
<header>
<center>
<font size=30>
All Users
</font>
</center>
</header>
<br>
<a href="userPage.jsp">
<font size=5 color="orange" alignment="left">
>>Admin
</font>
</a>

<br>
<center><h1><i>User List</i></h1></center>
<br>
<div align="center">
Delete Selected >> <input type="submit" value="Delete Selected" id="update" name="deletem">
</div>
<table id="userlist" align="center">
	<thead border="black">
		<tr>
			<th >
				Select
			</th>
			<th >
				User Name
			</th>
			
			<th >
				Email
			</th>
			
			<th >
				delete
			</th>
			
			<th >
				update
			</th>
			
		</tr>
<%
	
	String update=(String)request.getAttribute("update");
	String delete=(String)request.getAttribute("delete");
	if(update!=null)
	{
%>
<center>
<%=update%>
</center>
<%
	}
	if(delete!=null)
	{
%>
<center>
<%=delete %>
</center>
<%	}
	HttpSession s=request.getSession();
	System.out.println("userlist jsp session id >> "+s.getId());
	HashMap<String,ArrayList<String>> users=(HashMap<String, ArrayList<String>>)s.getAttribute("users");
	Set<String> keys=users.keySet();
	for(String key:keys)
	{
		ArrayList<String> user=users.get(key);
	%>	
	
	<br>
	<br>
	<tr>
			<td>
				<input type="checkbox" value="<%=user.get(0)%>" name="deletem" id="button-1"/>
			</td>
			<td>
				<%=user.get(0)%>
			</td>
			<td>
				<%=user.get(1) %>
			</td>
				<td>				
					<input type="hidden" value="Delete" name="delete">
					<button type="submit" value="Delete" id="delete" name="<%=key%>">Delete</button>
				</td>
			</form>		
			<form action="userUpdate" method="post" >
			<td>
				<input type="hidden" value="Update" name="update">
				<button type="submit" value="Update" id="update" name="<%=key%>">Update</button>
			</td>
			</form>
	</tr>
	
	<% } 	%>
	
	</thead>
	
</table>
</form>
</body>
</html>