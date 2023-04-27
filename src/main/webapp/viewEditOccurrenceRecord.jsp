<%@page import="net.javatutorial.DAO.SiteManagerDAO"%>
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
<%@page import="net.javatutorial.DAO.SiteManagerDAO"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
<style type="text/css">
.container {
	margin-top: 5px;
	margin-left: 10px;
	width: 600px;
}
</style>
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Occurrence Management System</label> <br>
			<b>How to use:</b> Please enter Occurrence Details.
			<%
			Occurrence v = null;
			ArrayList<Site> siteDropdown = new ArrayList<Site>();
			String actionStatus = null;
		 	if (request.getAttribute("occurrenceRecord") != null) {
		 		v = (Occurrence) request.getAttribute("occurrenceRecord");
		 	}
		 	if (request.getAttribute("siteDropdown") != null) {
		 		siteDropdown = (ArrayList<Site>) request.getAttribute("siteDropdown");
		 	}
		 	if (request.getAttribute("actionStatus") != null) {
		 		actionStatus = (String) request.getAttribute("actionStatus");
		 	}
			%>
			<center>
			<% if (actionStatus != null && !StringUtils.isEmpty(actionStatus) && actionStatus.equals("view")) { %>
				<form action="-" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="occurrenceId">Occurrence ID: </label> <input type="text" class="form-control"
								name="occurrenceId" 
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getOccurrenceId())%>" readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="reportingSite">Reporting Site : </label> 
							<select
									name="reportingSite" class="form-control" readonly>
									<%
									for (Site eachSite : siteDropdown) {
									%>
									<option value="<%=eachSite.getSiteName()%>" 
									<%=(v.getReportingSite() != null && v.getReportingSite().equals(eachSite.getSiteName())) ? "selected" : "" %>>
										 <%=eachSite.getSiteName()%></option>
									<%
									}
									%>
								</select>
						</div>
						<div class="form-group col-md-6">
							<label for="reportingClassification">Reporting
								Classification: </label> <input type="text" class="form-control"
								name="reportingClassification" 
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getReportingClassification())%>" readonly>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="officerOnDutyName">Officer on Duty
								Name: </label> <input type="text" class="form-control"
								name="officerOnDutyName"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getOfficerOnDutyName())%>" readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="officerOnDutyId"> Officer on Duty ID: </label> <input
								type="text" class="form-control" name="officerOnDutyId"
								oninput="this.value = this.value.toUpperCase()"
								placeholder="e.g. S1234567D" 
								value="<%=((v == null) ? "" : v.getOfficerOnDutyId())%>" readonly>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="securityRiskThreat"> Security Risk
								Threat: </label> <input type="text" class="form-control"
								name="securityRiskThreat"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getSecurityRiskThreat())%>" readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="descriptionOfSecurityRiskThreat">
							 Description of Security Risk Threat: </label>
							<textarea type="text" class="form-control"
								name="descriptionOfSecurityRiskThreat"
								readonly><%=((v == null) ? "" : v.getDescriptionOfSecurityRiskThreat())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="nonConformanceSOP">
							 Non-Conformance SOP: </label> <input type="text" class="form-control"
								name="nonConformanceSOP"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getNonConformanceSOP())%>" readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="desciptionOfNonConformance">
							 Description of Non-Conformance SOP: </label>
							<textarea type="text" class="form-control"
								name="desciptionOfNonConformance" 
								readonly><%=((v == null) ? "" : v.getDesciptionOfNonConformance())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="partiesInvolved">
							 Parties Involved: </label> <input type="text" class="form-control"
								name="partiesInvolved"
								oninput="this.value = this.value.toUpperCase()" placeholder="e.g. K11,JLOG,Police"
								value="<%=((v == null) ? "" : v.getPartiesInvolved())%>" readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="descriptionOfPartiesInvolved">
							 Description of Parties Involved: </label>
							<textarea type="text" class="form-control"
								name="descriptionOfPartiesInvolved" 
								readonly><%=((v == null) ? "" : v.getDescriptionOfPartiesInvolved())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="descriptionOfK11Notified">
							 Description of K11 Notified: </label>
							<textarea type="text" class="form-control"
								name="descriptionOfK11Notified" 
								readonly><%=((v == null) ? "" : v.getDescriptionOfK11Notified())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="howIncidentOccurred">
							 How Incident Occurred: </label>
							<textarea type="text" class="form-control"
								name="howIncidentOccurred" 
								readonly><%=((v == null) ? "" : v.getHowIncidentOccurred())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="whatIncidentOccurred">
							 What Incident Occurred: </label>
							<textarea type="text" class="form-control"
								name="whatIncidentOccurred" 
								readonly><%=((v == null) ? "" : v.getWhatIncidentOccurred())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="whyIncidentOccurred">
							 Why Incident Occurred: </label>
							<textarea type="text" class="form-control"
								name="whyIncidentOccurred" 
								readonly><%=((v == null) ? "" : v.getWhyIncidentOccurred())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="whatActionWasTaken">
							 What Action was Taken: </label>
							<textarea type="text" class="form-control"
								name="whatActionWasTaken" 
								readonly><%=((v == null) ? "" : v.getWhatActionWasTaken())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="extraDetailsOnAction">
							 Extra Details on Action: </label>
							<textarea type="text" class="form-control"
								name="extraDetailsOnAction" 
								readonly><%=((v == null) ? "" : v.getExtraDetailsOnAction())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="pendingAction">
							 Pending Action: </label>
							<textarea type="text" class="form-control"
								name="pendingAction" 
								readonly><%=((v == null) ? "" : v.getPendingAction())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="incidentCategory">
							 Incident Category: </label> <input type="text" class="form-control"
								name="incidentCategory"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getIncidentCategory())%>" readonly>
						</div>
					</div>
					<div class="form-row">
						<a href="/manageOccurrenceRecord" class="btn btn-warning btn-lg active"
							role="button" aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
				<%} %>
				
				<% if (actionStatus != null && !StringUtils.isEmpty(actionStatus) && actionStatus.equals("edit")) { %>
				<form action="updateOccurrenceRecord" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="occurrenceId">Occurrence ID: </label> <input type="text" class="form-control"
								name="occurrenceId" 
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getOccurrenceId())%>" readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="reportingSite">Reporting Site : </label> 
							<select
									name="reportingSite" class="form-control" readonly>
									<%
									for (Site eachSite : siteDropdown) {
									%>
									<option value="<%=eachSite.getSiteName()%>" 
									<%=(v.getReportingSite() != null && v.getReportingSite().equals(eachSite.getSiteName())) ? "selected" : "" %>>
										 <%=eachSite.getSiteName()%></option>
									<%
									}
									%>
								</select>
						</div>
						<div class="form-group col-md-6">
							<label for="reportingClassification">Enter Reporting
								Classification: </label> <input type="text" class="form-control"
								name="reportingClassification" 
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getReportingClassification())%>" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="officerOnDutyName">Enter Officer on Duty
								Name: </label> <input type="text" class="form-control"
								name="officerOnDutyName"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getOfficerOnDutyName())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="officerOnDutyId">Enter Officer on Duty ID: </label> <input
								type="text" class="form-control" name="officerOnDutyId"
								oninput="this.value = this.value.toUpperCase()"
								placeholder="e.g. S1234567D" 
								value="<%=((v == null) ? "" : v.getOfficerOnDutyId())%>" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="securityRiskThreat">Enter Security Risk
								Threat: </label> <input type="text" class="form-control"
								name="securityRiskThreat"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getSecurityRiskThreat())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="descriptionOfSecurityRiskThreat">
							Enter Description of Security Risk Threat: </label>
							<textarea type="text" class="form-control"
								name="descriptionOfSecurityRiskThreat"
								required><%=((v == null) ? "" : v.getDescriptionOfSecurityRiskThreat())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="nonConformanceSOP">
							Enter Non-Conformance SOP: </label> <input type="text" class="form-control"
								name="nonConformanceSOP"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getNonConformanceSOP())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="desciptionOfNonConformance">
							Enter Description of Non-Conformance SOP: </label>
							<textarea type="text" class="form-control"
								name="desciptionOfNonConformance" 
								required><%=((v == null) ? "" : v.getDesciptionOfNonConformance())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="partiesInvolved">
							Enter Parties Involved: </label> <input type="text" class="form-control"
								name="partiesInvolved"
								oninput="this.value = this.value.toUpperCase()" placeholder="e.g. K11,JLOG,Police"
								value="<%=((v == null) ? "" : v.getPartiesInvolved())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="descriptionOfPartiesInvolved">
							Enter Description of Parties Involved: </label>
							<textarea type="text" class="form-control"
								name="descriptionOfPartiesInvolved" 
								required><%=((v == null) ? "" : v.getDescriptionOfPartiesInvolved())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="descriptionOfK11Notified">
							Enter Description of K11 Notified: </label>
							<textarea type="text" class="form-control"
								name="descriptionOfK11Notified" 
								required><%=((v == null) ? "" : v.getDescriptionOfK11Notified())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="howIncidentOccurred">
							Enter How Incident Occurred: </label>
							<textarea type="text" class="form-control"
								name="howIncidentOccurred" 
								required><%=((v == null) ? "" : v.getHowIncidentOccurred())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="whatIncidentOccurred">
							Enter What Incident Occurred: </label>
							<textarea type="text" class="form-control"
								name="whatIncidentOccurred" 
								required><%=((v == null) ? "" : v.getWhatIncidentOccurred())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="whyIncidentOccurred">
							Enter Why Incident Occurred: </label>
							<textarea type="text" class="form-control"
								name="whyIncidentOccurred" 
								required><%=((v == null) ? "" : v.getWhyIncidentOccurred())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="whatActionWasTaken">
							Enter What Action was Taken: </label>
							<textarea type="text" class="form-control"
								name="whatActionWasTaken" 
								required><%=((v == null) ? "" : v.getWhatActionWasTaken())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="extraDetailsOnAction">
							Enter Extra Details on Action: </label>
							<textarea type="text" class="form-control"
								name="extraDetailsOnAction" 
								required><%=((v == null) ? "" : v.getExtraDetailsOnAction())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="pendingAction">
							Enter Pending Action: </label>
							<textarea type="text" class="form-control"
								name="pendingAction" 
								required><%=((v == null) ? "" : v.getPendingAction())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="incidentCategory">
							Enter Incident Category: </label> <input type="text" class="form-control"
								name="incidentCategory"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getIncidentCategory())%>" required>
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit
							Record</button>
							
						<a href="/manageOccurrenceRecord" class="btn btn-warning btn-lg active"
							role="button" aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
				<%} %>
			</center>
		</div>
	</div>

	<script type="text/javascript"></script>
</body>
</html>