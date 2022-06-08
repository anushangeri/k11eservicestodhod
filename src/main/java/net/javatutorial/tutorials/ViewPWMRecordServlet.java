package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.PWMManagerDAO;
import net.javatutorial.entity.PWMDetails;

/**
 * Servlet implementation class ViewSiteRecordServlet
 */
public class ViewPWMRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String message = "No PWM details available";
		ArrayList<PWMDetails> vList = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT") )) {
			vList = PWMManagerDAO.retrieveAll();
			message = "List of PWM details";
			request.setAttribute("vList", vList);
		}
		
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("pwmManager.jsp");
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
