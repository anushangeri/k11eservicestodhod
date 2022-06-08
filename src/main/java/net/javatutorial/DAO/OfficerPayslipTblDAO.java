package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class OfficerPayslipTblDAO {

	public static String createPayslipTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS OFFICERPAYSLIP;"
	        		+ " CREATE TABLE IF NOT EXISTS OFFICERPAYSLIP (\r\n" + 
	        		"   PAYSLIP_ID VARCHAR (100)  NOT NULL,\r\n" +
	        		"   ID_NO VARCHAR (100)  NOT NULL,\r\n" +
	        		"   FILE bytea,\r\n" + 
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW()\r\n"  +
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
	public static String deletePayslipTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE OFFICERPAYSLIP;");
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
