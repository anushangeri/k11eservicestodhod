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
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/plug-ins/1.10.24/sorting/datetime-moment.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$.fn.dataTable.moment('DD/MM/YYYY hh:mm:ss A');
			$('table').DataTable({
				"order": [[ 0, "desc" ]],
				dom : 'Blfrtip',
				buttons : [ {
					text : 'Export To Excel',
					extend : 'excelHtml5',
					exportOptions : {
						modifier : {
							selected : true
						},
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
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
				"order": [[15, 'desc']]
			});
		});
	});
</script>
</head>

	<%
	 	String message = (String) request.getAttribute("message");
		ArrayList<TodHodRecord> vList = (ArrayList<TodHodRecord>) request.getAttribute("vList");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String usertype = "";
		String idNo = "";
		if (message != null && !StringUtils.isEmpty(message)) {
	%>
			<label class="heading"><%=message%></label>
	<%} %>
<body>
	<center>
	<%if ( !(session.getAttribute("usertype") == null)) {
		usertype = (String) session.getAttribute("usertype");
		idNo = (String) session.getAttribute("idNo");
			
		%>
			<br>
			<label class="heading">Please complete HOD before submitting another TOD.</label>
			<br>
			<div class="container body-content">
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
							 		View TOD/HOD records
							 	</td>
							 	<td> 
								 	<a href="todhodsearch.jsp" class="btn btn-warning btn-lg active" role="button"
									aria-pressed="true">View TOD HOD
									</a>
								</td>
							</tr>
							<tr>
							 	<td>
							 		Add TOD/HOD record
							 	</td>
							 	<td> 
								 	<form  method="GET" action="/populateTodHod">
										<button type="submit" class="btn btn-warning btn-lg active" 
										<%= !idNo.equals("K11RELIEF") && vList != null && vList.size() > 0 ? "disabled" : "" %>>Add TOD HOD</button>
									</form>
								</td>
							</tr>
							<!-- access control for K11 Admin -->
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
								 		Create Site record
								 	</td>
								 	<td> 
									 	<a href="/addSite.jsp" class="btn btn-warning btn-lg active" role="button"
												aria-pressed="true">Add Site</a>
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