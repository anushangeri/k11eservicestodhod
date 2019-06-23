
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
    	<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
        <!-- Bootstrap Date-Picker Plugin -->
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
        <script>
        	$(document).ready(function(){
		      var date_input=$('input[name="from"]'); //our date input has the name "date"
		      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
		      var options={
		        format: 'mm/dd/yyyy',
		        container: container,
		        todayHighlight: true,
		        autoclose: true,
		      };
		      date_input.datepicker(options);
		    })
		    
		    $(document).ready(function(){
		      var date_input=$('input[name="to"]'); //our date input has the name "date"
		      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
		      var options={
		        format: 'mm/dd/yyyy',
		        container: container,
		        todayHighlight: true,
		        autoclose: true,
		      };
		      date_input.datepicker(options);
		    })
        </script>
	</head>

	<body>
	<%
	ArrayList<String> dutySites = new ArrayList<String>();
	SpreadsheetService service = new SpreadsheetService("K11CLICKS: DROPDOWN EXCEL");
	 try {
     	//Dropdown for marital status START
         String dutySitesUrl
                 = "https://spreadsheets.google.com/feeds/list/116L_MDacE0331uQDZLRQD4UKpKXfHgWKcMFeD0ne324/9/public/values";
         // Use this String as url
         URL dutySitesurl = new URL(dutySitesUrl);

         // Get Feed of Spreadsheet url
         ListFeed dutySiteslf = service.getFeed(dutySitesurl, ListFeed.class);
        
         for (ListEntry le : dutySiteslf.getEntries()) {
             CustomElementCollection cec = le.getCustomElements();
             dutySites.add(cec.getValue("dutysites").trim());
         }
       //Dropdown for marital status END
	 } catch (Exception e) {
     	%>
			<h1><%=e %></h1>
			<%
     }
	%>
		<form action="todHodSearch" method="post">
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="site">Site: </label>
		      <select name="site" class="form-control">
		      	<%
				for(int i=0; i < dutySites.size(); i++)
				{
				%>
				<option value="<%=dutySites.get(i)%>"> <%=dutySites.get(i)%></option>
				<% } %>
		      </select>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="idNo">NRIC/FIN: </label>
		      <input type="text" class="form-control" name="idNo" placeholder="Enter NRIC/FIN">
		    </div>
		    <div class="form-group col-md-6">
		      <label for="from">From: </label>
		      <input class="form-control" id="date" name="from" placeholder="MM/DD/YYYY" type="text" required/>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="to">To: </label>
		      <input class="form-control" id="date" name="to" placeholder="MM/DD/YYYY" type="text"  required/>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="shift">Select Shift Type: </label>
		      <select class="form-control" name="shift">
			    <option selected="selected">Day</option>
			    <option>Night</option>
			  </select>
		    </div>
		    <button type="submit" class="btn btn-primary">Search NRIC/FIN</button>
		    </div>
		</form>
    </body>
</html>
