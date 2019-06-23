package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.entity.Employee;
import net.javatutorial.tutorials.Main;

public class EmployeesManagerDAO {
	
	public static String addEmployee(Employee em){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS EMPLOYEES(\r\n" + 
	        		"   EMPLOYEE_ID   VARCHAR (100)              NOT NULL,\r\n" + 
	        		"   FIRST_NAME VARCHAR (100)     NOT NULL,\r\n" + 
	        		"   LAST_NAME VARCHAR (100)     NOT NULL,\r\n" + 
	        		"   GENDER VARCHAR (2)     NOT NULL,\r\n" + 
	        		"   MARITAL_STATUS VARCHAR (50)     NULL, \r\n" + 
	        		"   DOB DATE     NOT NULL,\r\n" + 
	        		"   AGE  INT     NOT NULL,\r\n" + 
	        		"   NATIONALITY VARCHAR (100)     NOT NULL,\r\n" + 
	        		"   POB VARCHAR (100)     NULL, \r\n" + 
	        		"   IDENTIFICATION VARCHAR (100)    NOT NULL, \r\n" + 
	        		"   ID_TYPE VARCHAR (50)    NOT NULL, \r\n" + 
	        		"   ID_NO VARCHAR (100)    NOT NULL, \r\n" + 
	        		"   RELIGION VARCHAR (100)     NULL, \r\n" + 
	        		"   RACE VARCHAR (100)     NULL,\r\n" + 
	        		"   MOBILE_NO  VARCHAR (100) NOT NULL,   \r\n" + 
	        		"   EMERGENCY_NAME  VARCHAR (100) NOT NULL, \r\n" + 
	        		"   EMERGENCY_RLP  VARCHAR (50) NULL,  \r\n" + 
	        		"   EMERGENCY_CONTACT  VARCHAR (100) NOT NULL,\r\n" + 
	        		"   EMAIL  VARCHAR (100) NULL,\r\n" + 
	        		"   ALLOW_LOGIN VARCHAR (2)     NOT NULL,	\r\n" + 
	        		"   EMPLOYEE_STATUS VARCHAR(50) NOT NULL, \r\n" + 
	        		"   JOINING_DT DATE     NOT NULL,\r\n" + 
	        		"   PROB_FROM_DT DATE   NULL, \r\n" + 
	        		"   PROB_TO_DT DATE   NULL,	\r\n" + 
	        		"   SUPERVISOR_NAME VARCHAR (100)     NOT NULL, \r\n" + 
	        		"   HIGHEST_QUAL VARCHAR (100)     NOT NULL, \r\n" + 
	        		"   PRIMARY KEY (EMPLOYEE_ID)\r\n" + 
	        		");");
//	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//	        while (rs.next()) {
//	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
//	        }
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
	public static int getNextVal(){
		Connection connection;
		int result = 0;
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("SELECT count(*) FROM EMPLOYEES;");
	        ResultSet rs = stmt.executeQuery("SELECT count(LAST_INSERT_ID()) FROM EMPLOYEES;");
	        while (rs.next()) {
                result = rs.getInt(1) + 1;
            }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
