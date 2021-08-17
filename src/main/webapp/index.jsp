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
	function validateForm() {
		var idNo = document.forms["checkNRIC"]["idNo"].value;
		var first = idNo.charAt(0);
		var isDigitFirst = (first >= '0' && first <= '9');
		var second = idNo.charAt(1);
		var isDigitSecond = (second >= '0' && second <= '9');
		var third = idNo.charAt(2);
		var isDigitThird = (third >= '0' && third <= '9');
		var forth = idNo.charAt(3);
		var isDigitForth = (forth >= '0' && forth <= '9');
		var n = idNo.length;
		if (idNo != "K11ADMIN" && (!(n >= 4) ||
				!isDigitFirst || !isDigitSecond || !isDigitThird || isDigitForth))  {
			alert("PDPA Compliance: Enter ONLY last 3 digit and letter of ID Number. E.g. 409J ");
			return false;
		}
		if (idNo != "K11ADMIN" && (!(n >= 4) ||
				!isDigitFirst || !isDigitSecond || !isDigitThird || !isDigitForth))  {
			alert("PDPA Compliance: Enter ONLY last 4 digit of Passport No. E.g. 4456");
			return false;
		}
	}
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
	<form name="verifyLogin" action="verifyLogin" method="post"
			onsubmit="return validateForm()">
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
						required><input type="checkbox" onclick="showPassword()">Show Password
				</div>
			</div>
			<br>
			<div class="form-row">
				<button type="submit" class="btn btn-primary btn-lg active">
				Login</button>
			</div>
		</form>
	</center>
</body>
</html>