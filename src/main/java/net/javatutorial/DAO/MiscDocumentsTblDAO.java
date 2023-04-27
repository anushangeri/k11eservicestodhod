package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class MiscDocumentsTblDAO {

	public static String createMiscDocumentsTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS MISCDOCUMENTS;"
	        		+ "CREATE TABLE IF NOT EXISTS MISCDOCUMENTS (\r\n" + 
	        		"   DOCUMENT_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   EMPLOYEE_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   DOCUMENT bytea NULL, \r\n" + 
	        		"   DESCRIPTION VARCHAR (100)   NULL, \r\n" + 
	        		"   CREATED_BY  VARCHAR (255) NOT NULL,   \r\n" + 
	        		"   LAST_MODIFIED_BY VARCHAR (100)   NULL, \r\n" + 
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n" + 
	        		"   LAST_MODIFIED_DT TIMESTAMP   NULL \r\n" + 
	        		"); "
	        		);
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
	public static String deleteMiscDocumentsTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE MISCDOCUMENTS;");
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
	
	public static String updateMiscDocumentsTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE MISCDOCUMENTS\r\n" + 
	        		"ADD COLUMN SITE VARCHAR (100)  NULL;");
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
