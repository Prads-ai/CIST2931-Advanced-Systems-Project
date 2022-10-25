/** ******************************************************************************
 * CIST2931 Advanced Systems Project
 * PatientEditAppointmentServlet
 * Author: Pradsley D'Haiti
 * Date: 10/20/2022
 ******************************************************************************** */
package Business_Object.Servlets;

import Business_Object.Appointments;
import Business_Object.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//====================== PatientEditAppointmentServlet=========================================

/**
 * **************************************************************************************************************************************************
 * - PatientEditAppointmentServlet : Note: This Servlet is an experimental
 * feature, It's purpose is to allow a patient to edit a single appointment from
 * a collection of appointments.
 * ***************************************************************************************************************************************************
 */
@WebServlet(name = "PatientEditAppointmentServlet", urlPatterns = {"/PatientEditAppointmentServlet"})
public class PatientEditAppointmentServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            HttpSession sessionArrayList = request.getSession();

            ArrayList<Appointments> appl = (ArrayList<Appointments>) sessionArrayList.getAttribute("Session_list");

            Appointments appt;
            appt = (Appointments) session.getAttribute("appt");

            int userDB_id = Integer.parseInt(request.getParameter("id"));

            for (int i = 0; i < appl.size(); i++) {
                if (appl.get(i).getId() == userDB_id) {

                    String patientDate = appl.get(i).getApptDateTime();
                    String patientID = appl.get(i).getPatId();
                    String chiro = appl.get(i).getChiropractorId();
                    String proc = appl.get(i).getProcCode();
                    int office_num = appl.get(i).getOfficeNum();
                    int id = appl.get(i).getId();

                    // Update the appointments fields
                    appt.setApptDateTime(patientDate);
                    appt.setPatId(patientID);
                    appt.setChiropractorId(chiro);
                    appt.setOfficeNum(office_num);
                    appt.setProcCode(proc);
                    appt.updateDB(id);
                    appl.set(id, appt);

                    HttpSession newAppSession = request.getSession();
                    newAppSession.setAttribute("updatedAppt", appt);

                    RequestDispatcher dp = request.getRequestDispatcher("PatientEditAppointment.jsp");
                    dp.forward(request, response);

                }

            }

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
