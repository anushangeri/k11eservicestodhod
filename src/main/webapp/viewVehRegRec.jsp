<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="com.google.gdata.client.spreadsheet.SpreadsheetService"%>
<%@page import="com.google.gdata.data.spreadsheet.CustomElementCollection"%>
<%@page import="com.google.gdata.data.spreadsheet.ListEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.ListFeed"%>
<%@page import="com.google.gdata.util.ServiceException"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#escalation').DataTable();
        });
    </script>
</head>
<body>

    <%
        SpreadsheetService service = new SpreadsheetService("K11 Site Vehicle Registration Record");
        try {
//            //getting the sheet number:
//            String sheetNameUrl
//                    = "https://spreadsheets.google.com/feeds/list/1Fcv9alOBh-SHR7Yxl_yiQMK05lbjJaDX7ba5AkTrK90/1/public/values";
//
//            // Use this String as url
//            URL urlName = new URL(sheetNameUrl);
//
//            // Get Feed of Spreadsheet url
//            ListFeed lfName = service.getFeed(urlName, ListFeed.class);
//            String sheetNumber = "1";
//            //Iterate over feed to get cell value
//            for (ListEntry le : lfName.getEntries()) {
//                CustomElementCollection cec = le.getCustomElements();
//                //Pass column name to access it's cell values
//                if (cec.getValue("sheetname").equals("Employment Particulars")) {
//                    sheetNumber = cec.getValue("sheetnumber");
//                }
//            }
            String sheetUrl
                    = "https://spreadsheets.google.com/feeds/list/1rr0X99WE0CFibWw-Vj98m49cMSFdXZW0MK6yIiP394k/1/public/values";

            // Use this String as url
            URL url = new URL(sheetUrl);

            // Get Feed of Spreadsheet url
            ListFeed lf = service.getFeed(url, ListFeed.class);
    %>
    <div class="container body-content">
        <div class="page-header">
            <label class="heading">Site Vehicle Registration</label>
            <br>
            <div class="form-group">
                <fieldset>
                    <form action="" class="form-group" method="post">
                        <div class="table-responsive">
                            <div class="table-responsive">                            

                                <table id="escalation" class="table table-striped table-bordered everytable" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th><center><b>	Timestamp	</th><center><b>
											<th><center><b>	Vehicle Number	</th><center><b>
											<th><center><b>	Car Decal	</th><center><b>
											<th><center><b>	Site User	</th><center><b>
											<th><center><b>	Staff Name	</th><center><b>
											<th><center><b>	Staff NRIC/FIN (Driver)	</th><center><b>
											<th><center><b>	Driver Company ( Site User)	</th><center><b>
											<th><center><b>	Contact No / HP No. ( Driver)	</th><center><b>
											<th><center><b>	Staff In_Charge Name & Contact No	</th><center><b>
											<th><center><b>	Email (Staff )	</th><center><b>
											<th><center><b>	Security Officer Name </th><center><b>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%
                                            //Iterate over feed to get cell value
                                            for (ListEntry le : lf.getEntries()) {
                                                CustomElementCollection cec = le.getCustomElements();
                                                if ( !(session.getAttribute("siteUser") == null)) {
                                            		String siteUserSession = (String) session.getAttribute("siteUser");
                                            		String siteUser = cec.getValue("siteuser");
                                            		if (siteUserSession.equalsIgnoreCase(siteUser)){
                                            		 //Pass column name to access it's cell values
                                                    String timestamp = cec.getValue("timestamp");
                                                    String vehiclenumber = cec.getValue("vehiclenumber");
                                                    String cardecal = cec.getValue("cardecal");
                                                    String staffname = cec.getValue("staffname");
                                                    String staffnricfindriver = cec.getValue("staffnricfindriver");
                                                    String drivercompanysiteuser = cec.getValue("drivercompanysiteuser)");
                                                    String contactnohpnodriver = cec.getValue("contactnohpnodriver");
                                                    String staffinchargenamecontactno = cec.getValue("staffinchargenamecontactno");
                                                    String emailstaff = cec.getValue("emailstaff");
                                                    String securityofficername = cec.getValue("securityofficername");
 												 %>
		                                            <tr>
		                                                 <td><center><%=	timestamp	%></center></td>
														 <td><center><%=	vehiclenumber	%></center></td>
														 <td><center><%=	cardecal	%></center></td>
														 <td><center><%=	siteUser	%></center></td>
														 <td><center><%=	staffname	%></center></td>
														 <td><center><%=	staffnricfindriver	%></center></td>
														 <td><center><%=	drivercompanysiteuser	%></center></td>
														 <td><center><%=	contactnohpnodriver	%></center></td>
														 <td><center><%=	staffinchargenamecontactno	%></center></td>
														 <td><center><%=	emailstaff	%></center></td>
														 <td><center><%=	securityofficername	%></center></td>

		                                            </tr>

                                    <%
                                            		}//if (siteUserSession.equalsIgnoreCase(siteUser))
                                                }//if ( !(session.getAttribute("siteUser") == null)) 
                                          }//for
                                    %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>            <!--END OF FORM ^^-->
                </fieldset>
            </div>
        </div>
    </div>

    <%
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</body>
</html>
