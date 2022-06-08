package net.javatutorial.tutorials;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gdata.util.common.base.StringUtil;

import net.javatutorial.DAO.PayslipManagerDAO;
import net.javatutorial.entity.AttendanceUploadFile;
import net.javatutorial.entity.Payslip;

/**
 * Servlet implementation class CreatePayslipServlet
 * https://dev.to/codesharing/converting-excel-to-pdf-in-java-application-474i
 */
public class CreatePayslipServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AttendanceUploadFile p = PayslipManagerDAO.retrieveAll();
		
		SimpleDateFormat display = new SimpleDateFormat("dd-MMM-yyyy");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		String currentDt = display.format(timestamp);
		
		LocalDate localDate = LocalDate.now(ZoneId.of("GMT+08:00"));
		String fileName = "payslips_"+ localDate +".xlsx";
		
		System.out.println("reading each row in excel file upload...");		
		try {

			//for payslip email attachment:
			// workbook object
			XSSFWorkbook workbookEmailAttachment = new XSSFWorkbook();
			
			// obtaining input bytes from a file
			InputStream fis = p.getPayslip();
			// creating workbook instance that refers to .xls file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			// creating a Sheet object to retrieve the object
			XSSFSheet sheet = wb.getSheetAt(0);
			sheet.setColumnWidth(1, 3766);
			sheet.setColumnWidth(3, 3766);
			//evaluating cell type   
			FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
			//creating a list of payslips
			for (Row row : sheet) // iteration over row using for each loop
			{
				//only make payslip if there is a ID in column E (4)
				if(row.getCell(4) != null && !StringUtils.isEmpty((String) row.getCell(4).getStringCellValue())) {
					String idNo = row.getCell(4).getStringCellValue() == null ? " " : row.getCell(4).getStringCellValue();
					String name = row.getCell(5).getStringCellValue() == null ? " " : row.getCell(5).getStringCellValue();
					String current_dt = currentDt;
					
					int dayCount = (int) row.getCell(70).getNumericCellValue() ;

					double dayPay = row.getCell(70).getNumericCellValue() * row.getCell(0).getNumericCellValue();
						
					int nightCount = (int) row.getCell(71).getNumericCellValue();
					double nightPay = row.getCell(71).getNumericCellValue() * row.getCell(1).getNumericCellValue();
						
					double kpiPay = row.getCell(79).getNumericCellValue();
					double phMcAdhocPay = row.getCell(73).getNumericCellValue();
					double siteICPay = row.getCell(81).getNumericCellValue();
						
					int fescCount = (int) row.getCell(82).getNumericCellValue();
					//$10 per shift
					double fescPay = row.getCell(82).getNumericCellValue() * 10;
						
					int swvCount = (int) row.getCell(84).getNumericCellValue();	
					//$5 per shift
					double swvPay = row.getCell(84).getNumericCellValue() * 5;
						
					int ddjvCount = (int) row.getCell(85).getNumericCellValue() ;
					//$5 per shift
					double ddjvPay = row.getCell(85).getNumericCellValue() * 5;
						
					int alpsCount = (int) row.getCell(90).getNumericCellValue();
					//$5 per shift
					double alpsPay = row.getCell(90).getNumericCellValue() * 5;
						
					int delgroCount = (int) row.getCell(88).getNumericCellValue();
					double delgroPay = row.getCell(88).getNumericCellValue() * 5;

					int expandDNCount = (int) row.getCell(87).getNumericCellValue();	
					double expandDNPay = row.getCell(87).getNumericCellValue() * 5;
						
					int cnwDNCount = (int) row.getCell(89).getNumericCellValue() ;	
					double cnwPay = row.getCell(89).getNumericCellValue() * 5;
						
					int jlogCount = (int) row.getCell(83).getNumericCellValue();	
					double jlogCPPay = row.getCell(83).getNumericCellValue() * 5;
						
					int tvDormCount = (int) row.getCell(86).getNumericCellValue();	
					double tvDormPay = row.getCell(86).getNumericCellValue() * 10;

					double mbikePay = row.getCell(80).getNumericCellValue();
					double bonusPay = row.getCell(92).getNumericCellValue();
					double uniformAllowancePay = row.getCell(74).getNumericCellValue();
					double grossPay = row.getCell(75).getNumericCellValue();
					double deductionPay = row.getCell(93).getNumericCellValue();

					Map<Date, Double> paymentsMade = new HashMap<Date, Double>();
					//now to get the list of payments made
					int cellPaymentDate = 94;
					Row rowPaymentDate = sheet.getRow(1);
					double totalDispursed = 0.0;
					while(rowPaymentDate.getCell(cellPaymentDate).getDateCellValue() != null) {
						//has a date
						Date d = rowPaymentDate.getCell(cellPaymentDate).getDateCellValue();
						Double payment = 0.0;
						switch(formulaEvaluator.evaluateInCell(row.getCell(cellPaymentDate)).getCellType())  
						{  
							case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
							//getting the value of the cell as a number  
							payment = row.getCell(cellPaymentDate).getNumericCellValue();
							break;  
							case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
							//getting the value of the cell as a string
							String val = row.getCell(cellPaymentDate).getStringCellValue() == null 
							|| row.getCell(cellPaymentDate).getStringCellValue().trim().length() == 0
							? "0.0" : row.getCell(cellPaymentDate).getStringCellValue();	
							
							payment = Double.parseDouble(val);  
							break;  
						}  
						totalDispursed = totalDispursed + payment;
						paymentsMade.put(d, payment);
						cellPaymentDate++;
					}
					double advancedPay = totalDispursed;
					double balancePay = grossPay - advancedPay;
					
					Payslip payslip = new Payslip(idNo, name, current_dt, dayCount, dayPay, nightCount,
							nightPay, kpiPay, phMcAdhocPay, siteICPay, fescCount, fescPay,
							swvCount, swvPay, ddjvCount, ddjvPay, alpsCount, alpsPay, delgroCount,
							delgroPay, expandDNCount, expandDNPay, cnwDNCount, cnwPay, jlogCount,
							jlogCPPay, tvDormCount, tvDormPay, mbikePay, bonusPay,
							uniformAllowancePay, grossPay, advancedPay, deductionPay, balancePay,paymentsMade);
					
					//Now to add the actual days worked - the list of attendance at the bottom
					//We will capture it in ArrayList<String> - ArrayList<Date, Day or Night Shift, Status>
					//loop through the dates
					System.out.println("starting to read attendance");
					ArrayList<ArrayList<String>> attendance = new ArrayList<ArrayList<String>>();
					int aCol = 8; //attendance starts from column 8
					String checkColName = "";
					while(aCol <= 69) {
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
					System.out.println("creating sheet: " + payslip.getName());
					XSSFSheet spreadsheet = workbookEmailAttachment.createSheet(payslip.getName());
					
					//creating styles start
					XSSFFont bold = workbookEmailAttachment.createFont();
					bold.setBold(true);
				    
		            CellStyle topCornerLeft = workbookEmailAttachment.createCellStyle();  
		            topCornerLeft.setBorderLeft(CellStyle.BORDER_MEDIUM);    
		            topCornerLeft.setBorderTop(CellStyle.BORDER_MEDIUM);
		            topCornerLeft.setBorderBottom(CellStyle.BORDER_MEDIUM);
		            topCornerLeft.setFont(bold);
		            
		            CellStyle topMiddle = workbookEmailAttachment.createCellStyle();     
		            topMiddle.setBorderTop(CellStyle.BORDER_MEDIUM);
		            topMiddle.setBorderBottom(CellStyle.BORDER_MEDIUM);
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
		            System.out.println("creating header: " + payslip.getIdNo());
					XSSFRow header = spreadsheet.createRow(1);
					Cell idNoCell = header.createCell(1);
					idNoCell.setCellValue(payslip.getIdNo());
					idNoCell.setCellStyle(topCornerLeft); 
					
					header.createCell(2).setCellStyle(topMiddle);
					
					Cell nameCell = header.createCell(3);
					nameCell.setCellValue(payslip.getName());
					nameCell.setCellStyle(topMiddle);
					
					header.createCell(4).setCellStyle(topMiddle);
					header.createCell(5).setCellStyle(topMiddle);
					
					Cell currDtCell = header.createCell(6);
					currDtCell.setCellValue(payslip.getCurrent_dt());
					currDtCell.setCellStyle(topMiddle);
					
					header.createCell(7).setCellStyle(topCornerRight);
					
					System.out.println("creating content: " + payslip.getDayCount());
					XSSFRow row2 = spreadsheet.createRow(2);
					Cell c1r2 = row2.createCell(1);
					c1r2.setCellValue("Day ");
					c1r2.setCellStyle(contentColumn1);
					
					Cell c2r2 = row2.createCell(2);
					c2r2.setCellValue(payslip.getDayCount());
					c2r2.setCellStyle(contentColumn2);
					
					Cell c3r2 = row2.createCell(3);
					c3r2.setCellValue("$ " + payslip.getDayPay());
					c3r2.setCellStyle(contentColumn2);
					
					XSSFRow row3 = spreadsheet.createRow(3);
					Cell c1r3 = row3.createCell(1);
					c1r3.setCellValue("Night ");
					c1r3.setCellStyle(contentColumn1);

					Cell c2r3 = row3.createCell(2);
					c2r3.setCellValue(payslip.getNightCount());
					c2r3.setCellStyle(contentColumn2);

					Cell c3r3 = row3.createCell(3);
					c3r3.setCellValue("$ " + payslip.getNightPay());
					c3r3.setCellStyle(contentColumn2);
					
					XSSFRow row4 = spreadsheet.createRow(4);
					Cell c1r4 = row4.createCell(1);
					c1r4.setCellValue("KPI >26 dayshift ");
					c1r4.setCellStyle(contentColumn1);
					
					Cell c2r4 = row4.createCell(2);		
					c2r4.setCellStyle(contentColumn2);
					
					Cell c3r4 = row4.createCell(3);	
					c3r4.setCellValue("$ " + payslip.getKpiPay());					
					c3r4.setCellStyle(contentColumn2);
					
					XSSFRow row5 = spreadsheet.createRow(5);
					Cell c1r5 = row5.createCell(1);	
					c1r5.setCellValue("PH/MC/Ad-Hoc ");
					c1r5.setCellStyle(contentColumn1);
					
					Cell c2r5 = row5.createCell(2);	
					c2r5.setCellStyle(contentColumn2);
					
					Cell c3r5 = row5.createCell(3);	
					c3r5.setCellValue("$ " + payslip.getPhMcAdhocPay());					
					c3r5.setCellStyle(contentColumn2);
					
					XSSFRow row6 = spreadsheet.createRow(6);
					Cell c1r6 = row6.createCell(1);	
					c1r6.setCellValue("Site IC ");
					c1r6.setCellStyle(contentColumn1);
					
					Cell c2r6 = row6.createCell(2);	
					c2r6.setCellStyle(contentColumn2);
					
					Cell c3r6 = row6.createCell(3);	
					c3r6.setCellValue("$ " + payslip.getSiteICPay());
					c3r6.setCellStyle(contentColumn2);	
					
					XSSFRow row7 = spreadsheet.createRow(7);
					Cell c1r7 = row7.createCell(1);	
					c1r7.setCellValue("FESC ");
					c1r7.setCellStyle(contentColumn1);
					
					Cell c2r7 = row7.createCell(2);
					c2r7.setCellValue(payslip.getFescCount());
					c2r7.setCellStyle(contentColumn2);
					
					Cell c3r7 = row7.createCell(3);
					c3r7.setCellValue("$ " + payslip.getFescPay());
					c3r7.setCellStyle(contentColumn2);
					
					XSSFRow row8 = spreadsheet.createRow(8);
					Cell c1r8 = row8.createCell(1);
					c1r8.setCellValue("SWV ");
					c1r8.setCellStyle(contentColumn1);
					
					Cell c2r8 = row8.createCell(2);
					c2r8.setCellValue(payslip.getSwvCount());
					c2r8.setCellStyle(contentColumn2);
					
					Cell c3r8 = row8.createCell(3);
					c3r8.setCellValue("$ " + payslip.getSwvPay());
					c3r8.setCellStyle(contentColumn2);
					
					XSSFRow row9 = spreadsheet.createRow(9);
					Cell c1r9 = row9.createCell(1);
					c1r9.setCellValue("DDJV (D) ");
					c1r9.setCellStyle(contentColumn1);
					
					Cell c2r9 = row9.createCell(2);
					c2r9.setCellValue(payslip.getDdjvCount());
					c2r9.setCellStyle(contentColumn2);

					Cell c3r9 = row9.createCell(3);
					c3r9.setCellValue("$ " + payslip.getDdjvPay());
					c3r9.setCellStyle(contentColumn2);
					
					XSSFRow row10 = spreadsheet.createRow(10);
					Cell c1r10 = row10.createCell(1);
					c1r10.setCellValue("ALPS ( D) ");
					c1r10.setCellStyle(contentColumn1);
					
					Cell c2r10 = row10.createCell(2);
					c2r10.setCellStyle(contentColumn2);
					
					Cell c3r10 = row10.createCell(3);
					c3r10.setCellValue("$ " + payslip.getAlpsPay());
					c3r10.setCellStyle(contentColumn2);
					
					XSSFRow row11 = spreadsheet.createRow(11);
					Cell c1r11 = row11.createCell(1);
					c1r11.setCellValue("Delgro ");
					c1r11.setCellStyle(contentColumn1);
					
					Cell c2r11 = row11.createCell(2);
					c2r11.setCellValue(payslip.getDelgroCount());
					c2r11.setCellStyle(contentColumn2);
					
					Cell c3r11 = row11.createCell(3);
					c3r11.setCellValue("$ " + payslip.getDelgroPay());
					c3r11.setCellStyle(contentColumn2);
					
					XSSFRow row12 = spreadsheet.createRow(12);
					Cell c1r12 = row12.createCell(1);
					c1r12.setCellValue("Expand D&N ");
					c1r12.setCellStyle(contentColumn1);
					
					Cell c2r12 = row12.createCell(2);
					c2r12.setCellValue(payslip.getExpandDNCount());
					c2r12.setCellStyle(contentColumn2);
					
					Cell c3r12 = row12.createCell(3);
					c3r12.setCellValue("$ " + payslip.getExpandDNPay());
					c3r12.setCellStyle(contentColumn2);
					
					XSSFRow row13 = spreadsheet.createRow(13);
					Cell c1r13 = row13.createCell(1);
					c1r13.setCellValue("21CNW (D) ");
					c1r13.setCellStyle(contentColumn1);
					
					Cell c2r13 = row13.createCell(2);
					c2r13.setCellValue(payslip.getCnwDNCount());
					c2r13.setCellStyle(contentColumn2);
					
					Cell c3r13 = row13.createCell(3);
					c3r13.setCellValue("$ " + payslip.getCnwPay());
					c3r13.setCellStyle(contentColumn2);
					
					XSSFRow row14 = spreadsheet.createRow(14);
					Cell c1r14 = row14.createCell(1);
					c1r14.setCellValue("Jlog C&P ");
					c1r14.setCellStyle(contentColumn1);
					
					Cell c2r14 = row14.createCell(2);
					c2r14.setCellValue(payslip.getJlogCount());
					c2r14.setCellStyle(contentColumn2);
					
					Cell c3r14 = row14.createCell(3);
					c3r14.setCellValue("$ " + payslip.getJlogCPPay());
					c3r14.setCellStyle(contentColumn2);
					
					XSSFRow row15 = spreadsheet.createRow(15);
					Cell c1r15 = row15.createCell(1);
					c1r15.setCellValue("TVDorm ");
					c1r15.setCellStyle(contentColumn1);
					
					Cell c2r15 = row15.createCell(2);
					c2r15.setCellValue(payslip.getTvDormCount());
					c2r15.setCellStyle(contentColumn2);
					
					Cell c3r15 = row15.createCell(3);
					c3r15.setCellValue("$ " + payslip.getTvDormPay());
					c3r15.setCellStyle(contentColumn2);
					
					XSSFRow row16 = spreadsheet.createRow(16);
					Cell c1r16 = row16.createCell(1);
					c1r16.setCellValue("Mbike ");
					c1r16.setCellStyle(contentColumn1);
					
					Cell c2r16 = row16.createCell(2);
					c2r16.setCellStyle(contentColumn2);
					
					Cell c3r16 = row16.createCell(3);
					c3r16.setCellValue("$ " + payslip.getMbikePay());
					c3r16.setCellStyle(contentColumn2);
					
					XSSFRow row17 = spreadsheet.createRow(17);
					Cell c1r17 = row17.createCell(1);
					c1r17.setCellValue("Bonus ");
					c1r17.setCellStyle(contentColumn1);
					
					Cell c2r17 = row17.createCell(2);
					c2r17.setCellStyle(contentColumn2);
					
					Cell c3r17 = row17.createCell(3);
					c3r17.setCellValue("$ " + payslip.getBonusPay());
					c3r17.setCellStyle(contentColumn2);
					
					XSSFRow row18 = spreadsheet.createRow(18);
					Cell c1r18 = row18.createCell(1);
					c1r18.setCellValue("Uniform Allowance ");
					c1r18.setCellStyle(contentColumn1);
					
					Cell c2r18 = row18.createCell(2);
					c2r18.setCellStyle(contentColumn2);
					
					Cell c3r18 = row18.createCell(3);
					c3r18.setCellValue("$ " + payslip.getUniformAllowancePay());
					c3r18.setCellStyle(contentColumn2);
					
					XSSFRow row19 = spreadsheet.createRow(19);
					Cell c1r19 = row19.createCell(1);
					c1r19.setCellValue("Gross pay ");
					c1r19.setCellStyle(contentColumn1);
					
					Cell c2r19 = row19.createCell(2);
					c2r19.setCellStyle(contentColumn2);
					
					Cell c3r19 = row19.createCell(3);
					c3r19.setCellValue("$ " + payslip.getGrossPay());
					c3r19.setCellStyle(contentColumn2);
					
					XSSFRow row20 = spreadsheet.createRow(20);
					Cell c1r20 = row20.createCell(1);
					c1r20.setCellValue("Advance ");
					c1r20.setCellStyle(contentColumn1);
					
					Cell c2r20 = row20.createCell(2);
					c2r20.setCellStyle(contentColumn2);
					
					Cell c3r20 = row20.createCell(3);
					c3r20.setCellValue("$ " + payslip.getAdvancedPay());
					c3r20.setCellStyle(contentColumn2);
					
					XSSFRow row21 = spreadsheet.createRow(21);
					Cell c1r21 = row21.createCell(1);
					c1r21.setCellValue("Deduction ");
					c1r21.setCellStyle(contentColumn1);

					Cell c2r21 = row21.createCell(2);
					c2r21.setCellStyle(contentColumn2);
					

					Cell c3r21 = row21.createCell(3);
					c3r21.setCellValue("$ " + payslip.getDeductionPay());
					c3r21.setCellStyle(contentColumn2);
					
					XSSFRow row22 = spreadsheet.createRow(22);
					Cell c1r22 = row22.createCell(1);
					c1r22.setCellValue("Balance ");
					c1r22.setCellStyle(bottomCornerLeft);
					
					Cell c2r22 = row22.createCell(2);
					c2r22.setCellStyle(bottomMiddle);
					
					
					Cell c3r22 = row22.createCell(3);
					c3r22.setCellValue("$ " + payslip.getBalancePay());
					c3r22.setCellStyle(bottomMiddle);
					
					row22.createCell(4).setCellStyle(bottomMiddle);
					row22.createCell(5).setCellStyle(bottomMiddle);
					row22.createCell(6).setCellStyle(bottomMiddle);
					row22.createCell(7).setCellStyle(bottomCornerRight);
					
					for(int col = 4; col <= 7; col++) {
						for(int rw = 2; rw <=21; rw++) {
							XSSFRow r = spreadsheet.getRow(rw);
							Cell c = r.createCell(col);
							c.setCellStyle(contentColumn2);
						}
					}

					//for all the payments paid out
					Map<Date, Double> pm = payslip.getPaymentsMade();
					
					Cell c6r2 = row2.createCell(6);
					c6r2.setCellValue("Payment ");
					c6r2.setCellStyle(topMiddleHeader);
					
					Cell c7r2 = row2.createCell(7);
					c7r2.setCellValue("Amount ");
					c7r2.setCellStyle(topCornerHeader);
					Set<Date> dates = pm.keySet();
					int i = 3;
					for(Iterator<Date> it = dates.iterator(); it.hasNext(); ) {
						Date f = it.next();
						if(pm.get(f) > 0) {
							XSSFRow r = spreadsheet.getRow(i);
							Cell c6 = r.createCell(6);
							c6.setCellValue(display.format(f));
							c6.setCellStyle(contentColumn2);
							
							Cell c7 = r.createCell(7);
							c7.setCellValue("$ " + pm.get(f));
							c7.setCellStyle(contentColumn2);

							i++;
						}
					}
					
					//start at row 25 to list out attendance
					System.out.println("creating attendance content... ");
				    XSSFRow attendanceRowHeader = spreadsheet.createRow(25);
					Cell c1 = attendanceRowHeader.createCell(1);
					c1.setCellValue("Date ");
					c1.setCellStyle(contentColumn1);
					
					Cell c2 = attendanceRowHeader.createCell(2);
					c2.setCellValue("Time In ");
					c2.setCellStyle(contentColumn1);
					
					Cell c3 = attendanceRowHeader.createCell(3);
					c3.setCellValue("Date Out ");
					c3.setCellStyle(contentColumn1);
					
					Cell c4 = attendanceRowHeader.createCell(4);
					c4.setCellValue("Time Out ");
					c4.setCellStyle(contentColumn1);
					
					Cell c5 = attendanceRowHeader.createCell(5);
					c5.setCellValue("Break Hours ");
					c5.setCellStyle(contentColumn1);
					
					Cell c6 = attendanceRowHeader.createCell(6);
					c6.setCellValue("Type ");
					c6.setCellStyle(contentColumn1);

					SimpleDateFormat formatter5 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy"); 
					 
					int r = 26;
					for(int e = 0; e <= attendance.size() - 1; e++) {
						//even
						ArrayList<String> aEven = attendance.get(e);
						String dateEven = aEven.get(0) != null || !StringUtil.isEmpty(aEven.get(0)) ? aEven.get(0) : "";
						String statusEven = aEven.get(2) != null || !StringUtil.isEmpty(aEven.get(2)) ? aEven.get(2) : "";
						
						e++;
						
						//odd
						ArrayList<String> aOdd = attendance.get(e);
						String dateOdd = aOdd.get(0) != null || !StringUtil.isEmpty(aOdd.get(0)) ? aOdd.get(0) : "";
						String statusOdd = aOdd.get(2) != null || !StringUtil.isEmpty(aOdd.get(2)) ? aOdd.get(2) : "";
						
						if(dateEven.equals(dateOdd)) {
							Date dateEvenDt = formatter5.parse(dateEven);
							String d = display.format(dateEvenDt);
							//if day shift and night shift is the same
							if(statusEven.equals(statusOdd)) {
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

							}
							//if day shift 
							else if(statusEven.equals("1") && !statusOdd.equals("1")) {
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

							}
							//if night shift 
							else if(!statusEven.equals("1") && statusOdd.equals("1")) {
								XSSFRow attendanceRow = spreadsheet.createRow(r);
								c1 = attendanceRow.createCell(1);
								c1.setCellValue(d);
								
								c2 = attendanceRow.createCell(2);
								c2.setCellValue("20:00");
								
								c3 = attendanceRow.createCell(3);
								c3.setCellValue(d);
								
								c4 = attendanceRow.createCell(4);
								c4.setCellValue("8:00");
								
								c5 = attendanceRow.createCell(5);
								c5.setCellValue("2.0 hr");
								
								c6 = attendanceRow.createCell(6);
								c6.setCellValue(statusOdd);

							}
							//if day shift is X 
							else if(statusEven.equals("X") && !statusOdd.equals("X") && !statusOdd.equals("1")) {
								XSSFRow attendanceRow = spreadsheet.createRow(r);
								c1 = attendanceRow.createCell(1);
								c1.setCellValue(d);
								
								c2 = attendanceRow.createCell(2);
								c2.setCellValue("20:00");
								
								c3 = attendanceRow.createCell(3);
								c3.setCellValue(d);
								
								c4 = attendanceRow.createCell(4);
								c4.setCellValue("8:00");
								
								c5 = attendanceRow.createCell(5);
								c5.setCellValue("2.0 hr");
								
								c6 = attendanceRow.createCell(6);
								c6.setCellValue(statusOdd);

							}
							//if night shift is X
							else if(statusOdd.equals("X") && !statusEven.equals("X") && !statusEven.equals("1")) {
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

							}
							r++;
						}
						
					}
				}
				
			}
			
			System.out.println("writing ...");
			// .xlsx is the format for Excel Sheets...
			// writing the workbook into the file...
			FileOutputStream fos = new FileOutputStream(fileName);
			workbookEmailAttachment.write(fos);
			fos.close();
			workbookEmailAttachment.close();
			wb.close();
			
			System.out.println("writing finished, sending email ...");
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
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("K11 eService Attendance");

				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();

				// Fill the message
				messageBodyPart.setText(
						"K11 eService Attendance - test");

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
		}
		catch(Exception e) {
			System.out.println(e);
		}
		String message = PayslipManagerDAO.deleteAll();
		request.setAttribute("didItWork", message);
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
