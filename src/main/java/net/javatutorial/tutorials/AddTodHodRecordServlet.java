package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class AddTodHodRecordServlet
 */
public class AddTodHodRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = TodHodManagerDAO.getNextVal();
		
		String recordId = "" + nextVal;
		String officerName = (StringUtils.isEmpty(request.getParameter("officerName"))) ? "" : request.getParameter("officerName").trim();
		String officerIdNo = (StringUtils.isEmpty(request.getParameter("officerIdNo"))) ? "" : request.getParameter("officerIdNo").trim();
		String siteName = (StringUtils.isEmpty(request.getParameter("siteName"))) ? "" : request.getParameter("siteName").trim();
		String shift = (StringUtils.isEmpty(request.getParameter("shift"))) ? "" : request.getParameter("shift").trim();
		String timeInDt = (String) request.getParameter("timeInDt");
		
		Timestamp timestamp = null;
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
		    Date parsedDate = dateFormat.parse(timeInDt);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
				
		TodHodRecord v = new TodHodRecord( recordId, officerName, officerIdNo, siteName, shift,  timestamp);

		String message = TodHodManagerDAO.addTodHod(v);
		
		
		request.setAttribute("message", message);
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
