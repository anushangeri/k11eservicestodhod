package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.entity.Employee;

/**
 * Servlet implementation class ViewEmployeeRecordServlet
 * viewEmp
 */
public class ViewEmployeeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String message = "No employee accounts available";
		ArrayList<Employee> vList = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT") )) {
			vList = EmployeeManagerDAO.retrieveAll();
			message = "List of employee accounts";
			request.setAttribute("vList", vList);
		}
		if(usertype != null && (usertype.toUpperCase().equals("OFFICER"))) {
			vList = EmployeeManagerDAO.retrieveEmployeeByID(idNo);
			message = "List of employee accounts";
			request.setAttribute("vList", vList);
		}
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("viewEmployee.jsp");
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
