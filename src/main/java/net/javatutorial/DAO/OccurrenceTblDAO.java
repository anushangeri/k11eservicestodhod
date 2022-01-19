package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class OccurrenceTblDAO {

	public static String createOccurrenceTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS OCCURRENCE (\r\n" + 
	        		 "OCCURRENCE_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		 "REPORTINGSITE VARCHAR (100)  NOT NULL,\r\n" +
	        		 "REPORTINGCLASSIFICATION VARCHAR (100)  NOT NULL,\r\n" +
	        		 "OFFICERONDUTYNAME VARCHAR (100)  NOT NULL,\r\n" +
	        		 "OFFICERONDUTYID VARCHAR (100)  NOT NULL,\r\n" +
	        		 "SECURIYRISKTHREAT VARCHAR (100)  NOT NULL,\r\n" +
	        		 "DESCRIPTIONOFSECURITYRISKTHREAT VARCHAR (100)  NOT NULL,\r\n" +
	        		 "NONCONFORMANCESOP VARCHAR (100)  NOT NULL,\r\n" +
	        		 "DESCIPTIONOFNONCONFORMANCE VARCHAR (100)  NOT NULL,\r\n" +
	        		 "PARTIESINVOLVED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "DESCRIPTIONOFPARTIESINVOLVED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "DESCRIPTIONOFK11NOTIFIED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "HOWINCIDENTOCCURRED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "WHATINCIDENTOCCURRED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "WHYINCIDENTOCCURRED VARCHAR (100)  NOT NULL,\r\n" +
	        		 "WHATACTIONWASTAKEN VARCHAR (100)  NOT NULL,\r\n" +
	        		 "EXTRADETAILSONACTION VARCHAR (100)  NOT NULL,\r\n" +
	        		 "PENDINGACTION VARCHAR (100)  NOT NULL,\r\n" +
	        		 "INCIDENTCATEGORY VARCHAR (100)  NOT NULL,\r\n" +
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
	public static String deleteOccurrenceTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE OCCURRENCE;");
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
	
	public static String updateOccurrenceTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE OCCURRENCE\r\n" + 
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
