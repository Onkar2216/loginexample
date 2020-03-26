package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class DBConnection {

	/*
	 * //@Resource(name="jdbc/login") private static DataSource datasource; static
	 * Context ctx=null;
	 */
	public static Connection connect() throws NamingException, ClassNotFoundException, SQLException {
		/*
		 * ctx = new InitialContext(); datasource=(DataSource)
		 * ctx.lookup("java:/comp/env/jdbc/login"); System.out.println(datasource);
		 */
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","root");
		System.out.println("Connected Successfully...");
		return con;
	}
}
