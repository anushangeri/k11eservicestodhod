package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class SiteTblDAO {

	public static String createSiteTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS SITE (\r\n" + 
	        		"   SITE_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   SITE_NAME VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   DAY_MANPOWER INT  NULL,\r\n"  +
	        		"   NIGHT_MANPOWER INT  NULL,\r\n"  +
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n"  +
	        		"   LAST_MODIFIED_DT TIMESTAMP   NULL \r\n"  +
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
	public static String deleteSiteTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE SITE;");
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
	
	public static String updateSiteTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE SITE\r\n" + 
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
