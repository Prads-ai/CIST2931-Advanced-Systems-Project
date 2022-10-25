/** ******************************************************************************
 * CIST2931 Advanced Systems Project
 * SearchAppointmentServlet
 * Author: Pradsley D'Haiti
 * Date: 10/20/2022
 ******************************************************************************** */
package Business_Object.Servlets;

import Business_Object.AppointmentList;
import Business_Object.Appointments;
import Business_Object.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//====================== LoginPatientServlet=========================================

/**
 * **************************************************************************************************************
 * - SearchAppointmentServlet(String date) : - Get the appointment and the
 * patient session - Get the user input from the search field - Return a single
 * appointment object from the appointment collection that matches the searched
 * input field
 * ***************************************************************************************************************
 */
@WebServlet(name = "SearchAppointmentServlet", urlPatterns = {"/SearchAppointmentServlet"})
public class SearchAppointmentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sessionA = request.getSession();
            HttpSession sessionP = request.getSession();
            HttpSession sessionAlist = request.getSession();

            // Get the appointment and the patient session 
            Appointments appt = (Appointments) sessionA.getAttribute("appt");
            Patient currentPatient = (Patient) sessionP.getAttribute("currentPatient");
            // Get the user input
            String searchInput = request.getParameter("searchAppointment");
            //Create an appointmentList Object
            AppointmentList appL = new AppointmentList();
            appL.getAppointments(currentPatient.getPatientId());
            // Call the SearchAppointment method and assign it to a variable call result;
            appt = appL.SearchAppointment(searchInput);

            if (appt.getApptDateTime().equalsIgnoreCase("")) {
                appt.setApptDateTime("No appointment");
                appt.setChiropractorId("No Chiropractor");
                appt.setProcCode("No Procedure ");
                appt.setOfficeNum(0);
            }
            // Call the request dispatcher
            sessionAlist.setAttribute("appointmentResult", appt);
            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchAppointmentResult.jsp");
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
