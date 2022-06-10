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
<script type="text/javascript">
</script>
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Payslip Management System</label> <br> 
			<b>How to use:</b>Please upload attendance file, then click "Create Payslip"<br>
			<%
			 String message = (String) request.getAttribute("message");
			 if (message != null && !StringUtils.isEmpty(message)) {
			 %>
				<p><%=message%></p><br>
			<%
			}
			%>
			<br>
			<small>
			Things to note for input file:  <br>
			- use 123 - no formulars <br>
			- convert all data type of columns to "general" format  - don't worry if date is a number  <br>
			- remember to remove backslash from "employee name"  <br>
			<br>
			*An email will be sent to k11.sivalingam@gmail.com of any errors or PWM non-compliance is caught <br>
			</small>
			<center>
				<form action="uploadFilePayslip" enctype='multipart/form-data' method="post">
					<div class="form-row">
						<div class="form-group">
							<label for="uploadFile">Upload File: </label> <input type="file"
								class="form-control" name="uploadFile">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="submit" value="Submit File" class="btn btn-primary btn-lg active">
						</div>
						<div class="form-group col-md-6">
							<a href="/dashboard.jsp" class="btn btn-warning btn-lg active"
								role="button" aria-pressed="true">Back</a>
						</div>
					</div>
				</form>
				<br>
				<form action="generateOfficerPayslip" method="post">
					<div class="form-row">
						<div class="form-group">
							<input type="submit" value="Create Payslip" class="btn btn-primary btn-lg active"
							    onclick="document.getElementById('loader').style.display = 'block';">
						</div>
					</div>
				</form>
				<br>
				<div id="loader"  style="display: none;">
					<img src="1473.gif"/>
					<label>Please Wait..generating payslips. If page times out, just go back to previous page and log in, DO NOT REFRESH as it will create duplicate payslips. 
					It happens when there is a lot of payslips to generate. Programme is still running in the background with no issues.</label>
				</div>
						
			</center>
		</div>
	</div>
</body>
</html>