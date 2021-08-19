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
<!-- jquery JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

<!-- Bootstrap js -->
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Propeller textfield js --> 
<script type="text/javascript" src="dist/js/propeller.min.js"></script>

<!-- Datepicker moment with locales -->
<script type="text/javascript" language="javascript" src="datetimepicker/js/moment-with-locales.js"></script>

<!-- Propeller Bootstrap datetimepicker -->
<script type="text/javascript" language="javascript" src="datetimepicker/js/bootstrap-datetimepicker.js"></script>

<script>
$(document).ready(function(){
	$('#datetimepicker-default').datetimepicker();
});
</script>


</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">TOD/HOD Management System</label> <br>
			<b>How to use:</b> Please enter Tod-Hod Details.
			<% 
				TodHodRecord v = null;
			 	if (request.getAttribute("todhodRecord") != null) {
			 		v = (TodHodRecord) request.getAttribute("todhodRecord");
			 	}
			%>
			<center>
				<form action="addTodHodRecord" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="officerName">Officer Name: </label> <input type="text"
								class="form-control" name="officerName"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getOfficerName())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="officerIdNo">Officer Id Number: </label> <input type="text"
								class="form-control" name="officerIdNo"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getOfficerIdNo())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="siteName">Site Name: </label> <input type="text"
								class="form-control" name="siteName"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getSiteName())%>" required>
						</div>
						<div class="form-group pmd-textfield pmd-textfield-floating-label">
							<label class="control-label" for="datetimepicker-default">Select Date and Time</label>
							<input type="text" id="datetimepicker-default" class="form-control" />
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
			</center>
		</div>
	</div>
</body>
</html>