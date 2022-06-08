package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.javatutorial.DAO.LeaveManagerDAO;
import net.javatutorial.entity.Leave;

/**
 * Servlet implementation class UpdateLeaveRecordServlet
 */
public class UpdateLeaveRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String leaveId = (String) request.getParameter("leaveId");
		String status = (String) request.getParameter("status");
		String remarks = (String) request.getParameter("remarks");
		
		ArrayList<Leave> vList = null;
		Leave v = null;
		String message = "Leave ID of Leave is unavailable, please add leave record.";
		if(leaveId != null && !StringUtils.isEmpty(leaveId)) {
			//retrieve Site object
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
			message = LeaveManagerDAO.updateLeaveDetails(v);

		}
		request.setAttribute("message", message);
		// Redirect to view site servlet to query all the sites again.
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
