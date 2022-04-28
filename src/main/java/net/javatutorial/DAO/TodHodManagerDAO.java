package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

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
	        	message = "successfully added record";
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
	        	message = "Successfully updated TOD / HOD record";
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
            		"FROM TODHOD "
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
	
	public static ArrayList<TodHodRecord> retrieveByLatestTod(String officerIdNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT \r\n" + 
            		" FROM TODHOD "
            		+ " WHERE OFFICER_IDNO ='" + officerIdNo + "' AND TIME_OUT_DT IS NULL"
    				+ " ORDER BY TIME_IN_DT DESC LIMIT 1;";
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
	
	public static ArrayList<TodHodRecord> retrieveByTime( String shift, Timestamp from, Timestamp to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT " + 
            		"FROM TODHOD "
    				+ " WHERE TIME_IN_DT >='" + from + "' "
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
	
	public static ArrayList<TodHodRecord> retrieveBySiteTime(String shift, String site, Timestamp from, Timestamp to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT " + 
            		"FROM TODHOD "
    				+ " WHERE SITE_NAME ='" + site + "' "
    				+ " AND TIME_IN_DT >='" + from + "' "
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
	
	public static HashMap<String, Integer> retrieveByTimeShift(String shift, Timestamp date) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        HashMap<String, Integer> vList = new HashMap<String, Integer>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT SITE_NAME, COUNT(*) FROM "
            		+ " (SELECT DISTINCT OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT "  
            		+ " FROM TODHOD "
    				+ " WHERE DATE(TIME_IN_DT)  ='" + date + "' "
    				+ " AND SHIFT ='" + shift + "' ) t"
					+ " GROUP BY SITE_NAME ;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	vList.put(rs.getString(1), 
            			rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<TodHodRecord> retrieveByIdNoSiteTime(String shift, String idNo, String site, Timestamp from, Timestamp to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT " + 
            		"FROM TODHOD "
    				+ " WHERE SITE_NAME ='" + site + "' "
    				+ " AND OFFICER_IDNO ='" + idNo + "' "
    				+ " AND TIME_IN_DT >='" + from + "' "
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
	
	public static ArrayList<TodHodRecord> retrieveByIdNoTime(String shift, String officerIdNo, Timestamp from, Timestamp to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT " + 
            		"FROM TODHOD "
            		+ " WHERE OFFICER_IDNO ='" + officerIdNo + "' "
    				+ " AND TIME_IN_DT >='" + from + "' "
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
	
	public static TodHodRecord retrieveByRecordId(String recordId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT RECORD_ID, OFFICER_NAME, OFFICER_IDNO, SITE_NAME, SHIFT, TIME_IN_DT, TIME_OUT_DT " + 
            		"FROM TODHOD "
            		+ " WHERE RECORD_ID ='" + recordId + "' "
    				+ " LIMIT 1 ; ";
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
        return v;
    }
	
	public static TodHodRecord retrieveShiftCountByIdNo(String officerIdNo, Timestamp from, Timestamp to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        TodHodRecord v = null;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT SHIFT, COUNT(*) " + 
            		"FROM TODHOD "
            		+ " WHERE time_in_dt >= '" + from + "' "
            		+ " AND time_in_dt <= '" + to + "' "
            		+ " AND officer_idno <= '" + officerIdNo + "' "
    				+ " GROUP BY SHIFT ; ";
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
        return v;
    }
	
	public static int retrieveDaysWorkedByIdNo(String officerIdNo, String from, String to) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        int v = 0;
        ArrayList<TodHodRecord> vList = new ArrayList<TodHodRecord>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT COUNT(*) " + 
            		"FROM (SELECT DISTINCT officer_idno, CAST(time_in_dt AS DATE) AS TIME_IN_DATE "
            		+ " FROM todhod "
            		+ " WHERE time_in_dt >= '" + from + "' "
            		+ " AND time_in_dt <= '" + to + "' "
            		+ " AND officer_idno <= '" + officerIdNo + "' ) temp; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return v;
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

            pstmt.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
	
	public static String deleteAll() {
		Statement stmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "Records deleted - 60 days worth of records in database";
        try {
            String sql = "DELETE FROM TODHOD WHERE DATE_PART('day', NOW()::timestamp - TIME_IN_DT::timestamp) >= 37;";
            connection = Main.getConnection();
			stmt = connection.createStatement();
	        stmt.executeUpdate(sql);
            
        } catch (Exception e) {
        	message = "there is issue: " + e;
            e.printStackTrace();
        } finally {
        	Main.close(connection, stmt, rs);
        }
        return message;
    }
}
