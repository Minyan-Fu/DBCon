package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBManager;

public class DeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeviceServlet() {
        super();
    }
    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if ("GET".equals(method)) {
			doGet(request, response);
		} 
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = "";
		String message = "";
 
		String deviceName = request.getParameter("deviceName");
		String userId = request.getParameter("userId");
		System.out.println(deviceName + ";" + userId);
 
		Connection connect = DBManager.getConnect();
		try {
			Statement statement = connect.createStatement();
			String sql = "select deviceName from " + DBManager.TABLE_Device + " where deviceName='" + deviceName 
					+ "'and userId='" + userId +"'";
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) { 
				code = "100";
				message = "same name is impossible";
			} else {
				String sqlInsert = "insert into " + DBManager.TABLE_Device + "(deviceName, userId) values('"
						+ deviceName + "', '" + userId + "')";
				if (statement.executeUpdate(sqlInsert) > 0) { 
					code = "200";
					message = "add successfully";
				} else {
					code = "300";
					message = "failed";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		response.getWriter().append("code:").append(code).append(";message:").append(message);

	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	public void destroy() {
		System.out.println("DeviceServlet destory.");
		super.destroy();
	}
}