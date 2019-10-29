<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.google.gdata.data.spreadsheet.CellEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.Cell"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gdata.client.spreadsheet.SpreadsheetService"%>
<%@page import="com.google.gdata.data.spreadsheet.CustomElementCollection"%>
<%@page import="com.google.gdata.data.spreadsheet.ListEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.ListFeed"%>
<%@page import="com.google.gdata.util.ServiceException"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
</head>

<body>

	<%
		ArrayList<String> responseObj = (ArrayList<String>) request.getAttribute("responseObj");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date fromDt = null;
		Date toDt = null;
		String site = "";
		String idNo = "";
		String from = "";
		String to = "";
		if (responseObj != null) {
			site = responseObj.get(0);
			idNo = responseObj.get(1);
			from = responseObj.get(2);
			to = responseObj.get(3);
// 			System.out.println("idNo: " + idNo);
// 			System.out.println("toDt before format: " + to);
	        try {
	        	if(from.length() != 0 && !StringUtils.isEmpty(from)){
	        		fromDt = dateFormat.parse(from);
	        	}
	        	if(to.length() != 0 && !StringUtils.isEmpty(to)){
	        		toDt = dateFormat.parse(to);
	        	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        SpreadsheetService service = new SpreadsheetService("Form Responses 1");
        SpreadsheetService service2 = new SpreadsheetService("Sheet1");
        try {
        	List<TodHodPair> todHodPairs = new ArrayList<TodHodPair>();
            String sheetUrl
                    = //1TwURCxMStzOp_jFMisNFF01PswassfcM-J4Ma90o23A (test)
                    //1i_3_wI3ClPXE_nX4biN3oNrqxMgyswPuzklAx8mwivY  (real)
                    //1nuQlSMmThaj3YxBktjn771wvzZflDwmS746STcsUcJI (real v2)
                    "https://spreadsheets.google.com/feeds/list/1SCtQDA0BESQ51VLCm4IKq_XKbDBMkPwhcRCXgb7TNVA/2/public/values";

            // Use this String as url
            URL url = new URL(sheetUrl);

            // Get Feed of Spreadsheet url
            ListFeed lf = service.getFeed(url, ListFeed.class);
            
            //Iterate over feed to get cell value
            ArrayList<TodHodDetails> allTodDetails = new ArrayList<TodHodDetails>();
            ArrayList<TodHodDetails> allHodDetails = new ArrayList<TodHodDetails>();
            for (ListEntry le : lf.getEntries()) {
                
                CustomElementCollection cec = le.getCustomElements();
                
                if (cec != null){
                    String enternricfin = cec.getValue("securityofficernricfinnumber").trim();
                    //System.out.println("THE PROBLEM IS HERE: " + enternricfin + " " + idNo);
                    String shift = cec.getValue("shift");
                    //System.out.println("THE PROBLEM IS HERE: " + shift);
                    String timestamp = cec.getValue("timestamp");
                    //System.out.println("THE PROBLEM IS HERE: " + timestamp);
                    String securityofficername = cec.getValue("securityofficername");
                    //System.out.println("THE PROBLEM IS HERE: " + securityofficername);
                    String date = cec.getValue("date");
                    //System.out.println("THE PROBLEM IS HERE: " + date);
                    String time = cec.getValue("time");
                    //System.out.println("THE PROBLEM IS HERE: " + time);
                    String areyoutodhod = cec.getValue("areyoutodorhod");
                    //System.out.println("THE PROBLEM IS HERE: " + areyoutodhod);
                    String dutysite = cec.getValue("todhoddutysites");
                    //System.out.println("THE PROBLEM IS HERE: " + dutysite);
                    String standbyremark = cec.getValue("reportingofficername");
                       
                    if(!StringUtils.isEmpty(site) || !StringUtils.isEmpty(idNo)){
	                    	if(!StringUtils.isEmpty(site)  && StringUtils.isEmpty(idNo)){
	                    		//if search by site only
	                    		//OC - On Course, MC - Medical Leave, AL - Annual Leave, HC - Hospital Leave
// 	                    		if(dutysite != null && !dutysite.isEmpty() && !dutysite.toUpperCase().contains("OC") && !dutysite.toUpperCase().contains("AL")
// 	                                    && !dutysite.toUpperCase().contains("MC") && !dutysite.toUpperCase().contains("HC")
// 	                                    && !dutysite.toUpperCase().contains("RD") && !dutysite.toUpperCase().contains("PH")
// 	                                    && !dutysite.toUpperCase().contains("UL") && dutysite.equals(site)){
	                            if(dutysite != null && !dutysite.isEmpty() && dutysite.equals(site)){
	
	                                if (areyoutodhod.toUpperCase().contains("TOD")) {
	                                TodHodDetails todDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                        securityofficername, date, time, areyoutodhod,
	                                        dutysite, standbyremark);
	                                todDetails.setTimestamp(timestamp);
	                                if((todDetails.getDate().compareTo(fromDt) >= 0 && todDetails.getDate().compareTo(toDt) <= 0)){
	                                		allTodDetails.add(todDetails);
	                                	}
	                                }
	
	                                if (areyoutodhod.toUpperCase().contains("HOD")) {
	                                    TodHodDetails hodDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                            securityofficername, date, time, areyoutodhod,
	                                            dutysite, standbyremark);
	                                    hodDetails.setTimestamp(timestamp);
	                                    allHodDetails.add(hodDetails);
	                                }
	                            }
	                    	}
	                    	if(StringUtils.isEmpty(site)   && !StringUtils.isEmpty(idNo)){
	                    		//if search by nric/fin only
	                    		//OC - On Course, MC - Medical Leave, AL - Annual Leave, HC - Hospital Leave
	                            if(idNo != null && !idNo.isEmpty() && idNo.contains(enternricfin)){
	                                if (areyoutodhod.toUpperCase().contains("TOD")) {
	                                TodHodDetails todDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                        securityofficername, date, time, areyoutodhod,
	                                        dutysite, standbyremark);
	                                todDetails.setTimestamp(timestamp);
// 	                                System.out.println("comparing: " + todDetails.getDate().compareTo(fromDt));
		                                if((todDetails.getDate().compareTo(fromDt) >= 0 && todDetails.getDate().compareTo(toDt) <= 0)){
	                                		allTodDetails.add(todDetails);
	                                	}
	                                }
	
	                                if (areyoutodhod.toUpperCase().contains("HOD")) {
	                                    TodHodDetails hodDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                            securityofficername, date, time, areyoutodhod,
	                                            dutysite, standbyremark);
	                                    hodDetails.setTimestamp(timestamp);
	                                    allHodDetails.add(hodDetails);
	                                }
	                            }
	                    	}
	                    	if(!StringUtils.isEmpty(site)  && !StringUtils.isEmpty(idNo)){
	                    		//if search by both
	                    		//OC - On Course, MC - Medical Leave, AL - Annual Leave, HC - Hospital Leave
	                            if(dutysite != null && !dutysite.isEmpty() && idNo.contains(enternricfin) && dutysite.equals(site)){
	
	                                if (areyoutodhod.toUpperCase().contains("TOD")) {
	                                TodHodDetails todDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                        securityofficername, date, time, areyoutodhod,
	                                        dutysite, standbyremark);
	                                todDetails.setTimestamp(timestamp);
		                                if((todDetails.getDate().compareTo(fromDt) >= 0 && todDetails.getDate().compareTo(toDt) <= 0)){
	                                		allTodDetails.add(todDetails);
	                                	}
	                                }
	
	                                if (areyoutodhod.toUpperCase().contains("HOD")) {
	                                    TodHodDetails hodDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                            securityofficername, date, time, areyoutodhod,
	                                            dutysite, standbyremark);
	                                    hodDetails.setTimestamp(timestamp);
	                                    allHodDetails.add(hodDetails);
	                                }
	                            }
	                    	}
	                    	
                    	}
	                    else{
	                		//if search by date (minimum requirement)
	                		//OC - On Course, MC - Medical Leave, AL - Annual Leave, HC - Hospital Leave
	                        if(fromDt != null && !fromDt.toString().isEmpty()){
	
	                            if (areyoutodhod.toUpperCase().contains("TOD")) {
	                            TodHodDetails todDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                    securityofficername, date, time, areyoutodhod,
	                                    dutysite, standbyremark);
	                            todDetails.setTimestamp(timestamp);
	                                if((todDetails.getDate().compareTo(fromDt) >= 0 && todDetails.getDate().compareTo(toDt) <= 0)){
	                            		allTodDetails.add(todDetails);
	                            	}
	                            }
	
	                            if (areyoutodhod.toUpperCase().contains("HOD")) {
	                                TodHodDetails hodDetails = new TodHodDetails(enternricfin, shift, timestamp,
	                                        securityofficername, date, time, areyoutodhod,
	                                        dutysite, standbyremark);
	                                hodDetails.setTimestamp(timestamp);
	                                allHodDetails.add(hodDetails);
	                            }
	                        }
	                	}
                    }	
             }// for (ListEntry le : lf.getEntries())
         		
          //find the HOD pair using nric, site, shift and entry day must be on the day off or the next day
            if (!allTodDetails.isEmpty()) {
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
                SimpleDateFormat datetimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                SimpleDateFormat datetime24Format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                for (TodHodDetails eachTodDetail : allTodDetails) {
                    
                    if( eachTodDetail.getShift().equals("Day") && !eachTodDetail.getDateAsStr().isEmpty()
                            && eachTodDetail.getDateAsStr() != null && !eachTodDetail.getTimeAsStr().isEmpty()
                            && eachTodDetail.getTimeAsStr() != null){
                    
//                    if(eachTodDetail.getAreyoutodhod() ==null
//                                    || eachTodDetail.getDateAsStr() == null
//                                    || eachTodDetail.getDutysite() == null
//                                    || eachTodDetail.getEnternricfin() == null
//                                    || eachTodDetail.getSecurityofficername()== null
//                                    || eachTodDetail.getShift() == null
//                                    || eachTodDetail.getTimeAsStr()== null
//                                    || eachTodDetail.getTimestamp() == null
//                                    || eachTodDetail.getTimestampAsStr() == null){
//                                System.out.println("THIS THE CAUSE tOD: " + eachTodDetail.toString());
//                            }
                    String enternricfin = eachTodDetail.getEnternricfin().trim();
                    String todShift = eachTodDetail.getShift();
                    String timestampAsStr = eachTodDetail.getTimestampAsStr();
                    Timestamp timestamp = eachTodDetail.getTimestamp();
                    String securityofficername = eachTodDetail.getSecurityofficername();
                    String todDateAsStr = eachTodDetail.getDateAsStr();
                    String todTimeAsStr = eachTodDetail.getTimeAsStr();
                    String areyoutodhod = eachTodDetail.getAreyoutodhod();
                    String dutysite = eachTodDetail.getDutysite();
                    
                    //doing 12 hour check
                    boolean isMoreThan12Hr = false;
                    Long workingHours = 0L;
                    
                    String hodDateAsStr = "";
                    String hodTimeAsStr = "";
                    String hodShift = "";
                    String standbyremark = "";

                    //getting date from timestamp for TOD
                    Date todDateFromTS = eachTodDetail.getDate();
                    String todDateFromTSAsStr = dateFormat.format(todDateFromTS);
                    Date todDateFromTSAsDate = dateFormat.parse(todDateFromTSAsStr);
                   
                    
                    //getting TOD time to check if HOD time is within 12 hours
                    //Date todTimeFromTSAsDate = timeFormat.parse(eachTodDetail.getTimeAsStr());
                    String todDateTime = todDateAsStr + " " + todTimeAsStr;
                    //System.out.println("toddateTimeStr " + todDateTime);
                    Date toddateTime = datetimeFormat.parse(todDateTime);
                    String todDateTime24 = datetime24Format.format(toddateTime);
                    //System.out.println("toddateTime " + todDateTime24);
                    Date toddateTime24 = datetime24Format.parse(todDateTime24);
                    //to display tod and hod as a pair
                    if (allHodDetails.size() > 0) {
                        //find HOD pair
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(todDateFromTS);
                        cal.add(Calendar.DATE, 1); //minus number would decrement the days
                        //Long milisecondsOneDay = Long.valueOf(1 * 24 * 60 * 60 * 1000);
                        //Timestamp timestampTheDayAfter = new Timestamp(timestamp.getTime() + milisecondsOneDay);
                        Date theDayAfter = cal.getTime();

                        String theDayAfterAsStr = dateFormat.format(theDayAfter);
                        Date theDayAfterAsDate = dateFormat.parse(theDayAfterAsStr);

                        for (Iterator<TodHodDetails> iterator = allHodDetails.iterator(); iterator.hasNext();) {
                            TodHodDetails eachHodDetail = iterator.next();
                            //getting the date from timestamp
                            Date hodDateFromTS = eachHodDetail.getDate();
                            String hodDateFromTSAsStr = dateFormat.format(hodDateFromTS);
                            Date hodDateFromTSAsDate = dateFormat.parse(hodDateFromTSAsStr);
                            //System.out.println(todDateFromTSAsDate + " " + hodDateFromTSAsDate + " " + theDayAfterAsDate);
//                            if(eachHodDetail.getAreyoutodhod() ==null
//                                    || eachHodDetail.getDateAsStr() == null
//                                    || eachHodDetail.getDutysite() == null
//                                    || eachHodDetail.getEnternricfin() == null
//                                    || eachHodDetail.getSecurityofficername()== null
//                                    || eachHodDetail.getShift() == null
//                                    || eachHodDetail.getTimeAsStr()== null
//                                    || eachHodDetail.getTimestamp() == null
//                                    || eachHodDetail.getTimestampAsStr() == null){
//                                System.out.println("THIS THE CAUSE HOD: " + eachHodDetail.toString());

                            if (enternricfin.equalsIgnoreCase(eachHodDetail.getEnternricfin())
                                    && todShift.equals(eachHodDetail.getShift())
                                    && dutysite.equals(eachHodDetail.getDutysite())
                                    && (hodDateFromTSAsDate.compareTo(theDayAfterAsDate) == 0
                                    || hodDateFromTSAsDate.compareTo(todDateFromTSAsDate) == 0)) {

                                Date st = timeFormat.parse(eachHodDetail.getTimeAsStr());
                                cal.setTime(st);
                                
                                //getting HOD time to check if HOD time is within 12 hours from TOD
                                //Date hodTimeFromTSAsDate = timeFormat.parse(eachHodDetail.getTimeAsStr());
                    
                                //if day shift, hod should be PM and date should be on the day off
                               if (cal.get(Calendar.AM_PM) == Calendar.PM && todShift.equals("Day") && hodDateFromTSAsDate.compareTo(todDateFromTSAsDate) == 0
                                     && !eachHodDetail.getDateAsStr().isEmpty() && eachHodDetail.getDateAsStr() != null) {
                            	   //System.out.println(hodDateFromTSAsDate + " Day " + todDateFromTSAsDate);
                                   hodDateAsStr = eachHodDetail.getDateAsStr();
                                   hodTimeAsStr = eachHodDetail.getTimeAsStr();
                                   
                                   //getting HOD time to check if HOD time is within 12 hours from TOD
                                   //Date hodTimeFromTSAsDate = timeFormat.parse(eachHodDetail.getTimeAsStr());
                                   String hodDateTime = hodDateAsStr + " " + hodTimeAsStr;
                                   Date hoddateTime = datetimeFormat.parse(hodDateTime);
                                   //System.out.println("hoddateTime " + hoddateTime);
                                   String hodDateTime24 = datetime24Format.format(hoddateTime);
                                   //System.out.println("hodDateTime24 " + hodDateTime24);
                                   Date hoddateTime24 = datetime24Format.parse(hodDateTime24);
                                   
                                   hodShift = eachHodDetail.getShift();
                                   standbyremark = eachHodDetail.getStandbyremark();
                                   
//                                   Long diff = hodTimeFromTSAsDate.getTime() - todTimeFromTSAsDate.getTime() - 5400000;
//                                   workingHours = diff / (60 * 60 * 1000) % 24;
                                   //in milliseconds
                                   long diff = hoddateTime24.getTime() - toddateTime24.getTime();
                                   workingHours = diff / (60 * 60 * 1000) % 24;                                
                                                                    
                                   if(workingHours > 12){
                                     isMoreThan12Hr = true; 
                                   }
                                   iterator.remove();
                                 }
                                //if night shift, hod should be AM and date should be the day after
//                                 if (cal.get(Calendar.AM_PM) == Calendar.AM && todShift.equals("Night") && (hodDateFromTSAsDate.compareTo(theDayAfterAsDate) == 0)
//                                         && !eachHodDetail.getDateAsStr().isEmpty() && eachHodDetail.getDateAsStr() != null) {
//                                     //System.out.println(hodDateFromTSAsDate + " Night " + todDateFromTSAsDate);
//                                     hodDateAsStr = eachHodDetail.getDateAsStr();
//                                     hodTimeAsStr = eachHodDetail.getTimeAsStr();
//                                     //getting HOD time to check if HOD time is within 12 hours from TOD
//                                     //Date hodTimeFromTSAsDate = timeFormat.parse(eachHodDetail.getTimeAsStr());
//                                     String hodDateTime = hodDateAsStr + " " + hodTimeAsStr;
//                                     Date hoddateTime = datetimeFormat.parse(hodDateTime);
//                                     //System.out.println("hoddateTime " + hoddateTime);
//                                     String hodDateTime24 = datetime24Format.format(hoddateTime);
//                                     //System.out.println("hodDateTime24 " + hodDateTime24);
//                                     Date hoddateTime24 = datetime24Format.parse(hodDateTime24);
                                    
//                                     hodShift = eachHodDetail.getShift();
//                                     standbyremark = eachHodDetail.getStandbyremark();
// //                                    Long hr = (long) 24 * 60 * 60 * 1000;
// //                                    Long diff = (hr - toddateTime.getTime()) + (hr + hoddateTime.getTime())  - 5400000;
//                                     //in milliseconds
//                                     long diff = hoddateTime24.getTime() - toddateTime24.getTime();
//                                     workingHours = diff / (60 * 60 * 1000) % 24;
                                    
//                                     if(workingHours > 12){
//                                        isMoreThan12Hr = true; 
//                                     }
//                                     iterator.remove();
//                                 }
                            }
                            
                        }
                    }
                  //add into session, all the tod hod pair
                    todHodPairs.add(new TodHodPair(enternricfin, todShift, timestampAsStr, securityofficername.toUpperCase(),
                    		todDateAsStr, todTimeAsStr, ((hodDateAsStr.isEmpty()) ? "Invalid Data" : hodDateAsStr),
                    		((hodTimeAsStr.isEmpty()) ? "Invalid Data" : hodTimeAsStr), dutysite, standbyremark));
                    
        
                 }
                    
                  
                } //for (TodHodDetails eachTodDetail : allTodDetails)
                session.setAttribute("todHodPairs", todHodPairs);
             }	// if (!allTodDetails.isEmpty())
            
          } catch (Exception e) {
			e.printStackTrace();
		}
                	
               	
            
    %>


        <div style="display: block; width: 80%" id="todhodtablediv">
	            <display:table name="sessionScope.todHodPairs" pagesize="20"
	                           export="true" sort="list" class="table">
	                <display:column property="enternricfin" title="NRIC/FIN"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="securityofficername" title="Name"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="shift" title="Shift"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="todDateAsStr" title="TOD Date"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="todTimeAsStr" title="TOD Time"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="hodDateAsStr" title="HOD Date"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="hodTimeAsStr" title="HOD Time"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="dutysite" title="Duty Site"
	                                sortable="true" headerClass="sortable" />
	                <display:column property="standbyremark" title="Stand by Remark"
	                                sortable="true" headerClass="sortable" />
	            </display:table>
        </div>
        <br>
       	<br>
       <center><a href="todhodsearch.jsp" class="btn btn-warning btn-lg active" role="button" aria-pressed="true">Back</a></center>
    </body>
</html>
