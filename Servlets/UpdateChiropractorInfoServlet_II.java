/** ******************************************************************************
 * CIST2931 Advanced Systems Project
 * UpdatePatientInfoServlet
 * Author: Pradsley D'Haiti
 * Date: 10/20/2022
 ******************************************************************************** */
package Business_Object.Servlets;

import Business_Object.Chiropractor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//====================== UpdateChiropractorInfoServlet_II=========================================

/**
 * **************************************************************************************************************
 * - UpdateChiropractorInfoServlet_II: - Get the chiropractor session - Get the
 * values of all the fields of the UpdateChiropractorInfo.jsp - Call the
 * updateDB method from the chiropractor Object - Forward the chiropractor back
 * to the patient_dashboard
 * ***************************************************************************************************************
 */
@WebServlet(name = "UpdateChiropractorInfoServlet_II", urlPatterns = {"/UpdateChiropractorInfoServlet_II"})
public class UpdateChiropractorInfoServlet_II extends HttpServlet {

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
            Chiropractor chiro = (Chiropractor) session.getAttribute("cc");

            String firstName, lastName, email, office_num, password;

            firstName = request.getParameter("fname");
            lastName = request.getParameter("lname");
            office_num = request.getParameter("office_num");
            password = request.getParameter("password");
            email = request.getParameter("email");

            chiro.setfirstName(firstName);
            chiro.setlastName(lastName);
            chiro.setemail(email);
            chiro.setoffice_num(office_num);
            chiro.setPassword(password);

            chiro.UpdateDB();

            RequestDispatcher dispatcher = request.getRequestDispatcher("NoAppointmentDashboard.jsp");
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
