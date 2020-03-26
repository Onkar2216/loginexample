<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String usr=(String)session.getAttribute("user");
	
	if(session ==null && usr==null)
	{	
		response.sendRedirect("index.jsp");
	}

%>