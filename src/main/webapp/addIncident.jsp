<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page import="net.javatutorial.DAO.*"%>
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
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
	function handleFileSelect() {
		//Check File API support
		if (window.File && window.FileList && window.FileReader) {

			var files = event.target.files; //FileList object
			var output = document.getElementById("result");
			for (var i = 0; i < files.length; i++) {
				var file = files[i];
				//Only pics
				if (!file.type.match('image'))
					continue;

				var picReader = new FileReader();
				picReader
						.addEventListener(
								"load",
								function(event) {
									var picFile = event.target;
									var div = document.createElement("div");
									div.innerHTML = "<img width=200 class='thumbnail' src='" + picFile.result + "'" + "title='" + picFile.name + "'/>";
									output.insertBefore(div, null);
								});
				//Read the image
				picReader.readAsDataURL(file);
			}
		} else {
			console.log("Your browser does not support File API");
		}
	}
	function clearUploads() {
		document.getElementById("files").value = "";
		document.getElementById("result").value = "";
	}
	function stateReasonDeclarationByOfficerOnDuty() {
		var yesSelected = document
				.getElementById("yesCheckDeclarationByOfficerOnDuty");
		var noSelected = document
				.getElementById("noCheckDeclarationByOfficerOnDuty");
		var text = document.getElementById("reasonDeclarationByOfficerOnDuty");
		if (noSelected.checked == true && yesSelected.checked == false) {
			text.style.display = "block";
		} else {
			text.style.display = "none";
		}
	}
	function stateReasonDeclarationofSecurityImplications() {
		var yesSelected = document
				.getElementById("yesCheckDeclarationofSecurityImplications");
		var noSelected = document
				.getElementById("noCheckDeclarationofSecurityImplications");
		var text = document.getElementById("reasonDeclarationofSecurityImplications");
		if (yesSelected.checked == true && noSelected.checked == false) {
			text.style.display = "block";
		} else {
			text.style.display = "none";
		}
	}
</script>
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Incident Management System</label> <br>
			<b>How to use:</b> Please enter Client Details.
			<%
			//getting all sites
			ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
			%>
			<center>
				<form action="addIncident" enctype="multipart/form-data"
					method="post">
					<div class="form-row">
						<div class="form-group col-md-5">
							<label for="officerOnDutyName">Officer on Duty Name: </label> <input
								type="text" class="form-control" name="officerOnDutyName"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
						<div class="form-group col-md-3">
							<label for="officerOnDutyId">Officer on Duty ID: </label> <input
								type="text" class="form-control" name="officerOnDutyId"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
						<div class="form-group col-md-4">
							<label for="officerOnDutyDesignation">Officer on Duty
								Designation: </label> <input type="text" class="form-control"
								name="officerOnDutyDesignation"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="reportingSite">Reporting Site : </label> <select
								name="reportingSite" class="form-control" required>
								<%
								for (Site eachSite : siteDropdown) {
								%>
								<option value="<%=eachSite.getSiteName()%>">
									<%=eachSite.getSiteName()%></option>
								<%
								}
								%>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="partiesInvolved">Name of Person(s) Involved:
							</label> <input type="text" class="form-control" name="partiesInvolved"
								required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-3">
							<label for="dateOfIncident">Date of Incident: </label> <input
								type="date" class="form-control" name="dateOfIncident" required>
						</div>
						<div class="form-group col-md-3">
							<label for="timeOfIncident">Time of Incident: </label> <input
								type="time" class="form-control" name="timeOfIncident" required>
						</div>
						<div class="form-group col-md-4">
							<label for="dateOfIncidentReported">Date of Incident
								REPORTED: </label> <input type="date" class="form-control"
								name="dateOfIncidentReported" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-12">
							<label for="incidentCategory">Incident Category </label>
							<div class="form-check">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Accident"> <label
										class="form-check-label" for="incidentCategory">Accident</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="First Aid"> <label
										class="form-check-label" for="incidentCategory">First
										Aid</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Fatality"> <label
										class="form-check-label" for="incidentCategory">Fatality</label>
								</div>
							</div>
							<div class="form-check">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Complaint"> <label
										class="form-check-label" for="incidentCategory">Complaint</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Theft"> <label
										class="form-check-label" for="incidentCategory">Theft</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Near Miss"> <label
										class="form-check-label" for="incidentCategory">Near
										Miss</label>
								</div>
							</div>
							<div class="form-check">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Dangerous Occurrence">
									<label class="form-check-label" for="incidentCategory">Dangerous
										Occurrence</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Unauthorized Entry"> <label
										class="form-check-label" for="incidentCategory">Unauthorized
										Entry</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										name="incidentCategory" value="Alarm Trigger"> <label
										class="form-check-label" for="incidentCategory">Alarm
										Trigger</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="whatIncidentOccurred">What Happened?: </label>
							<textarea type="text" class="form-control"
								name="whatIncidentOccurred" required></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="howIncidentOccurred">How Incident Happened?:
							</label>
							<textarea type="text" class="form-control"
								name="howIncidentOccurred" required></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="whyIncidentOccurred">Why Incident Happened?:
							</label>
							<textarea type="text" class="form-control"
								name="whyIncidentOccurred" required></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-12">
							<label for="declarationByOfficerOnDuty">The security
								officer involved is sober, free from medical issue(s) and
								fitness of state prior to the time of incident/ accident </label>
							<div class="form-check">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="declarationByOfficerOnDuty"
										onclick="stateReasonDeclarationByOfficerOnDuty()"
										id="yesCheckDeclarationByOfficerOnDuty" value="Yes" checked> <label
										class="form-check-label" for="declarationByOfficerOnDuty">Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										onclick="stateReasonDeclarationByOfficerOnDuty()"
										name="declarationByOfficerOnDuty"
										id="noCheckDeclarationByOfficerOnDuty" value="No"> <label
										class="form-check-label" for="declarationByOfficerOnDuty">No</label>

									<textarea type="text" class="form-control"
										name="reasonDeclarationByOfficerOnDuty"
										id="reasonDeclarationByOfficerOnDuty"
										style="margin-left: 5px; display: none"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-12">
							<label for="declarationofSecurityImplications">Is there
								any security implication due to the incident?</label>
							<div class="form-check">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="declarationofSecurityImplications"
										onclick="stateReasonDeclarationofSecurityImplications()"
										id="yesCheckDeclarationofSecurityImplications" value="Yes">
									<label class="form-check-label"
										for="declarationofSecurityImplications">Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										onclick="stateReasonDeclarationofSecurityImplications()"
										name="declarationofSecurityImplications"
										id="noCheckDeclarationofSecurityImplications" value="No" checked>
									<label class="form-check-label"
										for="declarationofSecurityImplications">No</label>

									<textarea type="text" class="form-control"
										name="reasonDeclarationofSecurityImplications"
										id="reasonDeclarationofSecurityImplications"
										style="margin-left: 5px; display: none"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="files">Select files:</label> <input type="file"
								class="form-control" id="files" onchange="handleFileSelect()"
								name="files" accept="image/png" multiple="multiple">
						</div>
						<div class="form-group col-md-4">
							<button type="button" class="btn btn-primary btn-md active"
								onclick="clearUploads()" id="clear">Clear Uploads</button>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-12">
							<output id="result" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="signatureOfOfficerOnDuty">Signature of Officer on Duty:</label>
							<span> By entering your name, 
							it is equivalent to signature to sign off this report</span>  <input
								type="text" class="form-control" name="signatureOfOfficerOnDuty"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
						<div class="form-group col-md-6">
							<label for="signatureOfOpsManagerOnDuty">Signature of Ops Manager:</label>
							<span> By entering your name, 
							it is equivalent to signature to sign off this report</span>  <input
								type="text" class="form-control" name="signatureOfOpsManagerOnDuty"
								oninput="this.value = this.value.toUpperCase()">
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit
							Record</button>

						<a href="/dashboard.jsp" class="btn btn-warning btn-lg active"
							role="button" aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
			</center>
		</div>
	</div>

</body>
</html>