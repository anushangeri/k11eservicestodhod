<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="com.google.gdata.data.spreadsheet.CellEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.Cell"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gdata.client.spreadsheet.SpreadsheetService"%>
<%@page
	import="com.google.gdata.data.spreadsheet.CustomElementCollection"%>
<%@page import="com.google.gdata.data.spreadsheet.ListEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.ListFeed"%>
<%@page import="com.google.gdata.util.ServiceException"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
<!-- password check styling -->	
<style type="text/css">
/* The message box is shown when the user clicks on the password field */
#message {
  display:none;
  background: #f1f1f1;
  color: #000;
  position: relative;
  padding: 20px;
  margin-top: 10px;
}

#message p {
  padding: 10px 35px;
  font-size: 18px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
  color: green;
}

.valid:before {
  position: relative;
  left: -35px;
  content: "✔";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
  color: red;
}

.invalid:before {
  position: relative;
  left: -35px;
  content: "✖";
}
</style>
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
		  var y = document.getElementById("oldpassword");
		  if (x.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
		  if (y.type === "password") {
		    y.type = "text";
		  } else {
		    y.type = "password";
		  }
	}
</script>
</head>
<body>
	<%
		ArrayList<String> idType = new ArrayList<String>();
		ArrayList<String> accessType = new ArrayList<String>();
		SpreadsheetService service = new SpreadsheetService("K11CLICKS: DROPDOWN EXCEL");
		try {
			//Dropdown for idType START
			String idTypeUrl = "https://spreadsheets.google.com/feeds/list/116L_MDacE0331uQDZLRQD4UKpKXfHgWKcMFeD0ne324/3/public/values";
			// Use this String as url
			URL idTypeurl = new URL(idTypeUrl);

			// Get Feed of Spreadsheet url
			ListFeed idTypelf = service.getFeed(idTypeurl, ListFeed.class);

			for (ListEntry le : idTypelf.getEntries()) {
				CustomElementCollection cec = le.getCustomElements();
				idType.add(cec.getValue("idtype").trim());
			}
			//Dropdown for idType END
			
			//Dropdown for accessType START
			//REMEMBER TO ADD A SPACE FOR THE FIRST EMPTY ROW!
			String accessTypeUrl = "https://spreadsheets.google.com/feeds/list/116L_MDacE0331uQDZLRQD4UKpKXfHgWKcMFeD0ne324/10/public/values";
			// Use this String as url
			URL accessTypeurl = new URL(accessTypeUrl);

			// Get Feed of Spreadsheet url
			ListFeed accessTypelf = service.getFeed(accessTypeurl, ListFeed.class);

			for (ListEntry le : accessTypelf.getEntries()) {
				CustomElementCollection cec = le.getCustomElements();
				accessType.add(cec.getValue("accesstype").trim());
				
			}
			//Dropdown for accessType END

		} catch (Exception e) {
	%>
	<h1><%=e%></h1>
	<%
		}
	%>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Visitor/Vehicle Management System</label> <br>
			<b>How to use:</b> Please enter Client Details.
			<center>
				<form action="resetPassword" method="post" onsubmit="return validateForm()">
					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="idType">ID Type: </label> <select name="idType"
								class="form-control" required>
								<%
									for (int i = 0; i < idType.size(); i++) {
								%>
								<option value="<%=idType.get(i)%>">
									<%=idType.get(i)%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="idNo">ID Number: </label> <input type="text"
								class="form-control" name="idNo"
								oninput="this.value = this.value.toUpperCase()" minlength="4"
								maxlength="9">
						</div>
						<div class="form-group col-md-4">
							<label for="oldpassword">Old Password</label> <input type="password" class="form-control" id="oldpassword"
								name="oldpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
								required>
						</div>
						<div class="form-group col-md-4">
							<label for="psw">New Password</label> <input type="password" class="form-control" id="psw"
								name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
								required><input type="checkbox" onclick="showPassword()">Show Password
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit
							Record</button>
							
						<a href="/index.jsp" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
				<div id="message">
					  <h3>Password must contain the following:</h3>
					  <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
					  <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
					  <p id="number" class="invalid">A <b>number</b></p>
					  <p id="length" class="invalid">Minimum <b>8 characters</b></p>
				</div>
		
			</center>
		</div>
	</div>
	<script>
		//script must be loaded at the end for the password check to work
		var myInput = document.getElementById("psw");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");
		
		// When the user clicks on the password field, show the message box
		myInput.onfocus = function() {
		  document.getElementById("message").style.display = "block";
		}
		
		// When the user clicks outside of the password field, hide the message box
		myInput.onblur = function() {
		  document.getElementById("message").style.display = "none";
		}
		
		// When the user starts to type something inside the password field
		myInput.onkeyup = function() {
		  // Validate lowercase letters
		  var lowerCaseLetters = /[a-z]/g;
		  if(myInput.value.match(lowerCaseLetters)) {  
		    letter.classList.remove("invalid");
		    letter.classList.add("valid");
		  } else {
		    letter.classList.remove("valid");
		    letter.classList.add("invalid");
		  }
		  
		  // Validate capital letters
		  var upperCaseLetters = /[A-Z]/g;
		  if(myInput.value.match(upperCaseLetters)) {  
		    capital.classList.remove("invalid");
		    capital.classList.add("valid");
		  } else {
		    capital.classList.remove("valid");
		    capital.classList.add("invalid");
		  }
		
		  // Validate numbers
		  var numbers = /[0-9]/g;
		  if(myInput.value.match(numbers)) {  
		    number.classList.remove("invalid");
		    number.classList.add("valid");
		  } else {
		    number.classList.remove("valid");
		    number.classList.add("invalid");
		  }
		  
		  // Validate length
		  if(myInput.value.length >= 8) {
		    length.classList.remove("invalid");
		    length.classList.add("valid");
		  } else {
		    length.classList.remove("valid");
		    length.classList.add("invalid");
		  }
		}
	</script>
</body>
</html>