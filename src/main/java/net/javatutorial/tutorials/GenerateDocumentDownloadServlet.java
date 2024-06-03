package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.DAO.IncidentManagerDAO;
import net.javatutorial.DAO.MiscDocumentsManagerDAO;
import net.javatutorial.entity.Employee;
import net.javatutorial.entity.MiscDocuments;
/**
 * Servlet implementation class GenerateDocumentDownloadServlet
 * generateDocDwnld
 */
@MultipartConfig
public class GenerateDocumentDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dwnldDocInd = request.getParameter("dwnldDocInd");
		if(dwnldDocInd == null || StringUtils.isEmpty(dwnldDocInd)) {
			dwnldDocInd = (String) request.getAttribute("dwnldDocInd");
		}
		System.out.println("dwnldDocInd: " + dwnldDocInd);
		InputStream inputStream = null;
		if(dwnldDocInd != null && !(StringUtils.isEmpty(dwnldDocInd)) && dwnldDocInd.equals("dwnldKET")) {
			String employeeID = request.getParameter("employeeID");
	        Employee v = EmployeeManagerDAO.retrieveEmployeeByEmployeeID(employeeID);
	       
	        inputStream = v.getKetDocument(); // input stream of the upload file
		}
		if(dwnldDocInd != null && !(StringUtils.isEmpty(dwnldDocInd)) && dwnldDocInd.equals("dwnldMiscDoc")) {
			String documentId = request.getParameter("documentId");
			if(documentId == null || StringUtils.isEmpty(documentId)) {
				documentId = (String) request.getAttribute("documentId"); //coming from another page
			}
			System.out.println("documentId: " + documentId);
			MiscDocuments v = MiscDocumentsManagerDAO.retrieveByDocumentID(documentId);
			System.out.println("v: " + v.toString());
	        inputStream = v.getDocument(); // input stream of the upload file
		}
		if(dwnldDocInd != null && !(StringUtils.isEmpty(dwnldDocInd)) && dwnldDocInd.equals("dwnldIncidentDoc")) {
			String incidentId = request.getParameter("incidentId");
			if(incidentId != null || !StringUtils.isEmpty(incidentId)) {
				inputStream = IncidentManagerDAO.retrieveFilesByIncidentId(incidentId);
			}
		}
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();
        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
        	output.write(buffer, 0, bytesRead);
        }
        output.close();
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
