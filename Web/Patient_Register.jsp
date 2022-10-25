<%-- 
    Document   : DisplayPatientInfo
    Created on :  9/10/2022, 3:09:26 PM
    Author : Pradsley D'Haiti, Kevin Lyons
--%>
<%@page import="Business_Object.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient information</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <style>
            body {
                color: #000;
                overflow-x: hidden;
                height: 100%;
                background-image: url("https://img.freepik.com/free-vector/city-hospital-building_74855-6301.jpg?t=st=1650049871~exp=1650050471~hmac=c668f799f5aaae82bf35d0a132094418231bf477a5555f1901733836d2cdbfb6&w=1060");
                background-repeat: no-repeat;
                background-size: 100% 100%
            }

            .card {
                padding: 30px 40px;
                margin-top: 60px;
                margin-bottom: 60px;
                border: none !important;
                box-shadow: 0 6px 12px 0 rgba(0, 0, 0, 0.2)
            }

            .blue-text {
                color: #00BCD4
            }

            .form-control-label {
                margin-bottom: 0
            }

            input,
            textarea,
            button {
                padding: 8px 15px;
                border-radius: 5px !important;
                margin: 5px 0px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                font-size: 18px !important;
                font-weight: 300
            }

            input:focus,
            textarea:focus {
                -moz-box-shadow: none !important;
                -webkit-box-shadow: none !important;
                box-shadow: none !important;
                border: 1px solid #00BCD4;
                outline-width: 0;
                font-weight: 400
            }

            .btn-block {
                text-transform: uppercase;
                font-size: 15px !important;
                font-weight: 400;
                height: 43px;
                cursor: pointer
            }

            .btn-block:hover {
                color: #fff !important
            }

            button:focus {
                -moz-box-shadow: none !important;
                -webkit-box-shadow: none !important;
                box-shadow: none !important;
                outline-width: 0
            }
        </style>
    </head>

    <body>
        <div class="container-fluid px-1 py-5 mx-auto">
            <div class="row d-flex justify-content-center">
                <div class="col-xl-7 col-lg-8 col-md-9 col-11 text-center">

                    <h3>Create an Account</h3>
                    <jsp:useBean id="currentPatient" scope="session" class="Business_Object.Patient"/>
                    <div class="card">
                        <h5 class="text-center mb-4"></h5>
                        <form class="form-card" action ="CreateAccountPatientServlet" method="POST" >
                            <div class="row justify-content-between text-left">
                                <input type="text" id="ans" name="patient_id" data-toggle="tooltip" title="Cannot change the ID" placeholder="Patient_id" ></div>
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">First name<span class="text-danger"> *</span></label> 
                                    <input  type="text" id="fname" name="fname" placeholder="Firstname "</div>
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">Last name<span class="text-danger"> *</span></label> 
                                    <input type="text" id="lname" name="lname"  placeholder="Lastname "</div>
                            </div>
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">Email<span class="text-danger"> *</span></label> 
                                    <input type="text" id="email" name="email" placeholder="Email"</div>
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">Address<span class="text-danger"> *</span></label>
                                    <input  type="text" id="mob" name="address"  placeholder="Address"> </div>
                            </div>
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">City<span class="text-danger"> *</span></label> 
                                    <input type="text" id="email" name="city" placeholder="City"</div>
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">State<span class="text-danger"> *</span></label>
                                    <input  type="text" id="mob" name="state"  placeholder="State"> </div>
                            </div>
                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">Zip Code<span class="text-danger"> *</span></label>
                                    <input  type="text" id="mob" name="zip"  placeholder="Zip Code"> </div>
                            </div>

                            <div class="row justify-content-between text-left">
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">Insurance Company<span class="text-danger"> *</span></label>
                                    <input  type="text" id="job" name="insurance"  placeholder="Insurance"> </div>
                                <div class="form-group col-sm-12 flex-column d-flex"> <label class="form-control-label px-3">Password<span class="text-danger"> *</span></label>
                                    <input  type="password" id="job" name="pass" placeholder="Password"> </div>
                            </div>
                            <div class="row justify-content-around">
                                <div class="form-group col-sm-4"> <button type="submit" class="btn-block btn-primary"  name="submit">Register</button> </div>
                            </div>
                    </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
