package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.StatementEventListener;

public class CheckNames {
	/*public static boolean checkUser (String username) {
		boolean st = false;
		try {
			Connection conn = null;
			ConnectionFactory cf = ConnectionFactory.getInstance();
			conn = cf.getConnection();
			
			Statement stmt = conn.createStatement();
			String nameQuery = "select # from staff where username=" + username;
			ResultSet rs =
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
}*/
	
	public static boolean checkUser(String username, String password) {
		
		boolean st =false;
		try {
			//Connect to Database - details in Connection Factory class
			//Connection conn = null;
			//ConnectionFactory cf = ConnectionFactory.getInstance();
			//conn = cf.getConnection();
			
			Connection conn = ConnectionFactory.getInstance().getConnection();
			
			String checkQuery = "select * from staff where username=? and passcode=?";
			PreparedStatement pstmt = conn.prepareStatement(checkQuery);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			st = rs.next();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return st; 
	}

}
