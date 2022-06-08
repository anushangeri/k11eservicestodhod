package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.DAO.TodHodManagerDAO;
import net.javatutorial.entity.Site;
import net.javatutorial.entity.TodHodRecord;

/**
 * Servlet implementation class LoadDashboardServlet
 */
public class LoadDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String idNo = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");
		
		//retrieving on idNo entered by user
		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
		ArrayList<TodHodRecord> todRecords = TodHodManagerDAO.retrieveByLatestTod(idNo);
		
		session.setAttribute("siteDropdown", siteDropdown);
		request.setAttribute("vList", todRecords);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
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
