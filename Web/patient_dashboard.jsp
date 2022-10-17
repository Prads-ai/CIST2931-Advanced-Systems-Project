<%-- 
    Document   : patient_dashboard
    Created on : Aug 31, 2022, 11:25:37 AM
    Author     : pach3
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
    <title>Patient Dashboard</title>
</head>
<body>  
    <%
        Patient currentPatient;
        currentPatient = (Patient)session.getAttribute("currentPatient");
        String patientID = currentPatient.getPatientId();
        
        Appointments appt;
        appt = (Appointments)session.getAttribute("appt");
        appt.selectDB(patientID);
        
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
        <h1 class="display-5 mt-4 p-5">Welcome <%=currentPatient.getFirstName()%></h1>
      </div>
     </div>
    </section>
      
     
    <div class="container-fluid">
      <div class="container">
          <button class="btn btn-secondary mt-2"type="button" class="btn btn-primary"> <a href="BookAppointment.jsp" class="text-white text-decoration-none"><a href="BookAppointment.jsp" class="text-white text-decoration-none">ADD AN APPOINTMENT</a></button>
         
          <div class="row mt-5">
              <form action="SearchAppointmentServlet" method="post">
                <div class="input-group mb-3 me-5" style="padding-right: 30rem;">
                    <span class="input-group-text" id="basic-addon1">🔎</span>
                    <input type="text" class= "form-control" placeholder="Enter a date" aria-label="searchAppointment" name ="searchAppointment" aria-describedby="basic-addon1"> 
                    <button class="btn btn-secondary"type="submit">Search</button>
                </div>
              </form>
          <!--Appointment table-->
          <div class="col">
              <%
                       Patient p = (Patient)session.getAttribute("currentPatient");
                      
                       ArrayList<Appointments> appl = new AppointmentList().getAppointments(patientID);
                       HttpSession sessionList = request.getSession();
                       sessionList.setAttribute("updatedAppt", appl);
                       int count = 0;
                       if (!appl.isEmpty()){
                           for(Appointments a : appl) {
                               count++;
              %>
                               <table class=" shadow table table-striped custab" style="width:100%">
                                <thead class="thead-light">
                                    <tr>
                                        <th>Appointment</th>
                                        <th>Chiropractor's name</th>
                                        <th>Procedure Code</th>
                                        <th>Office Number</th>
                                         <th class="text-center">Action</th>
                                    </tr>
                                </thead>
                                <tr>
                                    <td><%= a.getApptDateTime()%></td>
                                    <td><%=a.getChiropractorId()%></td>
                                    <td><%= a.getProcCode()%></td>
                                    <td><%= a.getOfficeNum()%></td>
                                    <td class="text-center">
                                        <a class='btn btn-info btn-xs' href="PatientEditAppointmentServlet?id=<%=a.getId()%>"><span class="glyphicon glyphicon-edit "></span> Edit</a>
                                        <a href="#" class="btn btn-danger btn-xs"><span
                                        class="glyphicon glyphicon-remove"></span> Del</a></td>
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
                <h5 class="text-center card-title">Address Information</h5>
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item"><%=p.getAddress()%></li>
                <li class="list-group-item"><%=p.getCity()%></li>
                <li class="list-group-item"><%=p.getState()%></li>
                 <li class="list-group-item"><%=p.getZip()%></li>
              </ul>
              <div class="card-body">
                <a href="UpdatePatientInfo.jsp" class="text-center text-decoration-none card-link">Edit Address</a>
              </div>
            </div>
          </div>
        </div>
      </div>
  </section>
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
