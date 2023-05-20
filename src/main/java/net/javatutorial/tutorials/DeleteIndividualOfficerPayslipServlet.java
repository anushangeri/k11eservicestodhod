package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.MiscDocumentsManagerDAO;
import net.javatutorial.DAO.OfficerPayslipManagerDAO;
import net.javatutorial.entity.OfficerPayslip;
/**
 * Servlet implementation class DeleteIndividualOfficerPayslipServlet
 * Payslips can be in the OFFICERPAYSLIPS table or MISCDOCUMENTS table
 * So we need to check and delete from the correct table
 */
public class DeleteIndividualOfficerPayslipServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = "DeleteIndividualOfficerPayslipServlet error or payslip cannot be found.";
        String payslipId = request.getParameter("payslipId");
        OfficerPayslip v = OfficerPayslipManagerDAO.retrieveByPayslipId(payslipId);
        //if document is from the OFFICERPAYSLIPS table delete from there
        if(v != null) {
        	message = OfficerPayslipManagerDAO.deleteRecordByPayslipId(payslipId);
        }
        else {
        	//else delete from MISCDOCUMENTS table
        	message = MiscDocumentsManagerDAO.deleteRecordByDocumentId(payslipId);
        }
        request.setAttribute("deleteIndOfficerPayslipServMsg", message);
		RequestDispatcher rd = request.getRequestDispatcher("/viewOfficerPayslip");
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
