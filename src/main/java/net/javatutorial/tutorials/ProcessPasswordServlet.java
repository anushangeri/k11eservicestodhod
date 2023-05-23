package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javatutorial.DAO.ClientAccountManagerDAO;
import net.javatutorial.entity.ClientAccount;

/**
 * Servlet implementation class PasswordVerifiedServlet
 */
public class ProcessPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(0);
		String idNo = request.getParameter("idNo");
		String password = request.getParameter("psw");
				
		//retrieving the hashed password by DB based on idNo entered by user
		ArrayList<ClientAccount> vList = ClientAccountManagerDAO.retrieveByID(idNo);
		
		boolean verified = false;
		String key = " ";
		String salt = " ";
		ClientAccount c = null;
		if(vList != null && vList.size() > 0 ) {
			c = vList.get(0);
			if(c != null) {
				key = c.getPassword();
				salt = c.getSalt();
				verified = PasswordUtils.verifyPassword(password, key, salt);
			}
		}
		if(verified) {
			session.setAttribute("idNo", c.getIdNo());
			session.setAttribute("name", c.getName());
			session.setAttribute("usertype", c.getAccessType());
			request.setAttribute("onload",true);
			RequestDispatcher rd = request.getRequestDispatcher("/loadDashboard");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("responseObj","Invalid Password or ID");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
