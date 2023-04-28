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
<%@page import="net.javatutorial.entity.ClientAccount"%>
<%@page import="net.javatutorial.DAO.EmployeeManagerDAO"%>
<%@page import="net.javatutorial.DAO.ClientAccountManagerDAO"%>
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
			<%
			//if it is not modify, the employeeID will be null
			String employeeID = "";
			if (request.getParameter("employeeID") != null) {
				employeeID = (String) request.getParameter("employeeID");
		 	}
			
			ArrayList<ClientAccount> eList = ClientAccountManagerDAO.retrieveAll();
			Employee e = EmployeeManagerDAO.retrieveEmployeeByEmployeeID(employeeID);
			%>
			
				<form action="addUpdateEmployee" enctype='multipart/form-data' method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="idNo">ID Number: </label>
							<%
							if (e == null || e.getIdNo() == null) {
							%>
							<select name="idNo" class="form-control">
								<option style="display:none;"></option>
								<%
								for (ClientAccount eachEmp : eList) {
								%>
								<option value="<%=eachEmp.getIdNo()%>">
									<%=eachEmp.getName()%></option>
								<%
								}
								%>
							</select>
							<%
							} else {
							%>
							<select name="idNo" class="form-control">
								<%
								for (ClientAccount eachEmp : eList) {
								%>
								<option value="<%=eachEmp.getIdNo()%>"
									<%=e.getIdNo() != null && e.getIdNo().equals(eachEmp.getIdNo()) ? "selected" : ""%>>
									<%=eachEmp.getName()%></option>
								<%
								}
								%>
							</select>
							<%
							}
							%>
						</div>
						<div class="form-group col-md-6">
							<label for="annualLeave">Paid Annual Leave Per Year: </label> <input type="number"
								class="form-control" name="annualLeave" value="<%=((e == null) ? 0 : e.getPaidAnnualLeavePerYear())%>"
								required>
						</div>
						<div class="form-group col-md-6">
							<label for="annualOutpatientLeave">Paid Outpatient Sick Leave Per Year: </label> <input type="number"
								class="form-control" name="annualOutpatientLeave" value="<%=((e == null) ? 0 : e.getPaidOutpatientSickLeavePerYear())%>"
								required>
						</div>
						<div class="form-group col-md-6">
							<label for="annualHospitalLeave">Paid Hospitalisation Leave Per Year: </label> <input type="number"
								class="form-control" name="annualHospitalLeave" value="<%=((e == null) ? 0 : e.getPaidHospitalisationLeavePerYear())%>"
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