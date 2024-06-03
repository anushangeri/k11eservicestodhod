package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.IncidentManagerDAO;
import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.entity.Incident;
import net.javatutorial.entity.Site;

/**
 * Servlet implementation class ViewEditOccurrenceRecordServlet
 * To retrieve latest vehicle record to populate in viewEditOccurrenceRecord.jsp
 * 
 */
public class ViewEditIncidentRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String incidentId = request.getParameter("incidentId");
		String actionStatus = request.getParameter("actionStatus");
		InputStream file = null;
		ArrayList<Incident> vList = null;
		Incident v = null;
		
		vList = IncidentManagerDAO.retrieveByIncidentId(incidentId);
		if(vList != null && vList.size() > 0) {
			v = vList.get(0);
			file = v.getFile();
		}
		//getting all the dropdown
		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
		System.out.println(v.toString());
		request.setAttribute("incidentRecord", v);
		request.setAttribute("fileStreams", file);
		request.setAttribute("siteDropdown", siteDropdown);
		request.setAttribute("actionStatus", actionStatus);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewIncident.jsp");
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
