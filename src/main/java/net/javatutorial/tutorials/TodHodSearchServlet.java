package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class TodHodSearchServlet
 */
public class TodHodSearchServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String site = request.getParameter("site");
		String idNo = request.getParameter("idNo");
		String shift = request.getParameter("shift");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
//		to make all UPPERCASE for comparing later
		if(!StringUtils.isEmpty(idNo)){
			idNo = idNo.trim();
			if(!StringUtils.isEmpty(idNo)){
				idNo = idNo.toUpperCase();
			}
		}
		ArrayList<String> responseObj = new ArrayList<String>();
		responseObj.add(site);
		responseObj.add(idNo);
		responseObj.add(from);
		responseObj.add(to);
		request.setAttribute("responseObj", responseObj);
		RequestDispatcher rd = null;
		if (shift.equals("Day")){
			rd = request.getRequestDispatcher("todhodday.jsp");
		}
		else{
			rd = request.getRequestDispatcher("todhodnight.jsp");
		}
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
