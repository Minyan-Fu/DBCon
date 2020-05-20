package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		String sent ="";
		String sent2 ="";
		Connection connect = null;
	    Statement statement = null;
	    ArrayList<String> Routines = new ArrayList<String>();
 
		String userId = request.getParameter("userId");	
		
		String printSQL = "SELECT * FROM "+DBManager.TABLE_Routine+" where userId='"+userId+"'";
		System.out.println("connect success");
		 try {
			 	connect = DBManager.getConnect();
			 	statement = connect.createStatement();
		        ResultSet rs=statement.executeQuery(printSQL);
		        while(rs.next()){
		            //Retrieve by column name
		            String deviceid  = rs.getString("deviceId");
		            String routineName  = rs.getString("routineName");
		            String routineId=rs.getString("routineId");
		            Routines.add("{DeviceId:"+deviceid+","+" RoutineName(Id):"+routineName+"("+routineId+")"+"}");	
		        }    
	            System.out.println(Routines.toString()); 
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    } 
		 
		
		response.getWriter().write(Routines.toString());
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age","3600");
		System.out.println("sent data");

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