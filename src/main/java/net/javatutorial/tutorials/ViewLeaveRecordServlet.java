package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.DAO.LeaveManagerDAO;
import net.javatutorial.entity.Employee;
import net.javatutorial.entity.Leave;

/**
 * Servlet implementation class ViewLeaveRecordServlet
 */
public class ViewLeaveRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String message = "No leave accounts available";
		ArrayList<Leave> vList = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT") )) {
			vList = LeaveManagerDAO.retrieveAll();
			message = "List of leave accounts";
			request.setAttribute("vList", vList);
		}
		if(usertype != null && (usertype.toUpperCase().equals("OFFICER"))) {
			vList = LeaveManagerDAO.retrieveByOfficeIdNo(idNo);
			message = "List of leave accounts";
			request.setAttribute("vList", vList);
		}
		
		
		//Calculate remainder leaves

		if(usertype != null && (usertype.toUpperCase().equals("OFFICER"))) {
			//Step 1: get number of leaves from KET
			ArrayList<Employee> eList = EmployeeManagerDAO.retrieveEmployeeByID(idNo);
			ArrayList<Leave> approvedLeaveList = LeaveManagerDAO.retrieveApprovedLeavesByOfficeIdNo(idNo);
			Employee e = null;
			int paidAnnualLeavePerYear = 0;
			int paidOutpatientSickLeavePerYear = 0;
			int paidHospitalisationLeavePerYear = 0;
			
			int totalAnnualLeaveTaken = 0;
			int totalOutpatientSickLeaveTaken = 0;
			int totalHospitalisationLeaveTaken = 0;
			
			if(eList != null && eList.size() > 0) {
				e = eList.get(0);
				paidAnnualLeavePerYear = e.getPaidAnnualLeavePerYear();
				paidOutpatientSickLeavePerYear = e.getPaidOutpatientSickLeavePerYear();
				paidHospitalisationLeavePerYear = e.getPaidHospitalisationLeavePerYear();
			}
			//Step 2: loop through the leaves and sum all the leaves taken so far for the year
			for(Leave v : approvedLeaveList) {
				if(v.getLeaveType().equalsIgnoreCase("ANNUAL LEAVE")) {
					totalAnnualLeaveTaken = totalAnnualLeaveTaken + v.getNumDaysLeave();
				}
				if(v.getLeaveType().equalsIgnoreCase("ANNUAL SICK LEAVE")) {
					totalOutpatientSickLeaveTaken = totalOutpatientSickLeaveTaken + v.getNumDaysLeave();
				}
				if(v.getLeaveType().equalsIgnoreCase("ANNUAL HOSPITAL LEAVE")) {
					totalHospitalisationLeaveTaken = totalHospitalisationLeaveTaken + v.getNumDaysLeave();
					
				}
			}
			request.setAttribute("annualLeave", paidAnnualLeavePerYear - totalAnnualLeaveTaken);
			request.setAttribute("annualSickLeave", paidOutpatientSickLeavePerYear - totalOutpatientSickLeaveTaken);
			request.setAttribute("annualHospitalLeave", paidHospitalisationLeavePerYear - totalHospitalisationLeaveTaken);
		}
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("viewLeave.jsp");
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
