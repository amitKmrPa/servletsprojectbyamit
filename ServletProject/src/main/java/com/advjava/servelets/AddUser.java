package com.advjava.servelets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advjava.factory.JdbcFactory;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		conn = JdbcFactory.getMyConnection();
		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("insert into std_table values(?,?,?,?,?)");
				Statement st = conn.createStatement();
				int id = 0;
				PreparedStatement ps1 = conn.prepareStatement("select std_id from std_table");
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					id = rs.getInt("std_id");
				}
				id++;
				ps.setInt(1, Integer.valueOf(id));
				ps.setString(2, req.getParameter("userId"));
				ps.setString(3, req.getParameter("password"));
				ps.setString(4, req.getParameter("fullname"));
				ps.setInt(5, Integer.valueOf(req.getParameter("age")));
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}
	}
}
