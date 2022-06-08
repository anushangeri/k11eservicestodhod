package net.javatutorial.DAO;

import java.io.ByteArrayInputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.OfficerPayslip;
import net.javatutorial.tutorials.Main;

public class OfficerPayslipManagerDAO {
	
	public static String addOfficerPayslip(OfficerPayslip v){
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		String message = "";
		try {
			connection = Main.getConnection();
			
			// constructs SQL statement
            String sql = "INSERT INTO OFFICERPAYSLIP (PAYSLIP_ID, ID_NO, FILE, CREATED_DT) values (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            
            if (v.getPayslip() != null) {
                // fetches input stream of the upload file for the blob column
            	statement.setString(1, v.getPayslipId());
            	statement.setString(2, v.getOfficerIdNo());
                statement.setBinaryStream(3, v.getPayslip());
                statement.setTimestamp(4, v.getCreatedDt());
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(PAYSLIP_ID AS INTEGER)) FROM OFFICERPAYSLIP;");
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
	
	public static ArrayList<OfficerPayslip> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        ArrayList<OfficerPayslip> vList = new ArrayList<OfficerPayslip>();
        OfficerPayslip v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT PAYSLIP_ID, ID_NO, FILE, CREATED_DT FROM OFFICERPAYSLIP ORDER BY CREATED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	byte[] bytea = rs.getBytes(3);
            	v = new OfficerPayslip(
            			rs.getString(1),
            			rs.getString(2),
            			new ByteArrayInputStream(bytea), 
            			rs.getTimestamp(4));
            	vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<OfficerPayslip> retrieveByOfficeId(String idNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        ArrayList<OfficerPayslip> vList = new ArrayList<OfficerPayslip>();
        OfficerPayslip v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT PAYSLIP_ID, ID_NO, FILE, CREATED_DT FROM OFFICERPAYSLIP "
            		+ " WHERE ID_NO ='" + idNo + "' ORDER BY CREATED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	byte[] bytea = rs.getBytes(3);
            	v = new OfficerPayslip(
            			rs.getString(1),
            			rs.getString(2),
            			new ByteArrayInputStream(bytea), 
            			rs.getTimestamp(4));
            	vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static OfficerPayslip retrieveByPayslipId(String payslipIdNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        OfficerPayslip v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT PAYSLIP_ID, ID_NO, FILE, CREATED_DT FROM OFFICERPAYSLIP "
            		+ " WHERE PAYSLIP_ID ='" + payslipIdNo + "' ORDER BY CREATED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	byte[] bytea = rs.getBytes(3);
            	v = new OfficerPayslip(
            			rs.getString(1),
            			rs.getString(2),
            			new ByteArrayInputStream(bytea), 
            			rs.getTimestamp(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return v;
    }
	
	public static String deleteAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "Check email for payslips, file uploaded previously has been deleted.";
        try {
        	connection = Main.getConnection();
            String sql = "TRUNCATE TABLE OFFICERPAYSLIP;";
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
