package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.LeaveManagerDAO;

/**
 * Servlet implementation class DeleteLeaveRecordServlet
 * Deletes leave using leave id
 */
public class DeleteLeaveRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String leaveId = request.getParameter("leaveId");
		String message = LeaveManagerDAO.deleteByLeaveId(leaveId);
		
		request.setAttribute("message", message);
		response.sendRedirect("/viewLeave");
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
