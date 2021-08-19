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
<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
 href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">


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
						<div id="datetimepicker" class="input-append date">
					      <input type="text"></input>
					      <span class="add-on">
					        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
					      </span>
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
	<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script> 
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
</script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
</script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
</script>
<script type="text/javascript">
	$('#datetimepicker').datetimepicker({
	  format: 'dd/MM/yyyy hh:mm:ss',
	  language: 'pt-BR'
	});
</script>
</body>
</html>