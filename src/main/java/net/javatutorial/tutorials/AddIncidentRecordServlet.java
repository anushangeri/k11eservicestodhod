package net.javatutorial.tutorials;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.util.IOUtils;

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
		
		Collection<Part> parts = request.getParts();
		List<InputStream> images = new ArrayList<InputStream>();
	    for (Part part : parts) {
	    	InputStream inputStream = null; // input stream of the upload file
	    	// prints out some information for debugging
	    	if(part.getName().equals("files")) {
	            // obtains input stream of the upload image
	            inputStream = part.getInputStream();
	            images.add(inputStream);
	            
	    	}
	    }
		Incident i = new Incident(incidentId,  officerOnDutyName,  officerOnDutyId,
				officerOnDutyDesignation,  reportingSite,  dateOfIncident,  timeOfIncident,
				dateOfIncidentReported,  partiesInvolved, Arrays.asList(incidentCategory),
				howIncidentOccurred,  whatIncidentOccurred,  whyIncidentOccurred,
				declarationByOfficerOnDuty,  declarationofSecurityImplications,
				signatureOfOfficerOnDuty, images, timestamp, timestamp);
		
		String message = IncidentManagerDAO.addIncident(i);
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);
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
