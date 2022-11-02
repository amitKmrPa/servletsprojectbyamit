/**
 * 
 */
package com.advjava.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 * @author amit
 *
 */
public class JdbcFactory {
	private static ResourceBundle bundle = null;
	private static Connection conn;
	static {
		bundle  = ResourceBundle.getBundle("com.advjava.utilities.mysqlinfo");
	}
	public static Connection getMyConnection() {
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String pass = bundle.getString("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
