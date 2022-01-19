package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.DAO.OccurrenceManagerDAO;
import net.javatutorial.entity.Site;
import net.javatutorial.entity.Occurrence;

/**
 * Servlet implementation class ViewEditOccurrenceRecordServlet
 * To retrieve latest vehicle record to populate in viewEditOccurrenceRecord.jsp
 * 
 */
public class ViewEditOccurrenceRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("usertype"))) ? "" : (String) request.getSession(false).getAttribute("usertype");
		String idNo = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");

		String occurrenceId = request.getParameter("occurrenceId");
		String actionStatus = request.getParameter("actionStatus");
		
		ArrayList<Occurrence> vList = null;
		Occurrence v = null;
		
		vList = OccurrenceManagerDAO.retrieveByOccurrenceId(occurrenceId);
		if(vList != null && vList.size() > 0) {
			v = vList.get(0);
		}
		//getting all the dropdown
		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
		
		request.setAttribute("occurrenceRecord", v);
		request.setAttribute("siteDropdown", siteDropdown);
		request.setAttribute("actionStatus", actionStatus);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewEditOccurrenceRecord.jsp");
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
