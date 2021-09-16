<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />
<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<script>
	function showPassword() {
		  var x = document.getElementById("psw");
		  if (x.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
	}
</script>
</head>
<body>
	<%
		session.removeAttribute("usertype");
		session.removeAttribute("name");
		session.removeAttribute("idNo");
		
		String responseObj = (String) request.getAttribute("responseObj");
		if (responseObj != null && !StringUtils.isEmpty(responseObj)) {
	%>
			<label class="heading"><%=responseObj%> </label><br>
		<%} %>
	<center>
	<form name="verifyLogin" action="verifyLogin" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="idNo">ID Number: </label> <input type="text"
						class="form-control" name="idNo" id="idNo" placeholder="xxxx" oninput="this.value = this.value.toUpperCase()"
						minlength="4" maxlength="9" required>
				</div>
				<div class="form-group col-md-4">
					<label for="psw">Password</label> <input type="password" class="form-control" id="psw" 
						name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
						title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
						required>
						
						<input type="checkbox" onclick="showPassword()"><label for="showPassword">Show Password</label>
				</div>
				<div class="form-group">
					<input type="checkbox" value="lsRememberMe" id="rememberMe"> <label for="rememberMe">Remember me</label>
				</div>
			</div>
			<br>
			<div class="form-row">
				<button type="submit" class="btn btn-primary btn-lg active"  onclick="lsRememberMe()">
				Login</button>
			</div>
		</form>
	</center>
	
	
	<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>
	<script>
		const rmCheck = document.getElementById("rememberMe");
		idNo = document.getElementById("idNo");
		psw = document.getElementById("psw");
		if (localStorage.checkbox && localStorage.checkbox !== "") {
			rmCheck.setAttribute("checked", "checked");
			idNo.value = localStorage.username;
			psw.value = localStorage.psw;
		} else {
		  rmCheck.removeAttribute("checked");
		  idNo.value = "";
		  psw.value = "";
		}
		
		function lsRememberMe() {
		  if (rmCheck.checked && idNo.value !== "") {
		    localStorage.username = idNo.value;
		    localStorage.psw = psw.value;
		    localStorage.checkbox = rmCheck.value;
		  } else {
		    localStorage.username = "";
		    localStorage.psw = "";
		    localStorage.checkbox = "";
		  }
		}
	</script>
</body>
</html>