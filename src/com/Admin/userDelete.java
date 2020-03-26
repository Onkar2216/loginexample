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
import com.sun.corba.se.spi.orbutil.fsm.State;


@WebServlet("/userDelete")
public class userDelete extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession s=request.getSession(false);
		int idlogin=(Integer)s.getAttribute("id");
		System.out.println("idlogin >> "+idlogin);
		RequestDispatcher rd=null;
		if(s!=null){
			String deletem=request.getParameter("deletem");
			String delete=request.getParameter("delete");
			if(delete.equals("delete")&&(delete!=null))
			{
				Enumeration<String> e=request.getParameterNames();
				String UserName=null;
				while(e.hasMoreElements())
				{
					UserName=e.nextElement();
				}
				System.out.println("User Name >> "+UserName);
				
				
				
				System.out.println("delete >> "+delete);
				if(delete.equals("Delete"))
				try 
				{
					Connection con=DBConnection.connect();
					Statement stmt=con.createStatement();
					String sql="delete from adduser where userName='"+UserName+"'";
					int a=stmt.executeUpdate(sql);
					if(a>0)
					{
						System.out.println("user delete servlet session id >> "+s.getId());
						try 
						{
							HashMap<String, ArrayList<String>> users=serviceProvider.getData(idlogin);
							s.setAttribute("users", users);
							request.setAttribute("delete", "Record deleted of User is >> '"+UserName+"'");
							 rd=request.getRequestDispatcher("allUsersList.jsp");
							
						} catch ( SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					else
					{
						request.setAttribute("delete", "Record not deleted of User is >> '"+UserName+"'");
						 rd=request.getRequestDispatcher("allUsersList.jsp");
					
					}
				} catch (ClassNotFoundException | SQLException | NamingException e2)
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}			
			}
			else if(deletem.equals("Delete Selected")&&(delete!=null))
			{
				System.out.println("i m in multiple delete...");
				String[] users=request.getParameterValues("deletem");
				System.out.println("userss >> "+users);
				
				for(int i=0;i<users.length;i++)
				{
					String username=users[i];
					Connection con;
					try 
					{
						con = DBConnection.connect();
						Statement stmt = con.createStatement();
						String sql="delete from adduser where userName='"+username+"'";
						int a=stmt.executeUpdate(sql);
						if(a>0)
						{
							System.out.println("element deleted successfully >> "+username);
						}
						else
						{
							System.out.println("element was not deleted successfully >> "+username);
						}
					} catch (ClassNotFoundException | NamingException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				HashMap<String, ArrayList<String>> userrs;
				try {
					userrs = serviceProvider.getData(idlogin);
					s.setAttribute("users", userrs);
					rd=request.getRequestDispatcher("allUsersList.jsp");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		else
		{
			rd=request.getRequestDispatcher("index.jsp");
				
		}
		rd.forward(request, response);
		
	}

}
