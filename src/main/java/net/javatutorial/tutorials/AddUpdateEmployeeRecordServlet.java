package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.DAO.MiscDocumentsManagerDAO;
import net.javatutorial.entity.Employee;
import net.javatutorial.entity.MiscDocuments;

/**
 * Servlet implementation class AddUpdateEmployeeRecordServlet
 */
@MultipartConfig
public class AddUpdateEmployeeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = EmployeeManagerDAO.getNextVal();
		
		String employeeId = "" + nextVal;
		String idNo = request.getParameter("idNo");
		Integer annualLeave = request.getParameter("annualLeave") != null ? Integer.parseInt(request.getParameter("annualLeave")) : 0;
		Integer annualOutpatientLeave = request.getParameter("annualOutpatientLeave") != null ? Integer.parseInt(request.getParameter("annualOutpatientLeave")) : 0;
		Integer annualHospitalLeave = request.getParameter("annualHospitalLeave") != null ? Integer.parseInt(request.getParameter("annualHospitalLeave")) : 0;
		
		String createdBy = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");
		String lastModifiedBy = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");
		
		
		ArrayList<Employee> vList = EmployeeManagerDAO.retrieveEmployeeByID(idNo);
		String message = "Employee/KET record for this user already exists. Please verify.";
		
		InputStream inputStream = null; // input stream of the upload file
        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("uploadFile");

		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

		message = "Tried to add employee record, it did not work.";
        Employee v = null;
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            v = new Employee(employeeId, idNo, annualLeave, annualOutpatientLeave, annualHospitalLeave,inputStream,  timestamp, timestamp);
            System.out.println("Print employe : " + v.toString());
            
            if(vList == null || vList.size() == 0 ) {
                message = EmployeeManagerDAO.addEmployee(v);
    		}
    		else {
    			Employee oldEmpRecord = vList.get(0);
    			addMiscDoc(oldEmpRecord,createdBy,lastModifiedBy);
    			message = EmployeeManagerDAO.updateKETDocbyEmployeeID(v);
    		}
        }
		
		request.setAttribute("addEmpMessage", message);
		// Redirect to view employee servlet to query all the employee again.
		response.sendRedirect("/viewEmp");
	}
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
	public void addMiscDoc(Employee oldEmpRecord, String createdBy, String lastModifiedBy) {
		int nextVal = MiscDocumentsManagerDAO.getNextVal();
		String documentId = "" + nextVal;
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String employeeId = oldEmpRecord.getEmployeeId();
		String description =  "Archived KET for " + oldEmpRecord.getIdNo();
		InputStream inputStream = oldEmpRecord.getKetDocument();
		
		MiscDocuments v = new MiscDocuments(documentId, employeeId, inputStream, description, createdBy, lastModifiedBy,  timestamp, timestamp);
		MiscDocumentsManagerDAO.addMiscDocuments(v);
	}
}
