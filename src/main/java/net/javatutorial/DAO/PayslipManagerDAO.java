package net.javatutorial.DAO;

import java.io.ByteArrayInputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.javatutorial.entity.AttendanceUploadFile;
import net.javatutorial.tutorials.Main;

public class PayslipManagerDAO {
	
	public static String uploadPayslipFile(AttendanceUploadFile v){
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		String message = "";
		try {
			connection = Main.getConnection();
			
			// constructs SQL statement
            String sql = "INSERT INTO PAYSLIP (FILE) values (?)";
            statement = connection.prepareStatement(sql);
            
            if (v.getPayslip() != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBinaryStream(1, v.getPayslip());
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
	
	public static AttendanceUploadFile retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        AttendanceUploadFile v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT * FROM PAYSLIP ORDER BY CREATED_DT DESC LIMIT 1; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	byte[] bytea = rs.getBytes(1);
            	v = new AttendanceUploadFile(new ByteArrayInputStream(bytea), 
            			rs.getTimestamp(2));
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
            String sql = "TRUNCATE TABLE PAYSLIP;";
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
