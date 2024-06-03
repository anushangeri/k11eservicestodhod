package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.javatutorial.DAO.IncidentManagerDAO;
import net.javatutorial.entity.Incident;


/**
 * Servlet implementation class AddIncidentRecordServlet
 */
@MultipartConfig
public class AddIncidentRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = IncidentManagerDAO.getNextVal();
		
		String incidentId = "" + nextVal;
		String	officerOnDutyName	 =	request.getParameter("officerOnDutyName");
		String	officerOnDutyId	 =	request.getParameter("officerOnDutyId");
		String	officerOnDutyDesignation	 =	request.getParameter("officerOnDutyDesignation");

		String	reportingSite	 =	request.getParameter("reportingSite");
		String	dateOfIncident	 =	request.getParameter("dateOfIncident");
		String	timeOfIncident	 =	request.getParameter("timeOfIncident");
		String	dateOfIncidentReported	 =	request.getParameter("dateOfIncidentReported");

		String	partiesInvolved	 =	request.getParameter("partiesInvolved");
		String[]	incidentCategory	 =	 request.getParameterValues("incidentCategory");

		String	howIncidentOccurred	 =	request.getParameter("howIncidentOccurred");
		String	whatIncidentOccurred	 =	request.getParameter("whatIncidentOccurred");
		String	whyIncidentOccurred	 =	request.getParameter("whyIncidentOccurred");

		String	declarationByOfficerOnDuty	 =	request.getParameter("declarationByOfficerOnDuty").equals("No") ? "No: " + request.getParameter("reasonDeclarationByOfficerOnDuty") :  request.getParameter("declarationByOfficerOnDuty");
		String	declarationofSecurityImplications	 =	request.getParameter("declarationofSecurityImplications").equals("No") ? "No: " + request.getParameter("reasonDeclarationofSecurityImplications") : request.getParameter("declarationofSecurityImplications");

		String	signatureOfOfficerOnDuty	 =	request.getParameter("signatureOfOfficerOnDuty");
		//String	signatureOfOpsManagerOnDuty	 =	request.getParameter("signatureOfOpsManagerOnDuty");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		InputStream inputStream = null;
		// obtains the upload file part in this multipart request
        Part filePart = request.getPart("file");

        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
		}
        
		Incident i = new Incident(incidentId,  officerOnDutyName,  officerOnDutyId,
				officerOnDutyDesignation,  reportingSite,  dateOfIncident,  timeOfIncident,
				dateOfIncidentReported,  partiesInvolved, Arrays.asList(incidentCategory),
				howIncidentOccurred,  whatIncidentOccurred,  whyIncidentOccurred,
				declarationByOfficerOnDuty,  declarationofSecurityImplications,
				signatureOfOfficerOnDuty, "unknown" ,inputStream, timestamp, timestamp);
		
		String message = IncidentManagerDAO.addIncident(i);
		request.setAttribute("message", message);
		// Redirect to view incident servlet to query all the incidents again.
		response.sendRedirect("/manageIncidentRecord");
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
