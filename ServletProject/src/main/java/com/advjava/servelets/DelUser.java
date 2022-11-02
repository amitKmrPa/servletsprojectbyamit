package com.advjava.servelets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advjava.factory.JdbcFactory;

/**
 * Servlet implementation class DelUser
 */
@WebServlet("/DelUser")
public class DelUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userId = request.getParameter("userId");
		System.out.println("======================="+userId);
		String pass = request.getParameter("password");
		conn  = JdbcFactory.getMyConnection();
		String sql = "delete from `std_table` where userId='"+ userId + "' and stdPass= '"+pass+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
