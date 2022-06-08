package net.javatutorial.tutorials;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aspose.cells.PageOrientationType;
import com.aspose.cells.PageSetup;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import net.javatutorial.DAO.OfficerPayslipManagerDAO;
import net.javatutorial.entity.OfficerPayslip;
/**
 * Servlet implementation class DownloadOfficerPayslipServlet
 */
public class DownloadOfficerPayslipServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        
        String payslipId = request.getParameter("payslipId");
        OfficerPayslip v = OfficerPayslipManagerDAO.retrieveByPayslipId(payslipId);
        
        InputStream inputStream = v.getPayslip(); // input stream of the upload file
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename="+ v.getOfficerIdNo() +".pdf");
        OutputStream output = response.getOutputStream();

        
        // Create Workbook to load Excel file
        Workbook workbook;
		try {
			workbook = new Workbook(inputStream);
			PageSetup pageSetup = workbook.getWorksheets().get(0).getPageSetup();
			pageSetup.setOrientation(PageOrientationType.LANDSCAPE);
			 // Save the document in PDF format
	        workbook.save("Excel-to-PDF.pdf", SaveFormat.PDF);
	        
	        //Save to byte array output stream
	        ByteArrayOutputStream baout = new ByteArrayOutputStream();
	        workbook.save(baout, SaveFormat.PDF);

	        //Get the byte[] in which output pdf is saved
	        byte[] btsPdf = baout.toByteArray();
	        output.write(btsPdf, 0, btsPdf.length);
	        output.flush();
	        output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       

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