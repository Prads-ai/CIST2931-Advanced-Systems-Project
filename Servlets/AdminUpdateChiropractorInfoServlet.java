/** ******************************************************************************
 * CIST2931 Advanced Systems Project
 * AdminUpdateChiropractorInfoServlet
 * Author: Pradsley D'Haiti
 * Date: 10/20/2022
 ******************************************************************************** */
package Business_Object.Servlets;

import Business_Object.Chiropractor;
import Business_Object.ChiropractorList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//=============================== AdminUpdateChiropractorInfoServlet ============================================

/**
 * *************************************************************************************************************************************
 * AdminUpdateChiropractorInfoServlet: - Get the values of all the fields of the
 * AdminEditChiropractor.jsp form - Get the chiropractor object session and the
 * chiroList object session from the AdminEditChiropractor jsp file - Updated
 * the selected chiropractor from the chiroList collection - Forward the user to
 * the AdminUpdateConfirmationPage
 * *************************************************************************************************************************************
 */
@WebServlet(name = "AdminUpdateChiropractorInfoServlet", urlPatterns = {"/AdminUpdateChiropractorInfoServlet"})
public class AdminUpdateChiropractorInfoServlet extends HttpServlet {

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

            HttpSession session = request.getSession();
            HttpSession sessionArraylist = request.getSession();

            String chiroprac_id, fn, ln, em, off, shed, amId, pwd;

            chiroprac_id = request.getParameter("chiroprac_id");
            fn = request.getParameter("firstname");
            ln = request.getParameter("lastname");
            em = request.getParameter("email");
            off = request.getParameter("office_num");
            shed = request.getParameter("schedule");
            amId = request.getParameter("adminID");
            pwd = request.getParameter("password");

            // Get the chiropractor object session and the chiroList object session from the AdminEditChiropractor jsp file.
            Chiropractor selected = (Chiropractor) session.getAttribute("selected");
            ChiropractorList chiroList = (ChiropractorList) sessionArraylist.getAttribute("allChiropractor");

            // Updated the selected chiropractor from the chiroList collection. 
            selected = chiroList.updateSingleChiroctorFromAList(selected.getId());

            selected.setchiroprac_id(chiroprac_id);
            selected.setfirstName(fn);
            selected.setlastName(ln);
            selected.setemail(em);
            selected.setoffice_num(off);
            selected.setSchedule(shed);
            selected.setadmin_id(amId);
            selected.setPassword(pwd);

            selected.UpdateChiropractorSchedule(selected.getId());

            // forward the user to another page.
            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminUpdateConfirmationPage.jsp");
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
