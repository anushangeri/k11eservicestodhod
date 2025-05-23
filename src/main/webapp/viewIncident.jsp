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
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.awt.Image"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="org.apache.commons.codec.binary.Base64InputStream"%>
<%@page import="org.springframework.util.FileCopyUtils" %>
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
		//var test = document.getElementById("test");
		//var i = Convert.ToBase64String(test);
		//document.getElementById("ItemPreview").src = "data:image/jpeg;base64," + btoa(String.fromCharCode.apply(null, new Uint8Array(test)));
		
		//var base64 = Convert.ToBase64String(test);
		var errorMsg = "<p>Example error message</p>"
		document.getElementById("display").innerHTML = errorMsg
		//document.getElementById("display").value = "data:image/png;base64,"+base64;
	}
</script>
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Incident Management System</label> <br>
			<b>How to use:</b> Please enter Client Details.
			<%
			Incident v = null;
			String actionStatus = null;
		 	if (request.getAttribute("incidentRecord") != null) {
		 		v = (Incident) request.getAttribute("incidentRecord");
		 	}
			//getting all sites
			ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
			%>
			<center>
				<form >
					<div class="form-row">
						<div class="form-group col-md-5">
							<label for="officerOnDutyName">Officer on Duty Name: </label> <input
								type="text" class="form-control" name="officerOnDutyName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getOfficerOnDutyName())%>"
								readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="officerOnDutyId">Officer on Duty ID: </label> <input
								type="text" class="form-control" name="officerOnDutyId"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getOfficerOnDutyId())%>"
								readonly>
						</div>
						<div class="form-group col-md-4">
							<label for="officerOnDutyDesignation">Officer on Duty
								Designation: </label> <input type="text" class="form-control"
								name="officerOnDutyDesignation"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getOfficerOnDutyDesignation())%>"
								readonly>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="reportingSite">Reporting Site : </label> <input type="text" class="form-control"
								name="reportingSite"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getReportingSite())%>"
								readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="partiesInvolved">Name of Person(s) Involved:
							</label> <input type="text" class="form-control" name="partiesInvolved"
								value="<%=((v == null) ? "" : v.getPartiesInvolved())%>"
								readonly>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-3">
							<label for="dateOfIncident">Date of Incident: </label> <input
								type="date" class="form-control" name="dateOfIncident" value="<%=((v == null) ? "" : v.getDateOfIncident())%>"
								readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="timeOfIncident">Time of Incident: </label> <input
								type="time" class="form-control" name="timeOfIncident" value="<%=((v == null) ? "" : v.getTimeOfIncident())%>"
								readonly>
						</div>
						<div class="form-group col-md-4">
							<label for="dateOfIncidentReported">Date of Incident
								REPORTED: </label> <input type="date" class="form-control"
								name="dateOfIncidentReported" value="<%=((v == null) ? "" : v.getDateOfIncidentReported())%>"
								readonly>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-12">
							<label for="incidentCategory">Incident Category </label>
							<input type="text" class="form-control" name="incidentCategory"
								value="<%=((v == null) ? "" : v.getIncidentCategory())%>"
								readonly>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="whatIncidentOccurred">What Happened?: </label>
							<textarea type="text" class="form-control"
								name="whatIncidentOccurred" readonly><%=((v == null) ? "" : v.getWhatIncidentOccurred())%></textarea>
						</div>
						<div class="form-group col-md-6">
							<label for="howIncidentOccurred">How Incident Happened?:
							</label>
							<textarea type="text" class="form-control"
								name="howIncidentOccurred"
								readonly><%=((v == null) ? "" : v.getHowIncidentOccurred())%></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="whyIncidentOccurred">Why Incident Happened?:
							</label>
							<textarea type="text" class="form-control"
								name="whyIncidentOccurred"
								readonly><%=((v == null) ? "" : v.getWhyIncidentOccurred())%></textarea>
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
										id="yesCheckDeclarationByOfficerOnDuty" value="Yes" <%=((v != null) && v.getDeclarationByOfficerOnDuty().equals("Yes")  ? "checked" : "")%> readonly> <label
										class="form-check-label" for="declarationByOfficerOnDuty">Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										onclick="stateReasonDeclarationByOfficerOnDuty()"
										name="declarationByOfficerOnDuty"
										id="noCheckDeclarationByOfficerOnDuty" value="No" <%=((v != null) && !(v.getDeclarationByOfficerOnDuty().equals("Yes"))  ? "checked" : "")%> readonly> <label
										class="form-check-label" for="declarationByOfficerOnDuty" >No</label>

									<textarea type="text" class="form-control"
										name="reasonDeclarationByOfficerOnDuty"
										id="reasonDeclarationByOfficerOnDuty" 
										style="margin-left: 5px;" readonly><%=((v != null) ? v.getDeclarationByOfficerOnDuty() : "")%></textarea>
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
										id="yesCheckDeclarationofSecurityImplications" value="Yes" <%=((v != null) && v.getDeclarationofSecurityImplications().equals("Yes")  ? "checked" : "")%> readonly>
									<label class="form-check-label"
										for="declarationofSecurityImplications">Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										onclick="stateReasonDeclarationofSecurityImplications()"
										name="declarationofSecurityImplications"
										id="noCheckDeclarationofSecurityImplications" value="No" <%=((v != null) && !(v.getDeclarationofSecurityImplications().equals("Yes"))  ? "checked" : "")%> readonly>
									<label class="form-check-label"
										for="declarationofSecurityImplications">No</label>

									<textarea type="text" class="form-control"
										name="reasonDeclarationofSecurityImplications"
										id="reasonDeclarationofSecurityImplications"
										style="margin-left: 5px;" readonly><%=((v != null) ? v.getDeclarationofSecurityImplications() : "")%></textarea>
								</div>
							</div>
						</div>
					</div>
					<%
					    boolean isDisabled = (v == null) || (v.getIncidentId() == null) || (v.getFile() == null);
					%>
					<div class="form-row">
					    <a 
					        href="<%= isDisabled ? "#" : "generateDocDwnld?dwnldDocInd=dwnldIncidentDoc&incidentId=" + v.getIncidentId() %>" 
					        class="btn btn-warning btn-md <%= isDisabled ? "disabled" : "" %>" 
					        role="button" 
					        aria-pressed="true"
					        <%= isDisabled ? "tabindex='-1' aria-disabled='true'" : "" %>>
					        Download
					    </a>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="signatureOfOfficerOnDuty">Signature of
								Officer on Duty:</label> <span> By entering your name, it is
								equivalent to signature to sign off this report</span> <input
								type="text" class="form-control" name="signatureOfOfficerOnDuty"
								oninput="this.value = this.value.toUpperCase()"  value=<%=((v != null) ? v.getSignatureOfOfficerOnDuty() : "")%> readonly>
						</div>
						<div class="form-group col-md-6">
							<label for="signatureOfOpsManagerOnDuty">Signature of Ops
								Manager:</label> <span> By entering your name, it is equivalent
								to signature to sign off this report</span> <input type="text"
								class="form-control" name="signatureOfOpsManagerOnDuty"
								oninput="this.value = this.value.toUpperCase()" value=<%=((v != null) ? v.getSignatureOfOpsManagerOnDuty() : "")%> readonly>
						</div>
					</div>
					<div class="form-row">

						<a href="/manageIncidentRecord" class="btn btn-warning btn-lg active"
							role="button" aria-pressed="true">Back</a>
					</div>
				</form>
			</center>
		</div>
	</div>

</body>
</html>