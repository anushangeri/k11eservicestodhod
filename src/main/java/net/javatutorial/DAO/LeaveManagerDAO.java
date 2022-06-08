package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.Leave;
import net.javatutorial.tutorials.Main;

public class LeaveManagerDAO {
	
	public static String addLeave(Leave v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO LEAVE "
	        		+ "(LEAVE_ID, OFFICER_ID_NO, LEAVE_TYPE, LEAVE_START_DATE, LEAVE_END_DATE, NUM_DAYS_LEAVE, REQUEST_STATUS, "
	        		+ "APPROVING_OFFICER, REMARKS)" + 
	        		"  VALUES ('" +v.getLeaveID()+ "','" +v.getIdNo()+ "','" +v.getLeaveType()+ "','" 
	        		 +v.getLeaveStartDate()+ "','" +v.getLeaveEndDate()+"','" +v.getNumDaysLeave()+ "','" +v.getRequestStatus()+ "','" 
	        		 +v.getApprovingSupervisor()+ "','" +v.getRemarks()+"');");
	        rs = stmt.executeQuery("SELECT LAST(OFFICER_ID_NO) FROM LEAVE;");
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
	
	public static String updateLeaveDetails(Leave v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE LEAVE "
	        		+  "SET REQUEST_STATUS = '" + v.getRequestStatus() + "', "
	        		+  "APPROVING_OFFICER = '" + v.getApprovingSupervisor() + "', "
	        		+  "REMARKS = '" + v.getRemarks() + "', "
	        		+  "LAST_MODIFIED_DT = NOW() "
	        		+ "   WHERE LEAVE_ID = '" + v.getLeaveID() + "';");
	        rs = stmt.executeQuery("SELECT OFFICER_ID_NO FROM LEAVE WHERE LEAVE_ID ='" + v.getLeaveID() +"';");
	        while (rs.next()) {
	    		message = "Successful";
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
//	        stmt.executeUpdate("SELECT count(*) FROM EMPLOYEES;");
	        rs = stmt.executeQuery("SELECT MAX(CAST(LEAVE_ID AS INTEGER)) FROM LEAVE;");
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
	
	public static ArrayList<Leave> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Leave v = null;
        ArrayList<Leave> vList = new ArrayList<Leave>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT LEAVE_ID, OFFICER_ID_NO, LEAVE_TYPE, LEAVE_START_DATE, LEAVE_END_DATE, \r\n"
            		+ " NUM_DAYS_LEAVE, REQUEST_STATUS, APPROVING_OFFICER, REMARKS, CREATED_DT, LAST_MODIFIED_DT \r\n"
            		+ " FROM LEAVE WHERE EXTRACT(YEAR FROM CREATED_DT) = EXTRACT(YEAR FROM NOW()) ORDER BY LAST_MODIFIED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Leave(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getTimestamp(4),
            			rs.getTimestamp(5),
            			rs.getInt(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getString(9),
            			rs.getTimestamp(10),
            			rs.getTimestamp(11));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Leave> retrieveByLeaveId(String leaveId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Leave v = null;
        ArrayList<Leave> vList = new ArrayList<Leave>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT LEAVE_ID, OFFICER_ID_NO, LEAVE_TYPE, LEAVE_START_DATE, LEAVE_END_DATE, NUM_DAYS_LEAVE, \r\n"
            		+ " REQUEST_STATUS, APPROVING_OFFICER, REMARKS, CREATED_DT, LAST_MODIFIED_DT \r\n" 
            		+ " FROM LEAVE \r\n"
            		+ " WHERE LEAVE_ID ='" + leaveId + "' ORDER BY LAST_MODIFIED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Leave(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getTimestamp(4),
            			rs.getTimestamp(5),
            			rs.getInt(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getString(9),
            			rs.getTimestamp(10),
            			rs.getTimestamp(11));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Leave> retrieveByOfficeIdNo(String officerIdNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Leave v = null;
        ArrayList<Leave> vList = new ArrayList<Leave>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT LEAVE_ID, OFFICER_ID_NO, LEAVE_TYPE, LEAVE_START_DATE, LEAVE_END_DATE, NUM_DAYS_LEAVE,  \r\n"
            		+ " REQUEST_STATUS, APPROVING_OFFICER, REMARKS, CREATED_DT, LAST_MODIFIED_DT  \r\n" 
            		+ " FROM LEAVE \r\n"
            		+ " WHERE OFFICER_ID_NO ='" + officerIdNo + "' AND EXTRACT(YEAR FROM CREATED_DT) = EXTRACT(YEAR FROM NOW()) \r\n"
            		+ " ORDER BY LAST_MODIFIED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Leave(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getTimestamp(4),
            			rs.getTimestamp(5),
            			rs.getInt(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getString(9),
            			rs.getTimestamp(10),
            			rs.getTimestamp(11));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Leave> retrieveApprovedLeavesByOfficeIdNo(String officerIdNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Leave v = null;
        ArrayList<Leave> vList = new ArrayList<Leave>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT LEAVE_ID, OFFICER_ID_NO, LEAVE_TYPE, LEAVE_START_DATE, LEAVE_END_DATE, NUM_DAYS_LEAVE,  \r\n"
            		+ " REQUEST_STATUS, APPROVING_OFFICER, REMARKS, CREATED_DT, LAST_MODIFIED_DT  \r\n" 
            		+ " FROM LEAVE \r\n"
            		+ " WHERE OFFICER_ID_NO ='" + officerIdNo + "' AND EXTRACT(YEAR FROM CREATED_DT) = EXTRACT(YEAR FROM NOW()) \r\n"
            		+ " AND REQUEST_STATUS = 'Approve' ORDER BY LAST_MODIFIED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Leave(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getTimestamp(4),
            			rs.getTimestamp(5),
            			rs.getInt(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getString(9),
            			rs.getTimestamp(10),
            			rs.getTimestamp(11));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static String deleteByLeaveId(String leaveId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No leave records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM LEAVE WHERE LEAVE_ID ='" + leaveId + "'";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            
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
        String message = "All records deleted - No leave records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM LEAVE WHERE LAST_MODIFIED_DT <= GETDATE() - 30;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
}
