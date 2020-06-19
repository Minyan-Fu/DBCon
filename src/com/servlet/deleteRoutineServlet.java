package com.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBManager;


public class deleteRoutineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteRoutineServlet() {
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
		System.out.println("do get method start");
		Connection connect = null;
	    String message="";
	    System.out.println(request);
	    String routineId= request.getParameter("routineId");
		connect = DBManager.getConnect();
		try {
		Statement statement = connect.createStatement();
		String sqlInsert = "delete from " + DBManager.TABLE_Routine + " where routineId="+routineId;
		System.out.println(sqlInsert);
		if (statement.executeUpdate(sqlInsert) > 0) {
			message = "delete successfully";
		} else {
			message = "falied";
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		response.getWriter().write(message);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age","3600");
		System.out.println("sent data");

	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
        
	}
}
