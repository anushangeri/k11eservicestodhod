package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.EmployeeManagerDAO;
import net.javatutorial.entity.Employee;
/**
 * Servlet implementation class UploadFilePayslipServlet
 */
@MultipartConfig
public class GenerateKETServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        
        String employeeID = request.getParameter("employeeID");
        Employee v = EmployeeManagerDAO.retrieveEmployeeByEmployeeID(employeeID);
        
        InputStream inputStream = v.getKetDocument(); // input stream of the upload file
        response.setContentType("application/pdf");
        OutputStream output = response.getOutputStream();
        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
        	output.write(buffer, 0, bytesRead);
        }
        output.close();
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
