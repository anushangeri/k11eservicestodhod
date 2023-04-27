package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.entity.Employee;;

/**
 * Servlet implementation class DeleteKETDocumentServlet
 * Deletes employee KET document using employee id
 */
public class DeleteKETDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeId = request.getParameter("employeeID");
		String message = "KET document does not exists. Please verify.";
		//Step 1: for this delete func. first need to retrieve the KET inputstream
		Employee e = EmployeeManagerDAO.retrieveEmployeeByEmployeeID(employeeId);
		InputStream ketDocument = ((e == null) || e.getKetDocument() == null ? null : e.getKetDocument());
		if( ketDocument == null) {
			request.setAttribute("deleteKETDocServMsg", message);
			//Step 2a: Redirect to view employee servlet to query all the employee again (download button should be greyed out)
			response.sendRedirect("/viewEmp");
		}
		else {
			//Step 2b: set request parameters for AddMiscDocumentsRecordServlet
			request.setAttribute("employeeId", employeeId);
			request.setAttribute("description", "Archived KET for " + e.getIdNo());
			request.setAttribute("ketDocument", ketDocument);
			//Step 3: set status to deleteKET and call MiscDocumentsManagerDAO.AddMiscDocumentsRecordServlet
			request.setAttribute("status", "deleteKET");
			RequestDispatcher rd = request.getRequestDispatcher("/addMiscDocsRecord");
			rd.forward(request,response);
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
