package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.PWMDetails;
import net.javatutorial.tutorials.Main;

public class PWMManagerDAO {
	
	public static String addPWM(PWMDetails v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO PWM (PWM_ID, YEAR, PWMGRADE, PWMWAGES, CREATED_DT, LAST_MODIFIED_DT) "
	        		+ "VALUES ('" +v.getPwmId()+ "','" +v.getYear()+ "','" +v.getPwmGrade()+ "','" +v.getPwmWages()+ "','" 
	        		+v.getCreatedDt()+ "','" +v.getLastModifiedDt()+ "');");
	        rs = stmt.executeQuery("SELECT LAST(PWM_ID) FROM PWM;");
	        while (rs.next()) {
	        	message = "Read from DB: " + rs.getTimestamp("tick");
	        }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		finally {
        	Main.close(connection, stmt, rs);
        }
		message = "successfull" ;
		return message;
	}
	public static int getNextVal(){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		int result = 1;
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();
//	        stmt.executeUpdate("SELECT count(*) FROM EMPLOYEES;");
	        rs = stmt.executeQuery("SELECT MAX(CAST(PWM_ID AS INTEGER)) FROM PWM;");
	        if(rs != null) {
	        	while (rs.next()) {
		        	if(rs.getString(1) != null && !rs.getString(1).isEmpty()) {
		        		result = Integer.parseInt(rs.getString(1)) + 1;
		        	}
	                
	            }
	        }
	        
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
        	Main.close(connection, stmt, rs);
        }
		return result;
	}
	
	public static ArrayList<PWMDetails> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        PWMDetails v = null;
        ArrayList<PWMDetails> vList = new ArrayList<PWMDetails>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT PWM_ID, YEAR, PWMGRADE, PWMWAGES, CREATED_DT, LAST_MODIFIED_DT \r\n"
            		+ "FROM PWM ORDER BY LAST_MODIFIED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new PWMDetails(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getDouble(4),
            			rs.getTimestamp(5),
            			rs.getTimestamp(6));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static PWMDetails retrieveByYearAndGrade(String grade) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        PWMDetails v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT PWM_ID, YEAR, PWMGRADE, PWMWAGES, CREATED_DT, LAST_MODIFIED_DT \r\n" + 
            		"FROM PWM \r\n"
            		+ " WHERE YEAR =CAST(DATE_PART('year', NOW()) AS VARCHAR) AND PWMGRADE ='" + grade + "' ORDER BY LAST_MODIFIED_DT DESC  LIMIT 1;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new PWMDetails(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getDouble(4),
            			rs.getTimestamp(5),
            			rs.getTimestamp(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return v;
    }
	

}
