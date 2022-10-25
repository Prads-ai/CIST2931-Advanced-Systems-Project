<%--
    Document   : AdminEditChiropractor
    Created on : Oct 18, 2022, 10:33:25 PM
    Author     : Pradsley D'Haiti, Kevin Lyons
--%>

<%@page import="Business_Object.Chiropractor"%>
<%@page import="Business_Object.ChiropractorList"%>
<%@page import="Business_Object.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Edit Page</title>
        <style>
            .header{
                border-top: 3px solid #28a745;
                color: #fff;
                width: 70%;
                padding: 1%;
            }
            .header-left i{
                margin-left: 5%;
            }
            .header-right h4{
                text-align: right;
                margin-right: 10%;
                line-height: 2;
            }
            .content{
                background: #28a745;
                width: 70%;
            }
            .content .form-content{
                padding:10%;
            }
            .content .form-content .input-group .input-group-text{
                background:#333;
                color:#fff;
                border:none;
                border-radius:0;
                font-weight: 600;
            }
            .content .form-content .input-group input{
                border-radius: 0;
            }
            .content .form-content .input-group input:focus{
                border-color: transparent;
            }
            .content .form-content h4{
                margin-bottom:5%;
            }
            .content .form-content button{
                background: #333;
                color: #fff;
                border-radius: 0;
                width: 20%;
                font-weight: 600;
            }
        </style>
    </head>

    <body>
        <%
            HttpSession selectedChiro = request.getSession();

            Admin ad;
            ad = (Admin) session.getAttribute("admin");
            String admin;

            ChiropractorList chiroList = (ChiropractorList) session.getAttribute("allChiropractor");
            String id = request.getParameter("id");
            int convertId = Integer.parseInt(id);

            Chiropractor selected = chiroList.getSingleChiropractor(convertId);
            selectedChiro.setAttribute("selected", selected);


        %>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <div class="container header">
            <div class="row">
                <div class="col-md-6 header-left">
                    <i class="fab fa-canadian-maple-leaf fa-3x"></i>
                </div>
                <div class="col-md-6 header-right">
                    <h4>Learn from the best.</h4>
                </div>
            </div>
        </div>
        <div class="container content">
            <form action="AdminUpdateChiropractorInfoServlet" method="post">
                <div class="form-content">
                    <h4>Please fill out the details to update the chiropractor data on the  website.</h4>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">Chiropractor ID</span>
                        </div>
                        <input type="text" name ="chiroprac_id" class="form-control" aria-label="chiroprac_id" aria-describedby="basic-addon1" value="<%=selected.getchiroprac_id()%>">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon2">FirstName</span>
                        </div>
                        <input type="text" name = "firstname"class="form-control" aria-label="firstname" aria-describedby="basic-addon2" value="<%=selected.getfirstName()%>">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon2">Lastname</span>
                        </div>
                        <input type="text" name = "lastname" class="form-control" aria-label="lastname" aria-describedby="basic-addon2" value="<%=selected.getlastName()%>">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span  class="input-group-text" id="basic-addon2">Email</span>
                        </div>
                        <input type="text" name ="email"class="form-control" aria-label="email" aria-describedby="basic-addon2" value="<%=selected.getemail()%>">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon2">office_num</span>
                        </div>
                        <input type="text" name="office_num"class="form-control" aria-label="office_num" aria-describedby="basic-addon2" value="<%=selected.getoffice_num()%>">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon2">Schedule</span>
                        </div>
                        <input type="text" name="schedule" class="form-control" aria-label="schedule" aria-describedby="basic-addon2" value="<%=selected.getSchedule()%>">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon2">Admin ID</span>
                        </div>
                        <input type="text" name="adminID"  class="form-control" aria-label="adminID" aria-describedby="basic-addon2" value="<%=selected.getadmin_id()%>">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon2">Password</span>
                        </div>
                        <input type="text" name="password" class="form-control" aria-label="password" aria-describedby="basic-addon2" value="<%=selected.getPassword()%>">
                    </div>
                    <div class="input-group mb-3">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        </div>

    </body>
</html>
