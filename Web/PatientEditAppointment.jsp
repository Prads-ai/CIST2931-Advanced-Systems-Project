<%-- 
    Document   : patient_dashboard
    Created on : Aug 31, 2022, 11:25:37 AM
    Author     : Pradsley D'Haiti, Kevin Lyons
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Business_Object.AppointmentList"%>
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
        <title>Book An Appointment</title>
    </head>
    <body>  
        <%
            Patient currentPatient;
            currentPatient = (Patient) session.getAttribute("currentPatient");
            String patientID = currentPatient.getPatientId();

            Appointments appt;
            appt = (Appointments) session.getAttribute("updatedAppt");


        %>

        <header>
            <div class="container-fluid bg-dark">
                <div class="container">
                    <nav class="navbar navbar-expand-lg bg-dark navbar-dark">
                        <a href="#" class="navbar-brand">Patient Dashboard</a>
                        <ul class="navbar-nav mb-2 ms-auto">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <iconify-icon icon="carbon:user-avatar"></iconify-icon>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="UpdatePatientInfo.jsp">Update Profile</a></li>
                                    <li><a class="dropdown-item" href="#">View Appointments</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="#">Log out</a></li>
                                </ul>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <section id="ads">
            <div class="container">
                <div class="bg-light shadow-sm">
                    <h1 class="display-5 mt-4 p-5">  Hello <%=currentPatient.getFirstName()%></h1>
                </div>
            </div>
        </section>

        <section id=" info-table">


            <div class="container-fluid">
                <div class="container">
                    <button class="btn btn-secondary mt-2 d-none"type="button" >EDIT AN APPOINTMENT</button>

                    <form action="PatientEditAppointmentServlet" method="POST">
                        <input type="hidden" name="command">
                        <p>
                            <label>Choose a new  date</label><br>
                            <input class="form-control form-control-md" type="date" name="date" value="<%= appt.getApptDateTime()%>" required>
                        </p>
                        <p><label>Choose a Chiropractor</label></p>
                        <p>

                            <select class="form-control form-control-md" name="chiropract_id" required>Chiropractor 
                                <option value="<%=appt.getChiropractorId()%>"><%=appt.getChiropractorId()%></option>
                                <option value="C540">C540</option>
                                <option value="C510">C510</option>

                            </select>
                        </p>

                        <%-------------------------- Procedure ----------------------------%>

                        <p><label>Choose a procedure</label></p>
                        <p>
                            <select class="form-control form-control-md" name="proc_code" required>Chiropractor 

                                <option value="<%=appt.getProcCode()%>"><%=appt.getProcCode()%></option>
                                <option value="PR302">PR302</option>
                                <option value="PR303">PR303</option>
                                <option value="PR304">PR304</option>
                                <option value="PR305">PR305</option>

                            </select>
                        </p>
                        <%-------------------------- Office Number ----------------------------%>

                        <p><label>Choose an office number</label></p>
                        <p>
                            <select class="form-control form-control-md" name="office_num" required>Chiropractor 
                                <option value="<%=appt.getOfficeNum()%>"><%=appt.getOfficeNum()%></option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </p>
                        <button type="submit" class="btn btn-secondary"  name="submit">Save</button>
                    </form> 
                </div>
            </div>
        </div>

        <%-- <p> Values entered: <%= patientID%> + ${param.date}  + ${param.chiropract_id} + ${param.proc_code} + ${param.office_num} </p> --%>
    </div>
</section>
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