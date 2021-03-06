<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<%
		ArrayList<String> responseObj = (ArrayList<String>) request.getAttribute("responseObj");
		if (responseObj != null) {
	%>
	<label class="heading"><%=responseObj.toString()%></label>
	<%} %>
	<center>
	<%if ( !(session.getAttribute("usertype") == null)) {
			String usertype = (String) session.getAttribute("usertype");
			
		%>
				<!-- access control for K11 Security -->
				<%if (usertype.equals("K11SECURITY")) {%>
				<br>
				<div class="card">
					<a href="todhodsearch.jsp">
						<div class="eachCard crop">
							<img class="center-block" src="todhod.png" alt="todhodday.jpg">
							<h4 class="fontheader">
								<b>VIEW TOD/HOD DETAILS</b>
							</h4>
						</div>
					</a>
				</div>
		
				<br>
				<%} %>
				<!-- access control for K11 Admin -->
				<%if (usertype.equals("K11ADMIN")) {%>
				<br>
				<div class="card">
					<a href="todhodsearch.jsp">
						<div class="eachCard crop">
							<img class="center-block" src="todhod.png" alt="todhodday.jpg">
							<h4 class="fontheader">
								<b>VIEW TOD/HOD DETAILS</b>
							</h4>
						</div>
					</a>
				</div>
		
				<br>
			<%} %>
<%} %>
<!-- the following is for vehicle registration -->
<%if ( !(session.getAttribute("siteUser") == null)) {
		String siteUser = (String) session.getAttribute("siteUser");
		
%>
	<div class="card">
		<a href="viewVehRegRec.jsp">
			<div class="eachCard crop">
				<img class="center-block" src="VMS_icon.png"
					alt="VMS_icon.png">
				<h4 class="fontheader">
					<b>Site Vehicle Registration Record</b>
				</h4>
			</div>
		</a>
	</div>	
<%} %>
</center>
</body>
</html>
