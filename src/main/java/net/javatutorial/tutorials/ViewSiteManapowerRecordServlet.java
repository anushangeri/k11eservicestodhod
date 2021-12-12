package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.DAO.TodHodManagerDAO;
import net.javatutorial.entity.Manpower;
import net.javatutorial.entity.Site;
import net.javatutorial.entity.TodHodRecord;

/**
 * Servlet implementation class ViewSiteManapowerRecordServlet
 * This servlet will return list of TOD HOD to count the manpower at each site
 */
public class ViewSiteManapowerRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.removeAttribute("mList");	
		request.removeAttribute("manpowerStatusMessage");
		
		String usertype = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("usertype"))) ? "" : (String) request.getSession(false).getAttribute("usertype");
		String idNo = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");

		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String date = (StringUtils.isEmpty(request.getParameter("from"))) ? "01/01/2012" : request.getParameter("from");
		String shift = (StringUtils.isEmpty(request.getParameter("shift"))) ? "" : request.getParameter("shift");
		
		String manpowerStatusMessage = "No site records available";
		Timestamp datets = null;
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    Date parsedFromDate = dateFormat.parse(date);
		    datets = new java.sql.Timestamp(parsedFromDate.getTime());
		    
		} catch(Exception e) { 
			// this generic but you can control another types of exception
		    // look the origin of exception 
		}
		//Step 1: retrieve the count of records based on TOD HOD for each site
		HashMap<String, Integer> vList = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT"))) {
			if(!StringUtils.isEmpty(shift)) {
				vList = TodHodManagerDAO.retrieveByTimeShift(shift, datets);
			}
		}
		//Step 2: retrieve all sites 
		ArrayList<Site> sites = SiteManagerDAO.retrieveAll();
		ArrayList<Manpower> mList = new ArrayList<Manpower>();
		
		
		
		//Step 3: loop through sites
		if(vList != null && vList.size() > 0 && sites != null && sites.size() > 0 ) {
			for(Site s: sites) {
				int requiredManpower = 0;
				int actualManpower = 0;
				if(shift.toUpperCase().equals("DAY")){
					requiredManpower = s.getDayShiftManpower();
					if(vList.containsKey(s.getSiteName())) {
						actualManpower = vList.get(s.getSiteName());
					}
				}
				if(shift.toUpperCase().equals("NIGHT")){
					requiredManpower = s.getNightShiftManpower();
					if(vList.containsKey(s.getSiteName())) {
						actualManpower = vList.get(s.getSiteName());
					}
				}
				Manpower m = new Manpower(s.getSiteName(), requiredManpower, actualManpower);
				mList.add(m);
			}
			manpowerStatusMessage = "Manpower status on " + date + " for " + shift + " shift: ";
		}
		
		
		request.setAttribute("mList", mList);
		request.setAttribute("manpowerStatusMessage", manpowerStatusMessage);
        RequestDispatcher rd = request.getRequestDispatcher("sitemanpowermanager.jsp");
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
