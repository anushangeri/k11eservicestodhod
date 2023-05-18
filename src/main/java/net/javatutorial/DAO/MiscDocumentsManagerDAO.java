package net.javatutorial.DAO;

import java.io.ByteArrayInputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.MiscDocuments;
import net.javatutorial.entity.OfficerPayslip;
import net.javatutorial.tutorials.Main;

public class MiscDocumentsManagerDAO {
	
	public static String addMiscDocuments(MiscDocuments v){
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();

			String sql = "INSERT INTO MISCDOCUMENTS "
	        		+ "(DOCUMENT_ID, EMPLOYEE_ID, DOCUMENT, DESCRIPTION, CREATED_BY, LAST_MODIFIED_BY, CREATED_DT, LAST_MODIFIED_DT)"
	        		+ " VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW());";
			
			stmt = connection.prepareStatement(sql);
            
            if (v.getDocument() != null) {
                // fetches input stream of the upload file for the blob column
            	stmt.setString(1, v.getDocumentId());
            	stmt.setString(2, v.getEmployeeId());
            	stmt.setBinaryStream(3, v.getDocument());
            	stmt.setString(4, v.getDescription());
            	stmt.setString(5, v.getCreatedBy());
            	stmt.setString(6, v.getLastModifiedBy());
            }
 
            // sends the statement to the database server
            int row = stmt.executeUpdate();
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
        	Main.close(connection, stmt, rs);
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(DOCUMENT_ID AS INT)) FROM MISCDOCUMENTS;");
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

	public static MiscDocuments retrieveByDocumentID(String documentId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        MiscDocuments v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT DOCUMENT_ID, EMPLOYEE_ID, DOCUMENT, DESCRIPTION, CREATED_BY, LAST_MODIFIED_BY, CREATED_DT, LAST_MODIFIED_DT"
            		+ " FROM MISCDOCUMENTS "
            		+ " WHERE DOCUMENT_ID ='" + documentId + "' \r\n"
    				+ " ORDER BY LAST_MODIFIED_DT DESC LIMIT 1";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	byte[] bytea = rs.getBytes(3);
            	v = new MiscDocuments(rs.getString(1), 
            			rs.getString(2),
            			new ByteArrayInputStream(bytea),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getString(6),
            			rs.getTimestamp(7),
            			rs.getTimestamp(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return v;
    }
	
	public static ArrayList<MiscDocuments> retrieveByEmployeeID(String employeeId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        MiscDocuments v = null;
        ArrayList<MiscDocuments> vList = new ArrayList<MiscDocuments>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT DOCUMENT_ID, EMPLOYEE_ID, DOCUMENT, DESCRIPTION, CREATED_BY, LAST_MODIFIED_BY, CREATED_DT, LAST_MODIFIED_DT"
            		+ " FROM MISCDOCUMENTS "
            		+ " WHERE EMPLOYEE_ID ='" + employeeId + "' \r\n"
    				+ " ORDER BY LAST_MODIFIED_DT DESC";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	byte[] bytea = rs.getBytes(3);
            	v = new MiscDocuments(rs.getString(1), 
            			rs.getString(2),
            			new ByteArrayInputStream(bytea),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getString(6),
            			rs.getTimestamp(7),
            			rs.getTimestamp(8));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<OfficerPayslip> retrieveByEmployeeIDJustPayslip(String employeeId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        OfficerPayslip v = null;
        ArrayList<OfficerPayslip> vList = new ArrayList<OfficerPayslip>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT m.DOCUMENT_ID, e.ID_NO, m.DOCUMENT, m.CREATED_DT"
            		+ " FROM MISCDOCUMENTS m INNER JOIN EMPLOYEE e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID"
            		+ " WHERE m.EMPLOYEE_ID = '" + employeeId + "' \r\n"
            		+ " AND LOWER(m.DESCRIPTION) LIKE '%payslip%' "
    				+ " ORDER BY m.LAST_MODIFIED_DT DESC";
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
	
	public static ArrayList<MiscDocuments> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        MiscDocuments v = null;
        ArrayList<MiscDocuments> vList = new ArrayList<MiscDocuments>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT DOCUMENT_ID, EMPLOYEE_ID, DOCUMENT, DESCRIPTION, CREATED_BY, LAST_MODIFIED_BY, CREATED_DT, LAST_MODIFIED_DT"
            		+ " FROM MISCDOCUMENTS "
    				+ " ORDER BY LAST_MODIFIED_DT DESC";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	byte[] bytea = rs.getBytes(3);
            	v = new MiscDocuments(rs.getString(1), 
            			rs.getString(2),
            			new ByteArrayInputStream(bytea),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getString(6),
            			rs.getTimestamp(7),
            			rs.getTimestamp(8));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }

	public static ArrayList<OfficerPayslip> retrieveAllJustPayslips() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        OfficerPayslip v = null;
        ArrayList<OfficerPayslip> vList = new ArrayList<OfficerPayslip>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT m.DOCUMENT_ID, e.ID_NO, m.DOCUMENT, m.CREATED_DT"
            		+ " FROM MISCDOCUMENTS m INNER JOIN EMPLOYEE e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
            		+ " WHERE LOWER(m.DESCRIPTION) LIKE '%payslip%' "
    				+ " ORDER BY m.LAST_MODIFIED_DT DESC";
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
	
	public static String deleteRecordByDocumentId(String documentId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "MiscDocumentsManagerDAO.deleteRecordByDocumentId error";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM MISCDOCUMENTS WHERE DOCUMENT_ID ='" + documentId + "'";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            message = "Document deleted";
            
        } catch (Exception e) {
        	message = "" + e;
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
        String message = "MiscDocumentsManagerDAO.deleteAll error";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM MISCDOCUMENTS WHERE CREATED_DT <= GETDATE() - 30;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            message = "All records deleted - No miscellaneous documents available";
            
        } catch (Exception e) {
        	message = "" + e;
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
}
