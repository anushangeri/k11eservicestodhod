package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class EmployeeTblDAO {

	public static String createEmployeeTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS EMPLOYEE;"
	        		+ " CREATE TABLE IF NOT EXISTS EMPLOYEE (\r\n" + 
	        		"   EMPLOYEE_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   ID_NO VARCHAR (100)   NULL, \r\n" +
	        		"   PAID_ANNUAL_LEAVE INT   NULL, \r\n" +
	        		"   PAID_ANNUAL_OUTPATIENT_LEAVE INT   NULL, \r\n" +
	        		"   PAID_ANNUAL_HOSPITAL_LEAVE INT   NULL, \r\n" +
	        		"   FILE bytea,\r\n" + 
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(), \r\n"  +
	        		"   LAST_MODIFIED_DT TIMESTAMP  NOT NULL DEFAULT NOW() \r\n"  +
	        		");");
			message = "Successful";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}

		return message;
	}
	public static String deleteEmployeeTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE EMPLOYEE;");
	        message = "Successful";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		return message;
	}
	
}
