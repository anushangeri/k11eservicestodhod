package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.LeaveTblDAO;

/**
 * Servlet implementation class CLeaveTblServlet
 * To create table Leave
 */
public class CLeaveTblServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String responseObj = LeaveTblDAO.createLeaveTbl();
		
		
		request.setAttribute("responseObj", responseObj);
        RequestDispatcher rd = request.getRequestDispatcher("managedatabase.jsp");
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
