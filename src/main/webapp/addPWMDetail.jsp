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
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Paylsip Management System</label> <br>
			<b>How to use:</b> Please enter PWM Details.
			<center>
				<form action="addPWM" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="year">Year: </label> <input type="number"
								class="form-control" name="year" required>
						</div>
						<div class="form-group col-md-3">
							<label for="pwmGrade">PWM Grade: </label> 
							<select class="form-control" id="pwmGrade" name="pwmGrade" required>
								<option>SO</option>
								<option>SSO</option>
								<option>SS</option>
								<option>SSS</option>
						    </select>
						</div>
						<div class="form-group col-md-6">
							<label for="pwmWages">PWM Wages: </label> 
							$<input type="number" min="0.00" max="10000.00" step="0.01" id="pwmWages" name="pwmWages" required>
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