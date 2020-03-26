package com.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
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
 * Servlet implementation class userUpdate
 */
@WebServlet("/userUpdate")
public class userUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
	   HttpSession s=request.getSession(false);
	   RequestDispatcher rd = null;
		if(s!=null) 
		{
		   	Enumeration<String> e=request.getParameterNames();
			String UserName=null;
			while(e.hasMoreElements())
			{
				UserName=e.nextElement();
			}
			
			System.out.println("User Name >> "+UserName);
			String update=request.getParameter("update");
			
		
			System.out.println("update >> "+update);
			System.out.println("user update servlet session id >> "+s.getId());
			if(update!=null)
			{
				System.out.println("in upd");
				Connection con;
				try 
				{
					con = DBConnection.connect();
					Statement stmt1=con.createStatement();
					String id="select userName, password, email from adduser where userName='"+UserName+"'";
					ResultSet rs=stmt1.executeQuery(id);
					HashMap<String, ArrayList<String>> users=new HashMap<>();
					
					while(rs.next())
					{
						String uname=rs.getString(1);
						String pass=rs.getString(2);
						String email=rs.getString(3);
						ArrayList<String> user=new ArrayList<>();
						user.add(uname);
						user.add(pass);
						user.add(email);
						users.put(UserName, user);
					}
				
					s.setAttribute("user", UserName);
					s.setAttribute("userdata", users);
					rd=request.getRequestDispatcher("updateUser.jsp");
				} catch (ClassNotFoundException | SQLException | NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else
		{
			rd=request.getRequestDispatcher("index.jsp");
			
		}
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession s1=req.getSession();

		int idlogin=(Integer)s1.getAttribute("id");
		System.out.println("Idlogin >> "+idlogin);
		if(s1!=null)
		{
			String updateuser=req.getParameter("updateUser");
			String userName=req.getParameter("uname");
			String password=req.getParameter("password");
			String email=req.getParameter("email");
			System.out.println("uname >> "+userName);
			System.out.println("pass >> "+password);
			System.out.println("email >> "+email);
			System.out.println("update user >> "+updateuser);
			RequestDispatcher rd = null;
			
			String UserName=(String)s1.getAttribute("user");
			System.out.println("User >> " + UserName);
				if(updateuser!=null)
				{
					System.out.println("Update userr");
					try 
					{
						Connection con=DBConnection.connect();
						Statement stmt1=con.createStatement();
						String id="select idadduser from adduser where userName='"+UserName+"'";
						ResultSet rs=stmt1.executeQuery(id);
						int iduser=0;
						while(rs.next())
						{
							iduser=rs.getInt(1);
						}
						System.out.println("IDuser >> "+iduser);
						String sql="update adduser set userName='"+userName+"', password='"+password+"', email='"+email+"' where idadduser='"+iduser+"'";
						int a=stmt1.executeUpdate(sql);
						System.out.println(sql);
						System.out.println("upadet >> "+a);
						if(a>0)
						{
							HashMap<String, ArrayList<String>> users=serviceProvider.getData(idlogin);
							s1.setAttribute("users", users);
							req.setAttribute("update", "User data Update Successfully....'"+UserName+"'");
							rd=req.getRequestDispatcher("allUsersList.jsp");
						}
						else
						{
							req.setAttribute("update", "User data Update UnSuccessful....");
							rd=req.getRequestDispatcher("updateUser.jsp");
						}
					} catch (SQLException | ClassNotFoundException | NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					rd=req.getRequestDispatcher("allUsersList.jsp");
					
				}
				rd.forward(req, resp);
		}
	}
}
