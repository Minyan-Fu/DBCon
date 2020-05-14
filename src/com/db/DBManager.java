package com.db;
 
import java.sql.*;
 
public class DBManager {
 
	public static final String TABLE_UserLogin = "userlog";
	public static final String TABLE_Device = "device";
	public static final String TABLE_Routine = "routine";
	
 
    public static Connection getConnect() {
		String url = "jdbc:mysql://localhost:3306/bathesis"; // 数据库的Url
		Connection connecter = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // java反射，固定写法
			connecter = (Connection) DriverManager.getConnection(url, "root", "123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return connecter;
	}

}