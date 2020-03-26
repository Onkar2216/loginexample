package com.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.DBConnection;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		String register=request.getParameter("register");
		String login=request.getParameter("login");
		String email=request.getParameter("email");
		RequestDispatcher rd;
		if(register!=null) 
		{
			try 
			{
				Connection con=DBConnection.connect();
				Statement stmt= con.createStatement();
				String sql="insert into registration (userName,password,email) values('"+uname+"','"+password+"','"+email+"');";
				System.out.println(sql);
				int a=stmt.executeUpdate(sql);
				if(a>0)
				{
					request.setAttribute("success", "Record Inserted successfully...");
					System.out.println("Record Inserted successfully...");
					rd=request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
				else
				{
					System.out.println("Record not Inserted successfully...");
					request.setAttribute("Error", "Record Not Inserted..");
					rd=request.getRequestDispatcher("Registration.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(login!=null)
		{
			rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		
	}

}
