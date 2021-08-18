<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="com.google.gdata.data.spreadsheet.CellEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.Cell"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">TOD/HOD Management System</label> <br>
			<b>How to use:</b> Please enter Site Details.
			<% 
				Site v = null;
				String recordType = "create";
			 	if (request.getAttribute("siteRecord") != null) {
			 		v = (Site) request.getAttribute("siteRecord");
			 		recordType = "update";
			 	}
			%>
			<center>
			<% if(recordType.equals("create")){ %>
				<form action="addSite" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="siteName">Site Name: </label> <input type="text"
								class="form-control" name="siteName"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
						<div class="form-group col-md-6">
							<label for="dayManpower">Day Shift Manpower: </label> <input type="text"
								class="form-control" name="dayManpower"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
						<div class="form-group col-md-6">
							<label for="nightManpower">Night Shift Manpower: </label> <input type="text"
								class="form-control" name="nightManpower"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit
							Record</button>
							
						<a href="/dashboard.jsp" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
			<%} 
			else{%>
				<form action="updateSite" method="post">
					<div class="form-row">
						<input type="hidden" id="siteId" name="siteId" value="<%=v.getSiteId()%>">
						<div class="form-group col-md-6">
							<label for="siteName">Site Name: </label> <input type="text"
								class="form-control" name="siteName"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getSiteName())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="dayManpower">Day Shift Manpower: </label> <input type="text"
								class="form-control" name="dayManpower"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
						<div class="form-group col-md-6">
							<label for="nightManpower">Night Shift Manpower: </label> <input type="text"
								class="form-control" name="nightManpower"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Update
							Record</button>
							
						<a href="/dashboard.jsp" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
			<%} %>
			</center>
		</div>
	</div>
</body>
</html>