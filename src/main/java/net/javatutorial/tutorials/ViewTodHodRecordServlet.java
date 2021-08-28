package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.TodHodManagerDAO;
import net.javatutorial.entity.TodHodRecord;

/**
 * Servlet implementation class ViewTodHodRecordServlet
 */
public class ViewTodHodRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("usertype"))) ? "" : (String) request.getSession(false).getAttribute("usertype");
		String idNo = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");
		String name = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("name"))) ? "" : (String) request.getSession(false).getAttribute("name");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String site = (StringUtils.isEmpty(request.getParameter("site"))) ? "" : request.getParameter("site");
		String from = (StringUtils.isEmpty(request.getParameter("from"))) ? "01/01/2012" : request.getParameter("from");
		String to = (StringUtils.isEmpty(request.getParameter("to"))) ? timestamp.toString() : request.getParameter("to");
		String shift = (StringUtils.isEmpty(request.getParameter("shift"))) ? "" : request.getParameter("shift");
		
		String message = "No TOD HOD records available";
		Timestamp fromts = null;
		Timestamp tots = null;
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    Date parsedFromDate = dateFormat.parse(from);
		    fromts = new java.sql.Timestamp(parsedFromDate.getTime());
		    
		    Date parsedToDate = dateFormat.parse(to);
		    tots = new java.sql.Timestamp(parsedToDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		ArrayList<TodHodRecord> vList = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT"))) {
			if(!StringUtils.isEmpty(site)) {
				vList = TodHodManagerDAO.retrieveBySiteTime(shift, site, fromts, tots);
			}
			else {
				vList = TodHodManagerDAO.retrieveByTime(shift, fromts, tots);
			}
			
			message = "List of TOD HOD records";
			request.setAttribute("vList", vList);
		}
		else {
			if(!StringUtils.isEmpty(idNo) && !StringUtils.isEmpty(site)) {
				vList = TodHodManagerDAO.retrieveByIdNoSiteTime(shift, idNo, site, fromts, tots);
			}
			else {
				vList = TodHodManagerDAO.retrieveByIdNoTime(shift, idNo, fromts, tots);
			}
			message = "List of TOD HOD records";
			request.setAttribute("vList", vList);
		}
		
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("viewtodhod.jsp");
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
