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
			<b>How to use:</b> Please enter Employee Details.
			<center>
				<form action="addEmployee" enctype='multipart/form-data' method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="idNo">ID Number: </label> <input type="text"
								class="form-control" name="idNo"
								oninput="this.value = this.value.toUpperCase()" minlength="4"
								maxlength="9">
						</div>
						<div class="form-group col-md-6">
							<label for="annualLeave">Paid Annual Leave Per Year: </label> <input type="number"
								class="form-control" name="annualLeave"
								required>
						</div>
						<div class="form-group col-md-6">
							<label for="annualOutpatientLeave">Paid Outpatient Sick Leave Per Year: </label> <input type="number"
								class="form-control" name="annualOutpatientLeave"
								required>
						</div>
						<div class="form-group col-md-6">
							<label for="annualHospitalLeave">Paid Hospitalisation Leave Per Year: </label> <input type="number"
								class="form-control" name="annualHospitalLeave"
								required>
						</div>
						<div class="form-group col-md-6">
							<label for="uploadFile">Upload KET File: </label> <input type="file"
								class="form-control" name="uploadFile">
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