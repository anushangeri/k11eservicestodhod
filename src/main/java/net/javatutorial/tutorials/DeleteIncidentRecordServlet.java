package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.IncidentManagerDAO;;

/**
 * Servlet implementation class DeleteIncidentRecordServlet
 * Deletes incident using incidentId
 */
public class DeleteIncidentRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String incidentId = request.getParameter("incidentId");
		String message = IncidentManagerDAO.deleteByIncidentId(incidentId);
		
		request.setAttribute("message", message);
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
