package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String name = (String) request.getSession(false).getAttribute("name");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String site = request.getParameter("siteName");
		String from = (request.getParameter("from") == null) ? "01/01/2012 00:00" : request.getParameter("from");
		String to = (request.getParameter("to") == null) ? timestamp.toString() : request.getParameter("to");
		String shift = request.getParameter("shift");
		
		String message = "No TOD HOD accounts available";
		
		ArrayList<TodHodRecord> vList = null;
		if(usertype != null && usertype.toUpperCase().equals("ADMIN")) {
			if(!StringUtils.isEmpty(site)) {
				vList = TodHodManagerDAO.retrieveBySiteTime(shift, site, from, to);
			}
			else {
				vList = TodHodManagerDAO.retrieveByTime(shift, from, to);
			}
			
			message = "List of TOD HOD accounts";
			request.setAttribute("vList", vList);
		}
		else {
			if(!StringUtils.isEmpty(idNo) && !StringUtils.isEmpty(site)) {
				vList = TodHodManagerDAO.retrieveByIdNoSiteTime(shift, idNo, site, from, to);
			}
			else {
				vList = TodHodManagerDAO.retrieveByIdNoTime(shift, idNo, from, to);
			}
			message = "List of TOD HOD accounts";
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
