package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class LeaveTblDAO {

	public static String createLeaveTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS LEAVE (\r\n" + 
	        		"   LEAVE_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   OFFICER_ID_NO VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   LEAVE_TYPE VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   LEAVE_START_DATE TIMESTAMP  NULL,\r\n"  +
	        		"   LEAVE_END_DATE TIMESTAMP  NULL,\r\n"  +
	        		"   NUM_DAYS_LEAVE INT  NULL,\r\n"  +
	        		"   REQUEST_STATUS VARCHAR (100)  NULL,\r\n"  +
	        		"   APPROVING_OFFICER VARCHAR (100)  NULL,\r\n"  +
	        		"   REMARKS VARCHAR (100)  NULL,\r\n"  +
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n"  +
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
	public static String deleteLeaveTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE LEAVE;");
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
