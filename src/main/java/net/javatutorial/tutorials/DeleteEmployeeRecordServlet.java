package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.EmployeeManagerDAO;;

/**
 * Servlet implementation class DeleteEmployeeRecordServlet
 * Deletes employee record using employee id
 * TO-DO: NEED TO HANDLE MISC DOCS AND PAYSLIPS AND CLIENT ACCOUNT AND LEAVES WHEN YOU DELETE EMPLOYEE
 */
public class DeleteEmployeeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeId = request.getParameter("employeeId");
		String message = EmployeeManagerDAO.deleteByEmployeeId(employeeId);
		
		request.setAttribute("message", message);
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
