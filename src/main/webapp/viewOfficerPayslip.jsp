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
			$('table').DataTable({
				"order": [[0, "asc"]],
				dom : 'Blfrtip',
				buttons : [ {
					text : 'Export To Excel',
					extend : 'excelHtml5',
					exportOptions : {
						modifier : {
							selected : true
						},
						columns : [ 0, 1, 2, 3],
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
				"order": [[0, 'asc']]
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
		ArrayList<OfficerPayslip> vList = (ArrayList<OfficerPayslip>) request.getAttribute("vList");
		String message = (String) request.getAttribute("message");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String idNo = "";
		String userType = "";
		if (request.getSession(false).getAttribute("idNo") != null) {
			idNo = (String) request.getSession(false).getAttribute("idNo");
			userType = (String) request.getSession(false).getAttribute("usertype");
		}
		
		
		if (message != null && !StringUtils.isEmpty(message)) {
		%>
		<label class="heading"><%=message%> </label><br> <b>*Individuals
			are required to self-identify should they experience any COVID-19
			symptoms.</b>
	</center>
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
							<th class="th-sm">Officer ID No.</th>
							<th class="th-sm">Created Date</th>
							<th class="th-sm">Payslip</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (!vList.isEmpty()) {
							Iterator<OfficerPayslip> vListIter = vList.iterator();
							while (vListIter.hasNext()) {
								OfficerPayslip v = vListIter.next();
						%>
						<tr>
							<td><%=v.getPayslipId()%></td>
							<td><%=v.getOfficerIdNo()%></td>
							<td><%=v.getCreatedDt()%></td>
							<td><a href="downloadPayslip?payslipId=<%=v.getPayslipId()%>">Download</a></td>
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
				<a href="/dashboard.jsp" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Back</a>
			</div>
		</center>
	</div>

</body>
</html>