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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = "";
		String message = "";
 
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account + ";" + password);
 
		Connection connect = DBManager.getConnect();
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
 
		response.getWriter().append("code:").append(code).append(";message:").append(message);

	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account;
		String password;
		account = request.getParameter("account");
		password = request.getParameter("password"); 
		
		// œÏ”¶
		response.getWriter().append("account: ").append(account).append("\npassword:").append(password);
	}

	
}