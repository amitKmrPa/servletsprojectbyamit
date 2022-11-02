package com.advjava.servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.advjava.factory.JdbcFactory;
import com.mysql.cj.jdbc.JdbcConnection;

/**
 * Servlet implementation class UserData
 */
@WebServlet("/UserData")
public class UserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		conn = JdbcFactory.getMyConnection();
		String sql = "select * from `std_table`;";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			JSONObject obj = new JSONObject();
			String data = "{";
			while (rs.next() == true) {
			data = data + rs.getInt(1) + ","+rs.getString(2)+","+rs.getString(3)+",";			
			}
			data +="}";
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
				obj.put("data", data);
		        out.print(obj.toString());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
