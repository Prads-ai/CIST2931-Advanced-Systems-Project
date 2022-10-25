<%-- 
    Document   : patient_dashboard
    Created on : Aug 31, 2022, 11:25:37 AM
    Author     : Pradsley D'Haiti, Kevin Lyons
--%>

<%@page import="Business_Object.AppointmentList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business_Object.Chiropractor"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Business_Object.Patient"%>
<%@page import="Business_Object.Appointments"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link
            href="https://fonts.googleapis.com/css?family=Montserrat:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700"
            rel="stylesheet">
        <style>


        </style>
        <title>Chiropractor Dashboard</title>
    </head>
    <body>  
        <%
            Chiropractor chiro;
            chiro = (Chiropractor) session.getAttribute("cc");
            String chiro_ID = chiro.getchiroprac_id();

            Appointments appt = (Appointments) session.getAttribute("nameResult");
            Patient p = new Patient();

            AppointmentList appL = (AppointmentList) session.getAttribute("recentAppointment");
            Appointments ap = appL.RecentAppointment(appL.appointment);

        %>

        <header>
            <div class="container-fluid bg-dark">
                <div class="container">
                    <nav class="navbar navbar-expand-lg bg-dark navbar-dark">
                        <a href="#" class="navbar-brand">Chiropractor Dashboard</a>
                        <ul class="navbar-nav mb-2 ms-auto">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <iconify-icon icon="carbon:user-avatar"></iconify-icon>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="UpdateChiropractorInfo.jsp">Update Profile</a></li>
                                    <li><a class="dropdown-item" href="#">View Appointments</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="index.html">Log out</a></li>
                                </ul>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <section id="ads">
            <div class="container">
                <div class="bg-light shadow-sm">
                    <h1 class="display-5 mt-4 p-5">Welcome <%=chiro.getfirstName()%></h1>
                </div>
            </div>
        </section>


        <div class="container-fluid">
            <div class="container">
                <button class="btn btn-secondary mt-2" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> Recent Appointment</button>

                <div class="row mt-5">
                    <form action="SearchPatientServlet" method="post">
                        <div class="input-group mb-3 me-5" style="padding-right: 30rem;">
                            <span class="input-group-text" id="basic-addon1">🔎</span>
                            <input type="text" class= "form-control" placeholder="Enter the patient ID" aria-label="searchPatientName" name ="searchPatientName" aria-describedby="basic-addon1"> 
                            <button class="btn btn-secondary"type="submit">Search</button>
                        </div>
                    </form>
                    <!--Appointment table-->
                    <div class="col">
                        <%

                            ArrayList<Appointments> appl = new AppointmentList().getChiroAppointments(chiro_ID);

                            int count = 0;
                            if (!appl.isEmpty()) {
                                for (Appointments a : appl) {

                                    p.selectDB(a.getPatId());
                                    count++;

                        %>
                        <table class=" shadow table table-striped custab" style="width:100%">
                            <thead class="thead-light text-white" style="background-color:green;">
                                <tr>
                                    <th>Appointment</th>
                                    <th>Patient Name</th>
                                    <th>Patient ID</th>
                                    <th>Procedure Code</th>
                                    <th>Office Number</th>
                                    <th class=" d-none text-center">Action</th>
                                </tr>
                            </thead>
                            <tr>
                                <td><%= a.getApptDateTime()%></td>
                                <td><%=p.getFirstName()%></td>
                                <td><%= a.getPatId()%></td>
                                <td><%= a.getProcCode()%></td>
                                <td><%= a.getOfficeNum()%></td>
                                <td class=" d-none text-center">
                                    <a class='btn btn-info btn-xs' href="PatientEditAppointmentServlet?id=<%=a.getId()%>"><span class="glyphicon glyphicon-edit "></span> Edit</a>
                                    <a href="#" class="btn btn-danger btn-xs"><span
                                            class=" glyphicon glyphicon-remove"></span> Del</a></td>
                            </tr>
                        </table>
                        <%}
                            }

                        %>
                    </div>
                    <!--New Column-->
                    <div class="col-3">
                        <div class="card shadow-sm" style="width: 18rem; position: relative; bottom: 12rem;">
                            <img
                                src="https://img.freepik.com/premium-vector/chiropractic-logo-design-massage-teraphy-with-unique-concept-premium-vector_427060-982.jpg?size=626&ext=jpg&uid=R21005023&ga=GA1.2.1224631341.1657856377"
                                class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="text-center card-title"> Information</h5>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><%=chiro.getlastName()%></li>
                                <li class="list-group-item"><%=chiro.getemail()%></li>
                                <li class="list-group-item"><%=chiro.getoffice_num()%></li>
                                <li class="list-group-item"><%= chiro.getadmin_id()%></li>
                            </ul>
                            <div class="card-body">
                                <a href="UpdateChiropractorInfo.jsp"  class="text-center text-decoration-none card-link"><button class=" ms-5 btn btn-info">Update profile</button></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- Recent Appointment Pop up -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Recent Appointment Detail</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>
                    <h6>Appointment Date Time : </h6> <%= ap.getApptDateTime()%>
                    </p>
                    <p>
                    <h6>Patient ID : </h6> <%=ap.getPatId()%>
                    </p>
                    <p>
                    <h6>Procedure Code : </h6> <%=ap.getProcCode()%>
                    </p>
                    <p>
                    <h6>Office Number : </h6> <%=ap.getOfficeNum()%>
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap Javascript-->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
    crossorigin="anonymous"></script>
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <!-- End of Bootstrap Javascript-->
    <!--Icony cdn Script-->
    <script src="https://code.iconify.design/iconify-icon/1.0.0-beta.3/iconify-icon.min.js"></script>

</body>

</html>