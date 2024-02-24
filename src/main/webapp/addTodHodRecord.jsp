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
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
<script>
	$(function() {
		$('#datetimepicker1').datetimepicker();
	});
</script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
<style>
.container {
	margin-top: 5px;
	margin-left: 10px;
	width: 600px;
}
</style>
</head>
<%
String usertype = "";
String idNo = "";
String name = "";
%>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">TOD/HOD Management System</label> <br> <b>How
				to use:</b> Please enter Tod-Hod Details.
			<%
			 TodHodRecord v = null;
			 ArrayList<Site> siteDropdown = new ArrayList<Site>();
			 if (request.getAttribute("todhodRecord") != null) {
			 	v = (TodHodRecord) request.getAttribute("todhodRecord");
			 }
			 if (request.getAttribute("siteDropdown") != null) {
			 	siteDropdown = (ArrayList<Site>) request.getAttribute("siteDropdown");
			 }
			 %>
			<center>
				<%
				if (!(session.getAttribute("usertype") == null)) {
					usertype = (String) session.getAttribute("usertype");
					idNo = (String) session.getAttribute("idNo");
					name = (String) session.getAttribute("name");
					
				%>
				<form action="addTodHodRecord" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="officerName">Officer Name: </label> <input
								type="text" class="form-control" name="officerName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=(!((v == null) || usertype.equals("MANAGEMENT") || usertype.equals("ADMIN")) ? name : " ")%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="officerIdNo">Officer Id Number: </label> <input
								type="text" class="form-control" name="officerIdNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=(!((v == null) || usertype.equals("MANAGEMENT") || usertype.equals("ADMIN")) ? idNo : " ")%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="shift">Shift: </label>
							<%
							if (v == null) {
							%>
							<select name="shift" class="form-control" required>
								<option value="DAY">DAY</option>
								<option value="NIGHT">NIGHT</option>
							</select>
							<%
							} else {
							%>
							<select name="shift" class="form-control" required>
								<option value="DAY"
									<%=v.getShift() != null && v.getShift().equals("DAY") ? "selected" : ""%>>DAY</option>
								<option value="NIGHT"
									<%=v.getShift() != null && v.getShift().equals("NIGHT") ? "selected" : ""%>>NIGHT</option>
							</select>
							<%
							}
							%>
						</div>
						<div class="form-group col-md-6">
							<label for="siteName">Site : </label>
							<%
							if (v == null) {
							%>
							<select name="siteName" class="form-control" required>
								<%
								for (Site eachSite : siteDropdown) {
								%>
								<option value="<%=eachSite.getSiteName()%>">
									<%=eachSite.getSiteName()%></option>
								<%
								}
								%>
							</select>
							<%
							} else {
							%>
							<select name="siteName" class="form-control" required>
								<%
								for (Site eachSite : siteDropdown) {
								%>
								<option value="<%=eachSite.getSiteName()%>"
									<%=v.getSiteName() != null && v.getSiteName().equals(eachSite.getSiteName()) ? "selected" : ""%>>
									<%=eachSite.getSiteName()%></option>
								<%
								}
								%>
							</select>
							<%
							}
							%>
						</div>
						<div class="form-group col-md-6">
							<label for="timeInDt">Time-In Date: </label>
							<div class='input-group date' id='datetimepicker1'>
								<input type='text' class="form-control" name="timeInDt" /> <span
									class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label for="remarks">Remarks: </label> <input type="text"
								class="form-control" name="remarks"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getRemark())%>">
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit
							Record</button>

						<a href="dashboard.jsp" class="btn btn-warning btn-lg active"
							role="button" aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
				<%
				}
				%>
			</center>
		</div>
	</div>

	</script>
</body>
</html>