<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
    <center>
        <br>
        <div class="card">
            <a href="todhodsearch.jsp">
                <div class="eachCard crop">
                    <img class="center-block" src="todhod.png" alt="todhodday.jpg">
                    <h4 class="fontheader"><b>VIEW TOD/HOD DETAILS</b></h4> 
                </div>
            </a>
        </div>
        
        <br>
       <%
	        String responseObj = (String) request.getAttribute("responseObj");
	        if (responseObj != null) {
	            %>
	            <label class="heading"><%=responseObj%></label>
	            <%
	        }
        %>
       </center>
    </body>
</html>
