<%-- 
    Document   : patient_dashboard
    Created on : Aug 31, 2022, 11:25:37 AM
    Author     : Pradsley D'Haiti
--%>

<%@page import="Business_Object.ChiropractorList"%>
<%@page import="Business_Object.Admin"%>
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

            Admin ad;
            ad = (Admin) session.getAttribute("admin");
            String admin;
            ChiropractorList chiroList = (ChiropractorList) session.getAttribute("allChiropractor");

            response.setHeader("Refresh", 5 + "; URL = Admin_Login.jsp");


        %>

        <div class="card text-center" >
            <div class="card-header">
                <img src="confirmation.png" class="card-img-top" style=" width: 65rem; height: 38rem;" alt="confirmation page">
            </div>
            <div class="card-body">
                <h5 class="card-title">Important information ></h5>
                <p class=" card-text">In order to view your recent changes you will need to log back in to the system.</p>
                <a href="index.html" class="d-none btn btn-primary">Go back to dashboard</a>
            </div>
            <div class="card-footer text-muted">
                log in out in 5 secs...
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