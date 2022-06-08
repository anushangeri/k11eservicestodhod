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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script>
    function displayDaysWorked() {
        $.ajax({
            type: 'POST',
            url: '/retrieveTodHodCount',
            data: 'from=' + from + '&to=' + to + '&retrieveTodHodCount_requestType=' + retrieveTodHodCount_requestType,
            error: function(response) {
                // Gets called when an error occurs with error details in variable response
            },
            success: function(response) {
                // Gets called when the action is successful with server response in variable response
            	var val = request.responseText;
            	console.log(val);
            }
        });
    }
</script>
</head>

	<%
	 	String message = (String) request.getAttribute("message");
		ArrayList<TodHodRecord> vList = (ArrayList<TodHodRecord>) request.getAttribute("vList");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String usertype = "";
		String idNo = "";
		String name = "";
		if (message != null && !StringUtils.isEmpty(message)) {
	%>
			<label class="heading"><%=message%></label>
	<%} %>
<body>
	<center>
	<%if ( !(session.getAttribute("usertype") == null)) {
		usertype = (String) session.getAttribute("usertype");
		idNo = (String) session.getAttribute("idNo");
		name = (String) session.getAttribute("name");	
		%>
			<br>
			<label class="heading">Welcome, <%=name%></label>
			<br>
			<label class="heading">Please complete HOD before submitting another TOD.</label>
			<br>
			<div class="container body-content">
				<table id="example"
						class="table table-striped table-bordered table-sm" style="width: 80%;">
						<thead>
							<tr>
								<th class="th-sm" colspan="5"><center>DAYS WORKED</center></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<form action="retrieveTodHodCount" method="post">
									<input type="hidden" class="form-control" 
									name="retrieveTodHodCount_requestType" value="month">
									<td>
										<button type="submit" class="btn btn-primary btn-sm active" onclick="displayDaysWorked();" >Month</button>
									</td>
								</form>
								<form action="retrieveTodHodCount" method="post">
									<input type="hidden" class="form-control" 
									name="retrieveTodHodCount_requestType" value="week">
									<td>
										<button type="submit" class="btn btn-primary btn-sm active" onclick="displayDaysWorked();" >Week</button>
									</td>
								</form>
								<form action="retrieveTodHodCount" method="post">
									<td><label for="from">From: </label> <input type="date"
										class="form-control" name="from">
									</td>
									<td><label for="to">To: </label> <input type="date"
										class="form-control" name="to">
									</td>
									<input type="hidden" class="form-control" 
									name="retrieveTodHodCount_requestType" value="custom">
									<td>
										<button type="submit" class="btn btn-primary btn-sm active" onclick="displayDaysWorked();" >Submit</button>
									</td>
								</form>
								
							</tr>
							<tr>
								<th colspan="5"><center><%= request.getAttribute("daysWorked") == null ? 0 : request.getAttribute("daysWorked") %></center></th>
							</tr>
						</tbody>
				</table>
				<br>
				<table id="example"
						class="table table-striped table-bordered table-sm" style="width: 80%;">
						<thead>
							<tr>
								<th class="th-sm">Description</th>
								<th class="th-sm">Click!</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							 	<td>
							 		View TOD/HOD Record
							 	</td>
							 	<td> 
								 	<a href="todhodsearch.jsp" class="btn btn-warning btn-lg active" role="button"
									aria-pressed="true">View TOD HOD
									</a>
								</td>
							</tr>
							<tr>
							 	<td>
							 		Add TOD/HOD Record
							 	</td>
							 	<td> 
								 	<form  method="GET" action="/populateTodHod">
										<button type="submit" class="btn btn-warning btn-lg active" 
										<%= !idNo.equals("K11RELIEF") && vList != null && vList.size() > 0 && !(usertype.equals("MANAGEMENT") || usertype.equals("ADMIN")) ? "disabled" : "" %>>Add TOD HOD</button>
									</form>
								</td>
							</tr>
							<tr>
							 	<td>
							 		Request for Leave
							 	</td>
							 	<td> 
								 	<a href="addLeave.jsp" class="btn btn-warning btn-lg active" role="button"
									aria-pressed="true">Request for Leave
									</a>
								</td>
							</tr>
							<tr>
							 	<td>
							 		View Leave Requests
							 	</td>
							 	<td> 
								 	<a href="/viewLeave" class="btn btn-warning btn-lg active" role="button"
									aria-pressed="true">View Leave Requests
									</a>
								</td>
							</tr>					
							<tr>
								<td>
							 		View Employee KET Record
							 		<br><small>To view your KET</small>
							 	</td>
							 	<td> 
								 	<a href="/viewEmp" class="btn btn-warning btn-lg active" role="button"
											aria-pressed="true">View Employee KET</a>
								</td>
							</tr>
							<tr>
								<td>
							 		View Payslips
								 	<br><small>To view all payslip records</small>
							 	</td>
							 	<td> 
								 	<a href="/viewOfficerPayslip" class="btn btn-warning btn-lg active" role="button"
											aria-pressed="true">View Payslips</a>
								</td>
							</tr>
							<!-- access control for K11 Admin/Management -->
							<%if (usertype.equals("MANAGEMENT") || usertype.equals("ADMIN")) {%>
								<%if (usertype.equals("ADMIN")) {%>
									<tr>
										<td>
									 		Create Account for K11 Employee
									 	</td>
									 	<td> 
										 	<a href="/addClientAccount.jsp" class="btn btn-warning btn-lg active" role="button"
													aria-pressed="true">Add Account</a>
										</td>
									</tr>
									<tr>
										<td>
									 		View/Update Account for K11 Employee
									 	</td>
									 	<td> 
										 	<a href="/retrieveAllClientRecords" class="btn btn-warning btn-lg active" role="button"
													aria-pressed="true">View Account</a>
										</td>
									</tr>
									<tr>
										<td>
									 		Create Site Record
									 	</td>
									 	<td> 
										 	<a href="/addSite.jsp" class="btn btn-warning btn-lg active" role="button"
													aria-pressed="true">Add Site</a>
										</td>
									</tr>
									<tr>
										<td>
									 		Delete All Officer Payslip Records
									 	</td>
									 	<td> 
										 	<a href="/deleteAllOfficerPayslips" class="btn btn-warning btn-lg active" role="button"
													aria-pressed="true">Delete All Officer Payslips</a>
										</td>
									</tr>
								<%} %>
								<tr>
									<td>
								 		View/Update Site Record
								 	</td>
								 	<td> 
									 	<a href="/retrieveAllSiteRecords" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">View Site</a>
									</td>
								</tr>
								<tr>
									<td>
								 		View Site Manpower Status 
								 	</td>
								 	<td> 
									 	<a href="/sitemanpowermanager.jsp" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">View Status</a>
									</td>
								</tr>
								<tr>
									<td>
								 		Manage Occurrence Reports 
								 		<br><small>To view and add occurences</small>
								 	</td>
								 	<td> 
									 	<a href="/manageOccurrenceRecord" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">Manage Occurences</a>
									</td>
								</tr>
								<tr>
									<td>
								 		Manage Incident Reports 
								 		<br><small>To view and add incidents</small>
								 	</td>
								 	<td> 
									 	<a href="/manageIncidentRecord" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">Manage Incidents</a>
									</td>
								</tr>
								<tr>
									<td>
								 		Generate Payslip
								 		<br><small>This will generate all the individual officer payslips for the month.</small>
								 	</td>
								 	<td> 
									 	<a href="payslipGenerator.jsp" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">Generate Payslips</a>
									</td>
								</tr>
								<tr>
									<td>
								 		Add Employee KET Record
								 	</td>
								 	<td> 
									 	<a href="/addEmployee.jsp" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">Add Employee KET</a>
									</td>
								</tr>
								<tr>
									<td>
								 		Manage PWM Details
								 		<br><small>Click to view PWM wage model or add a PWM record.This will be used in payslip 
								 		generation.</small>
								 	</td>
								 	<td> 
									 	<a href="retrieveAllPWMRecords" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">Manage PWM Details</a>
									</td>
								</tr>
								
							<%} %>
						</tbody>
				</table>		
				<br>
				<a href="index.jsp" class="btn btn-warning btn-lg active" role="button"
														aria-pressed="true">Back</a>	
			</div>
		<%} %>
</center>
<br>
	<% 
		if  (!idNo.equals("K11RELIEF") && vList != null && vList.size() > 0) {
	%>
			<div class="container body-content" id="tableview">
				<table id="example"
					class="table table-striped table-bordered table-sm sortable" style="width: 80%;">
					<thead>
						<tr>
							<th class="th-sm">S/N</th>
							<th class="th-sm">Officer Name</th>
							<th class="th-sm">Officer ID</th>
							<th class="th-sm">Site Name</th>
							<th class="th-sm">Shift</th>
							<th class="th-sm">TOD Time</th>
							<th class="th-sm">HOD Time</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (!vList.isEmpty()) {
								Iterator<TodHodRecord> vListIter = vList.iterator();
								while (vListIter.hasNext()) {
									TodHodRecord v = vListIter.next();
						%>
								<tr>
									<td><%=v.getRecordId()%></td>
									<td><%=v.getOfficerName()%></td>
									<td><%=v.getOfficerIdNo()%></td>
									<td><%=((v.getSiteName() == null) ? "" : v.getSiteName())%></td>
									<td><%=v.getShift()%></td>
									<td><%=sdf.format(v.getTimeInDt())%></td>
<!-- 								TO DO: if timeout is null - send to update servlet to update with system time -->
 									<% if (v.getTimeOutDt() != null) { %> 
 										<td><%=sdf.format(v.getTimeOutDt())%></td> 
 									<% 
 										}
 										else{
 									%> 
 										<td><form method="POST" action ="/updateTodHodRecord"> 
 											<input type="hidden" id="recordId" name="recordId" value="<%=v.getRecordId()%>"> 
 											<input type="submit" name="Submit"  class="btn btn-primary btn-lg active" value="Update">
											</form>
										</td> 
									<% 
										}
									%>
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
	</div>
</body>
</html>