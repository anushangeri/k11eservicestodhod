package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.TodHodManagerDAO;

/**
 * Servlet implementation class RetrieveTodHodCountServlet
 * it will return the number of days worked in a given period
 * it is not a count of the distinct shifts
 */
public class RetrieveTodHodCountServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idNo = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");
		String from = (StringUtils.isEmpty(request.getParameter("from"))) ? "" : request.getParameter("from");
		String to = (StringUtils.isEmpty(request.getParameter("to"))) ? "" : request.getParameter("to");
		String requestType = (StringUtils.isEmpty(request.getParameter("retrieveTodHodCount_requestType"))) ? "" : request.getParameter("retrieveTodHodCount_requestType");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		int daysWorked = 0;
		
		if(idNo != null && !StringUtils.isEmpty(idNo)) {
			if(requestType != null && !StringUtils.isEmpty(requestType)) {
				if( requestType.equalsIgnoreCase("week")) {
					ZonedDateTime week = zdt.minusWeeks(1);
					Timestamp fromTimestamp = Timestamp.valueOf(week.toLocalDateTime());
					from = fromTimestamp.toString();
					to = timestamp.toString();
				}
				else if(requestType.equalsIgnoreCase("month")) {
					ZonedDateTime month = zdt.minusMonths(1);
					Timestamp fromTimestamp = Timestamp.valueOf(month.toLocalDateTime());
					from = fromTimestamp.toString();
					to = timestamp.toString();
				}
				
				if(from != null && !StringUtils.isEmpty(from) && to != null && !StringUtils.isEmpty(to)) {
					daysWorked = TodHodManagerDAO.retrieveDaysWorkedByIdNo(idNo, from, to);
				}
			}
		}
		
		request.setAttribute("daysWorked", daysWorked);
		RequestDispatcher rd = request.getRequestDispatcher("/loadDashboard");
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
