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

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.DAO.MiscDocumentsManagerDAO;
import net.javatutorial.entity.Employee;
import net.javatutorial.entity.MiscDocuments;

/**
 * Servlet implementation class ViewMiscDocumentsRecordServlet
 * viewMiscDocs
 * For retrieveALL and retrieveAll30Days - it will be a download trigger #todo as of 20240420
 */
public class ViewMiscDocumentsRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		
		String recordsToReceive = (String) request.getParameter("recordsToReceive");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String message = "No miscellaneous documents available";
		ArrayList<MiscDocuments> vList = null;
		ArrayList<Employee> eList = null;
		Employee e = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT") )) {
			if((StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null)) {
				vList = MiscDocumentsManagerDAO.retrieveAllLast5Days(timestamp);
				message = "List of miscellaneous documents";
				request.setAttribute("vList", vList);
			}
			else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("currdate") ) {
				vList = MiscDocumentsManagerDAO.retrieveAllCurrentDay(timestamp);
				message = "List of miscellaneous documents";
				request.setAttribute("vList", vList);
			}
			else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("5days") ) {
				vList = MiscDocumentsManagerDAO.retrieveAllLast5Days(timestamp);
				message = "List of miscellaneous documents";
				request.setAttribute("vList", vList);
			}
			else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("10days") ) {
				vList = MiscDocumentsManagerDAO.retrieveAllLast10Days(timestamp);
				message = "List of miscellaneous documents";
				request.setAttribute("vList", vList);
			}
			else {
				//to do: download function for 30day and all - no display, auto download.
				message = "coming soon: download function for 30days before and all because of out of memory issue.";
			}
			
		}
		if(usertype != null && (usertype.toUpperCase().equals("OFFICER"))) {
			eList = EmployeeManagerDAO.retrieveEmployeeByID(idNo);
			if(eList != null && eList.size() > 0) {
				e = eList.get(0);
				String employeeId = e.getEmployeeId();
				vList = MiscDocumentsManagerDAO.retrieveByEmployeeID(employeeId);
				message = "List of miscellaneous documents";
				request.setAttribute("vList", vList);
			}
			
		}
		request.setAttribute("viewMiscDocsServMsg", message);
        RequestDispatcher rd = request.getRequestDispatcher("viewMiscDocuments.jsp");
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
