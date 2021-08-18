package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.entity.Site;

/**
 * Servlet implementation class AddSiteRecordServlet
 */
public class AddSiteRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = SiteManagerDAO.getNextVal();
		
		String siteId = "" + nextVal;
		String siteName = request.getParameter("siteName");
		Integer dayManpower = Integer.parseInt( (String) request.getParameter("dayManpower"));
		Integer nightManpower = Integer.parseInt( (String) request.getParameter("nightManpower"));
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

				
		Site v = new Site( siteId, siteName, dayManpower, nightManpower, timestamp, timestamp);
		
		String message = SiteManagerDAO.addSite(v);
		
		
		request.setAttribute("responseObj", message);
		// Redirect to view site servlet to query all the site again.
		response.sendRedirect("/retrieveAllSiteRecords");
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
