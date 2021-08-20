package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String officerName = request.getParameter("officerName");
		String officerIdNo = (String) request.getParameter("officerIdNo");
		String siteName = (String) request.getParameter("siteName");
		String shift = (String) request.getParameter("shift");
		String timeInDt = (String) request.getParameter("timeInDt");
		
		Timestamp ts = Timestamp.valueOf(timeInDt);
		
				
		TodHodRecord v = new TodHodRecord( recordId, officerName, officerIdNo, siteName, shift,  ts, null);
		System.out.println(v.toString());
		String message = TodHodManagerDAO.addTodHod(v);
		
		
		request.setAttribute("responseObj", message);
		RequestDispatcher rd = request.getRequestDispatcher("todhodsearch.jsp");
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
