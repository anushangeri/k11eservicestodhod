<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.javatutorial.entity.ClientAccount"%>
<%@page import="net.javatutorial.DAO.ClientAccountManagerDAO"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(document).ready(function() {
        $("#fileTypeSelect").on("change", function() {
            toggleFields();
        });

        // Initialize Datepicker for monthPicker
        $("#monthPicker").datepicker({
            dateFormat: 'MM',
            changeMonth: true,
            changeYear: false,
            showButtonPanel: true,
            onClose: function(dateText, inst) {
                $(this).datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, 1));
                updateDescription();
            }
        });

        // Initialize Datepicker for yearPicker
        $("#yearPicker").datepicker({
            dateFormat: 'yy',
            changeMonth: false,
            changeYear: true,
            showButtonPanel: true,
            onClose: function(dateText, inst) {
                $(this).datepicker('setDate', new Date(inst.selectedYear, 0, 1));
                updateDescription();
            }
        });

        $("#addMiscDocsForm").submit(function(event) {
            // No need to prevent default form submission here
            // Your form submission code here
            var formData = $(this).serialize();
            console.log(formData);
            // You can submit the form data using AJAX or any other method
        });
    });

    function toggleFields() {
        var fileType = $("#fileTypeSelect").val();
        var otherDescription = $("#otherDescription");
        var payslipDescription = $("#payslipDescription");

        if (fileType === "other") {
            otherDescription.show();
            otherDescription.find("input").prop("required", true); // Make the otherDescription input required
            $("#monthPicker").val().find("input").prop("required", false); // Remove the required attribute
            $("#yearPicker").val().find("input").prop("required", false); // Remove the required attribute
            payslipDescription.hide();
        } else if (fileType === "payslip") {
            otherDescription.hide();
            payslipDescription.show();
            $("#monthPicker").val().find("input").prop("required", true); // Make the payslipDescription input required
            $("#yearPicker").val().find("input").prop("required", true); // Make the payslipDescription input required
            otherDescription.find("input").prop("required", false); // Remove the required attribute
            updateDescription();
        }
    }

    function updateDescription() {
        var month = $("#monthPicker").val();
        var year = $("#yearPicker").val();
        var description = "Payslip " + month + " " + year;
        $("#description").val(description);
    }
</script>
</head>
<body>
    <div class="container body-content">
        <div class="page-header">
            <label class="heading">Employee Management System</label> <br>
            <b>How to use:</b> Please enter Miscellaneous or Individual Payslip Documents.<br>
            <b>IMPORTANT:</b> Please ensure you have Create Account for K11 Employee and Add Employee KET Record. This is needed before you add any
            miscellaneous documents for this K11 Employee, if not you will not see the document added successfully.
            <center>
                <%
                ArrayList<ClientAccount> eList = ClientAccountManagerDAO.retrieveAllInnerJoinEmpTbl();
                %>

                <form id="addMiscDocsForm" action="addMiscDocsRecord" enctype='multipart/form-data' method="post">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="employeeIdNo">Employee ID No: </label>
                            <select name="employeeIdNo" class="form-control">
                                <option style="display:none;"></option>
                                <%
                                for (ClientAccount eachEmp : eList) {
                                %>
                                    <option value="<%=eachEmp.getIdNo()%>">
                                    <%=eachEmp.getName()%>(<%=eachEmp.getIdNo()%>)</option>
                                <%
                                }
                                %>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="description">File Description: </label> 
                            <select name="fileType" id="fileTypeSelect" class="form-control" onchange="toggleFields()">
                                <option value=""></option>
                                <option value="other">Other</option>
                                <option value="payslip">Payslip</option>
                            </select>
                            <br>
                            <div id="otherDescription" style="display:none;">
                                <label for="description">File Description: </label>
                                <input type="text" class="form-control" name="description" id="description" >
                            </div>
                            <br>
                            <div id="payslipDescription" style="display:none;">
                                <label for="month">Month: </label>
                                <input type="text" id="monthPicker" class="form-control" name="monthPayslip" >

                                <label for="year">Year: </label>
                                <input type="text" id="yearPicker" class="form-control" name="yearPayslip" >
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="uploadFile">Upload File: </label> <input type="file"
                                class="form-control" name="uploadFile">
                        </div>
                    </div>
                    <div class="form-row">
                        <button type="submit" class="btn btn-primary btn-lg active">Submit
                            Record</button>

                        <a href="/viewMiscDocs?recordsToReceive=5days" class="btn btn-warning btn-lg active" role="button"
                            aria-pressed="true">Back</a>
                    </div>
                </form>
            </center>
        </div>
    </div>
</body>
</html>