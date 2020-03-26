package com.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBConnection;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession s=request.getSession(false);
		RequestDispatcher rd=null;
		System.out.println("Admin session id >> "+s.getId());
		if(s!=null)
		{
			String uname=request.getParameter("uname");
			String  password=request.getParameter("password");
			String  email=request.getParameter("email");
			String  viewUser=request.getParameter("viewUser");
			System.out.println("viewUser >> "+viewUser);
			String  addUser=request.getParameter("addUser");
			System.out.println("addUser >> "+addUser);
			int idlogin=(Integer)s.getAttribute("id");
			System.out.println("ID >> "+idlogin);
			
			Connection con;
			try 
			{
				con = DBConnection.connect();
				Statement stmt, stmt1;
				if((addUser!=null)&&(addUser.equals("Add User")))
				{
					try 
					{
						stmt= con.createStatement();
						String sql="insert into adduser(userName,password,email,idlogin) values('"+uname+"','"+password+"','"+email+"',"+idlogin+");";
						System.out.println(sql);
						int a=stmt.executeUpdate(sql);
						if(a>0)
						{
							System.out.println("Record Inserted successfully by admin...");
							request.setAttribute("successAdmin", "User Added");
							rd=request.getRequestDispatcher("userPage.jsp");
							
						}
						else
						{
							System.out.println("Record not Inserted successfully...");
							request.setAttribute("Error", "Record Not Inserted..");
							rd=request.getRequestDispatcher("allUsersList.jsp");
							
						}
					} 
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if((viewUser!=null)&&(viewUser.equals("View User")))
					{
						HashMap<String, ArrayList<String>> users=serviceProvider.getData(idlogin);
						s.setAttribute("users", users);
						rd=request.getRequestDispatcher("allUsersList.jsp");
						
					}
			} catch (ClassNotFoundException | SQLException | NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			s.setAttribute("invaliduser", "You Are Not Logged in...");
			rd=request.getRequestDispatcher("index.jsp");
			
		}
		rd.forward(request, response);
	}
}
