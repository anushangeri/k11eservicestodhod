package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.PWMManagerDAO;
import net.javatutorial.entity.PWMDetails;

/**
 * Servlet implementation class AddSiteRecordServlet
 */
public class AddPWMRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = PWMManagerDAO.getNextVal();
		
		String pwmId = "" + nextVal;
		String year = request.getParameter("year");
		String pwmGrade = request.getParameter("pwmGrade");
		double pwmWages = Double.parseDouble((String) request.getParameter("pwmWages"));
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

				
		PWMDetails v = new PWMDetails( pwmId, year, pwmGrade, pwmWages, timestamp, timestamp);
		
		String message = PWMManagerDAO.addPWM(v);
		
		
		request.setAttribute("responseObj", message);
		// Redirect to view site servlet to query all the site again.
		response.sendRedirect("/retrieveAllPWMRecords");
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
