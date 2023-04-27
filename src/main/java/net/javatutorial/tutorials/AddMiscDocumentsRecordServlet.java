package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.DAO.MiscDocumentsManagerDAO;
import net.javatutorial.entity.MiscDocuments;

/**
 * Servlet implementation class AddMiscDocumentsRecordServlet
 */
@MultipartConfig
public class AddMiscDocumentsRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = MiscDocumentsManagerDAO.getNextVal();
		
		String message = "Tried to add Misc Documents record, it did not work.";
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		MiscDocuments v = null;
		
		String documentId = "" + nextVal;
		String createdBy = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");
		String lastModifiedBy = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");
		
		//if status from DeleteKETDocumentServlet, then just add inputstream, else need to this whole filepart portion
		String status = (String) request.getAttribute("status");
		InputStream inputStream = null; // input stream of the upload file
		if(status != null && !(StringUtils.isEmpty(status)) && status.equals("deleteKET")) {
			String employeeId = (String) request.getAttribute("employeeId");
			String description = (String) request.getAttribute("description");
			inputStream = (InputStream) request.getAttribute("ketDocument");
			//inputStream = new ByteArrayInputStream(ketDocument.getBytes());
			
			v = new MiscDocuments(documentId, employeeId, inputStream, description, createdBy, lastModifiedBy,  timestamp, timestamp);
			message = MiscDocumentsManagerDAO.addMiscDocuments(v);
			
			//delete the KET document tied to the employee - each employee can only have one active KET
			//so ADMIN need to update the employee record with the new KET
			message = EmployeeManagerDAO.deleteKETDocbyEmployeeID(employeeId);
			request.setAttribute("deleteKETDocServMsg", message);
			//Redirect to view employee servlet to query all the employee again (download button should be greyed out)
			response.sendRedirect("/viewEmp");
		}
		else {
			String employeeId = (String) request.getParameter("employeeId");
			String description = (String) request.getParameter("description");
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("uploadFile");
	
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	             
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();
	            v = new MiscDocuments(documentId, employeeId, inputStream, description, createdBy, lastModifiedBy,  timestamp, timestamp);
	            message = MiscDocumentsManagerDAO.addMiscDocuments(v);
			}
	        request.setAttribute("addMiscDocServMsg", message);
			// Redirect to view Misc Documents servlet to query all the Misc Documents again.
			response.sendRedirect("/viewMiscDocs");
		}
		
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
