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
import net.javatutorial.DAO.OfficerPayslipManagerDAO;
import net.javatutorial.entity.Employee;
import net.javatutorial.entity.OfficerPayslip;

/**
 * Servlet implementation class ViewSiteRecordServlet
 */
public class ViewOfficerPayslipRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String recordsToReceive = (String) request.getParameter("recordsToReceive");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String message = "No payslips available";
		ArrayList<OfficerPayslip> vList = new ArrayList<OfficerPayslip>();
		ArrayList<OfficerPayslip> mList = new ArrayList<OfficerPayslip>();
		ArrayList<Employee> eList = null;
		Employee e = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT") )) {
			if((StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) || recordsToReceive.equals("30days")) {
				vList = OfficerPayslipManagerDAO.retrieveAll30Days(timestamp);
				mList = MiscDocumentsManagerDAO.retrieveAllJustPayslips30Days(timestamp);
				if(!vList.isEmpty()) {
					vList.addAll(mList);
				}
				else {
					vList = mList;
				}
			}
			else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("currdate") ) {
				vList = OfficerPayslipManagerDAO.retrieveAllCurrentDay(timestamp);
				mList = MiscDocumentsManagerDAO.retrieveAllJustPayslipsCurrDate(timestamp);
				if(!vList.isEmpty()) {
					vList.addAll(mList);
				}
				else {
					vList = mList;
				}
			}
			else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("all") ) {
				vList = OfficerPayslipManagerDAO.retrieveAll();
				mList = MiscDocumentsManagerDAO.retrieveAllJustPayslips();
				if(!vList.isEmpty()) {
					vList.addAll(mList);
				}
				else {
					vList = mList;
				}
			}
			
		}
		if(usertype != null && (usertype.toUpperCase().equals("OFFICER"))) {
			eList = EmployeeManagerDAO.retrieveEmployeeByID(idNo);
			if(eList != null && eList.size() > 0) {
				e = eList.get(0);
				String employeeId = e.getEmployeeId();
				if((StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) || recordsToReceive.equals("30days")) {
					mList = MiscDocumentsManagerDAO.retrieveByEmployeeIDJustPayslip30Days(employeeId, timestamp);
				}
				else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("currdate") ) {
					mList = MiscDocumentsManagerDAO.retrieveByEmployeeIDJustPayslipCurrDate(employeeId, timestamp);
				}
				else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("all") ) {
					mList = MiscDocumentsManagerDAO.retrieveByEmployeeIDJustPayslip(employeeId);
				}
			}
			
			if((StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) || recordsToReceive.equals("30days")) {
				vList = OfficerPayslipManagerDAO.retrieveByOfficeId30Days(idNo, timestamp);
			}
			else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("currdate") ) {
				vList = OfficerPayslipManagerDAO.retrieveByOfficeIdCurrDate(idNo, timestamp);
			}
			else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("all") ) {
				vList = OfficerPayslipManagerDAO.retrieveByOfficeId(idNo);
			}
			if(!vList.isEmpty()) {
				vList.addAll(mList);
			}
			else {
				vList = mList;
			}
		}
		if(vList != null && vList.size() > 0) {
			message = "List of payslips";
			request.setAttribute("vList", vList);
		}
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("viewOfficerPayslip.jsp");
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
