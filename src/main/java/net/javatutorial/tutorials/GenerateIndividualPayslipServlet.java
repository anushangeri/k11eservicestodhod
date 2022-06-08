package net.javatutorial.tutorials;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gdata.util.common.base.StringUtil;

import net.javatutorial.DAO.OfficerPayslipManagerDAO;
import net.javatutorial.DAO.PWMManagerDAO;
import net.javatutorial.DAO.PayslipManagerDAO;
import net.javatutorial.entity.AttendanceUploadFile;
import net.javatutorial.entity.OfficerPayslip;
import net.javatutorial.entity.PWMDetails;

/**
 * Servlet implementation class GenerateIndividualPayslipServlet
 * https://dev.to/codesharing/converting-excel-to-pdf-in-java-application-474i
 * 
 * the difference with this and createPayslip is that this will generate a PDF and append to each of the office ID
 */
public class GenerateIndividualPayslipServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	private ExecutorService executor  = Executors.newFixedThreadPool(1);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			executePayslip();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message", "Payslip is being generated, check back in a 5 minutes.");
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
	
	public String checkCellType(Cell c, FormulaEvaluator formulaEvaluator) {
		String val = "0.0";
		if(c != null) {
			switch(formulaEvaluator.evaluateInCell(c).getCellType())  
			{  
				case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
				//getting the value of the cell as a number  
				val = c.getNumericCellValue() + "";
				break;  
				case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
				//getting the value of the cell as a string
				val =  c.getStringCellValue();
				break;  
			} 
		}
		return val != null || !StringUtil.isEmpty(val) ? val.trim() : "0.0";
	}

	private void executePayslip() throws Exception {
	    
	    executor.submit(new Runnable(){
	         public void run() {
	        	AttendanceUploadFile p = PayslipManagerDAO.retrieveAll();
	     		SimpleDateFormat display = new SimpleDateFormat("dd-MMM-yyyy");
	     		SimpleDateFormat formatter5 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy"); 
	     		SimpleDateFormat monthYearDisplay = new SimpleDateFormat("MMM-yy");
	     		String decimalPattern = "^-?[0-9]\\d*(\\.\\d+)?$";  

	     		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
	     		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
	     		String currentDt = display.format(timestamp);
	     		
	     		LocalDate localDate = LocalDate.now(ZoneId.of("GMT+08:00"));

	     		String message = "The following officers do not comply PWM. Please rectify and upload payslip details for there officers ONLY: ";
	     		System.out.println("reading each row in excel file upload...");		
	     		try {

	     			//for payslip email attachment:

	     			
	     			// obtaining input bytes from a file
	     			InputStream fis = p.getPayslip();
	     			// creating workbook instance that refers to .xls file
	     			XSSFWorkbook wb = new XSSFWorkbook(fis);
	     			// creating a Sheet object to retrieve the object
	     			XSSFSheet sheet = wb.getSheetAt(0);

	     			//evaluating cell type   
	     			FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
	     			//creating a list of payslips
	     			for (Row row : sheet) // iteration over row using for each loop
	     			{
	     				String idNo = checkCellType(row.getCell(52), formulaEvaluator);
	     				//check PWM
	     				String basicSalaryPerMonth = checkCellType(row.getCell(41), formulaEvaluator);
	     				String securityGrade = checkCellType(row.getCell(47), formulaEvaluator);
	     				boolean generatePayslip = false;
	     				//only make payslip if there is a ID in column BA (53)
	     				PWMDetails pwm = PWMManagerDAO.retrieveByYearAndGrade(securityGrade);
	     				if(pwm != null) {
	     					if(Pattern.matches(decimalPattern, basicSalaryPerMonth) && Double.parseDouble(basicSalaryPerMonth) >= pwm.getPwmWages()) {
	     						generatePayslip = true;
	     						
	     					}
	     				}
	     				if(idNo != null && !StringUtils.isEmpty(idNo)
	     						&& !idNo.equalsIgnoreCase("Emp ID") && !idNo.equalsIgnoreCase("0.0") && generatePayslip) {
	     					
	     					String name = checkCellType(row.getCell(53), formulaEvaluator);
	     					
	     					
	     					String payslipMonth = " ";
	     					String empStatus = checkCellType(row.getCell(44), formulaEvaluator);
	     					String salaryDate = " ";
	     					String otHours = checkCellType(row.getCell(35), formulaEvaluator);
	     					Date payslipMonthDt = null;
	     					String payslipMonthDisplay = " ";
	     					Date salaryDateDt = null;
	     					String salaryDateDisplay = " ";
	     					
	     					switch(formulaEvaluator.evaluateInCell(row.getCell(46)).getCellType())  
	     					{  
	     						case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type   
	     							payslipMonth = row.getCell(46).getDateCellValue() + "";
	     							payslipMonthDt = formatter5.parse(payslipMonth);
	     							payslipMonthDisplay = monthYearDisplay.format(payslipMonthDt);
	     							
	     						break;
	     						case Cell.CELL_TYPE_STRING:   //field that represents string cell type   
	     							payslipMonth = row.getCell(46).getStringCellValue() == null ? " " : row.getCell(46).getStringCellValue();
	     						break;
	     					}  
	     					switch(formulaEvaluator.evaluateInCell(row.getCell(45)).getCellType())  
	     					{  
	     						case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type   
	     							salaryDate = row.getCell(45).getDateCellValue() + "";
	     							salaryDateDt = formatter5.parse(salaryDate);
	     							salaryDateDisplay = monthYearDisplay.format(salaryDateDt);
	     						break;
	     						case Cell.CELL_TYPE_STRING:   //field that represents string cell type   
	     							salaryDate = row.getCell(45).getStringCellValue() == null ? " " : row.getCell(45).getStringCellValue();
	     						break;
	     					}  
	     					
	     					String age = checkCellType(row.getCell(6), formulaEvaluator);
	     					
	     					String fileName = idNo + "_payslip_"+ localDate +".xlsx";
	     					System.out.println("reading the numbers...");		
	     					String basicSalaryPerHour = checkCellType(row.getCell(43), formulaEvaluator);
	     					String basicSalaryPerDay = checkCellType(row.getCell(42), formulaEvaluator);
	     					
	     					double OTRate = Double.parseDouble(basicSalaryPerHour) * 1.5;
	     					String bank = checkCellType(row.getCell(39), formulaEvaluator);
	     					String bankNo = checkCellType(row.getCell(38), formulaEvaluator);
	     					
	     					String salaryBasic = basicSalaryPerMonth;
	     					String paymentType = checkCellType(row.getCell(37), formulaEvaluator);
	     					String advance = checkCellType(row.getCell(18), formulaEvaluator);
	     					
	     					String dailyRateGross = checkCellType(row.getCell(36), formulaEvaluator);
	     					String numDaysWorked = "0.0";
	     					//to get which shift to print payslip
	     					String payslipShift = checkCellType(row.getCell(121), formulaEvaluator);
	     					if(payslipShift.equalsIgnoreCase("Day")) {
	     						numDaysWorked = checkCellType(row.getCell(118), formulaEvaluator);	
	     					}
	     					if(payslipShift.equalsIgnoreCase("Night")) {
	     						numDaysWorked = checkCellType(row.getCell(119), formulaEvaluator);
	     					}
	     					double salary12HrsDailyRatedGross = Double.parseDouble(dailyRateGross) *  Double.parseDouble(numDaysWorked);
	     					String lessLoan = checkCellType(row.getCell(16), formulaEvaluator);

	     					String salaryFixOvertime = checkCellType(row.getCell(34), formulaEvaluator);
	     					String penalty = checkCellType(row.getCell(15), formulaEvaluator);
	     					
	     					String salaryAddOvertime = checkCellType(row.getCell(29), formulaEvaluator);
	     					String overPay = checkCellType(row.getCell(14), formulaEvaluator);
	     					
	     					String salaryUnpaidDays = checkCellType(row.getCell(31), formulaEvaluator);
	     					String employeeCPF = checkCellType(row.getCell(11), formulaEvaluator);
	     					String shortPay = checkCellType(row.getCell(13), formulaEvaluator);

	     					String salaryUnpaidHours = checkCellType(row.getCell(30), formulaEvaluator);
	     					String employerCPF = checkCellType(row.getCell(10), formulaEvaluator);
	     					String balSalary = checkCellType(row.getCell(12), formulaEvaluator);
	     					
	     					String salaryPH = checkCellType(row.getCell(33), formulaEvaluator);
	     					double totalCPF = Double.parseDouble(employeeCPF) + Double.parseDouble(employerCPF);
	     					String loanBal = checkCellType(row.getCell(17), formulaEvaluator);
	     					
	     					String salaryRestDays = checkCellType(row.getCell(28), formulaEvaluator);
	     					
	     					String allowanceAttendance = checkCellType(row.getCell(27), formulaEvaluator);
	     					String annualLeaveUsed = checkCellType(row.getCell(1), formulaEvaluator);
	     					
	     					String allowanceSite = checkCellType(row.getCell(26), formulaEvaluator);
	     					String annualLeaveBal = checkCellType(row.getCell(0), formulaEvaluator);

	     					String allowanceUniform = checkCellType(row.getCell(24), formulaEvaluator);
	     					String mcOutPatientUsed = checkCellType(row.getCell(3), formulaEvaluator);
	     					
	     					String allowanceSiteIC = checkCellType(row.getCell(25), formulaEvaluator);
	     					String mcOutPatientBal = checkCellType(row.getCell(2), formulaEvaluator);
	     					
	     					String bonus = checkCellType(row.getCell(23), formulaEvaluator);
	     					String mcHospitalBal = checkCellType(row.getCell(5), formulaEvaluator);
	     					
	     					String grossPay = checkCellType(row.getCell(20), formulaEvaluator);
	     					String mcHospitalUsed = checkCellType(row.getCell(4), formulaEvaluator);

	     					String lessEmployeeCPF = checkCellType(row.getCell(11), formulaEvaluator);
	     					String paymentClaim = checkCellType(row.getCell(21), formulaEvaluator);
	     					String netGrossSalary = checkCellType(row.getCell(19), formulaEvaluator);
	     					
	     					double finalAdvance = Double.parseDouble(advance) + Double.parseDouble(lessLoan);
	     					
	     					//Now to add the actual days worked - the list of attendance at the bottom
	     					//We will capture it in ArrayList<String> - ArrayList<Date, Day or Night Shift, Status>
	     					//loop through the dates
	     					System.out.println("starting to read attendance");
	     					ArrayList<ArrayList<String>> attendance = new ArrayList<ArrayList<String>>();
	     					int aCol = 56; //attendance starts from column 56
	     					String checkColName = "";
	     					while(aCol <= 117) {
	     						ArrayList<String> a = new ArrayList<>();
	     						if(aCol % 2 == 0) {
	     							switch(formulaEvaluator.evaluateInCell(wb.getSheetAt(0).getRow(1).getCell(aCol)).getCellType())  
	     							{  
	     								case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
	     								//getting the value of the cell as a number  
	     								checkColName = wb.getSheetAt(0).getRow(1).getCell(aCol).getDateCellValue() + "";
	     								if(!checkColName.equalsIgnoreCase("Day")) {
	     									a.add(checkColName); //date
	     									a.add("Day"); //Shift
	     									String val = "0"; //type
	     									switch(formulaEvaluator.evaluateInCell(row.getCell(aCol)).getCellType())  
	     									{  
	     										case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
	     										//getting the value of the cell as a number  
	     										val = row.getCell(aCol).getNumericCellValue() + "";
	     										break;  
	     										case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
	     										//getting the value of the cell as a string
	     										val =  row.getCell(aCol).getStringCellValue();
	     										break;  
	     									}
	     									a.add(val);
	     								}
	     							}  
	     						}
	     						else{
	     							switch(formulaEvaluator.evaluateInCell(wb.getSheetAt(0).getRow(1).getCell(aCol-1)).getCellType())  {
	     								case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
	     								//getting the value of the cell as a number  
	     								checkColName = wb.getSheetAt(0).getRow(1).getCell(aCol-1).getDateCellValue() + "";
	     								if(!checkColName.equalsIgnoreCase("Day")) {
	     									a.add(checkColName); //date
	     									a.add("Night"); //Shift
	     									String val = "0"; //type
	     									switch(formulaEvaluator.evaluateInCell(row.getCell(aCol)).getCellType())  
	     									{  
	     										case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
	     										//getting the value of the cell as a number  
	     										val = row.getCell(aCol).getNumericCellValue() + "";
	     										break;  
	     										case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
	     										//getting the value of the cell as a string
	     										val =  row.getCell(aCol).getStringCellValue();
	     										break;  
	     									} 
	     									a.add(val);
	     								}
	     							}
	     							
	     						}
	     						aCol++;
	     						attendance.add(a);
	     					}
	     					//create the payslip
	     					//create spreadsheet object
	     					// workbook object
	     					XSSFWorkbook workbookEmailAttachment = new XSSFWorkbook();
	     					System.out.println("creating sheet: " + name);
	     					XSSFSheet spreadsheet = workbookEmailAttachment.createSheet(name);
	     					spreadsheet.setColumnWidth(1, 256*13);
	     					spreadsheet.setColumnWidth(3, 256*13);
	     					//creating styles start
	     					XSSFFont bold = workbookEmailAttachment.createFont();
	     					bold.setBold(true);
	     				    
	     		            CellStyle topCornerLeft = workbookEmailAttachment.createCellStyle();  
	     		            topCornerLeft.setBorderLeft(CellStyle.BORDER_MEDIUM);    
	     		            topCornerLeft.setBorderTop(CellStyle.BORDER_MEDIUM);
	     		            topCornerLeft.setBorderBottom(CellStyle.BORDER_MEDIUM);
	     		            topCornerLeft.setBorderRight(CellStyle.BORDER_MEDIUM);
	     		            topCornerLeft.setFont(bold);
	     		            
	     		            CellStyle topMiddle = workbookEmailAttachment.createCellStyle();     
	     		            topMiddle.setBorderTop(CellStyle.BORDER_MEDIUM);
	     		            topMiddle.setBorderBottom(CellStyle.BORDER_MEDIUM);
	     		            topMiddle.setBorderRight(CellStyle.BORDER_MEDIUM);
	     		            topMiddle.setFont(bold);
	     		            
	     		            CellStyle topCornerRight = workbookEmailAttachment.createCellStyle();  
	     		            topCornerRight.setBorderRight(CellStyle.BORDER_MEDIUM);    
	     		            topCornerRight.setBorderTop(CellStyle.BORDER_MEDIUM);
	     		            topCornerRight.setBorderBottom(CellStyle.BORDER_MEDIUM);
	     		            topCornerRight.setFont(bold);
	     		            
	     		            CellStyle contentColumn1 = workbookEmailAttachment.createCellStyle();  
	     		            contentColumn1.setBorderLeft(CellStyle.BORDER_MEDIUM);   
	     		            contentColumn1.setBorderRight(CellStyle.BORDER_THIN); 
	     		            contentColumn1.setBorderBottom(CellStyle.BORDER_THIN); 
	     		            contentColumn1.setFont(bold);
	     		            
	     		            CellStyle contentColumn2 = workbookEmailAttachment.createCellStyle();  
	     		            contentColumn2.setBorderLeft(CellStyle.BORDER_THIN);   
	     		            contentColumn2.setBorderRight(CellStyle.BORDER_THIN); 
	     		            contentColumn2.setBorderBottom(CellStyle.BORDER_THIN); 
	     		            contentColumn2.setBorderTop(CellStyle.BORDER_THIN); 
	     		            
	     		            CellStyle bottomCornerLeft = workbookEmailAttachment.createCellStyle();  
	     		            bottomCornerLeft.setBorderLeft(CellStyle.BORDER_MEDIUM); 
	     		            bottomCornerLeft.setBorderRight(CellStyle.BORDER_THIN); 
	     		            bottomCornerLeft.setBorderBottom(CellStyle.BORDER_MEDIUM);
	     		            bottomCornerLeft.setFont(bold);
	     		            
	     		            CellStyle bottomMiddle = workbookEmailAttachment.createCellStyle();    
	     		            bottomMiddle.setBorderBottom(CellStyle.BORDER_MEDIUM);
	     		            bottomMiddle.setBorderRight(CellStyle.BORDER_THIN); 
	     		            bottomMiddle.setFont(bold);
	     		            
	     		            CellStyle topMiddleHeader = workbookEmailAttachment.createCellStyle();     
	     		            topMiddleHeader.setBorderTop(CellStyle.BORDER_MEDIUM);
	     		            topMiddleHeader.setBorderBottom(CellStyle.BORDER_THIN);
	     		            topMiddleHeader.setBorderLeft(CellStyle.BORDER_THIN);
	     		            topMiddleHeader.setBorderRight(CellStyle.BORDER_THIN);
	     		            topMiddleHeader.setFont(bold);
	     		            
	     		            CellStyle topCornerHeader = workbookEmailAttachment.createCellStyle();     
	     		            topCornerHeader.setBorderTop(CellStyle.BORDER_MEDIUM);
	     		            topCornerHeader.setBorderBottom(CellStyle.BORDER_THIN);
	     		            topCornerHeader.setBorderLeft(CellStyle.BORDER_THIN);
	     		            topCornerHeader.setBorderRight(CellStyle.BORDER_MEDIUM);
	     		            topCornerHeader.setFont(bold);
	     		            
	     		            CellStyle bottomCornerRight = workbookEmailAttachment.createCellStyle();  
	     		            bottomCornerRight.setBorderRight(CellStyle.BORDER_MEDIUM); 
	     		            bottomCornerRight.setBorderLeft(CellStyle.BORDER_THIN); 
	     		            bottomCornerRight.setBorderBottom(CellStyle.BORDER_MEDIUM);
	     		            bottomCornerRight.setFont(bold);
	     		            //creating styles end
	     		            


	     					
	     					// creating header row
	     		            System.out.println("creating header: " + idNo);
	     		            NumberFormat nf = NumberFormat.getCurrencyInstance();
	     		            //row 1
	     		        	// Merges the cells
	     		            CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 5);
	     		            spreadsheet.addMergedRegion(cellRangeAddress);
	     		            
	     					XSSFRow header = spreadsheet.createRow(1);
	     					Cell headerCell = header.createCell(1);
	     					headerCell.setCellValue("K11 Security Engineering");
	     					headerCell.setCellStyle(topCornerLeft); 
	     					// Sets the allignment to the created cell
	     					CellUtil.setAlignment(headerCell, workbookEmailAttachment, CellStyle.ALIGN_CENTER);
	     					
	     					Cell r1c2 = header.createCell(2);
	     					r1c2.setCellStyle(topMiddle);
	     					
	     					Cell r1c3 = header.createCell(3);
	     					r1c3.setCellStyle(topMiddle);
	     					
	     					Cell r1c4 = header.createCell(4);
	     					r1c4.setCellStyle(topMiddle);
	     					
	     					Cell r1c5 = header.createCell(5);
	     					r1c5.setCellStyle(topMiddle);
	     					
	     					Cell gradeTitleCell = header.createCell(6);
	     					gradeTitleCell.setCellValue("Deployment Grade");
	     					gradeTitleCell.setCellStyle(topMiddle);

	     					Cell r1c7 = header.createCell(7);
	     					r1c7.setCellStyle(topMiddle);
	     					
	     					Cell r1c8 = header.createCell(8);
	     					r1c8.setCellStyle(topMiddle);
	     					
	     					Cell r1c9 = header.createCell(9);
	     					r1c9.setCellValue(securityGrade);
	     					r1c9.setCellStyle(topMiddle);
	     					
	     					Cell headerC10 = header.createCell(10);
	     					headerC10.setCellStyle(topCornerRight);
	     					
	     					
	     					//row 2
	     					XSSFRow r2 = spreadsheet.createRow(2);
	     					Cell r2c1 = r2.createCell(1);
	     					r2c1.setCellValue("Payslip Month");
	     					r2c1.setCellStyle(contentColumn2);
	     					
	     					Cell r2c2 = r2.createCell(2);
	     					r2c2.setCellValue(payslipMonthDisplay);
	     					r2c2.setCellStyle(contentColumn2);
	     					
	     					Cell r2c3 = r2.createCell(3);
	     					r2c3.setCellValue("Emp Status");
	     					r2c3.setCellStyle(contentColumn2);
	     					
	     					Cell r2c4 = r2.createCell(4);
	     					r2c4.setCellValue(empStatus);
	     					r2c4.setCellStyle(contentColumn2);
	     					
	     					Cell r2c5 = r2.createCell(5);
	     					r2c5.setCellValue("Salary Date");
	     					r2c5.setCellStyle(contentColumn2);
	     					
	     					Cell r2c6 = r2.createCell(6);
	     					r2c6.setCellStyle(contentColumn2);
	     					
	     					Cell r2c7 = r2.createCell(7);
	     					r2c7.setCellValue(salaryDateDisplay);
	     					r2c7.setCellStyle(contentColumn2);
	     					
	     					Cell r2c8 = r2.createCell(8);
	     					r2c8.setCellValue("OT Hours");
	     					r2c8.setCellStyle(contentColumn2);
	     					
	     					Cell r2c9 = r2.createCell(9);
	     					r2c9.setCellValue(otHours);
	     					r2c9.setCellStyle(contentColumn2);
	     					
	     					Cell r2c10 = r2.createCell(10);
	     					r2c10.setCellStyle(contentColumn2);
	     					
	     					//row 3
	     					XSSFRow r3 = spreadsheet.createRow(3);
	     					Cell r3c1 = r3.createCell(1);
	     					r3c1.setCellValue("Employee Name");
	     					r3c1.setCellStyle(contentColumn2);
	     					
	     					Cell r3c2 = r3.createCell(2);
	     					r3c2.setCellStyle(contentColumn2);
	     					
	     					Cell r3c3 = r3.createCell(3);
	     					r3c3.setCellValue(name);
	     					r3c3.setCellStyle(contentColumn2);
	     					
	     					Cell r3c4 = r3.createCell(4);
	     					r3c4.setCellStyle(contentColumn2);
	     					
	     					Cell r3c5 = r3.createCell(5);
	     					r3c5.setCellStyle(contentColumn2);
	     					
	     					Cell r3c6 = r3.createCell(6);
	     					r3c6.setCellValue("Age");
	     					r3c6.setCellStyle(contentColumn2);
	     					
	     					Cell r3c7 = r3.createCell(7);
	     					r3c7.setCellValue(age);
	     					r3c7.setCellStyle(contentColumn2);
	     					
	     					Cell r3c8 = r3.createCell(8);
	     					r3c8.setCellValue("NRIC");
	     					r3c8.setCellStyle(contentColumn2);
	     					
	     					Cell r3c9 = r3.createCell(9);
	     					r3c9.setCellValue(idNo);
	     					r3c9.setCellStyle(contentColumn2);
	     					
	     					Cell r3c10 = r3.createCell(10);
	     					r3c10.setCellStyle(contentColumn2);
	     					
	     					//row 4
	     					// Merges the cells
	     		            CellRangeAddress cellRangeR4 = new CellRangeAddress(4, 4, 1, 2);
	     		            spreadsheet.addMergedRegion(cellRangeR4);
	     					XSSFRow r4 = spreadsheet.createRow(4);
	     					Cell r4c1 = r4.createCell(1);
	     					r4c1.setCellValue("Basic Salary:");
	     					r4c1.setCellStyle(contentColumn2);
	     					
	     					Cell r4c2 = r4.createCell(2);
	     					r4c2.setCellStyle(contentColumn2);
	     					
	     					Cell r4c3 = r4.createCell(3);
	     					r4c3.setCellValue(nf.format(new BigDecimal(basicSalaryPerHour)) );
	     					r4c3.setCellStyle(contentColumn2);
	     					
	     					Cell r4c4 = r4.createCell(4);
	     					r4c4.setCellValue("per hour");
	     					r4c4.setCellStyle(contentColumn2);
	     					
	     					Cell r4c5 = r4.createCell(5);
	     					r4c5.setCellValue(nf.format(new BigDecimal(basicSalaryPerMonth)));
	     					r4c5.setCellStyle(contentColumn2);
	     					
	     					Cell r4c6 = r4.createCell(6);
	     					r4c6.setCellValue("per month");
	     					r4c6.setCellStyle(contentColumn2);
	     					
	     					Cell r4c7 = r4.createCell(7);
	     					r4c7.setCellStyle(contentColumn2);
	     					
	     					Cell r4c8 = r4.createCell(8);
	     					r4c8.setCellValue(nf.format(new BigDecimal(basicSalaryPerDay)) );
	     					r4c8.setCellStyle(contentColumn2);
	     					
	     					Cell r4c9 = r4.createCell(9);
	     					r4c9.setCellValue("per 8hr shift");
	     					r4c9.setCellStyle(contentColumn2);
	     					
	     					Cell r4c10 = r4.createCell(10);
	     					r4c10.setCellStyle(contentColumn2);
	     					
	     					//row 5
	     					XSSFRow r5 = spreadsheet.createRow(5);
	     					Cell r5c1 = r5.createCell(1);
	     					r5c1.setCellValue("OT Rate X 1.5: ");
	     					r5c1.setCellStyle(contentColumn2);
	     					
	     					Cell r5c2 = r5.createCell(2);
	     					r5c2.setCellValue(nf.format(new BigDecimal(OTRate)));
	     					r5c2.setCellStyle(contentColumn2);
	     					
	     					Cell r5c3 = r5.createCell(3);
	     					r5c3.setCellValue("Bank");
	     					r5c3.setCellStyle(contentColumn2);
	     					
	     					Cell r5c4 = r5.createCell(4);
	     					r5c4.setCellValue(bank);
	     					r5c4.setCellStyle(contentColumn2);
	     					
	     					Cell r5c5 = r5.createCell(5);
	     					r5c5.setCellValue(bankNo);
	     					r5c5.setCellStyle(contentColumn2);
	     					
	     					Cell r5c6 = r5.createCell(6);
	     					r5c6.setCellStyle(contentColumn2);
	     					
	     					Cell r5c7 = r5.createCell(7);
	     					r5c7.setCellStyle(contentColumn2);
	     					
	     					Cell r5c8 = r5.createCell(8);
	     					r5c8.setCellStyle(contentColumn2);
	     					
	     					Cell r5c9 = r5.createCell(9);
	     					r5c9.setCellStyle(contentColumn2);
	     					
	     					Cell r5c10 = r5.createCell(10);
	     					r5c10.setCellStyle(contentColumn2);
	     					
	     					//row 6
	     		            CellRangeAddress cellRangeR6 = new CellRangeAddress(6, 6, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRangeR6);
	     		            CellRangeAddress cellRangeR6b = new CellRangeAddress(6, 6, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRangeR6b);
	     		            
	     					XSSFRow r6 = spreadsheet.createRow(6);
	     					Cell r6c1 = r6.createCell(1);
	     					r6c1.setCellValue("Item ");
	     					r6c1.setCellStyle(contentColumn2);
	     					
	     					Cell r6c2 = r6.createCell(2);
	     					r6c2.setCellStyle(contentColumn2);
	     					
	     					Cell r6c3 = r6.createCell(3);
	     					r6c3.setCellStyle(contentColumn2);
	     					
	     					Cell r6c4 = r6.createCell(4);
	     					r6c4.setCellValue("Amount");
	     					r6c4.setCellStyle(contentColumn2);
	     					
	     					Cell r6c5 = r6.createCell(5);
	     					r6c5.setCellStyle(contentColumn2);
	     					
	     					Cell r6c6 = r6.createCell(6);
	     					r6c6.setCellStyle(contentColumn2);
	     					
	     					Cell r6c7 = r6.createCell(7);
	     					r6c7.setCellStyle(contentColumn2);
	     					
	     					Cell r6c8 = r6.createCell(8);
	     					r6c8.setCellValue("Payment");
	     					r6c8.setCellStyle(contentColumn2);
	     					
	     					Cell r6c9 = r6.createCell(9);
	     					r6c9.setCellStyle(contentColumn2);
	     					
	     					Cell r6c10 = r6.createCell(10);
	     					r6c10.setCellValue("Amount");
	     					r6c10.setCellStyle(contentColumn2);
	     					
	     					//row 7
	     		            CellRangeAddress cellRangeR7 = new CellRangeAddress(7, 7, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRangeR7);
	     		            CellRangeAddress cellRangeR7b = new CellRangeAddress(7, 7, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRangeR7b);
	     		            
	     					XSSFRow r7 = spreadsheet.createRow(7);
	     					Cell r7c1 = r7.createCell(1);
	     					r7c1.setCellValue("Salary (Basic): ");
	     					r7c1.setCellStyle(contentColumn2);
	     					
	     					Cell r7c2 = r7.createCell(2);
	     					r7c2.setCellStyle(contentColumn2);
	     					
	     					Cell r7c3 = r7.createCell(3);
	     					r7c3.setCellStyle(contentColumn2);
	     					
	     					Cell r7c4 = r7.createCell(4);
	     					r7c4.setCellValue(nf.format(new BigDecimal(salaryBasic)));
	     					r7c4.setCellStyle(contentColumn2);
	     					
	     					Cell r7c5 = r7.createCell(5);
	     					r7c5.setCellValue("Payment Type");
	     					r7c5.setCellStyle(contentColumn2);
	     					
	     					Cell r7c6 = r7.createCell(6);
	     					r7c6.setCellStyle(contentColumn2);
	     					
	     					Cell r7c7 = r7.createCell(7);
	     					r7c7.setCellValue(paymentType);
	     					r7c7.setCellStyle(contentColumn2);
	     					
	     					Cell r7c8 = r7.createCell(8);
	     					r7c8.setCellValue("Advance");
	     					r7c8.setCellStyle(contentColumn2);
	     					
	     					Cell r7c9 = r7.createCell(9);
	     					r7c9.setCellStyle(contentColumn2);
	     					
	     					Cell r7c10 = r7.createCell(10);
	     					r7c10.setCellValue(Pattern.matches(decimalPattern, advance) ? nf.format(new BigDecimal(advance)) : advance);
	     					r7c10.setCellStyle(contentColumn2);
	     					
	     					//row 8
	     					CellRangeAddress cellRangeR8b = new CellRangeAddress(8, 8, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRangeR8b);
	     		            
	     					XSSFRow r8 = spreadsheet.createRow(8);
	     					Cell r8c1 = r8.createCell(1);
	     					r8c1.setCellValue("Salary (12hrs Daily Rated Gross) ");
	     					r8c1.setCellStyle(contentColumn2);
	     					
	     					Cell r8c2 = r8.createCell(2);
	     					r8c2.setCellStyle(contentColumn2);
	     					
	     					Cell r8c3 = r8.createCell(3);
	     					r8c3.setCellStyle(contentColumn2);
	     					
	     					Cell r8c4 = r8.createCell(4);
	     					r8c4.setCellValue(nf.format(new BigDecimal(salary12HrsDailyRatedGross)));
	     					r8c4.setCellStyle(contentColumn2);
	     					
	     					Cell r8c5 = r8.createCell(5);
	     					r8c5.setCellValue(dailyRateGross);
	     					r8c5.setCellStyle(contentColumn2);
	     					
	     					Cell r8c6 = r8.createCell(6);
	     					r8c6.setCellValue(numDaysWorked);
	     					r8c6.setCellStyle(contentColumn2);
	     					
	     					Cell r8c7 = r8.createCell(7);
	     					r8c7.setCellStyle(contentColumn2);
	     					
	     					Cell r8c8 = r8.createCell(8);
	     					r8c8.setCellValue("Less Loan");
	     					r8c8.setCellStyle(contentColumn2);
	     					
	     					Cell r8c9 = r8.createCell(9);
	     					r8c9.setCellStyle(contentColumn2);
	     					
	     					Cell r8c10 = r8.createCell(10);
	     					r8c10.setCellValue(Pattern.matches(decimalPattern, lessLoan) ? nf.format(new BigDecimal(lessLoan)) : lessLoan);
	     					r8c10.setCellStyle(contentColumn2);
	     					
	     					//row 9
	     					CellRangeAddress cellRangeR9 = new CellRangeAddress(9, 9, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRangeR9);
	     		            CellRangeAddress cellRangeR9b = new CellRangeAddress(9, 9, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRangeR9b);
	     		            
	     					XSSFRow r9 = spreadsheet.createRow(9);
	     					Cell r9c1 = r9.createCell(1);
	     					r9c1.setCellValue("Salary (Fix Overtime)");
	     					r9c1.setCellStyle(contentColumn2);
	     					
	     					Cell r9c2 = r9.createCell(2);
	     					r9c2.setCellStyle(contentColumn2);
	     					
	     					Cell r9c3 = r9.createCell(3);
	     					r9c3.setCellStyle(contentColumn2);
	     					
	     					Cell r9c4 = r9.createCell(4);
	     					r9c4.setCellValue(Pattern.matches(decimalPattern, salaryFixOvertime) ? nf.format(new BigDecimal(salaryFixOvertime)) : salaryFixOvertime);
	     					r9c4.setCellStyle(contentColumn2);
	     					
	     					Cell r9c5 = r9.createCell(5);
	     					r9c5.setCellStyle(contentColumn2);
	     					
	     					Cell r9c6 = r9.createCell(6);
	     					r9c6.setCellStyle(contentColumn2);
	     					
	     					Cell r9c7 = r9.createCell(7);
	     					r9c7.setCellStyle(contentColumn2);
	     					
	     					Cell r9c8 = r9.createCell(8);
	     					r9c8.setCellValue("Penalty");
	     					r9c8.setCellStyle(contentColumn2);
	     					
	     					Cell r9c9 = r9.createCell(9);
	     					r9c9.setCellStyle(contentColumn2);
	     					
	     					Cell r9c10 = r9.createCell(10);
	     					r9c10.setCellValue(Pattern.matches(decimalPattern, penalty) ? nf.format(new BigDecimal(penalty)) : penalty);
	     					r9c10.setCellStyle(contentColumn2);
	     					
	     					//row 10
	     					CellRangeAddress cellRanger10 = new CellRangeAddress(10, 10, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger10);
	     		            CellRangeAddress cellRanger10b = new CellRangeAddress(10, 10, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRanger10b);
	     		            
	     					XSSFRow r10 = spreadsheet.createRow(10);
	     					Cell r10c1 = r10.createCell(1);
	     					r10c1.setCellValue("Salary (Add Overtime)");
	     					r10c1.setCellStyle(contentColumn2);
	     					
	     					Cell r10c2 = r10.createCell(2);
	     					r10c2.setCellStyle(contentColumn2);
	     					
	     					Cell r10c3 = r10.createCell(3);
	     					r10c3.setCellStyle(contentColumn2);
	     					
	     					Cell r10c4 = r10.createCell(4);
	     					r10c4.setCellValue(Pattern.matches(decimalPattern, salaryAddOvertime) ? nf.format(new BigDecimal(salaryAddOvertime)) : salaryAddOvertime);
	     					r10c4.setCellStyle(contentColumn2);
	     					
	     					Cell r10c5 = r10.createCell(5);
	     					r10c5.setCellStyle(contentColumn2);
	     					
	     					Cell r10c6 = r10.createCell(6);
	     					r10c6.setCellStyle(contentColumn2);
	     					
	     					Cell r10c7 = r10.createCell(7);
	     					r10c7.setCellValue("Amount");
	     					r10c7.setCellStyle(contentColumn2);
	     					
	     					Cell r10c8 = r10.createCell(8);
	     					r10c8.setCellValue("OverPay B/F");
	     					r10c8.setCellStyle(contentColumn2);
	     					
	     					Cell r10c9 = r10.createCell(9);
	     					r10c9.setCellStyle(contentColumn2);
	     					
	     					Cell r10c10 = r10.createCell(10);
	     					r10c10.setCellValue(Pattern.matches(decimalPattern, overPay) ? nf.format(new BigDecimal(overPay)) : overPay);
	     					r10c10.setCellStyle(contentColumn2);
	     					
	     					//row 11
	     					CellRangeAddress cellRanger11 = new CellRangeAddress(11, 11, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger11);
	     		            CellRangeAddress cellRanger11b = new CellRangeAddress(11, 11, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRanger11b);
	     		            CellRangeAddress cellRanger11c = new CellRangeAddress(11, 11, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger11c);
	     		            
	     					XSSFRow r11 = spreadsheet.createRow(11);
	     					Cell r11c1 = r11.createCell(1);
	     					r11c1.setCellValue("Salary (Unpaid days)");
	     					r11c1.setCellStyle(contentColumn2);
	     					
	     					Cell r11c2 = r11.createCell(2);
	     					r11c2.setCellStyle(contentColumn2);
	     					
	     					Cell r11c3 = r11.createCell(3);
	     					r11c3.setCellStyle(contentColumn2);
	     					
	     					Cell r11c4 = r11.createCell(4);
	     					r11c4.setCellValue(Pattern.matches(decimalPattern, salaryUnpaidDays) ? nf.format(new BigDecimal(salaryUnpaidDays)) : salaryUnpaidDays);
	     					r11c4.setCellStyle(contentColumn2);
	     					
	     					Cell r11c5 = r11.createCell(5);
	     					r11c5.setCellValue("EMPLOYEE'S CPF");
	     					r11c5.setCellStyle(contentColumn2);
	     					
	     					Cell r11c6 = r11.createCell(6);
	     					r11c6.setCellStyle(contentColumn2);
	     					
	     					Cell r11c7 = r11.createCell(7);
	     					r11c7.setCellValue(Pattern.matches(decimalPattern, employeeCPF) ? nf.format(new BigDecimal(employeeCPF)) : employeeCPF);
	     					r11c7.setCellStyle(contentColumn2);
	     					
	     					Cell r11c8 = r11.createCell(8);
	     					r11c8.setCellValue("Shortpay B/F");
	     					r11c8.setCellStyle(contentColumn2);
	     					
	     					Cell r11c9 = r11.createCell(9);
	     					r11c9.setCellStyle(contentColumn2);
	     					
	     					Cell r11c10 = r11.createCell(10);
	     					r11c10.setCellValue(Pattern.matches(decimalPattern, shortPay) ? nf.format(new BigDecimal(shortPay)) : shortPay);
	     					r11c10.setCellStyle(contentColumn2);
	     					
	     					//row 12
	     					CellRangeAddress cellRanger12 = new CellRangeAddress(12, 12, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger12);
	     		            CellRangeAddress cellRanger12b = new CellRangeAddress(12, 12, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRanger12b);
	     		            CellRangeAddress cellRanger12c = new CellRangeAddress(12, 12, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger12c);
	     		            
	     					XSSFRow r12 = spreadsheet.createRow(12);
	     					Cell r12c1 = r12.createCell(1);
	     					r12c1.setCellValue("Salary (Unpaid hours)");
	     					r12c1.setCellStyle(contentColumn2);
	     					
	     					Cell r12c2 = r12.createCell(2);
	     					r12c2.setCellStyle(contentColumn2);
	     					
	     					Cell r12c3 = r12.createCell(3);
	     					r12c3.setCellStyle(contentColumn2);
	     					
	     					Cell r12c4 = r12.createCell(4);
	     					r12c4.setCellValue(Pattern.matches(decimalPattern, salaryUnpaidHours) ? nf.format(new BigDecimal(salaryUnpaidHours)) : salaryUnpaidHours);
	     					r12c4.setCellStyle(contentColumn2);
	     					
	     					Cell r12c5 = r12.createCell(5);
	     					r12c5.setCellValue("EMPLOYER'S CPF");
	     					r12c5.setCellStyle(contentColumn2);
	     					
	     					Cell r12c6 = r12.createCell(6);
	     					r12c6.setCellStyle(contentColumn2);
	     					
	     					Cell r12c7 = r12.createCell(7);
	     					r12c7.setCellValue(Pattern.matches(decimalPattern, employerCPF) ? nf.format(new BigDecimal(employerCPF)) : employerCPF);
	     					r12c7.setCellStyle(contentColumn2);
	     					
	     					Cell r12c8 = r12.createCell(8);
	     					r12c8.setCellValue("Bal Salary");
	     					r12c8.setCellStyle(contentColumn2);
	     					
	     					Cell r12c9 = r12.createCell(9);
	     					r12c9.setCellStyle(contentColumn2);
	     					
	     					Cell r12c10 = r12.createCell(10);
	     					r12c10.setCellValue(Pattern.matches(decimalPattern, balSalary) ? nf.format(new BigDecimal(balSalary)) : balSalary);
	     					r12c10.setCellStyle(contentColumn2);
	     					
	     					//row 13
	     					CellRangeAddress cellRanger13 = new CellRangeAddress(13, 13, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger13);
	     		            CellRangeAddress cellRanger13b = new CellRangeAddress(13, 13, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRanger13b);
	     		            CellRangeAddress cellRanger13c = new CellRangeAddress(13, 13, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger13c);
	     		            
	     					XSSFRow r13 = spreadsheet.createRow(13);
	     					Cell r13c1 = r13.createCell(1);
	     					r13c1.setCellValue("Salary (PH)");
	     					r13c1.setCellStyle(contentColumn2);
	     					
	     					Cell r13c2 = r13.createCell(2);
	     					r13c2.setCellStyle(contentColumn2);
	     					
	     					Cell r13c3 = r13.createCell(3);
	     					r13c3.setCellStyle(contentColumn2);
	     					
	     					Cell r13c4 = r13.createCell(4);
	     					r13c4.setCellValue(Pattern.matches(decimalPattern, salaryPH) ? nf.format(new BigDecimal(salaryPH)) : salaryPH);
	     					r13c4.setCellStyle(contentColumn2);
	     					
	     					Cell r13c5 = r13.createCell(5);
	     					r13c5.setCellValue("Total CPF");
	     					r13c5.setCellStyle(contentColumn2);
	     					
	     					Cell r13c6 = r13.createCell(6);
	     					r13c6.setCellStyle(contentColumn2);
	     					
	     					Cell r13c7 = r13.createCell(7);
	     					r13c7.setCellValue(nf.format(new BigDecimal(totalCPF)));
	     					r13c7.setCellStyle(contentColumn2);
	     					
	     					Cell r13c8 = r13.createCell(8);
	     					r13c8.setCellValue("Loan Bal");
	     					r13c8.setCellStyle(contentColumn2);
	     					
	     					Cell r13c9 = r13.createCell(9);
	     					r13c9.setCellStyle(contentColumn2);
	     					
	     					Cell r13c10 = r13.createCell(10);
	     					r13c10.setCellValue(Pattern.matches(decimalPattern, loanBal) ? nf.format(new BigDecimal(loanBal)) : loanBal);
	     					r13c10.setCellStyle(contentColumn2);
	     					
	     					//row 14
	     					CellRangeAddress cellRanger14 = new CellRangeAddress(14, 14, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger14);
	     		            CellRangeAddress cellRanger14b = new CellRangeAddress(14, 14, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRanger14b);
	     		            CellRangeAddress cellRanger14c = new CellRangeAddress(14, 14, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger14c);
	     		            
	     					XSSFRow r14 = spreadsheet.createRow(14);
	     					Cell r14c1 = r14.createCell(1);
	     					r14c1.setCellValue("Salary(Rest Days)");
	     					r14c1.setCellStyle(contentColumn2);
	     					
	     					Cell r14c2 = r14.createCell(2);
	     					r14c2.setCellStyle(contentColumn2);
	     					
	     					Cell r14c3 = r14.createCell(3);
	     					r14c3.setCellStyle(contentColumn2);
	     					
	     					Cell r14c4 = r14.createCell(4);
	     					r14c4.setCellValue(Pattern.matches(decimalPattern, salaryRestDays) ? nf.format(new BigDecimal(salaryRestDays)) : salaryRestDays);
	     					r14c4.setCellStyle(contentColumn2);
	     					
	     					Cell r14c5 = r14.createCell(5);
	     					r14c5.setCellStyle(contentColumn2);
	     					
	     					Cell r14c6 = r14.createCell(6);
	     					r14c6.setCellStyle(contentColumn2);
	     					
	     					Cell r14c7 = r14.createCell(7);
	     					r14c7.setCellStyle(contentColumn2);
	     					
	     					Cell r14c8 = r14.createCell(8);
	     					r14c8.setCellStyle(contentColumn2);
	     					
	     					Cell r14c9 = r14.createCell(9);
	     					r14c9.setCellStyle(contentColumn2);
	     					
	     					Cell r14c10 = r14.createCell(10);
	     					r14c10.setCellStyle(contentColumn2);
	     					
	     					//row 15
	     					CellRangeAddress cellRanger15 = new CellRangeAddress(15, 15, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger15);
	     		            CellRangeAddress cellRanger15c = new CellRangeAddress(15, 15, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger15c);
	     		            
	     					XSSFRow r15 = spreadsheet.createRow(15);
	     					Cell r15c1 = r15.createCell(1);
	     					r15c1.setCellValue("Allowance Attendance");
	     					r15c1.setCellStyle(contentColumn2);
	     					
	     					Cell r15c2 = r15.createCell(2);
	     					r15c2.setCellStyle(contentColumn2);
	     					
	     					Cell r15c3 = r15.createCell(3);
	     					r15c3.setCellStyle(contentColumn2);
	     					
	     					Cell r15c4 = r15.createCell(4);
	     					r15c4.setCellValue(Pattern.matches(decimalPattern, allowanceAttendance) ? nf.format(new BigDecimal(allowanceAttendance)) : allowanceAttendance);
	     					r15c4.setCellStyle(contentColumn2);
	     					
	     					Cell r15c5 = r15.createCell(5);
	     					r15c5.setCellValue("Annual Leave Used");
	     					r15c5.setCellStyle(contentColumn2);
	     					
	     					Cell r15c6 = r15.createCell(6);
	     					r15c6.setCellStyle(contentColumn2);
	     					
	     					Cell r15c7 = r15.createCell(7);
	     					r15c7.setCellValue(annualLeaveUsed);
	     					r15c7.setCellStyle(contentColumn2);
	     					
	     					Cell r15c8 = r15.createCell(8);
	     					r15c8.setCellStyle(contentColumn2);
	     					
	     					Cell r15c9 = r15.createCell(9);
	     					r15c9.setCellStyle(contentColumn2);
	     					
	     					Cell r15c10 = r15.createCell(10);
	     					r15c10.setCellStyle(contentColumn2);
	     					
	     					//row 16
	     					CellRangeAddress cellRanger16 = new CellRangeAddress(16, 16, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger16);
	     		            CellRangeAddress cellRanger16c = new CellRangeAddress(16, 16, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger16c);
	     		            
	     					XSSFRow r16 = spreadsheet.createRow(16);
	     					Cell r16c1 = r16.createCell(1);
	     					r16c1.setCellValue("Allowance Site ");
	     					r16c1.setCellStyle(contentColumn2);
	     					
	     					Cell r16c2 = r16.createCell(2);
	     					r16c2.setCellStyle(contentColumn2);
	     					
	     					Cell r16c3 = r16.createCell(3);
	     					r16c3.setCellStyle(contentColumn2);
	     					
	     					Cell r16c4 = r16.createCell(4);
	     					r16c4.setCellValue(Pattern.matches(decimalPattern, allowanceSite) ? nf.format(new BigDecimal(allowanceSite)) : allowanceSite);
	     					r16c4.setCellStyle(contentColumn2);
	     					
	     					Cell r16c5 = r16.createCell(5);
	     					r16c5.setCellValue("Annual Leave Bal");
	     					r16c5.setCellStyle(contentColumn2);
	     					
	     					Cell r16c6 = r16.createCell(6);
	     					r16c6.setCellStyle(contentColumn2);
	     					
	     					Cell r16c7 = r16.createCell(7);
	     					r16c7.setCellValue(annualLeaveBal);
	     					r16c7.setCellStyle(contentColumn2);
	     					
	     					Cell r16c8 = r16.createCell(8);
	     					r16c8.setCellStyle(contentColumn2);
	     					
	     					Cell r16c9 = r16.createCell(9);
	     					r16c9.setCellStyle(contentColumn2);
	     					
	     					Cell r16c10 = r16.createCell(10);
	     					r16c10.setCellStyle(contentColumn2);
	     					
	     					//row 17
	     					CellRangeAddress cellRanger17 = new CellRangeAddress(17, 17, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger17);
	     		            CellRangeAddress cellRanger17c = new CellRangeAddress(17, 17, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger17c);
	     		            
	     					XSSFRow r17 = spreadsheet.createRow(17);
	     					Cell r17c1 = r17.createCell(1);
	     					r17c1.setCellValue("Allowance Uniform");
	     					r17c1.setCellStyle(contentColumn2);
	     					
	     					Cell r17c2 = r17.createCell(2);
	     					r17c2.setCellStyle(contentColumn2);
	     					
	     					Cell r17c3 = r17.createCell(3);
	     					r17c3.setCellStyle(contentColumn2);
	     					
	     					Cell r17c4 = r17.createCell(4);
	     					r17c4.setCellValue(Pattern.matches(decimalPattern, allowanceUniform) ? nf.format(new BigDecimal(allowanceUniform)) : allowanceUniform);
	     					r17c4.setCellStyle(contentColumn2);
	     					
	     					Cell r17c5 = r17.createCell(5);
	     					r17c5.setCellValue("MC Out Patient Used");
	     					r17c5.setCellStyle(contentColumn2);
	     					
	     					Cell r17c6 = r17.createCell(6);
	     					r17c6.setCellStyle(contentColumn2);
	     					
	     					Cell r17c7 = r17.createCell(7);
	     					r17c7.setCellValue(mcOutPatientUsed);
	     					r17c7.setCellStyle(contentColumn2);
	     					
	     					Cell r17c8 = r17.createCell(8);
	     					r17c8.setCellStyle(contentColumn2);
	     					
	     					Cell r17c9 = r17.createCell(9);
	     					r17c9.setCellStyle(contentColumn2);
	     					
	     					Cell r17c10 = r17.createCell(10);
	     					r17c10.setCellStyle(contentColumn2);
	     					
	     					//row 18
	     					CellRangeAddress cellRanger18 = new CellRangeAddress(18, 18, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger18);
	     		            CellRangeAddress cellRanger18c = new CellRangeAddress(18, 18, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger18c);
	     		            
	     					XSSFRow r18 = spreadsheet.createRow(18);
	     					Cell r18c1 = r18.createCell(1);
	     					r18c1.setCellValue("Allowance Site IC");
	     					r18c1.setCellStyle(contentColumn2);
	     					
	     					Cell r18c2 = r18.createCell(2);
	     					r18c2.setCellStyle(contentColumn2);
	     					
	     					Cell r18c3 = r18.createCell(3);
	     					r18c3.setCellStyle(contentColumn2);
	     					
	     					Cell r18c4 = r18.createCell(4);
	     					r18c4.setCellValue(Pattern.matches(decimalPattern, allowanceSiteIC) ? nf.format(new BigDecimal(allowanceSiteIC)) : allowanceSiteIC);
	     					r18c4.setCellStyle(contentColumn2);
	     					
	     					Cell r18c5 = r18.createCell(5);
	     					r18c5.setCellValue("MC Out Patient Bal");
	     					r18c5.setCellStyle(contentColumn2);
	     					
	     					Cell r18c6 = r18.createCell(6);
	     					r18c6.setCellStyle(contentColumn2);
	     					
	     					Cell r18c7 = r18.createCell(7);
	     					r18c7.setCellValue(mcOutPatientBal);
	     					r18c7.setCellStyle(contentColumn2);
	     					
	     					Cell r18c8 = r18.createCell(8);
	     					r18c8.setCellStyle(contentColumn2);
	     					
	     					Cell r18c9 = r18.createCell(9);
	     					r18c9.setCellStyle(contentColumn2);
	     					
	     					Cell r18c10 = r18.createCell(10);
	     					r18c10.setCellStyle(contentColumn2);
	     					
	     					//row 19
	     					CellRangeAddress cellRanger19 = new CellRangeAddress(19, 19, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger19);
	     		            CellRangeAddress cellRanger19c = new CellRangeAddress(19, 19, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger19c);
	     		            
	     					XSSFRow r19 = spreadsheet.createRow(19);
	     					Cell r19c1 = r19.createCell(1);
	     					r19c1.setCellValue("Bonus");
	     					r19c1.setCellStyle(contentColumn2);
	     					
	     					Cell r19c2 = r19.createCell(2);
	     					r19c2.setCellStyle(contentColumn2);
	     					
	     					Cell r19c3 = r19.createCell(3);
	     					r19c3.setCellStyle(contentColumn2);
	     					
	     					Cell r19c4 = r19.createCell(4);
	     					r19c4.setCellValue(Pattern.matches(decimalPattern, bonus) ? nf.format(new BigDecimal(bonus)) : bonus);
	     					r19c4.setCellStyle(contentColumn2);
	     					
	     					Cell r19c5 = r19.createCell(5);
	     					r19c5.setCellValue("MC Hospital Bal");
	     					r19c5.setCellStyle(contentColumn2);
	     					
	     					Cell r19c6 = r19.createCell(6);
	     					r19c6.setCellStyle(contentColumn2);
	     					
	     					Cell r19c7 = r19.createCell(7);
	     					r19c7.setCellValue(mcHospitalBal);
	     					r19c7.setCellStyle(contentColumn2);
	     					
	     					Cell r19c8 = r19.createCell(8);
	     					r19c8.setCellStyle(contentColumn2);
	     					
	     					Cell r19c9 = r19.createCell(9);
	     					r19c9.setCellStyle(contentColumn2);
	     					
	     					Cell r19c10 = r19.createCell(10);
	     					r19c10.setCellStyle(contentColumn2);
	     					
	     					//row 20
	     					CellRangeAddress cellRanger20 = new CellRangeAddress(20, 20, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger20);
	     		            CellRangeAddress cellRanger20c = new CellRangeAddress(20, 20, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger20c);
	     		            
	     					XSSFRow r20 = spreadsheet.createRow(20);
	     					Cell r20c1 = r20.createCell(1);
	     					r20c1.setCellValue("Gross Pay");
	     					r20c1.setCellStyle(contentColumn2);
	     					
	     					Cell r20c2 = r20.createCell(2);
	     					r20c2.setCellStyle(contentColumn2);
	     					
	     					Cell r20c3 = r20.createCell(3);
	     					r20c3.setCellStyle(contentColumn2);
	     					
	     					Cell r20c4 = r20.createCell(4);
	     					r20c4.setCellValue(Pattern.matches(decimalPattern, grossPay) ? nf.format(new BigDecimal(grossPay)) : grossPay);
	     					r20c4.setCellStyle(contentColumn2);
	     					
	     					Cell r20c5 = r20.createCell(5);
	     					r20c5.setCellValue("MC Hospital Used");
	     					r20c5.setCellStyle(contentColumn2);
	     					
	     					Cell r20c6 = r20.createCell(6);
	     					r20c6.setCellStyle(contentColumn2);
	     					
	     					Cell r20c7 = r20.createCell(7);
	     					r20c7.setCellValue(mcHospitalUsed);
	     					r20c7.setCellStyle(contentColumn2);
	     					
	     					Cell r20c8 = r20.createCell(8);
	     					r20c8.setCellStyle(contentColumn2);
	     					
	     					Cell r20c9 = r20.createCell(9);
	     					r20c9.setCellStyle(contentColumn2);
	     					
	     					Cell r20c10 = r20.createCell(10);
	     					r20c10.setCellStyle(contentColumn2);
	     					
	     					//row 21
	     					CellRangeAddress cellRanger21 = new CellRangeAddress(21, 21, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger21);
	     		            CellRangeAddress cellRanger21c = new CellRangeAddress(21, 21, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger21c);
	     		            
	     					XSSFRow r21 = spreadsheet.createRow(21);
	     					Cell r21c1 = r21.createCell(1);
	     					r21c1.setCellValue("Less Employee CPF");
	     					r21c1.setCellStyle(contentColumn2);
	     					
	     					Cell r21c2 = r21.createCell(2);
	     					r21c2.setCellStyle(contentColumn2);
	     					
	     					Cell r21c3 = r21.createCell(3);
	     					r21c3.setCellStyle(contentColumn2);
	     					
	     					Cell r21c4 = r21.createCell(4);
	     					r21c4.setCellValue(Pattern.matches(decimalPattern, lessEmployeeCPF) ? nf.format(new BigDecimal(lessEmployeeCPF)) : lessEmployeeCPF);
	     					r21c4.setCellStyle(contentColumn2);
	     					
	     					Cell r21c5 = r21.createCell(5);
	     					r21c5.setCellStyle(contentColumn2);
	     					
	     					Cell r21c6 = r21.createCell(6);
	     					r21c6.setCellStyle(contentColumn2);
	     					
	     					Cell r21c7 = r21.createCell(7);
	     					r21c7.setCellStyle(contentColumn2);
	     					
	     					Cell r21c8 = r21.createCell(8);
	     					r21c8.setCellStyle(contentColumn2);
	     					
	     					Cell r21c9 = r21.createCell(9);
	     					r21c9.setCellStyle(contentColumn2);
	     					
	     					Cell r21c10 = r21.createCell(10);
	     					r21c10.setCellStyle(contentColumn2);
	     					
	     					//row 22
	     					CellRangeAddress cellRanger22 = new CellRangeAddress(22, 22, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger22);
	     		            CellRangeAddress cellRanger22b = new CellRangeAddress(22, 22, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRanger22b);
	     		            CellRangeAddress cellRanger22c = new CellRangeAddress(22, 22, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger22c);
	     		            
	     					XSSFRow r22 = spreadsheet.createRow(22);
	     					Cell r22c1 = r22.createCell(1);
	     					r22c1.setCellValue("Payment Claim");
	     					r22c1.setCellStyle(contentColumn2);
	     					
	     					Cell r22c2 = r22.createCell(2);
	     					r22c2.setCellStyle(contentColumn2);
	     					
	     					Cell r22c3 = r22.createCell(3);
	     					r22c3.setCellStyle(contentColumn2);
	     					
	     					Cell r22c4 = r22.createCell(4);
	     					r22c4.setCellValue(Pattern.matches(decimalPattern, paymentClaim) ? nf.format(new BigDecimal(paymentClaim)) : paymentClaim);
	     					r22c4.setCellStyle(contentColumn2);
	     					
	     					Cell r22c5 = r22.createCell(5);
	     					r22c5.setCellStyle(contentColumn2);
	     					
	     					Cell r22c6 = r22.createCell(6);
	     					r22c6.setCellStyle(contentColumn2);
	     					
	     					Cell r22c7 = r22.createCell(7);
	     					r22c7.setCellStyle(contentColumn2);
	     					
	     					Cell r22c8 = r22.createCell(8);
	     					r22c8.setCellStyle(contentColumn2);
	     					
	     					Cell r22c9 = r22.createCell(9);
	     					r22c9.setCellStyle(contentColumn2);
	     					
	     					Cell r22c10 = r22.createCell(10);
	     					r22c10.setCellStyle(contentColumn2);
	     					
	     					//row 23
	     					CellRangeAddress cellRanger23 = new CellRangeAddress(23, 23, 1, 3);
	     		            spreadsheet.addMergedRegion(cellRanger23);
	     		            CellRangeAddress cellRanger23b = new CellRangeAddress(23, 23, 8, 9);
	     		            spreadsheet.addMergedRegion(cellRanger23b);
	     		            CellRangeAddress cellRanger23c = new CellRangeAddress(23, 23, 5, 6);
	     		            spreadsheet.addMergedRegion(cellRanger23c);
	     		            
	     					XSSFRow r23 = spreadsheet.createRow(23);
	     					Cell r23c1 = r23.createCell(1);
	     					r23c1.setCellValue("Net Gross Salary");
	     					r23c1.setCellStyle(contentColumn2);
	     					
	     					Cell r23c2 = r23.createCell(2);
	     					r23c2.setCellStyle(contentColumn2);
	     					
	     					Cell r23c3 = r23.createCell(3);
	     					r23c3.setCellStyle(contentColumn2);
	     					
	     					Cell r23c4 = r23.createCell(4);
	     					r23c4.setCellValue(Pattern.matches(decimalPattern, netGrossSalary) ? nf.format(new BigDecimal(netGrossSalary)) : netGrossSalary);
	     					r23c4.setCellStyle(contentColumn2);
	     					
	     					Cell r23c5 = r23.createCell(5);
	     					r23c5.setCellStyle(contentColumn2);
	     					
	     					Cell r23c6 = r23.createCell(6);
	     					r23c6.setCellStyle(contentColumn2);
	     					
	     					Cell r23c7 = r23.createCell(7);
	     					r23c7.setCellStyle(contentColumn2);
	     					
	     					Cell r23c8 = r23.createCell(8);
	     					r23c8.setCellStyle(contentColumn2);
	     					
	     					Cell r23c9 = r23.createCell(9);
	     					r23c9.setCellStyle(contentColumn2);
	     					
	     					Cell r23c10 = r23.createCell(10);
	     					r23c10.setCellValue(nf.format(new BigDecimal(finalAdvance)));
	     					r23c10.setCellStyle(contentColumn2);
	     					
	     					//row 24
	     					XSSFRow r24 = spreadsheet.createRow(24);
	     					Cell r24c1 = r24.createCell(1);
	     					r24c1.setCellValue("Note: * Rest Day/ PH worked on own request.");
	     					
	     					//start at row 25 to list out attendance
	     					System.out.println("creating attendance content... ");
	     				    XSSFRow attendanceRowHeader = spreadsheet.createRow(25);
	     					Cell c1 = attendanceRowHeader.createCell(1);
	     					c1.setCellValue("Date");
	     					c1.setCellStyle(contentColumn1);
	     					
	     					Cell c2 = attendanceRowHeader.createCell(2);
	     					c2.setCellValue("TimeIn");
	     					c2.setCellStyle(contentColumn1);
	     					
	     					Cell c3 = attendanceRowHeader.createCell(3);
	     					c3.setCellValue("DateOut");
	     					c3.setCellStyle(contentColumn1);
	     					
	     					Cell c4 = attendanceRowHeader.createCell(4);
	     					c4.setCellValue("TimeOut");
	     					c4.setCellStyle(contentColumn1);
	     					
	     					Cell c5 = attendanceRowHeader.createCell(5);
	     					c5.setCellValue("Break");
	     					c5.setCellStyle(contentColumn1);
	     					
	     					Cell c6 = attendanceRowHeader.createCell(6);
	     					c6.setCellValue("Type");
	     					c6.setCellStyle(contentColumn1);

	     					 
	     					int r = 26;
	     					for(int e = 0; e <= attendance.size() - 1; e++) {
	     						
	     						if(payslipShift.equalsIgnoreCase("Day") && (e % 2) == 0) {
	     							//even
	     							ArrayList<String> aEven = attendance.get(e);
	     							String dateEven = aEven.get(0) != null || !StringUtil.isEmpty(aEven.get(0)) ? aEven.get(0) : "";
	     							String statusEven = aEven.get(2) != null || !StringUtil.isEmpty(aEven.get(2)) ? aEven.get(2) : "";
	     							
	     							Date dateEvenDt = formatter5.parse(dateEven);
	     							String d = display.format(dateEvenDt);
	     							
	     							XSSFRow attendanceRow = spreadsheet.createRow(r);
	     							c1 = attendanceRow.createCell(1);
	     							c1.setCellValue(d);
	     							
	     							c2 = attendanceRow.createCell(2);
	     							c2.setCellValue("8:00");
	     							
	     							c3 = attendanceRow.createCell(3);
	     							c3.setCellValue(d);
	     							
	     							c4 = attendanceRow.createCell(4);
	     							c4.setCellValue("20:00");
	     							
	     							c5 = attendanceRow.createCell(5);
	     							c5.setCellValue("2.0 hr");
	     							
	     							c6 = attendanceRow.createCell(6);
	     							c6.setCellValue(statusEven);
	     							
	     							r++;
	     							
	     						}
	     						if(payslipShift.equalsIgnoreCase("Night") && (e % 2) != 0) {
	     							//odd
	     							ArrayList<String> aOdd = attendance.get(e);
	     							String dateOdd = aOdd.get(0) != null || !StringUtil.isEmpty(aOdd.get(0)) ? aOdd.get(0) : "";
	     							String statusOdd = aOdd.get(2) != null || !StringUtil.isEmpty(aOdd.get(2)) ? aOdd.get(2) : "";
	     							
	     							Date dateOddDt = formatter5.parse(dateOdd);
	     							String d = display.format(dateOddDt);
	     							
	     							Date nextDay = new Date(dateOddDt.getTime() + (1000 * 60 * 60 * 24));
	     							String nextDayStr = display.format(nextDay);
	     							
	     							XSSFRow attendanceRow = spreadsheet.createRow(r);
	     							c1 = attendanceRow.createCell(1);
	     							c1.setCellValue(d);
	     							
	     							c2 = attendanceRow.createCell(2);
	     							c2.setCellValue("20:00");
	     							
	     							c3 = attendanceRow.createCell(3);
	     							c3.setCellValue(nextDayStr);
	     							
	     							c4 = attendanceRow.createCell(4);
	     							c4.setCellValue("8:00");
	     							
	     							c5 = attendanceRow.createCell(5);
	     							c5.setCellValue("2.0 hr");
	     							
	     							c6 = attendanceRow.createCell(6);
	     							c6.setCellValue(statusOdd);
	     							r++;
	     							
	     						}
	     					}

	     					//row 58
	     					XSSFRow r58 = spreadsheet.createRow(58);
	     					Cell r58c1 = r58.createCell(1);
	     					r58c1.setCellValue("I " + name + " of NRIC/FIN: " + idNo + " have "
	     							+ "read and understood the the salary computation");
	     					
	     					//row 59
	     					XSSFRow r59 = spreadsheet.createRow(59);
	     					Cell r59c1 = r59.createCell(1);
	     					r59c1.setCellValue("stated herein and confirmed my acceptance. ");
	     					
	     					//row 60
	     					XSSFRow r60 = spreadsheet.createRow(60);
	     					Cell r60c1 = r60.createCell(1);
	     					r60c1.setCellValue("Employee Signature:");
	     					
	     					Cell r60c7 = r60.createCell(7);
	     					r60c7.setCellValue("Date:");
	     					
	     					Cell r60c8 = r60.createCell(8);
	     					r60c8.setCellValue(currentDt);
	     					
	     					
	     					System.out.println("writing ...");
	     					// .xlsx is the format for Excel Sheets...
	     					// writing the workbook into the file...
	     					FileOutputStream fos = new FileOutputStream(fileName);
	     					workbookEmailAttachment.write(fos);
	     					
	     					
	     					InputStream input = new FileInputStream(fileName);
	     					if(input != null) {
	     						String nextVal = OfficerPayslipManagerDAO.getNextVal() + "";
	     						OfficerPayslip officerPayslip = new OfficerPayslip(nextVal, idNo, input, timestamp);
	     						String value = OfficerPayslipManagerDAO.addOfficerPayslip(officerPayslip);
	     						System.out.println(idNo + " saved payslip to DB: " + value);
	     					}

	     					

	     					fos.close();
	     					workbookEmailAttachment.close();
	     				}else {
	     					message = message + " - " + idNo;
	     				}
	     			}

	     			wb.close();
	     			System.out.println("done with payslip portion");
	     			message = message + "Success for the other officers! Go to 'View Payslips' to verify payslip generation";
	     		}
	     		catch(Exception e) {
	     			message = message + " check logs: " + e.toString();
	     			System.out.println(e);
	     		}
	     		
     			String to = "k11.sivalingam@gmail.com";// change accordingly
     			final String user = "Shangeri1994@k11.com.sg";// change accordingly
     			final String password = "EbSDkwr+Hvc7!U57";// change accordingly

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

     				MimeMessage email = new MimeMessage(session);
     				email.setFrom(new InternetAddress(user));
     				email.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
     				email.setSubject("K11 Payslip Generation - Check PWM Compliance Update");
     				
     				// Create the message part
     				BodyPart messageBodyPart = new MimeBodyPart();
     				BodyPart messageBodyPart2 = new MimeBodyPart();
     				
     				// Fill the message
     				messageBodyPart.setText(message);
     				messageBodyPart2.setText(" If no ID numbers are listed, means all officer comply to PWM. ");
     				
     				Multipart multipart = new MimeMultipart();
     				multipart.addBodyPart(messageBodyPart);
     				multipart.addBodyPart(messageBodyPart2);
     				
     				// 6) set the multiplart object to the message object
     				email.setContent(multipart);
     				
     				// Send message
                    try{
                    	Transport.send(email);
                    }
                    catch(NullPointerException e){
                        System.out.println(e + " is occured");
                    }

     				System.out.println("email sent....");
     			} catch (MessagingException ex) {
     				message = message + " check logs: " + ex.toString();
     				ex.printStackTrace();
     			}
     			
     			PayslipManagerDAO.deleteAll();
	         }
	    });
	}

}
