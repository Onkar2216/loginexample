package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.database.DBConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		String register=request.getParameter("register");
		String login=request.getParameter("in");
	
		System.out.println("Login "+login);
		System.out.println("Register "+register);
		System.out.println(uname);
		System.out.println(password);
		Connection con=null;
		RequestDispatcher rd;
		HttpSession s=request.getSession(false);
		String sessionId=s.getId();
		System.out.println("login session id >> "+sessionId);
		if(s!=null)
		{
				if((login!=null)&&(login.equals("login")))
				{
					try 
					{
						con=DBConnection.connect();
						System.out.println("connected login");
						String sql="select userName,password,idregistration from registration where userName='"+uname+"'";
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(sql);
						String unamedb=null;
						String pass=null;
						int idlogin=0;
						while(rs.next())
						{
							unamedb=rs.getString(1);
							pass=rs.getString(2);
							idlogin=rs.getInt(3);
						}
						System.out.println("id >> "+idlogin);
						if((uname.equals(unamedb))&&(password.equals(pass)))
						{
							s.setAttribute("id", idlogin);
							//s.setAttribute("user", uname);
							s.setAttribute("sessionId", sessionId);
							rd=request.getRequestDispatcher("userPage.jsp");
							rd.forward(request, response);
						}
						else
						{
							request.setAttribute("invalid", "please enter correct User Name / Password");
							rd=request.getRequestDispatcher("index.jsp");
							rd.forward(request, response);
						}
					}
					catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(register.equals("Registration"))
				{
					System.out.println("Registration Block");
					rd=request.getRequestDispatcher("Registration.jsp");
					rd.forward(request, response);
				}
		}
		else
		{
			System.out.println("Session Is expired");
		}
	}

}
