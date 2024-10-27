package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
	
	private static Connection conn = null;
	private static String urlConn = "jdbc:mysql://localhost:3306/antiquariato";
	private static String usernameConn = "root";
	private static String passwordConn = "Antiquariato";
	
	private DBManager() {}
	
	public static Connection getConnection() throws SQLException {
			
			if(conn == null || conn.isClosed()) {
				try {
					conn = DriverManager.getConnection(urlConn, usernameConn, passwordConn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return conn;
		
	}
	
	
	public static void closeConnection() throws SQLException {
		
			if(conn != null) { 
				conn.close();
			}
	}

}
