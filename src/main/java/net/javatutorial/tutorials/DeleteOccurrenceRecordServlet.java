package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.ClientAccountManagerDAO;
import net.javatutorial.DAO.OccurrenceManagerDAO;;

/**
 * Servlet implementation class DeleteClientAccountServlet
 * Deletes client account using client account id
 */
public class DeleteOccurrenceRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = request.getParameter("occurrenceId");
		String message = OccurrenceManagerDAO.deleteByOccurrenceId(accountId);
		
		request.setAttribute("message", message);
		response.sendRedirect("/manageOccurrenceRecord");
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
