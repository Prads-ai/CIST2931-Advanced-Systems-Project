<%-- 
    Document   : patient_dashboard
    Created on : Aug 31, 2022, 11:25:37 AM
    Author     : pach3
--%>

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
        <h1 class="display-5 mt-4 p-5">  Welcome NAME_OF_CHIROPRACTOR </h1>
      </div>
     </div>
    </section>
      <section id=" info-table">
    <div class="container-fluid">
      <div class="container">
        <div class="row mt-5">
          <!--Appointment table-->
          <div class="col">
            <table class="table table-striped custab" style="width:100%">
              <thead class="thead-light">
                <tr>
                  <th>Appointment date and time</th>
                  <th>Chiropractor's name</th>
                  <th>Procedure Code</th>
                  <th class="text-center">Action</th>
                </tr>
              </thead>
              <tr>
                <td>Appointment</td>
                <td>Chiropractor_id</td>
                <td>Procedure Code</td>
                <td class="text-center"><a class='btn btn-info btn-xs' href="#"><span
                      class="glyphicon glyphicon-edit "></span> Edit</a> <a href="#" class="btn btn-danger btn-xs"><span
                      class="glyphicon glyphicon-remove"></span> Del</a></td>
              </tr>
            </table>
          </div>
        </div>
      </div>
  </section>
  <!-- Bootstrap Javascript-->
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
    integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
    integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
    crossorigin="anonymous"></script>
  <!-- End of Bootstrap Javascript-->
  <!--Icony cdn Script-->
  <script src="https://code.iconify.design/iconify-icon/1.0.0-beta.3/iconify-icon.min.js"></script>
</body>

</html>