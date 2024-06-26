package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class IncidentTblDAO {

	public static String createIncidentTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS INCIDENT (\r\n" + 
	        		 "INCIDENT_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		
	        		 "OFFICERONDUTYNAME VARCHAR (100)  NOT NULL,\r\n" +
	        		 "OFFICERONDUTYID VARCHAR (100)  NOT NULL,\r\n" +
	        		 "OFFICERONDUTYDESIGNATION VARCHAR (100)  NOT NULL,\r\n" +
	        		 
	        		 "REPORTINGSITE VARCHAR (100)  NULL,\r\n" +
	        		 "DATEOFINCIDENT VARCHAR (100)  NULL,\r\n" +
	        		 "TIMEOFINCIDENT VARCHAR (100)   NULL,\r\n" +
	        		 "DATEOFINCIDENTREPORTED VARCHAR (100)  NULL,\r\n" +
	        		 
					 "PARTIESINVOLVED VARCHAR (100)  NULL,\r\n" +
	        		 "INCIDENTCATEGORY TEXT []  NULL,\r\n" +
					 
	        		 "HOWINCIDENTOCCURRED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "WHATINCIDENTOCCURRED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "WHYINCIDENTOCCURRED VARCHAR (100)  NOT NULL,\r\n" +
	        		 
	        		 "DECLARATIONBYOFFICERONDUTY VARCHAR (100)  NOT NULL,\r\n" +
	        		 "DECLARATIONOFSECURITYIMPLICATIONS VARCHAR (100)  NOT NULL,\r\n" +
	        		 
	        		 "SIGNATUREOFOFFICERONDUTY VARCHAR (100)  NOT NULL,\r\n" +
	        		 "SIGNATUREOFOPSMANAGERONDUTY VARCHAR (100)  NOT NULL,\r\n" +
	        		 "FILE bytea NULL,\r\n" + 
	        		 "CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n"  +
	        		 "LAST_MODIFIED_DT TIMESTAMP  NOT NULL DEFAULT NOW() \r\n"  +
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
	public static String deleteIncidentTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE INCIDENT;");
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
	
	public static String updateIncidentTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE INCIDENT\r\n" + 
	        		" ALTER COLUMN  INCIDENT_ID TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  OFFICERONDUTYNAME TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  OFFICERONDUTYID TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  OFFICERONDUTYDESIGNATION TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  REPORTINGSITE TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  PARTIESINVOLVED TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  HOWINCIDENTOCCURRED TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  WHATINCIDENTOCCURRED TYPE varchar(4000), \r\n"
	        		+ "ALTER COLUMN  WHYINCIDENTOCCURRED TYPE varchar(4000) \r\n"
	        		+ ";");
	        
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
