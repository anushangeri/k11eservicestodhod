package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.Date;
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

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormatter;

import net.javatutorial.DAO.EmployeesManagerDAO;
import net.javatutorial.DAO.EmployeesTblDAO;

import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;

/**
 * Servlet implementation class TodHodSearchServlet
 */
public class TodHodSearchServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String site = request.getParameter("site");
		String idNo = request.getParameter("idNo");
		String shift = request.getParameter("shift");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		ArrayList<String> responseObj = new ArrayList<String>();
		responseObj.add(site);
		responseObj.add(idNo);
		responseObj.add(from);
		responseObj.add(to);
		request.setAttribute("responseObj", responseObj);
		RequestDispatcher rd = null;
		if (shift.equals("Day")){
			rd = request.getRequestDispatcher("todhodday.jsp");
		}
		else{
			rd = request.getRequestDispatcher("todhodnight.jsp");
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
