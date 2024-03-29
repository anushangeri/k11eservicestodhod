<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
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
<%@page import="net.javatutorial.entity.Employee"%>
<%@page import="net.javatutorial.DAO.EmployeeManagerDAO"%>
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
			<b>How to use:</b> Please enter Miscellaneous Documents.
			<center>
			<%
			ArrayList<Employee> eList = EmployeeManagerDAO.retrieveAll();
			%>
			
				<form action="addMiscDocsRecord" enctype='multipart/form-data' method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="employeeId">Employee ID No: </label>
							<select name="employeeId" class="form-control">
								<option style="display:none;"></option>
								<%
								for (Employee eachEmp : eList) {
								%>
								<option value="<%=eachEmp.getEmployeeId()%>">
									<%=eachEmp.getIdNo()%></option>
								<%
								}
								%>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="description">File Description: </label> <input type="text"
								class="form-control" name="description" 
								required>
						</div>
						<div class="form-group col-md-6">
							<label for="uploadFile">Upload File: </label> <input type="file"
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