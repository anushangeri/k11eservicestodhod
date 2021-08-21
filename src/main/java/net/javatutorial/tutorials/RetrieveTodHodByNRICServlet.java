package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.DAO.TodHodManagerDAO;
import net.javatutorial.entity.Site;
import net.javatutorial.entity.TodHodRecord;

/**
 * Servlet implementation class RetrieveTodHodByNRICServlet
 * To retrieve latest vehicle record to populate in addTodHodRecord.jsp
 * If the vehicle object is empty, then user will fill themselves - new user
 */
public class RetrieveTodHodByNRICServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("usertype"))) ? "" : (String) request.getSession(false).getAttribute("usertype");
		String idNo = (StringUtils.isEmpty((String) request.getSession(false).getAttribute("idNo"))) ? "" : (String) request.getSession(false).getAttribute("idNo");

		//from admin login view
		String idNoFromClient =  (StringUtils.isEmpty(request.getParameter("idNo"))) ? "" : request.getParameter("idNo");
				
		ArrayList<TodHodRecord> vList = null;
		TodHodRecord v = null;
		
		if(usertype != null && !StringUtils.isEmpty(usertype) && !StringUtils.isEmpty(idNoFromClient)
				&& usertype.toUpperCase().equals("ADMIN")) {
			vList = TodHodManagerDAO.retrieveByOfficerIdNo(idNoFromClient);
			if(vList != null && vList.size() > 0) {
				v = vList.get(0);
			}
		}
		else {
			if(idNo != null && !StringUtils.isEmpty(idNo)) {
				vList = TodHodManagerDAO.retrieveByOfficerIdNo(idNo);
				if(vList != null && vList.size() > 0) {
					v = vList.get(0);
				}
			}
		}
		//getting all the dropdown
		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
		
		request.setAttribute("todhodRecord", v);
		request.setAttribute("siteDropdown", siteDropdown);
		
        RequestDispatcher rd = request.getRequestDispatcher("addTodHodRecord.jsp");
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
