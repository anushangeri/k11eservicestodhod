package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import net.javatutorial.entity.TodHodRecord;
import net.javatutorial.tutorials.Main;

public class TodHodManagerDAO {
	
	public static String addTodHod(TodHodRecord v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO TODHOD "
	        		+ "(RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT)" + 
	        		"  VALUES ('" +v.getRecordId()+ "','" +v.getOfficerName()+ "','" +v.getOfficerIdNo()+ "','" 
	        		 +v.getSiteName()+ "','" +v.getShift()+ "','" +v.getTimeInDt()+ "');");
	        rs = stmt.executeQuery("SELECT OFFICER_NAME FROM TODHOD WHERE RECORD_ID = '" +v.getRecordId()+ "';");
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
	
	public static String updateTodHodDetails(TodHodRecord v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE TODHOD "
	        		+  "SET OFFICER_NAME = '" + v.getOfficerName() + "', "
	        		+  "OFFICER_IDNO = '" + v.getOfficerIdNo() + "', "
	        		+  "SITE_NAME = '" + v.getSiteName() + "', "
	        		+  "TIME_OUT_DT = NOW() "
	        		+ "   WHERE RECORD_ID = '" + v.getRecordId() + "';");
	        rs = stmt.executeQuery("SELECT OFFICER_NAME FROM TODHOD WHERE RECORD_ID ='" + v.getRecordId() +"';");
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
		message = "Successful";
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(RECORD_ID AS INTEGER)) FROM TODHOD;");
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
	
	public static ArrayList<TodHodRecord> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT \r\n"
            		+ "FROM TODHOD ORDER BY TIME_IN_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new TodHodRecord(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getTimestamp(6),
            			rs.getTimestamp(7));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<TodHodRecord> retrieveByOfficerIdNo(String officerIdNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT \r\n" + 
            		"FROM TODHOD \r\n"
            		+ " WHERE OFFICER_IDNO ='" + officerIdNo + "' ORDER BY TIME_IN_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new TodHodRecord(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getTimestamp(6),
            			rs.getTimestamp(7));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<TodHodRecord> retrieveByTime( String shift, String from, String to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT \r\n" + 
            		"FROM TODHOD "
    				+ " WHERE TIME_IN_DT =>'" + from + "' "
    				+ " AND TIME_IN_DT <='" + to + "' "
    				+ " AND SHIFT ='" + shift + "' "
					+ " ORDER BY TIME_IN_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new TodHodRecord(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getTimestamp(6),
            			rs.getTimestamp(7));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<TodHodRecord> retrieveBySiteTime(String shift, String site, String from, String to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT \r\n" + 
            		"FROM TODHOD "
    				+ " WHERE SITE_NAME ='" + site + "' "
    				+ " AND TIME_IN_DT =>'" + from + "' "
    				+ " AND TIME_IN_DT <='" + to + "' "
    				+ " AND SHIFT ='" + shift + "' "
					+ " ORDER BY TIME_IN_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new TodHodRecord(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getTimestamp(6),
            			rs.getTimestamp(7));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<TodHodRecord> retrieveByIdNoSiteTime(String shift, String idNo, String site, String from, String to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT \r\n" + 
            		"FROM TODHOD "
    				+ " WHERE SITE_NAME ='" + site + "' "
    				+ " AND OFFICER_IDNO ='" + idNo + "' "
    				+ " AND TIME_IN_DT =>'" + from + "' "
    				+ " AND TIME_IN_DT <='" + to + "' "
    				+ " AND SHIFT ='" + shift + "' "
					+ " ORDER BY TIME_IN_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new TodHodRecord(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getTimestamp(6),
            			rs.getTimestamp(7));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<TodHodRecord> retrieveByIdNoTime(String shift, String officerIdNo, String from, String to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT \r\n" + 
            		"FROM TODHOD "
            		+ " WHERE OFFICER_IDNO ='" + officerIdNo + "' "
    				+ " AND TIME_IN_DT =>'" + from + "' "
    				+ " AND TIME_IN_DT <='" + to + "' "
    				+ " AND SHIFT ='" + shift + "' "
    				+ " ORDER BY TIME_IN_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new TodHodRecord(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getTimestamp(6),
            			rs.getTimestamp(7));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static String deleteByRecordId(String recordId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No tod hod records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM TODHOD WHERE RECORD_ID ='" + recordId + "'";
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
        String message = "All records deleted - No tod hod records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM TODHOD WHERE TIME_OUT_DT <= GETDATE() - 30;";
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
