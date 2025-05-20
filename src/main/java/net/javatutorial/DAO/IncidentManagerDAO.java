package net.javatutorial.DAO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.javatutorial.entity.Incident;
import net.javatutorial.tutorials.Main;

public class IncidentManagerDAO {

	public static String addIncident(Incident v) {
	    Connection connection = null;
	    ResultSet rs = null;
	    PreparedStatement stmt = null;
	    String message = "";

	    try {
	        connection = Main.getConnection();

	        String sql = "INSERT INTO INCIDENT (INCIDENT_ID, OFFICERONDUTYNAME, OFFICERONDUTYID, "
	                + "OFFICERONDUTYDESIGNATION, REPORTINGSITE, DATEOFINCIDENT, TIMEOFINCIDENT, "
	                + "DATEOFINCIDENTREPORTED, PARTIESINVOLVED, INCIDENTCATEGORY, HOWINCIDENTOCCURRED, "
	                + "WHATINCIDENTOCCURRED, WHYINCIDENTOCCURRED, DECLARATIONBYOFFICERONDUTY, "
	                + "DECLARATIONOFSECURITYIMPLICATIONS, SIGNATUREOFOFFICERONDUTY, SIGNATUREOFOPSMANAGERONDUTY, "
	                + "FILE, CREATED_DT, LAST_MODIFIED_DT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW());";

	        stmt = connection.prepareStatement(sql);

	        stmt.setString(1, v.getIncidentId());
	        stmt.setString(2, v.getOfficerOnDutyName());
	        stmt.setString(3, v.getOfficerOnDutyId());
	        stmt.setString(4, v.getOfficerOnDutyDesignation());
	        stmt.setString(5, v.getReportingSite());
	        stmt.setString(6, v.getDateOfIncident());
	        stmt.setString(7, v.getTimeOfIncident());
	        stmt.setString(8, v.getDateOfIncidentReported());
	        stmt.setString(9, v.getPartiesInvolved());
	        stmt.setArray(10, connection.createArrayOf("text", v.getIncidentCategory().toArray()));
	        stmt.setString(11, v.getHowIncidentOccurred());
	        stmt.setString(12, v.getWhatIncidentOccurred());
	        stmt.setString(13, v.getWhyIncidentOccurred());
	        stmt.setString(14, v.getDeclarationByOfficerOnDuty());
	        stmt.setString(15, v.getDeclarationofSecurityImplications());
	        stmt.setString(16, v.getSignatureOfOfficerOnDuty());
	        stmt.setString(17, v.getSignatureOfOpsManagerOnDuty());
	        stmt.setBinaryStream(18, v.getFile());

	        int row = stmt.executeUpdate();
	        if (row > 0) {
	            message = "Incident successfully recorded in the database.";
	        }

	    } catch (URISyntaxException e) {
	        message = "" + e;
	    } catch (SQLException e) {
	        message = "" + e;
	    } finally {
	        Main.close(connection, stmt, rs);
	    }

	    return message;
	}
	
	public static String updateIncident(Incident v) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

			stmt.executeUpdate("SET TIMEZONE = 'Singapore'; " + "UPDATE INCIDENT " + "SET REPORTINGSITE = '"
					+ v.getReportingSite() + "', " + "OFFICERONDUTYNAME = '" + v.getOfficerOnDutyName() + "', "
					+ "OFFICERONDUTYID = '" + v.getOfficerOnDutyId() + "', " + "OFFICERONDUTYDESIGNATION = '"
					+ v.getOfficerOnDutyDesignation() + "', "

					+ "DATEOFINCIDENT = '" + v.getDateOfIncident() + "', " + "TIMEOFINCIDENT = '"
					+ v.getTimeOfIncident() + "', " + "DATEOFINCIDENTREPORTED = '" + v.getDateOfIncidentReported()
					+ "', " + "PARTIESINVOLVED = '" + v.getPartiesInvolved() + "', "

					+ "INCIDENTCATEGORY = '" + v.getIncidentCategory() + "', " + "HOWINCIDENTOCCURRED = '"
					+ v.getHowIncidentOccurred() + "', " + "WHATINCIDENTOCCURRED = '" + v.getWhatIncidentOccurred()
					+ "', " + "WHYINCIDENTOCCURRED = '" + v.getWhyIncidentOccurred() + "', "

					+ "DECLARATIONBYOFFICERONDUTY = '" + v.getDeclarationByOfficerOnDuty() + "', "
					+ "DECLARATIONOFSECURITYIMPLICATIONS = '" + v.getDeclarationofSecurityImplications() + "', "
					+ "SIGNATUREOFOPSMANAGERONDUTY = '" + v.getSignatureOfOpsManagerOnDuty() + "', "

					+ "LAST_MODIFIED_DT = NOW() " + "   WHERE INCIDENT_ID = '" + v.getIncidentId() + "';");
			rs = stmt.executeQuery(
					"SELECT REPORTINGSITE FROM INCIDENT WHERE INCIDENT_ID = '" + v.getIncidentId() + "';");
			while (rs.next()) {
				message = "Read from DB: " + rs.getString(1);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			// e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			message = "" + e;
		} finally {
			Main.close(connection, stmt, rs);
		}
		message = "Successful";
		return message;
	}

	public static int getNextVal() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		int result = 1;
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();
//	        stmt.executeUpdate("SELECT count(*) FROM EMPLOYEES;");
			rs = stmt.executeQuery("SELECT MAX(CAST(INCIDENT_ID AS INTEGER)) FROM INCIDENT;");
			if (rs != null) {
				while (rs.next()) {
					if (rs.getString(1) != null && !rs.getString(1).isEmpty()) {
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
		} finally {
			Main.close(connection, stmt, rs);
		}
		return result;
	}

	public static ArrayList<Incident> retrieveAll() {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		Incident v = null;
		ArrayList<Incident> vList = new ArrayList<Incident>();
		try {
			connection = Main.getConnection();
			String sql = "SELECT INCIDENT_ID ,OFFICERONDUTYNAME , OFFICERONDUTYID ,"
					+ "OFFICERONDUTYDESIGNATION ,REPORTINGSITE ,DATEOFINCIDENT ,"
					+ "TIMEOFINCIDENT ,DATEOFINCIDENTREPORTED ,PARTIESINVOLVED ,"
					+ "INCIDENTCATEGORY , HOWINCIDENTOCCURRED ,WHATINCIDENTOCCURRED,"
					+ "WHYINCIDENTOCCURRED ,DECLARATIONBYOFFICERONDUTY ,"
					+ "DECLARATIONOFSECURITYIMPLICATIONS ,SIGNATUREOFOFFICERONDUTY ,"
					+ "SIGNATUREOFOPSMANAGERONDUTY ,FILE, CREATED_DT  ,LAST_MODIFIED_DT "
					+ "FROM INCIDENT ORDER BY LAST_MODIFIED_DT DESC; ";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Array incidentCat_array = rs.getArray(10);
				String incidentCat_input = (incidentCat_array.toString()).replace('"',' ').replaceAll("\\{|\\}", " ").replaceAll("\\[|\\]", "");
				String[] incidentCat_result = incidentCat_input.substring(1, incidentCat_input.length() - 1).split(",");
				List<String> incidentCategories = Arrays.asList(incidentCat_result);
				
				byte[] bytea = rs.getBytes(18);
				
				v = new Incident(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), incidentCategories,
						rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
						rs.getString(16), rs.getString(17), new ByteArrayInputStream(bytea), rs.getTimestamp(19), rs.getTimestamp(20));
				vList.add(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Main.close(connection, pstmt, rs);
		}
		return vList;
	}

	@SuppressWarnings("resource")
	public static ArrayList<Incident> retrieveByIncidentId(String incidentId) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		Incident v = null;
		ArrayList<Incident> vList = new ArrayList<Incident>();
		try {
			connection = Main.getConnection();
			String sql = "SELECT INCIDENT_ID ,OFFICERONDUTYNAME , OFFICERONDUTYID ,"
					+ "OFFICERONDUTYDESIGNATION ,REPORTINGSITE ,DATEOFINCIDENT ,"
					+ "TIMEOFINCIDENT ,DATEOFINCIDENTREPORTED ,PARTIESINVOLVED ,"
					+ "INCIDENTCATEGORY , HOWINCIDENTOCCURRED ,WHATINCIDENTOCCURRED, "
					+ "WHYINCIDENTOCCURRED ,DECLARATIONBYOFFICERONDUTY ,"
					+ "DECLARATIONOFSECURITYIMPLICATIONS ,SIGNATUREOFOFFICERONDUTY ,"
					+ "SIGNATUREOFOPSMANAGERONDUTY , FILE, CREATED_DT  ,LAST_MODIFIED_DT  " + " FROM INCIDENT "
					+ " WHERE INCIDENT_ID ='" + incidentId + "' ORDER BY LAST_MODIFIED_DT DESC;";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Array incidentCat_array = rs.getArray(10);
				String incidentCat_input = (incidentCat_array.toString()).replace('"',' ').replaceAll("\\{|\\}", " ").replaceAll("\\[|\\]", "");
				String[] incidentCat_result = incidentCat_input.substring(1, incidentCat_input.length() - 1).split(",");
				List<String> incidentCategories = Arrays.asList(incidentCat_result);
				
				byte[] bytea = rs.getBytes(18);

				v = new Incident(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), incidentCategories,
						rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
						rs.getString(16), rs.getString(17), new ByteArrayInputStream(bytea), rs.getTimestamp(19), rs.getTimestamp(20));
				System.out.println(v);
				vList.add(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Main.close(connection, pstmt, rs);
		}
		return vList;
	}
	
	public static InputStream retrieveFilesByIncidentId(String incidentId) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		ResultSet file_rs = null;
		InputStream inputstream = null;
		try {
			connection = Main.getConnection();
			
			String file_sql = "SELECT FILE " + " FROM INCIDENT "
					+ " WHERE INCIDENT_ID ='" + incidentId + "' ORDER BY LAST_MODIFIED_DT DESC;";
			
			pstmt = connection.prepareStatement(file_sql);

			file_rs = pstmt.executeQuery();
			while (file_rs.next()) {
            	byte[] bytea = file_rs.getBytes(1);
            	inputstream = new ByteArrayInputStream(bytea);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Main.close(connection, pstmt, rs);
		}
		return inputstream;
	}

	public static String deleteByIncidentId(String incidentId) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		String message = "All records deleted - No incident records available";
		try {
			connection = Main.getConnection();
			String sql = "DELETE FROM INCIDENT WHERE INCIDENT_ID ='" + incidentId + "'";
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
		String message = "All records deleted - No incident records available";
		try {
			connection = Main.getConnection();
			String sql = "DELETE FROM INCIDENT WHERE LAST_MODIFIED_DT <= GETDATE() - 30;";
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
