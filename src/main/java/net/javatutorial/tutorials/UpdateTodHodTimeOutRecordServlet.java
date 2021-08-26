package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.javatutorial.DAO.TodHodManagerDAO;
import net.javatutorial.entity.TodHodRecord;

/**
 * Servlet implementation class UpdateVehicleTimeRecordServlet
 * updates the time out for vehicle record with system time 
 */
public class UpdateTodHodTimeOutRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recordId = (String) request.getParameter("recordId");
		TodHodRecord v = null;
		String message = "Record ID of TOD/HOD is unavailable, please add TOD/HOD record.";
		if(recordId != null && !StringUtils.isEmpty(recordId)) {
			//retrieve TOD/HOD object
			v = TodHodManagerDAO.retrieveByRecordId(recordId);
			//update Vehicle object with current system time as time out
			message = TodHodManagerDAO.updateTodHodDetails(v);
			
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("todhodsearch.jsp");
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
