package net.javatutorial.tutorials;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormatter;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

import net.javatutorial.DAO.EmployeesManagerDAO;
import net.javatutorial.DAO.EmployeesTblDAO;

import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;

/**
 * Servlet implementation class TodHodSearchServlet
 */
public class VehRegLoginVerifyServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ArrayList<String> responseObj = new ArrayList<String>();
		RequestDispatcher rd = null;
		
		String siteUser = request.getParameter("siteUser").trim();
		String password = request.getParameter("password").trim();
		//covert the string to date to compare:
		try {
			//make siteUser uppercase
        	if(siteUser != null && !siteUser.isEmpty() ){
        		siteUser = siteUser.toUpperCase();
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		boolean loginsuccessful = false;
		
		SpreadsheetService service = new SpreadsheetService("Form Responses 1");
		if(!StringUtils.isEmpty(siteUser) && !StringUtils.isEmpty(password)) {
			try {
	            String sheetUrl
	                    = "https://spreadsheets.google.com/feeds/list/116L_MDacE0331uQDZLRQD4UKpKXfHgWKcMFeD0ne324/10/public/values";

	            // Use this String as url
	            URL url = new URL(sheetUrl);

	            // Get Feed of Spreadsheet url
	            ListFeed lf = service.getFeed(url, ListFeed.class);
	            
	            //Iterate over feed to get cell value
	            for (ListEntry le : lf.getEntries()) {
	                
	                CustomElementCollection cec = le.getCustomElements();
	                
	                if (cec != null){
	                    String siteUserExcel = cec.getValue("siteuser").trim();
	                    //make siteUserExcel uppercase
	                	if(siteUserExcel != null && !siteUserExcel.isEmpty() ){
	                		siteUserExcel = siteUserExcel.toUpperCase();
	                	}
	                    //System.out.println("THE PROBLEM IS HERE siteUserExcel: " + siteUserExcel);
	                    String passwordExcel = cec.getValue("password");
	                    //System.out.println("THE PROBLEM IS HERE: " + dateofbirth);
	                    if(!StringUtils.isEmpty(siteUserExcel) && !StringUtils.isEmpty(passwordExcel)) {
	                    	if(siteUserExcel.equals(siteUser) && passwordExcel.equals(password) ) {
	                    		loginsuccessful = true;
	            				session.setAttribute("siteUser", siteUser);
	            				session.setAttribute("password", password);
	                    	}
	                    }
			
	                }
	            }
	            
	        }catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(loginsuccessful) {
			responseObj.add("Login successful: " + siteUser);
			request.setAttribute("responseObj", responseObj);
			rd = request.getRequestDispatcher("dashboard.jsp");
		}
		else{
			responseObj.add("Please try again: " + siteUser + " " +  password);
			request.setAttribute("responseObj", responseObj);
			rd = request.getRequestDispatcher("index.jsp");
		}
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
