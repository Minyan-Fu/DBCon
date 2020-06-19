package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBManager;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.mysql.cj.xdevapi.Result;


public class idGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public idGetServlet() {
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
		String code = "";
		Connection connect = null;
	    Statement statement = null;
	    String userid="";
 
		String userName = request.getParameter("userName");		
		String printSQL = "SELECT userId FROM "+DBManager.TABLE_UserLogin+" where userAccount='"+userName+"'";
		System.out.println("connect success");
		try {
			 	connect = DBManager.getConnect();
			 	statement = connect.createStatement();
		        ResultSet rs=statement.executeQuery(printSQL);
		        while(rs.next()){
		            //Retrieve by column name
		            userid  = rs.getString("userId");
		        }    
	            System.out.println(userid); 	            
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    } 
		 
		response.getWriter().write(userid);
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