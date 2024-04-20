<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://drvic10k.github.io/bootstrap-sortable/Contents/bootstrap-sortable.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.1/moment.js"
	type="text/javascript"></script>
<script
	src="https://drvic10k.github.io/bootstrap-sortable/Scripts/bootstrap-sortable.js"
	type="text/javascript"></script>
<link
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.2.1/css/buttons.dataTables.min.css" />
<script src="https://code.jquery.com/jquery-1.12.3.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/buttons/1.2.1/js/dataTables.buttons.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/buttons/1.2.1/js/buttons.html5.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.datatables.net/plug-ins/1.10.24/sorting/datetime-moment.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$.fn.dataTable.moment('DD/MM/YYYY hh:mm:ss A');
			$('#example').DataTable({
				dom : 'Blfrtip',
				buttons : [ {
					text : 'Export To Excel',
					extend : 'excelHtml5',
					exportOptions : {
						modifier : {
							selected : true
						},
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
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
				"order": [[0, 'desc']]
			});
		});
	});
	function showDiv(divId, element) {
		document.getElementById(divId).style.display = element.value == "Y" ? 'block': 'none';
	}
</script>
</head>
<body>
	<center>
		<%
		ArrayList<Leave> vList = (ArrayList<Leave>) request.getAttribute("vList");
		String message = (String) request.getAttribute("message");
		String updateLeaveRecordServMsg = (String) request.getAttribute("updateLeaveRecordServMsg");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String idNo = "";
		String userType = "";
		if (request.getSession(false).getAttribute("idNo") != null) {
			idNo = (String) request.getSession(false).getAttribute("idNo");
			userType = (String) request.getSession(false).getAttribute("usertype");
		}
		
		int annualLeave = 0;
		int annualSickLeave = 0;
		int annualHospitalLeave = 0;
		if ( request.getAttribute("annualLeave") != null ) {
			annualLeave = (int) request.getAttribute("annualLeave");
		}
		if (request.getAttribute("annualSickLeave") != null ) {
			annualSickLeave = (int) request.getAttribute("annualSickLeave");
		}
		if ( request.getAttribute("annualHospitalLeave") != null ) {
			annualHospitalLeave = (int) request.getAttribute("annualHospitalLeave");
		}
		if (updateLeaveRecordServMsg != null && !StringUtils.isEmpty(updateLeaveRecordServMsg)) {
		%>
			<label class="heading"><%=updateLeaveRecordServMsg%> </label><br></b>
		<%
		}
		if (message != null && !StringUtils.isEmpty(message)) {
		%>
		<label class="heading"><%=message%> </label><br></b>

			<table class="table-striped table-bordered table-sm" style="width: 80%;">
				<thead>
					<tr>
						<th class="th-sm" colspan="3"><center>ANNUAL LEAVE LEFT</center></th>
						<th class="th-sm" colspan="3"><center>ANNUAL SICK LEAVE LEFT</center></th>
						<th class="th-sm" colspan="3"><center>ANNUAL HOSPITAL LEAVE LEFT</center></th>
					</tr>
				</thead>
				<tbody>
					<tr>
					</tr>
					<tr>
						<th colspan="3"><center><%=annualLeave%></center></th>
						<th colspan="3"><center><%=annualSickLeave%></center></th>
						<th colspan="3"><center><%=annualHospitalLeave%></center></th>
					</tr>
				</tbody>
			</table>
		</center>
		<br>
		<%
			if (vList != null && vList.size() > 0) {
			%>
			<div class="container body-content" id="tableview">
				<table id="example"
					class="table table-striped table-bordered table-sm sortable"
					style="width: 80%;">
					<thead>
						<tr>
							<th class="th-sm">S/N</th>
							<th class="th-sm">Officer ID</th>
							<th class="th-sm">Leave Type</th>
							<th class="th-sm">Leave Start Date</th>
							<th class="th-sm">Leave End Date</th>
							<th class="th-sm">Number of Days Leave</th>
							<th class="th-sm">Request Status</th>
							<th class="th-sm">Approving Officer</th>
							<th class="th-sm">Remarks</th>
							<th class="th-sm">Created Date</th>
							<th class="th-sm">Last Modified Date</th>
							<th class="th-sm">Delete</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (!vList.isEmpty()) {
							Iterator<Leave> vListIter = vList.iterator();
							while (vListIter.hasNext()) {
								Leave v = vListIter.next();
						%>
						<tr>
							<td><%=v.getLeaveID()%></td>
							<td><%=v.getIdNo()%></td>
							<td><%=v.getLeaveType()%></td>
							<td><%=v.getLeaveStartDate()%></td>
							<td><%=v.getLeaveEndDate()%></td>
							<td><%=v.getNumDaysLeave()%></td>
							<td><%=v.getRequestStatus()%> <br> <%
							if ((userType.toUpperCase().equals("ADMIN") || userType.toUpperCase().equals("MANAGEMENT"))) {
							%> 
							 	<br>
							 	<a href="updateLeave?leaveId=<%=v.getLeaveID()%>&status=Approve">Approve</a>
								<br> 
								<a href="updateLeave?leaveId=<%=v.getLeaveID()%>&status=Reject">Reject</a>
							<%
							}
							%>
							</td>
							<td><%=(v.getApprovingSupervisor() == null || v.getApprovingSupervisor().equals("null")) ? "Not Assigned Yet"
							: v.getApprovingSupervisor()%></td>
							<td>
								<p><%=(v.getRemarks() != null ? v.getRemarks() : "No Remarks Yet")%></p>
								<select id="ddlRemarks"
								onchange="showDiv('dvRemarks<%=v.getLeaveID()%>', this)">
									<option value="N">No Edit Remarks</option>
									<option value="Y">Yes Edit Remarks</option>
							</select>
								<hr />
								<div id="dvRemarks<%=v.getLeaveID()%>" style="display: none">
									<form method="GET" action="/updateLeave">
										<input type="hidden" id="leaveId" name="leaveId"
											value="<%=v.getLeaveID()%>"> <input type="text"
											class="form-control" name="remarks"
											oninput="this.value = this.value.toUpperCase()"> <input
											type="submit" name="Submit" value="Update">
									</form>
								</div>
							</td>
							<td><%=v.getCreatedDt()%></td>
							<td><%=v.getLastModifiedDt()%></td>
							<td><a href="deleteLeave?leaveId=<%=v.getLeaveID()%>" class="btn btn-warning btn-md" <%=((v == null) || v.getLeaveID() == null ? "disabled" : "active")%> role="button"
									aria-pressed="true">Delete</a></td>
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
			<div class="form-row">
				<div class="form-group col-md-2">
					<form action="/loadDashboard" method="post">
					    <input  class="btn btn-warning btn-lg active" type="submit" value="Back">
					</form>
				</div>
			</div>
		</center>
	</div>

</body>
</html>