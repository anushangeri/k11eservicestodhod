package net.javatutorial.tutorials;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.javatutorial.DAO.PayslipManagerDAO;
import net.javatutorial.entity.AttendanceUploadFile;
/**
 * Servlet implementation class UploadFilePayslipServlet
 */
@MultipartConfig
public class UploadFilePayslipServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        InputStream inputStream = null; // input stream of the upload file
        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("uploadFile");
        String message = "did not work";
        AttendanceUploadFile v = null;
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            v = new AttendanceUploadFile(inputStream);
            message = PayslipManagerDAO.uploadPayslipFile(v);
        }
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("payslipGenerator.jsp");
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
