/** ******************************************************************************
 * CIST2931 Advanced Systems Project
 * LoginAdminServlet
 * Author: Wilcox Devares
 * Date: 10/20/2022
 ******************************************************************************** */
package Business_Object.Servlets;

import Business_Object.Admin;
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
//====================== LoginAdminServlet=========================================

/**
 * **************************************************************************************************************
 * - LoginAdminServlet : Get the values of all the fields of the Admin_Login.jsp
 * form Create and call the selectDB from the admin object class Get the Admin
 * id and name from the selectDB method Validate the authenticity of the admin
 * Forward the current admin to the admin dashboard page or to an error page if
 * there is a credential problem
 * ***************************************************************************************************************
 */
@WebServlet(name = "LoginAdminServlet", urlPatterns = {"/LoginAdminServlet"})
public class LoginAdminServlet extends HttpServlet {

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

            HttpSession sess = request.getSession();
            HttpSession sessList = request.getSession();
            //Storing user information
            String admin_id = request.getParameter("admin_id");
            String password = request.getParameter("password");

            Admin ad = new Admin();
            ChiropractorList chirolist = new ChiropractorList();
            chirolist.getAllchiropractor();
            ad.selectDB(admin_id);

            // Comparing user credentials 
            if (admin_id.equals(ad.getAdminid()) && password.equals(ad.getPassword())) {

                sess.setAttribute("allChiropractor", chirolist);
                sess.setAttribute("admin", ad);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Admin_dashboard.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("");
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
