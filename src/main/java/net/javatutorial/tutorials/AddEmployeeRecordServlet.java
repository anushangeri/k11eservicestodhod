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

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.entity.Employee;

/**
 * Servlet implementation class AddEmployeeRecordServlet
 */
@MultipartConfig
public class AddEmployeeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = EmployeeManagerDAO.getNextVal();
		
		String employeeId = "" + nextVal;
		String idNo = request.getParameter("idNo");
		Integer annualLeave = request.getParameter("annualLeave") != null ? Integer.parseInt(request.getParameter("annualLeave")) : 0;
		Integer annualOutpatientLeave = request.getParameter("annualOutpatientLeave") != null ? Integer.parseInt(request.getParameter("annualOutpatientLeave")) : 0;
		Integer annualHospitalLeave = request.getParameter("annualHospitalLeave") != null ? Integer.parseInt(request.getParameter("annualHospitalLeave")) : 0;
		
		 InputStream inputStream = null; // input stream of the upload file
	        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("uploadFile");

		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

		String message = "did not work";
        Employee v = null;
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            v = new Employee(employeeId, idNo, annualLeave, annualOutpatientLeave, annualHospitalLeave,inputStream,  timestamp, timestamp);

            message = EmployeeManagerDAO.addEmployee(v);
        }
		
		request.setAttribute("responseObj", message);
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

}
