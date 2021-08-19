package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class TodHodTblDAO {

	public static String createTodHodTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS TODHOD;"
	        		+ " CREATE TABLE IF NOT EXISTS TODHOD (\r\n" + 
	        		"   RECORD_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   OFFICER_NAME VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   OFFICER_IDNO VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   SITE_NAME VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   TIME_IN_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n"  +
	        		"   TIME_OUT_DT TIMESTAMP   NULL \r\n"  +
	        		");");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		message = "Successful";
		return message;
	}
	public static String deleteTodHodTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE TODHOD;");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		message = "Successful";
		return message;
	}
	
	public static String updateTodHodTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE TODHOD\r\n" + 
	        		"ADD COLUMN SEAL_NO VARCHAR (100)  NULL,"
	        		+ "ADD COLUMN CONTAINER_SIZE VARCHAR (100)  NULL;");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		message = "Successful";
		return message;
	}
}
