package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.DAO.LeaveManagerDAO;
import net.javatutorial.entity.Employee;
import net.javatutorial.entity.Leave;

/**
 * Servlet implementation class UpdateLeaveRecordServlet
 */
public class UpdateLeaveRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String leaveId = (String) request.getParameter("leaveId");
		String status = (String) request.getParameter("status");
		String remarks = (String) request.getParameter("remarks");
		
		ArrayList<Leave> vList = null;
		Leave v = null;
		String message = "Leave ID of Leave is unavailable, please add leave record.";
		String checkLeave = checkLeave(leaveId);
		if(checkLeave.equalsIgnoreCase("Approve") && status.equalsIgnoreCase("Approve")) {
			if(leaveId != null && !StringUtils.isEmpty(leaveId)) {
				vList = LeaveManagerDAO.retrieveByLeaveId(leaveId);
				if(vList != null && vList.size() > 0) {
					v = vList.get(0);
				}
				if(status != null) {
					v.setRequestStatus(status);
				}
				if(remarks != null) {
					v.setRemarks(remarks);
				}
				if(idNo != null) {
					v.setApprovingSupervisor(idNo);
				}
				message = LeaveManagerDAO.updateLeaveDetails(v);

			}
		}
		else if(status.equalsIgnoreCase("Reject")) {
			if(leaveId != null && !StringUtils.isEmpty(leaveId)) {
				vList = LeaveManagerDAO.retrieveByLeaveId(leaveId);
				if(vList != null && vList.size() > 0) {
					v = vList.get(0);
				}
				if(status != null) {
					v.setRequestStatus(status);
				}
				if(remarks != null) {
					v.setRemarks(remarks);
				}
				if(idNo != null) {
					v.setApprovingSupervisor(idNo);
				}
				message = LeaveManagerDAO.updateLeaveDetails(v);

			}
		}
		else {
			message = "Not enough leave";
		}
		
		request.setAttribute("updateLeaveRecordServMsg", message);
		// Redirect to view leave servlet to query all the leaves again.
		RequestDispatcher rd = request.getRequestDispatcher("/viewLeave");
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
	public String checkLeave(String leaveId) {
		ArrayList<Leave> leaveList = LeaveManagerDAO.retrieveByLeaveId(leaveId);
		String checkLeave = "Approve";
		Leave getLeaveToBeApproved = null;
		
		if(leaveList != null && leaveList.size() > 0) {
			getLeaveToBeApproved = leaveList.get(0);
		}
		String idNo = getLeaveToBeApproved.getIdNo();
		String leaveType = getLeaveToBeApproved.getLeaveType();
		int leaveToApprove = getLeaveToBeApproved.getNumDaysLeave();
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
		
		int checkLeaveNumber = 0;
		
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
		if(leaveType.equalsIgnoreCase("ANNUAL LEAVE")) {
			checkLeaveNumber = paidAnnualLeavePerYear - totalAnnualLeaveTaken - leaveToApprove;
		}
		if(leaveType.equalsIgnoreCase("ANNUAL SICK LEAVE")) {
			checkLeaveNumber = paidOutpatientSickLeavePerYear - totalOutpatientSickLeaveTaken - leaveToApprove;
		}
		if(leaveType.equalsIgnoreCase("ANNUAL HOSPITAL LEAVE")) {
			checkLeaveNumber = paidHospitalisationLeavePerYear - totalHospitalisationLeaveTaken - leaveToApprove;
			
		}
		if(checkLeaveNumber < 0) {
			checkLeave = "Reject";
		}
		
		return checkLeave;
	}
}
