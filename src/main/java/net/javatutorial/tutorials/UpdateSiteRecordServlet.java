package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormatter;

import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.entity.Site;

/**
 * Servlet implementation class UpdateSiteRecordServlet
 */
public class UpdateSiteRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String siteId = (String) request.getParameter("siteId");
		String siteName = request.getParameter("siteName").trim();
		Integer dayManpower = Integer.parseInt( (String) request.getParameter("dayManpower"));
		Integer nightManpower = Integer.parseInt( (String) request.getParameter("nightManpower"));
		
		ArrayList<Site> vList = null;
		Site v = null;
		String message = "Site ID of site is unavailable, please add site record.";
		if(siteId != null && !StringUtils.isEmpty(siteId)) {
			//retrieve Site object
			vList = SiteManagerDAO.retrieveBySiteId(siteId);
			if(vList != null && vList.size() > 0) {
				v = vList.get(0);
			}
			v.setSiteName(siteName);
			v.setDayShiftManpower(dayManpower);
			v.setNightShiftManpower(nightManpower);
			message = SiteManagerDAO.updateSiteDetails(v);

		}
		request.setAttribute("message", message);
		// Redirect to view site servlet to query all the sites again.
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
