<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.javatutorial.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://drvic10k.github.io/bootstrap-sortable/Contents/bootstrap-sortable.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.1/moment.js" type="text/javascript"></script>
<script src="https://drvic10k.github.io/bootstrap-sortable/Scripts/bootstrap-sortable.js" type="text/javascript"></script>
<link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.2.1/css/buttons.dataTables.min.css" />
<script src="https://code.jquery.com/jquery-1.12.3.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(document).ready(function() {
		$('table').DataTable({
			dom : 'Blfrtip',
			buttons : [ {
				text : 'Export To Excel',
				extend : 'excelHtml5',
				exportOptions : {
					modifier : {
						selected : true
					},
					columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20],
					format : {
						header : function(data, columnIdx) {
							return data;
						},
					}
				},
				footer : false,
				customize : function(xlsx) {
					var sheet = xlsx.xl.worksheets['sheet1.xml'];
				}
			} ],
			"order": [[20, 'desc']]
		});
	});
});
</script>
</head>
<body>
	<center>
	<%
		ArrayList<Occurrence> vList = (ArrayList<Occurrence>) request.getAttribute("vList");
		String message = (String) request.getAttribute("message");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String idNo = "SxxxxxxxJ";
		String userType = "";
	 	if (request.getSession(false).getAttribute("idNo") != null) {
	 		idNo = (String) request.getSession(false).getAttribute("idNo");
	 		userType = (String) request.getSession(false).getAttribute("usertype");
	 	}
		if (message != null && !StringUtils.isEmpty(message)) {
	%>
		<label class="heading"><%=message%> </label><br>
		<b>*Individuals are required to self-identify should they experience any COVID-19 symptoms.</b>
	</center>
		<% 
			if (vList != null && vList.size() > 0) {
		%>
			<div class="container body-content" id="tableview">
				<table id="example"
					class="table table-striped table-bordered table-sm sortable">
					<thead>
						<tr>
							<th class="th-sm">S/N</th>
							<th class="th-sm">Reporting Site</th>
							<th class="th-sm">Officer on Duty Name</th>
							
							<th class="th-sm" style="display:none;">reportingClassification</th>
							<th class="th-sm" style="display:none;">officerOnDutyId</th>
							<th class="th-sm" style="display:none;">securityRiskThreat</th>
							<th class="th-sm" style="display:none;">descriptionOfSecurityRiskThreat</th>
							<th class="th-sm" style="display:none;">nonConformanceSOP</th>
							<th class="th-sm" style="display:none;">desciptionOfNonConformance</th>
							<th class="th-sm" style="display:none;">partiesInvolved</th>
							<th class="th-sm" style="display:none;">descriptionOfPartiesInvolved</th>
							<th class="th-sm" style="display:none;">descriptionOfK11Notified</th>
							<th class="th-sm" style="display:none;">howIncidentOccurred</th>
							<th class="th-sm" style="display:none;">whatIncidentOccurred</th>
							<th class="th-sm" style="display:none;">whyIncidentOccurred</th>
							<th class="th-sm" style="display:none;">whatActionWasTaken</th>
							<th class="th-sm" style="display:none;">extraDetailsOnAction</th>
							<th class="th-sm" style="display:none;">pendingAction</th>
							<th class="th-sm" style="display:none;">incidentCategory</th>
							
							<th class="th-sm">Created Date</th>
							<th class="th-sm">Last Modified Date</th>
							
							<th class="th-sm">View</th>
							<th class="th-sm">Edit</th>
							<th class="th-sm">Delete</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (!vList.isEmpty()) {
								Iterator<Occurrence> vListIter = vList.iterator();
								while (vListIter.hasNext()) {
									Occurrence v = vListIter.next();
						%>
								<tr>
									<td><%=v.getOccurrenceId()%></td>
									<td><%=v.getReportingSite()%></td>
									<td><%=v.getOfficerOnDutyName()%></td>
									
									<td style="display:none;"><%=v.getReportingClassification()%></td>
									<td style="display:none;"><%=v.getOfficerOnDutyId()%></td>
									<td style="display:none;"><%=v.getSecurityRiskThreat()%></td>
									<td style="display:none;"><%=v.getDescriptionOfSecurityRiskThreat()%></td>
									<td style="display:none;"><%=v.getNonConformanceSOP()%></td>
									<td style="display:none;"><%=v.getDesciptionOfNonConformance()%></td>
									<td style="display:none;"><%=v.getPartiesInvolved()%></td>
									<td style="display:none;"><%=v.getDescriptionOfPartiesInvolved()%></td>
									<td style="display:none;"><%=v.getDescriptionOfK11Notified()%></td>
									<td style="display:none;"><%=v.getHowIncidentOccurred()%></td>
									<td style="display:none;"><%=v.getWhatIncidentOccurred()%></td>
									<td style="display:none;"><%=v.getWhyIncidentOccurred()%></td>
									<td style="display:none;"><%=v.getWhatActionWasTaken()%></td>
									<td style="display:none;"><%=v.getExtraDetailsOnAction()%></td>
									<td style="display:none;"><%=v.getPendingAction()%></td>
									<td style="display:none;"><%=v.getIncidentCategory()%></td>
									
									<td><%=v.getCreatedDt()%></td>
									<td><%=v.getLastModifiedDt()%></td>
									<td>
										<form method="POST" action ="/viewEditOccurrence">
											<input type="hidden" id="occurrenceId" name="occurrenceId" value="<%=v.getOccurrenceId()%>">
											<input type="hidden" id="actionStatus" name="actionStatus" value="view">
											<input type="submit" name="Submit" value="View">
										</form>
									</td>
									<td>
										<form method="POST" action ="/viewEditOccurrence">
											<input type="hidden" id="occurrenceId" name="occurrenceId" value="<%=v.getOccurrenceId()%>">
											<input type="hidden" id="actionStatus" name="actionStatus" value="edit">
											<input type="submit" name="Submit" value="Edit">
										</form>
									</td>
									<td>
										<form method="POST" action ="/deleteOccurrence">
											<input type="hidden" id="occurrenceId" name="occurrenceId" value="<%=v.getOccurrenceId()%>">
											<input type="submit" name="Submit" value="Delete">
										</form>
									</td>
								</tr>
							<%
								}
							%>
			
						<%
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
		<div class="container body-content">
			<center>
				<div class="form-group col-md-2">
					<form action="/loadDashboard" method="post">
					    <input  class="btn btn-warning btn-lg active" type="submit" value="Back">
					</form>
				</div>
					
				<a href="addOccurrenceRecord.jsp" class="btn btn-warning btn-lg active"
				role="button" aria-pressed="true">Add Occurrence</a>
			</center>
		</div>
	<br>
	
</body>
</html>