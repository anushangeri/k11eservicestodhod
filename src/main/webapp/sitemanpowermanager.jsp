<%@page import="com.google.gdata.data.spreadsheet.CellEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.Cell"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page import="net.javatutorial.DAO.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gdata.client.spreadsheet.SpreadsheetService"%>
<%@page
	import="com.google.gdata.data.spreadsheet.CustomElementCollection"%>
<%@page import="com.google.gdata.data.spreadsheet.ListEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.ListFeed"%>
<%@page import="com.google.gdata.util.ServiceException"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />
<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<script type="text/javascript">
	$(document).ready(
			function() {
				var date_input = $('input[name="from"]'); //our date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				var options = {
					format : 'mm/dd/yyyy',
					container : container,
					todayHighlight : true,
					autoclose : true,
				};
				date_input.datepicker(options);
			})

	$(document).ready(
			function() {
				var date_input = $('input[name="to"]'); //our date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				var options = {
					format : 'mm/dd/yyyy',
					container : container,
					todayHighlight : true,
					autoclose : true,
				};
				date_input.datepicker(options);
			})
</script>
</head>

<body>
	<%
	//using the usertype to determine what time of TOD/HOD to display
	String usertype = "";
	ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
	if (!(session.getAttribute("usertype") == null)) {
		usertype = (String) session.getAttribute("usertype");
	}
	String message = (String) request.getAttribute("message");

	if (message != null && !StringUtils.isEmpty(message)) {
	%>
	<label class="heading"><%=message%> </label>
	<br>
	<%
	}
	%>
	<center>
		<form action="viewSiteManpower" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="from">From: </label>
		      <input class="form-control" id="date" name="from" placeholder="MM/DD/YYYY" type="text" required/>
				</div>
				<div class="form-group col-md-6">
					<label for="shift">Select Shift Type: </label> <select
						class="form-control" name="shift">
						<option selected="selected">DAY</option>
						<option>NIGHT</option>
					</select>
				</div>
				<div class="form-row">
					<button type="submit" class="btn btn-primary">VIEW
						MANPOWER</button>
				</div>
			</div>
		</form>
		<br>
		<form action="loadDashboard" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<button type="submit" class="btn btn-warning btn-lg active">Back</button>
				</div>

			</div>
		</form>
	</center>

	<!-- display site manpower status based on date chosen above -->
	<center>
		<%
		ArrayList<Manpower> mList = (ArrayList<Manpower>) request.getAttribute("mList");
		String manpowerStatusMessage = (String) request.getAttribute("manpowerStatusMessage");
		if (manpowerStatusMessage != null && !StringUtils.isEmpty(manpowerStatusMessage)) {
		%>
		<label class="heading"><%=manpowerStatusMessage%> </label><br>
	</center>
	<%
	if (mList != null && mList.size() > 0) {
	%>
	<div class="container body-content" id="tableview">
		<table id="example"
			class="table table-striped table-bordered table-sm sortable"
			style="width: 80%;">
			<thead>
				<tr>
					<th class="th-sm">Site Name</th>
					<th class="th-sm">Required Manpower</th>
					<th class="th-sm">Actual Manpower</th>
					<th class="th-sm">Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (!mList.isEmpty()) {
					Iterator<Manpower> mListIter = mList.iterator();
					while (mListIter.hasNext()) {
						Manpower m = mListIter.next();
				%>
				<tr>
					<td><%=((m.getSiteName() == null) ? "" : m.getSiteName())%></td>
					<td><%=m.getRequiredManpower()%></td>
					<td><%=m.getActualManpower()%></td>
					<% 
						int diff = m.getRequiredManpower() - m.getActualManpower();
						String status = "";
						String style = "";
						if(diff > 0){
							status = "Short " + diff + " man";
							style = "color:red;";
						}
						else if(diff == 0){
							status = "Sufficent men";
							style = "color:green;";
						}
						else if(diff < 0){
							status = "Excess " + -(diff) + " man";
							style = "color:red;";
						}
					%>
					<td style="<%=style%>"><%=status%></td>
				</tr>
				<%
				}
			}
				%>
			</tbody>
		</table>
		<%
		}
		%>
		<%
		}
		%>
	</div>
</body>
</html>
