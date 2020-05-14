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

public class RoutineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoutineServlet() {
        super();
    }
    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if ("GET".equals(method)) {
			doGet(request, response);
		} 
		if ("POST".equals(method)) {
			doPost(request, response);
		} 
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = "";
		String message = "";
 
		String routineName = request.getParameter("routineName");
		String deviceId = request.getParameter("deviceId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		System.out.println(routineName + ";" + deviceId + ";" + startTime + ";" + endTime);
 
		Connection connect = DBManager.getConnect();
		try {
			Statement statement = connect.createStatement();
			String sql = "select routineName from " + DBManager.TABLE_Routine + " where routineName='" + deviceId 
					+ "'and deviceId='" + deviceId +"'";
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) { 
				code = "100";
				message = "same name is impossible";
			} else {
				String sqlInsert = "insert into " + DBManager.TABLE_Routine + "(routineName, deviceId, startTime, endTime) values('"
						+ routineName + "', '" + routineName + "', '" + startTime + "', '"+ endTime + "')";
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
		System.out.println("RoutineServlet destory.");
		super.destroy();
	}

	
}