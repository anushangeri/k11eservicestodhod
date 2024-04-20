package net.javatutorial.DAO;

import java.io.ByteArrayInputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.Employee;
import net.javatutorial.tutorials.Main;

public class EmployeeManagerDAO {
	
	public static String addEmployee(Employee v){
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		String message = "";
		try {
			connection = Main.getConnection();
			// constructs SQL statement
            String sql = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, ID_NO, PAID_ANNUAL_LEAVE, "
            		+ "PAID_ANNUAL_OUTPATIENT_LEAVE, PAID_ANNUAL_HOSPITAL_LEAVE, FILE)"
            		+ " values (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            
            if (v.getKetDocument() != null) {
                // fetches input stream of the upload file for the blob column
            	statement.setString(1, v.getEmployeeId());
            	statement.setString(2, v.getIdNo());
            	statement.setInt(3, v.getPaidAnnualLeavePerYear());
            	statement.setInt(4, v.getPaidOutpatientSickLeavePerYear());
            	statement.setInt(5, v.getPaidHospitalisationLeavePerYear());
                statement.setBinaryStream(6, v.getKetDocument());
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
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
        	Main.close(connection, statement, rs);
        }
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(EMPLOYEE_ID AS INTEGER)) FROM EMPLOYEE;");
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
	
	public static ArrayList<Employee> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Employee v = null;
        ArrayList<Employee> vList = new ArrayList<Employee>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT * FROM EMPLOYEE ORDER BY CREATED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	if(rs.getBytes(6) == null) {
            		v = new Employee(
                			rs.getString(1), 
                			rs.getString(2), 
                			rs.getInt(3),
                			rs.getInt(4),
                			rs.getInt(5),
                			null, 
                			rs.getTimestamp(7),
                			rs.getTimestamp(8));
            	}
            	else {
	            	byte[] bytea = rs.getBytes(6);
	            	v = new Employee(
	            			rs.getString(1), 
	            			rs.getString(2), 
	            			rs.getInt(3),
	            			rs.getInt(4),
	            			rs.getInt(5),
	            			new ByteArrayInputStream(bytea), 
	            			rs.getTimestamp(7),
	            			rs.getTimestamp(8));
            	}
            	vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Employee> retrieveEmployeeByID(String idNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Employee v = null;
        ArrayList<Employee> vList = new ArrayList<Employee>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT * FROM EMPLOYEE "
            		+ " WHERE ID_NO ='"+ idNo +"'"
            		+ "ORDER BY CREATED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	if(rs.getBytes(6) == null) {
            		v = new Employee(
                			rs.getString(1), 
                			rs.getString(2), 
                			rs.getInt(3),
                			rs.getInt(4),
                			rs.getInt(5),
                			null, 
                			rs.getTimestamp(7),
                			rs.getTimestamp(8));
            	}
            	else {
	            	byte[] bytea = rs.getBytes(6);
	            	v = new Employee(
	            			rs.getString(1), 
	            			rs.getString(2), 
	            			rs.getInt(3),
	            			rs.getInt(4),
	            			rs.getInt(5),
	            			new ByteArrayInputStream(bytea), 
	            			rs.getTimestamp(7),
	            			rs.getTimestamp(8));
            	}
            	vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static String updateKETDocbyEmployeeID(Employee v){
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
	        String sql = "SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE EMPLOYEE "
	        		+ " SET LAST_MODIFIED_DT = NOW(),"
	        		+ " PAID_ANNUAL_LEAVE = ?,"
	        		+ " PAID_ANNUAL_OUTPATIENT_LEAVE = ?,"
	        		+ " PAID_ANNUAL_HOSPITAL_LEAVE = ?,"
	        		+ " FILE = ?"
	        		+ "   WHERE ID_NO = ?;";
	        
	        stmt = connection.prepareStatement(sql);
	        if (v.getKetDocument() != null) {
                // fetches input stream of the upload file for the blob column
	        	stmt.setInt(1, v.getPaidAnnualLeavePerYear());
	        	stmt.setInt(2, v.getPaidOutpatientSickLeavePerYear());
	        	stmt.setInt(3, v.getPaidHospitalisationLeavePerYear());
	        	stmt.setBinaryStream(4, v.getKetDocument());
	        	stmt.setString(5, v.getIdNo());
            }
	        // sends the statement to the database server
            int row = stmt.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
	        rs = stmt.executeQuery("SELECT ID_NO FROM EMPLOYEE WHERE EMPLOYEE_ID ='" + v.getEmployeeId() +"' LIMIT 1 ;");
	        while (rs.next()) {
	        	message = "Read from DB successfull: " + rs.getString(1);
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
		return message;
	}
	
	public static Employee retrieveEmployeeByEmployeeID(String employeeID) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Employee v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT * FROM EMPLOYEE "
            		+ " WHERE EMPLOYEE_ID ='" + employeeID + "'"
            		+ "ORDER BY CREATED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	if(rs.getBytes(6) == null) {
            		v = new Employee(
                			rs.getString(1), 
                			rs.getString(2), 
                			rs.getInt(3),
                			rs.getInt(4),
                			rs.getInt(5),
                			null, 
                			rs.getTimestamp(7),
                			rs.getTimestamp(8));
            	}
            	else {
	            	byte[] bytea = rs.getBytes(6);
	            	v = new Employee(
	            			rs.getString(1), 
	            			rs.getString(2), 
	            			rs.getInt(3),
	            			rs.getInt(4),
	            			rs.getInt(5),
	            			new ByteArrayInputStream(bytea), 
	            			rs.getTimestamp(7),
	            			rs.getTimestamp(8));
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return v;
    }
	
	public static String deleteKETDocbyEmployeeID(String employeeID){
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			String sql = "SET TIMEZONE = 'Singapore'; "
	        		+ " UPDATE EMPLOYEE "
	        		+  "SET LAST_MODIFIED_DT = NOW(), FILE = null"
	        		+ " WHERE EMPLOYEE_ID = '" + employeeID + "';"; 
			System.out.println(sql);
			stmt = connection.prepareStatement(sql);
			// sends the statement to the database server
            int row = stmt.executeUpdate();
            if (row > 0) {
                message = "FILE DELETED";
            }
            System.out.println(message);
	        rs = stmt.executeQuery("SELECT ID_NO FROM EMPLOYEE WHERE EMPLOYEE_ID ='" + employeeID +"' LIMIT 1 ;");
	        while (rs.next()) {
	        	message = "Read from DB successfull: " + rs.getString(1);
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
		return message;
	}
	
	public static String deleteByEmployeeId(String employeeId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "Employee record deleted";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID ='" + employeeId + "'";
            pstmt = connection.prepareStatement(sql);

            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
	
	public static String deleteAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All employee records deleted";
        try {
        	connection = Main.getConnection();
            String sql = "TRUNCATE TABLE EMPLOYEE;";
            pstmt = connection.prepareStatement(sql);

            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
}
