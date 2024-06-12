<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
	</head>
<body>
 <%
	        String responseObj = (String) request.getAttribute("responseObj");
	        if (responseObj != null) {
	            %>
	            <label class="heading"><%=responseObj%></label>
	            <%
	        }
  %>
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Table Name</th>
	      <th scope="col">Create</th>
	      <th scope="col">Update</th>
	      <th scope="col">Delete</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Employee</td>
	      <td><form action="createEmpTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateEmpTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="deleteEmpTbl" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Client Account</td>
	      <td><form action="clientAccountTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateClientAccountTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	   	<tr>
	      <th scope="row">3</th>
	      <td>Site</td>
	      <td><form action="createSiteTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateSiteTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">4</th>
	      <td>Dropdown List - not started</td>
	      <td><form action="createDropdownTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">5</th>
	      <td>TOD HOD Records</td>
	      <td><form action="createTodHodTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateTodHodTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">6</th>
	      <td>Occurrence Records</td>
	      <td><form action="createOccurrenceTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateOccurrenceTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">7</th>
	      <td>Upload Payslip Document Records</td>
	      <td><form action="createPayslipTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="deletePayslipTbl" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>	
	    <tr>
	      <th scope="row">8</th>
	      <td>Incident Reports</td>
	      <td><form action="createIncidentTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateIncidentTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>	    
	    <tr>
	      <th scope="row">10</th>
	      <td>Leave</td>
	      <td><form action="createLeaveTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	   	<tr>
	      <th scope="row">11</th>
	      <td>Officer Payslip</td>
	      <td><form action="createOfficerPayslipTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">12</th>
	      <td>PWM Details</td>
	      <td><form action="createPWMTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">13</th>
	      <td>Misc Documents Records</td>
	      <td><form action="createMiscDocsTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	  </tbody>
	</table>
</body>
</html>