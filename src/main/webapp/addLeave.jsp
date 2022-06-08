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
			<label class="heading">Employee Management System</label> <br>
			<b>How to use:</b> Please enter Leave Details.
			<center>
			<%
			String idNo = "";
			if (request.getSession(false).getAttribute("idNo") != null) {
		 		idNo = (String) request.getSession(false).getAttribute("idNo");
		 	}
			%>
				<form action="addLeave" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="idNo">Officer ID Number: </label> <input type="text"
								class="form-control" name="idNo" value=<%=idNo%> readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="leaveType">Leave Type: </label> 
							<select class="form-control" id="leaveType" name="leaveType">
								<option>ANNUAL LEAVE</option>
								<option>ANNUAL SICK LEAVE</option>
								<option>ANNUAL HOSPITAL LEAVE</option>
						    </select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="leaveStartDate">Start Date: </label> 
							<input type="date" id="leaveStartDate" name="leaveStartDate">
						</div>
						<div class="form-group col-md-6">
							<label for="leaveEndDate">End Date: </label> 
							<input type="date" id="leaveEndDate" name="leaveEndDate">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="remarks">Remarks: </label> <input type="text"
								class="form-control" name="remarks" >
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
			</center>
		</div>
	</div>
</body>
</html>