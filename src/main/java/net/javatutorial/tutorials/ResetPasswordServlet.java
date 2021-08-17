package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormatter;

import net.javatutorial.DAO.ClientAccountManagerDAO;
import net.javatutorial.entity.ClientAccount;
import net.javatutorial.tutorials.PasswordUtils;

import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;

/**
 * Servlet implementation class PasswordVerifiedServlet
 */
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String idNo = request.getParameter("idNo");
		String idType = request.getParameter("idType");
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("psw");
				
		//retrieving the hashed password by DB based on idNo entered by user
		ArrayList<ClientAccount> vList = ClientAccountManagerDAO.retrieveByID(idNo);
		boolean verified = false;
		String key = " ";
		String salt = " ";
		ClientAccount c = null;
		if(vList != null && vList.size() > 0 ) {
			c = vList.get(0);
			if(c != null) {
				key = c.getPassword();
				salt = c.getSalt();
				verified = PasswordUtils.verifyPassword(oldPassword, key, salt);
			}
		}
		String newSalt = " ";
		if(verified) {
			// update password
			//hashing the new password
			newSalt = PasswordUtils.generateSalt(512).get();
			String hashedPassword = PasswordUtils.hashPassword(newPassword, salt).get();
			c.setSalt(salt);
			c.setPassword(hashedPassword);
			String message = ClientAccountManagerDAO.updateClientAccountPassword(c);
			request.setAttribute("responseObj", message);
			RequestDispatcher rd = request.getRequestDispatcher("clientLogin.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("responseObj", "Wrong NRIC and Password entered. Please try again or contact K11 Admin.");
			RequestDispatcher rd = request.getRequestDispatcher("clientLogin.jsp");
			rd.forward(request, response);
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