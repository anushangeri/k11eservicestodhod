package net.javatutorial.DAO;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.postgresql.jdbc4.Jdbc4Array;

import net.javatutorial.entity.AttendanceUploadFile;
import net.javatutorial.entity.Incident;
import net.javatutorial.tutorials.Main;

public class IncidentManagerDAO {

	public static String addIncident(Incident v) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

			stmt.executeUpdate("INSERT INTO INCIDENT " + "(" + "INCIDENT_ID ," + "OFFICERONDUTYNAME ,"
					+ "OFFICERONDUTYID ," + "OFFICERONDUTYDESIGNATION ," + "REPORTINGSITE ," + "DATEOFINCIDENT ,"
					+ "TIMEOFINCIDENT ," + "DATEOFINCIDENTREPORTED ," + "PARTIESINVOLVED ," + "INCIDENTCATEGORY ,"
					+ "HOWINCIDENTOCCURRED ," + "WHATINCIDENTOCCURRED ," + "WHYINCIDENTOCCURRED ,"
					+ "DECLARATIONBYOFFICERONDUTY ," + "DECLARATIONOFSECURITYIMPLICATIONS ,"
					+ "SIGNATUREOFOFFICERONDUTY ," + "SIGNATUREOFOPSMANAGERONDUTY ," + "IMAGES ," + "CREATED_DT  ,"
					+ "LAST_MODIFIED_DT  " + ")" + "  VALUES (" + "'" + v.getIncidentId() + "','"
					+ v.getOfficerOnDutyName() + "','" + v.getOfficerOnDutyId() + "','"
					+ v.getOfficerOnDutyDesignation() + "','" + v.getReportingSite() + "','" + v.getDateOfIncident()
					+ "','" + v.getTimeOfIncident() + "','" + v.getDateOfIncidentReported() + "','"
					+ v.getPartiesInvolved() + "','{" + v.getIncidentCategory() + "}','" + v.getHowIncidentOccurred()
					+ "','" + v.getWhatIncidentOccurred() + "','" + v.getWhyIncidentOccurred() + "','"
					+ v.getDeclarationByOfficerOnDuty() + "','" + v.getDeclarationofSecurityImplications() + "','"
					+ v.getSignatureOfOfficerOnDuty() + "','" + v.getSignatureOfOpsManagerOnDuty() + "','{"
					+ v.getImages() + "}','" + v.getCreatedDt() + "','" + v.getLastModifiedDt() + "'" + ")");
			rs = stmt.executeQuery(
					"SELECT INCIDENTCATEGORY FROM INCIDENT WHERE INCIDENT_ID = '" + v.getIncidentId() + "';");
			while (rs.next()) {
				message = "Read from DB: " +  ((Jdbc4Array) rs.getArray(1)).toString();
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
					+ "SIGNATUREOFOPSMANAGERONDUTY ,IMAGES, CREATED_DT  ,LAST_MODIFIED_DT "
					+ "FROM INCIDENT ORDER BY LAST_MODIFIED_DT DESC; ";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Array incidentCat_array = rs.getArray(10);
				String incidentCat_input = (incidentCat_array.toString()).replace('"',' ').replaceAll("\\{|\\}", " ").replaceAll("\\[|\\]", "");
				String[] incidentCat_result = incidentCat_input.substring(1, incidentCat_input.length() - 1).split(",");
				List<String> incidentCategories = Arrays.asList(incidentCat_result);
				
				Array imagesString_array = rs.getArray(18);
				String imagesString_input = (imagesString_array.toString()).replaceAll("\\{|\\}", " ").replaceAll("\\[|\\]", "");
				String[] imagesString_result = imagesString_input.substring(1, imagesString_input.length() - 1).split(",");

				List<InputStream> images = new ArrayList<InputStream>();
				 
				if (imagesString_result != null && imagesString_result.length > 0) {
					for (String s : imagesString_result) {
						byte[] bytea = s.getBytes();
						images.add(new ByteArrayInputStream(bytea));
					}
				}
				v = new Incident(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), incidentCategories,
						rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
						rs.getString(16), rs.getString(17), images, rs.getTimestamp(19), rs.getTimestamp(20));
				vList.add(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Main.close(connection, pstmt, rs);
		}
		return vList;
	}

	public static ArrayList<Incident> retrieveByIncidentId(String incidentId) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;
		ResultSet images_rs = null;
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
					+ "SIGNATUREOFOPSMANAGERONDUTY ,CREATED_DT  ,LAST_MODIFIED_DT  " + " FROM INCIDENT "
					+ " WHERE INCIDENT_ID ='" + incidentId + "' ORDER BY LAST_MODIFIED_DT DESC;";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			String images_sql = "SELECT unnest(images) " + " FROM INCIDENT "
					+ " WHERE INCIDENT_ID ='" + incidentId + "' ORDER BY LAST_MODIFIED_DT DESC;";
			
			pstmt = connection.prepareStatement(images_sql);

			images_rs = pstmt.executeQuery();
			List<InputStream> images = new ArrayList<InputStream>();
			while (images_rs.next()) {
            	byte[] bytea = images_rs.getBytes(1);
            	images.add(new ByteArrayInputStream(bytea));
            }
			while (rs.next()) {
				Array incidentCat_array = rs.getArray(10);
				String incidentCat_input = (incidentCat_array.toString()).replace('"',' ').replaceAll("\\{|\\}", " ").replaceAll("\\[|\\]", "");
				String[] incidentCat_result = incidentCat_input.substring(1, incidentCat_input.length() - 1).split(",");
				List<String> incidentCategories = Arrays.asList(incidentCat_result);
				
//				Array imagesString_array = rs.getArray(18);
//				String imagesString_input = (imagesString_array.toString()).replace('"',' ');
//				String[] imagesString_result = imagesString_input.substring(3, imagesString_input.length() - 1).split(",");
//
//				List<InputStream> images = new ArrayList<InputStream>();
//				List<InputStream> test = (List<InputStream>)(List<?>) rs.g(18);
//				System.out.println(test.toString());
//				if (imagesString_result != null && imagesString_result.length > 0) {
//					for (String s : imagesString_result) {
//						String getByte = s.trim();
//						byte[] bytea = s.getBytes();
//						images.add(new ByteArrayInputStream(Base64.decodeBase64(s.getBytes())));
//					}
//				}
				v = new Incident(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), incidentCategories,
						rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
						rs.getString(16), rs.getString(17), images, rs.getTimestamp(18), rs.getTimestamp(19));
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

	public static String deleteByOccurrenceId(String incidentId) {
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
