/** ******************************************************************************
 * CIST2931 Advanced Systems Project
 * SearchPatientServlet
 * Author: Pradsley D'Haiti
 * Date: 10/20/2022
 ******************************************************************************** */
package Business_Object.Servlets;

import Business_Object.AppointmentList;
import Business_Object.Appointments;
import Business_Object.Chiropractor;
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
//====================== SearchPatientServlet=========================================

/**
 * **************************************************************************************************************
 * - SearchPatientServlet(String patientID) : - Get the patient session - Get
 * the user input from the search field - Return a single patient object from
 * the appointment collection that matches the searched input field
 * ***************************************************************************************************************
 */
@WebServlet(name = "SearchPatientServlet", urlPatterns = {"/SearchPatientServlet"})
public class SearchPatientServlet extends HttpServlet {

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
            HttpSession sessionAp = request.getSession();

            Chiropractor chiro = (Chiropractor) session.getAttribute("cc");
            String name = request.getParameter("searchPatientName");
            AppointmentList appL = new AppointmentList();
            Patient pId = new Patient();

            appL.getChiroAppointments(chiro.getchiroprac_id());
            Appointments result = appL.FindPatientID(name);
            pId.selectDB(name);

            if (result.getPatId().equals(pId.findPatientName())) {
                session.setAttribute("nameResult", result);
                RequestDispatcher dispatcher = request.getRequestDispatcher("SearchPatientNameResult.jsp");
                dispatcher.forward(request, response);
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
