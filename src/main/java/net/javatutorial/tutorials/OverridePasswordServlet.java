package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.ClientAccountManagerDAO;
import net.javatutorial.entity.ClientAccount;

/**
 * Servlet implementation class OverridePasswordServlet - to reset client password to default
 */
public class OverridePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idNo = request.getParameter("idNo");
				
		//retrieving the hashed password by DB based on idNo entered by user
		ArrayList<ClientAccount> vList = ClientAccountManagerDAO.retrieveByID(idNo);
		String salt = " ";
		ClientAccount c = null;
		if(vList != null && vList.size() > 0 ) {
			c = vList.get(0);
			if(c != null) {
				salt = PasswordUtils.generateSalt(512).get();
				String hashedPassword = PasswordUtils.hashPassword("P@ssw0rd", salt).get();
				c.setSalt(salt);
				c.setPassword(hashedPassword);
				String message = ClientAccountManagerDAO.updateClientAccountPassword(c);
				request.setAttribute("responseObj", message);
				response.sendRedirect("/retrieveAllClientRecords");			
			}
		}
		else {
			request.setAttribute("responseObj", "Could not override password.");
			RequestDispatcher rd = request.getRequestDispatcher("clientManager.jsp");
			rd.forward(request, response);
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
