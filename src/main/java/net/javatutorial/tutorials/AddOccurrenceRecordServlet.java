package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.OccurrenceManagerDAO;
import net.javatutorial.entity.Occurrence;

/**
 * Servlet implementation class AddSiteRecordServlet
 */
public class AddOccurrenceRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = OccurrenceManagerDAO.getNextVal();
		
		String occurrenceId = "" + nextVal; 
		
		String reportingSite = request.getParameter("reportingSite");
		String reportingClassification = request.getParameter("reportingClassification").replaceAll("'", "");
		String officerOnDutyName = request.getParameter("officerOnDutyName").replaceAll("'", "");
		String officerOnDutyId = request.getParameter("officerOnDutyId").replaceAll("'", "");
		String securityRiskThreat = request.getParameter("securityRiskThreat").replaceAll("'", "");
		String descriptionOfSecurityRiskThreat = request.getParameter("descriptionOfSecurityRiskThreat").replaceAll("'", "");
		String nonConformanceSOP = request.getParameter("nonConformanceSOP").replaceAll("'", "");
		String desciptionOfNonConformance = request.getParameter("desciptionOfNonConformance").replaceAll("'", "");
		String partiesInvolved = request.getParameter("partiesInvolved").replaceAll("'", "");
		String descriptionOfPartiesInvolved = request.getParameter("descriptionOfPartiesInvolved").replaceAll("'", "");
		String descriptionOfK11Notified = request.getParameter("descriptionOfK11Notified").replaceAll("'", "");
		String howIncidentOccurred = request.getParameter("howIncidentOccurred").replaceAll("'", "");
		String whatIncidentOccurred = request.getParameter("whatIncidentOccurred").replaceAll("'", "");
		String whyIncidentOccurred = request.getParameter("whyIncidentOccurred").replaceAll("'", "");
		String whatActionWasTaken = request.getParameter("whatActionWasTaken").replaceAll("'", "");
		String extraDetailsOnAction = request.getParameter("extraDetailsOnAction").replaceAll("'", "");
		String pendingAction = request.getParameter("pendingAction").replaceAll("'", "");
		String incidentCategory = request.getParameter("incidentCategory").replaceAll("'", "");

		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

				
		Occurrence v = new Occurrence( occurrenceId,  reportingSite,  reportingClassification,
				officerOnDutyName,  officerOnDutyId,  securityRiskThreat,
				descriptionOfSecurityRiskThreat,  nonConformanceSOP,  desciptionOfNonConformance,
				partiesInvolved,  descriptionOfPartiesInvolved,  descriptionOfK11Notified,
				howIncidentOccurred,  whatIncidentOccurred,  whyIncidentOccurred,
				whatActionWasTaken,  extraDetailsOnAction,  pendingAction,  incidentCategory,
				timestamp, timestamp);
		
		String message = OccurrenceManagerDAO.addOccurrence(v);
		
		request.setAttribute("responseObj", message);
		// Redirect to view occurrence servlet to query all the occurrence again.
		response.sendRedirect("/manageOccurrenceRecord");
	}
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}

}
