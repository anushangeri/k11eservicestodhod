package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.Occurrence;
import net.javatutorial.tutorials.Main;

public class OccurrenceManagerDAO {
	
	public static String addOccurrence(Occurrence v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO OCCURRENCE " +
	        		"(" +
	        		"OCCURRENCE_ID, " + 
	        		"REPORTINGSITE, " +
	        		"REPORTINGCLASSIFICATION, " +
	        		"OFFICERONDUTYNAME, " +
	        		"OFFICERONDUTYID, " +
	        		"SECURIYRISKTHREAT, " +
	        		"DESCRIPTIONOFSECURITYRISKTHREAT, " +
	        		"NONCONFORMANCESOP, " +
	        		"DESCIPTIONOFNONCONFORMANCE, " +
	        		"PARTIESINVOLVED, " +
	        		"DESCRIPTIONOFPARTIESINVOLVED, " +
	        		"DESCRIPTIONOFK11NOTIFIED, " +
	        		"HOWINCIDENTOCCURRED, " +
	        		"WHATINCIDENTOCCURRED, " +
	        		"WHYINCIDENTOCCURRED, " +
	        		"WHATACTIONWASTAKEN, " +
	        		"EXTRADETAILSONACTION, " +
	        		"PENDINGACTION, " +
	        		"INCIDENTCATEGORY, " +
	        		"CREATED_DT, "  +
	        		"LAST_MODIFIED_DT  "  +
	        		 ")" + 
	        		"  VALUES ("
	        		+ "'" +v.getOccurrenceId()+ "','" +v.getReportingSite()+ "','" +v.getReportingClassification()+ "','" 
	        		+v.getOfficerOnDutyName()+ "','" +v.getOfficerOnDutyId()+ "','" +v.getSecurityRiskThreat()+ "','"
	        		+v.getDescriptionOfSecurityRiskThreat()+ "','" +v.getNonConformanceSOP()+ "','" +v.getDesciptionOfNonConformance()+ "','"
	        		+v.getPartiesInvolved()+ "','" +v.getDescriptionOfPartiesInvolved()+ "','" +v.getDescriptionOfK11Notified()+ "','"
	        		+v.getHowIncidentOccurred()+ "','" +v.getWhatIncidentOccurred()+ "','" +v.getWhyIncidentOccurred()+ "','" +v.getWhatActionWasTaken()+ "','"
	        		+v.getExtraDetailsOnAction()+ "','" +v.getPendingAction()+ "','" +v.getIncidentCategory()+ "','"
	        		+v.getCreatedDt()+ "','" +v.getLastModifiedDt()+ "'"
	        		+ ")");
	        rs = stmt.executeQuery("SELECT REPORTINGSITE FROM OCCURRENCE WHERE OCCURRENCE_ID = '" +v.getOccurrenceId()+ "';");
	        while (rs.next()) {
	        	message = "Read from DB: " + rs.getTimestamp("tick");
	        	message = "successfull" ;
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
	
	public static String updateOccurrenceDetails(Occurrence v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE OCCURRENCE "
	        		+  "SET REPORTINGSITE = '" + v.getReportingSite() + "', "
	        		+  "REPORTINGCLASSIFICATION = '" + v.getReportingClassification() + "', "
	        		+  "OFFICERONDUTYNAME = '" + v.getOfficerOnDutyName() + "', "
	        		+  "OFFICERONDUTYID = '" + v.getOfficerOnDutyId() + "', "
	    	        +  "SECURIYRISKTHREAT = '" + v.getSecurityRiskThreat() + "', "
	    	        +  "DESCRIPTIONOFSECURITYRISKTHREAT = '" + v.getDescriptionOfSecurityRiskThreat() + "', "
	    	        +  "NONCONFORMANCESOP = '" + v.getNonConformanceSOP() + "', "
	    	        +  "DESCIPTIONOFNONCONFORMANCE = '" + v.getDesciptionOfNonConformance() + "', "
	    		    +  "PARTIESINVOLVED = '" + v.getPartiesInvolved() + "', "
	    		    +  "DESCRIPTIONOFPARTIESINVOLVED = '" + v.getDescriptionOfPartiesInvolved() + "', "
	    	    	+  "DESCRIPTIONOFK11NOTIFIED = '" + v.getDescriptionOfK11Notified() + "', "
	    	    	+  "HOWINCIDENTOCCURRED = '" + v.getHowIncidentOccurred() + "', "
	    	    	+  "WHATINCIDENTOCCURRED = '" + v.getWhatIncidentOccurred() + "', "
	    	    	+  "WHYINCIDENTOCCURRED = '" + v.getWhyIncidentOccurred() + "', "
	    	    	+  "WHATACTIONWASTAKEN = '" + v.getWhatActionWasTaken() + "', "
	    	    	+  "EXTRADETAILSONACTION = '" + v.getExtraDetailsOnAction() + "', "
	    	    	+  "PENDINGACTION = '" + v.getPendingAction() + "', "
	    	    	+  "INCIDENTCATEGORY = '" + v.getIncidentCategory() + "', "
	        		+  "LAST_MODIFIED_DT = NOW() "
	        		+ "   WHERE OCCURRENCE_ID = '" + v.getOccurrenceId() + "';");
	        rs = stmt.executeQuery("SELECT REPORTINGSITE FROM OCCURRENCE WHERE OCCURRENCE_ID = '" +v.getOccurrenceId()+ "';");
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(OCCURRENCE_ID AS INTEGER)) FROM OCCURRENCE;");
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
	
	public static ArrayList<Occurrence> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Occurrence v = null;
        ArrayList<Occurrence> vList = new ArrayList<Occurrence>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT OCCURRENCE_ID, REPORTINGSITE,	REPORTINGCLASSIFICATION,	OFFICERONDUTYNAME,	"
            		+ "OFFICERONDUTYID,	SECURIYRISKTHREAT,	DESCRIPTIONOFSECURITYRISKTHREAT,	NONCONFORMANCESOP,	"
            		+ "DESCIPTIONOFNONCONFORMANCE,	PARTIESINVOLVED,	DESCRIPTIONOFPARTIESINVOLVED,	"
            		+ "DESCRIPTIONOFK11NOTIFIED,	HOWINCIDENTOCCURRED,	WHATINCIDENTOCCURRED,	"
            		+ "WHYINCIDENTOCCURRED,	WHATACTIONWASTAKEN,	EXTRADETAILSONACTION,	PENDINGACTION,	"
            		+ "INCIDENTCATEGORY, CREATED_DT, LAST_MODIFIED_DT \r\n"
            		+ "FROM OCCURRENCE ORDER BY LAST_MODIFIED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Occurrence(
            			rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3), 
            			rs.getString(4),
            			rs.getString(5), 
            			rs.getString(6),
            			rs.getString(7), 
            			rs.getString(8),
            			rs.getString(9), 
            			rs.getString(10),
            			rs.getString(11), 
            			rs.getString(12),
            			rs.getString(13), 
            			rs.getString(14),
            			rs.getString(15), 
            			rs.getString(16),
            			rs.getString(17), 
            			rs.getString(18),
            			rs.getString(19), 
            			rs.getTimestamp(20),
            			rs.getTimestamp(21));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Occurrence> retrieveByOccurrenceId(String occurrenceId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Occurrence v = null;
        ArrayList<Occurrence> vList = new ArrayList<Occurrence>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT OCCURRENCE_ID, REPORTINGSITE,	REPORTINGCLASSIFICATION,	OFFICERONDUTYNAME,	"
            		+ "OFFICERONDUTYID,	SECURIYRISKTHREAT,	DESCRIPTIONOFSECURITYRISKTHREAT,	NONCONFORMANCESOP,	"
            		+ "DESCIPTIONOFNONCONFORMANCE,	PARTIESINVOLVED,	DESCRIPTIONOFPARTIESINVOLVED,	"
            		+ "DESCRIPTIONOFK11NOTIFIED,	HOWINCIDENTOCCURRED,	WHATINCIDENTOCCURRED,	"
            		+ "WHYINCIDENTOCCURRED,	WHATACTIONWASTAKEN,	EXTRADETAILSONACTION,	PENDINGACTION,	"
            		+ "INCIDENTCATEGORY, CREATED_DT, LAST_MODIFIED_DT "
            		+ " FROM OCCURRENCE "
            		+ " WHERE OCCURRENCE_ID ='" + occurrenceId + "' ORDER BY LAST_MODIFIED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Occurrence(
            			rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3), 
            			rs.getString(4),
            			rs.getString(5), 
            			rs.getString(6),
            			rs.getString(7), 
            			rs.getString(8),
            			rs.getString(9), 
            			rs.getString(10),
            			rs.getString(11), 
            			rs.getString(12),
            			rs.getString(13), 
            			rs.getString(14),
            			rs.getString(15), 
            			rs.getString(16),
            			rs.getString(17), 
            			rs.getString(18),
            			rs.getString(19), 
            			rs.getTimestamp(20),
            			rs.getTimestamp(21));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static String deleteByOccurrenceId(String occurrenceId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No occurrence records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM OCCURRENCE WHERE OCCURRENCE_ID ='" + occurrenceId + "'";
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
        String message = "All records deleted - No occurrence records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM OCCURRENCE WHERE LAST_MODIFIED_DT <= GETDATE() - 30;";
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
