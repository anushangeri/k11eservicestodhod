<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page import="net.javatutorial.DAO.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	//using the usertype to determine what time of TOD/HOD to display
	String usertype ="";
	ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
	if ( !(session.getAttribute("usertype") == null)) {
			usertype = (String) session.getAttribute("usertype");
	}
	String nricfin = "";
	if ( !(session.getAttribute("nricfin") == null)) {
		nricfin = (String) session.getAttribute("nricfin");
	}
	//clear session when user press back
	if(session.getAttribute("todHodPairs") != null){
		session.removeAttribute("todHodPairs");
	}
	%>
	<center>
		<form action="todHodSearch" method="post">
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="site">Site: </label>
		      <select name="site" class="form-control">
		      	<%
				for(Site s: siteDropdown)
				{
				%>
				<option value="<%=s.getSiteName()%>"> <%=s.getSiteName()%></option>
				<% } %>
		      </select>
		    </div>
		    <div class="form-group col-md-6">
		    <%if (!StringUtils.isEmpty(usertype)){
		    	if(usertype.equals("OFFICER") && !StringUtils.isEmpty(nricfin)){
		    	%>
		    		 <input type="hidden" id="idNo" name="idNo" value=<%=nricfin%>>
		    	<%
		    	}
		    	else{//if not K11SECURITY, means its admin
		    	%>
		    		<label for="idNo">NRIC/FIN: </label>
		      		<input type="text" class="form-control" name="idNo" placeholder="Enter NRIC/FIN">
		    	<%	
		    	}
		    } 
		    else{//in case session empty
		    	%>
		    		<label for="idNo">NRIC/FIN: </label>
		      		<input type="text" class="form-control" name="idNo" placeholder="Enter NRIC/FIN">
		    	<%	
		    	}
		    %>
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
		    <button type="submit" class="btn btn-primary">VIEW TOD/HOD</button>
		    </div>
		</form>
		</center>
    </body>
</html>
