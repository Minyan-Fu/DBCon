package com.servlet;
import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBManager;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.mysql.cj.xdevapi.Result;

import net.sf.json.JSONObject;

public class showRoutineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showRoutineServlet() {
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
	    Statement statement = null;
	    ArrayList<String> points = new ArrayList<String>();
 
		String startTime = request.getParameter("startTime");	
		String endTime = request.getParameter("endTime");
		String deviceId= request.getParameter("deviceId");
	       		
		String printSQL = "SELECT AsText(nowPoint) FROM "+DBManager.TABLE_Record+" where deviceId='"+deviceId+
				"' and timestamp between '"+startTime+"' and '"+endTime+"' ORDER BY recordId";
		try {
			 	connect = DBManager.getConnect();
			 	statement = connect.createStatement();
		        ResultSet rs=statement.executeQuery(printSQL);
		        while(rs.next()){
		            //Retrieve by column name
		            String point  = rs.getString("AsText(nowPoint)");
		            points.add("Point:"+point); 
		        }              
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    } 
		 
		response.getWriter().write(points.toString());
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