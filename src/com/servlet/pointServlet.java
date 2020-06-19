package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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





public class pointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pointServlet() {
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
		Connection connect = null;
	    Statement statement = null;
	    ArrayList<String> devices = new ArrayList<String>();
 
		String routineId = request.getParameter("routineId");	
		
		String printSQL = "SELECT AsText(location),pointId,pointContent FROM "+DBManager.TABLE_Point+" where routineId='"+routineId+"'";
		System.out.println("connect success");
		 try {
			 	connect = DBManager.getConnect();
			 	statement = connect.createStatement();
			 	JSONObject object = new JSONObject();
		        ResultSet rs=statement.executeQuery(printSQL);
		        while(rs.next()){
		            //Retrieve by column name
		            String location  = rs.getString("AsText(location)");
		            String pointId  = rs.getString("pointId");
		            String pointContent  = rs.getString("pointContent");
		            object.put("pointId", pointId);
		            object.put("pointContent", pointContent);
		            object.put("coordinates", location);
		            System.out.println(object);
		        }    
	            System.out.println(devices.toString()); 
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    } 
		 
		
		response.getWriter().write(devices.toString());
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