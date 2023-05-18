package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String message = "No payslips available";
		ArrayList<OfficerPayslip> vList = new ArrayList<OfficerPayslip>();
		ArrayList<OfficerPayslip> mList = new ArrayList<OfficerPayslip>();
		ArrayList<Employee> eList = null;
		Employee e = null;
		if(usertype != null && (usertype.toUpperCase().equals("ADMIN") || usertype.toUpperCase().equals("MANAGEMENT") )) {
			vList = OfficerPayslipManagerDAO.retrieveAll();
			mList = MiscDocumentsManagerDAO.retrieveAllJustPayslips();
			if(!vList.isEmpty()) {
				vList.addAll(mList);
			}
			else {
				vList = mList;
			}
			message = "List of payslips";
			request.setAttribute("vList", vList);
		}
		if(usertype != null && (usertype.toUpperCase().equals("OFFICER"))) {
			eList = EmployeeManagerDAO.retrieveEmployeeByID(idNo);
			if(eList != null && eList.size() > 0) {
				e = eList.get(0);
				String employeeId = e.getEmployeeId();
				System.out.println("employeeId: " + employeeId);
				mList = MiscDocumentsManagerDAO.retrieveByEmployeeIDJustPayslip(employeeId);
				System.out.println("mList: " + mList.toString());
			}
			vList = OfficerPayslipManagerDAO.retrieveByOfficeId(idNo);
			if(!vList.isEmpty()) {
				vList.addAll(mList);
			}
			else {
				vList = mList;
			}
			message = "List of payslips accounts";
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
