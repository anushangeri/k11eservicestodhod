package net.javatutorial.tutorials;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.javatutorial.DAO.TodHodManagerDAO;
import net.javatutorial.entity.TodHodRecord;

/**
 * Servlet implementation class ArchiveVisitorRecordsServlet
 */
public class ArchiveTodHodRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<TodHodRecord> aList = TodHodManagerDAO.retrieveAll();
		System.out.println("retrived attendance successful");
		
		LocalDate localDate = LocalDate.now(ZoneId.of("GMT+08:00"));
		String fileName = "todhod_"+ localDate +".xls";
		
		// workbook object
		XSSFWorkbook workbook = new XSSFWorkbook();
		// spreadsheet object
		XSSFSheet spreadsheet = workbook.createSheet(" TOD HOD Data ");
		// creating a row object

		XSSFRow header = spreadsheet.createRow(0);
		header.createCell(0).setCellValue("RecordID");
		header.createCell(1).setCellValue("Officer Name");
		header.createCell(2).setCellValue("Office ID No");
		header.createCell(3).setCellValue("Site Name");
		header.createCell(4).setCellValue("Shift");
		header.createCell(5).setCellValue("Time In Date");
		header.createCell(6).setCellValue("Time Out Date");

		XSSFRow row;
		int rowid = 1;

		System.out.println("writing the data into the sheets...");
		// writing the data into the sheets...
		for (TodHodRecord a : aList) {

			row = spreadsheet.createRow(rowid++);

			row.createCell(0).setCellValue(a.getRecordId());
			row.createCell(1).setCellValue(a.getOfficerName());
			row.createCell(2).setCellValue(a.getOfficerIdNo());
			row.createCell(3).setCellValue(a.getSiteName());
			row.createCell(4).setCellValue(a.getShift());
			row.createCell(5).setCellValue(a.getTimeInDt().toString());
			if (a.getTimeOutDt() != null) {
				row.createCell(6).setCellValue(a.getTimeOutDt().toString());
			}
		}
		System.out.println("writing finished, sending email ...");
		// .xlsx is the format for Excel Sheets...
		// writing the workbook into the file...
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
		workbook.close();
		// out.close();

		String to = "k11.sivalingam@gmail.com";// change accordingly
		final String user = "shangeri.sivalingam@k11.com.sg";// change accordingly
		final String password = "Sh@ngeri94";// change accordingly

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.k11.com.sg");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "25");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("K11 eService Attendance");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(
					"K11 eService Attendance, there is 60 days worth of data in K11 eService now. The attached excel has the full set of data.");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileName);

			// Now use your ByteArrayDataSource as
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message
			Transport.send(message);

			System.out.println("email sent....");
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		String message = TodHodManagerDAO.deleteAll();
		System.out.println(message);
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
