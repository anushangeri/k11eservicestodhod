package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.MiscDocumentsManagerDAO;;

/**
 * Servlet implementation class DeleteMiscDocumentRecordServlet
 * Deletes misc document using document id
 */
public class DeleteMiscDocumentRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String documentId = request.getParameter("documentId");
		String message = MiscDocumentsManagerDAO.deleteRecordByDocumentId(documentId);
		
		request.setAttribute("message", message);
		response.sendRedirect("/viewMiscDocs");
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
