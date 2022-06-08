package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class PWMTblDAO {

	public static String createPWMTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS PWM (\r\n" + 
	        		"   PWM_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   YEAR VARCHAR (100)   NULL,\r\n" + 
	        		"   PWMGRADE VARCHAR (100)   NULL,\r\n" + 
	        		"   PWMWAGES DOUBLE PRECISION  NULL,\r\n"  +
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n"  +
	        		"   LAST_MODIFIED_DT TIMESTAMP NOT NULL DEFAULT NOW() \r\n"  +
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
	public static String deletePWMTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE PWM;");
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
