/** ******************************************************************************
 * CIST2931 Advanced Systems Project
 * BookAppointmentServlet
 * Author: Pradsley D'Haiti
 * Date: 10/20/2022
 ******************************************************************************** */
package Business_Object.Servlets;

import Business_Object.Appointments;
import Business_Object.Patient;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.rmi.server.Dispatcher;
//====================== BookAppointmentServlet=========================================

/**
 * **********************************************************************
 * - BookAppointmentServlet : Get the values of all the fields of the
 * BookAppointment.jsp form Get the all the fields of the current patient object
 * in session Call the insertDB() method of the Appointments class Add all the
 * fields to the appointments table of the database Forward the current patient
 * to the patient dashboard page
 * ***********************************************************************
 */
@WebServlet(name = "BookAppointmentServlet", urlPatterns = {"/BookAppointmentServlet"})
public class BookAppointmentServlet extends HttpServlet {

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

            HttpSession pSession = request.getSession();
            HttpSession aSession = request.getSession();

            String date, chiro, procedure;
            date = request.getParameter("date");
            chiro = request.getParameter("chiropract_id");
            procedure = request.getParameter("proc_code");
            int office_num = Integer.parseInt(request.getParameter("office_num"));

            Patient currentPatient = (Patient) pSession.getAttribute("currentPatient");
            Appointments appt = (Appointments) aSession.getAttribute("appt");
            String patientID = currentPatient.getPatientId();
            appt.insertDB(date, patientID, chiro, procedure, office_num);
            System.out.println(appt);

            RequestDispatcher dispatcher = request.getRequestDispatcher("patient_dashboard.jsp");
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
