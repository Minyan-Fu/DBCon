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

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if ("GET".contentEquals(method)) {
			doGet(request, response);
		} 
		if ("POST".contentEquals(method)) {
			doPost(request,response);
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get method start");
		String code = "";
		String message = "";
 
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account + ";" + password);
 
		Connection connect = DBManager.getConnect();
		System.out.println("connect success");
		try {
			Statement statement = connect.createStatement();
			String sql = "select userAccount from " + DBManager.TABLE_UserLogin + " where userAccount='" + account
					+ "' and userPassword='" + password + "'";
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				code = "200";
				message = "login successfully";
			} else {
				code = "100";
				message = "login failed";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		response.getWriter().write(code+":"+message);
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

	
}