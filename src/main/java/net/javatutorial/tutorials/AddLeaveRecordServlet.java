package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.LeaveManagerDAO;
import net.javatutorial.entity.Leave;

/**
 * Servlet implementation class AddLeaveRecordServlet
 */
public class AddLeaveRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = LeaveManagerDAO.getNextVal();
		
		String leaveID = "" + nextVal;
		String idNo = (StringUtils.isEmpty(request.getParameter("idNo"))) ? "" : request.getParameter("idNo").trim();
		String leaveType = (StringUtils.isEmpty(request.getParameter("leaveType"))) ? "" : request.getParameter("leaveType").trim();
		String leaveStartDate = (StringUtils.isEmpty(request.getParameter("leaveStartDate"))) ? "" : request.getParameter("leaveStartDate").trim();
		String leaveEndDate = (StringUtils.isEmpty(request.getParameter("leaveEndDate"))) ? "" : request.getParameter("leaveEndDate").trim();
		String remarks = (StringUtils.isEmpty(request.getParameter("remarks"))) ? "" : request.getParameter("remarks").trim();
		
		Timestamp leaveStartTimestamp = null;
		Timestamp leaveEndTimestamp = null;
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date leaveStartParseDate = dateFormat.parse(leaveStartDate);
		    leaveStartTimestamp = new java.sql.Timestamp(leaveStartParseDate.getTime());
		    
		    Date leaveEndParseDate = dateFormat.parse(leaveEndDate);
		    leaveEndTimestamp = new java.sql.Timestamp(leaveEndParseDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}

		long numDaysLeaveLong = Duration.between(leaveStartTimestamp.toInstant(), leaveEndTimestamp.toInstant()).toDays();
		int numDaysLeave = (int) numDaysLeaveLong;
		Leave v = new Leave(leaveID, idNo, leaveType, leaveStartTimestamp, leaveEndTimestamp,  numDaysLeave, "Pending", null, remarks);

		String message = LeaveManagerDAO.addLeave(v);
		
		
		request.setAttribute("message", message);
		response.sendRedirect("/viewLeave");  
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
