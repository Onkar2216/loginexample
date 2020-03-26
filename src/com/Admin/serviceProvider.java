package com.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;

import com.database.DBConnection;

public class serviceProvider 
{
	static Connection con;
	static Statement stmt1;
	public static HashMap<String, ArrayList<String>> getData(int idlogin) throws SQLException, ClassNotFoundException
	{
		HashMap<String, ArrayList<String>> users=new HashMap<>();
		try {
			con=DBConnection.connect();
			stmt1=con.createStatement();
			String sql="select userName, email, password from adduser where idlogin= "+idlogin;
			ResultSet rs=stmt1.executeQuery(sql);
			while(rs.next())
			{
				ArrayList<String> user=new ArrayList<>();
				String username=rs.getString(1);
				String email1=rs.getString(2);
				String password=rs.getString(3);
				System.out.println("service username >> "+username);
				System.out.println("service email1 >> "+email1);
				System.out.println("service password >> "+password);
				user.add(username);
				user.add(email1);
				user.add(password);
				users.put(username, user);
				
			}
		} catch (ClassNotFoundException | NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}
