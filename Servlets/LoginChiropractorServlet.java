package Business_Object.Servlets;

import Business_Object.Appointments;
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

/**
 *
 * @author pach3
 */
@WebServlet(name = "LoginChiropractorServlet", urlPatterns = {"/LoginChiropractorServlet"})
public class LoginChiropractorServlet extends HttpServlet {

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
           HttpSession sess = request.getSession();
           //Storing user information
          String chiroprac_id = request.getParameter("chiroprac_id");
          String password = request.getParameter("password");
          out.print(chiroprac_id);
          out.print(password);
          
          Chiropractor cc = new Chiropractor();
          Appointments appt = new Appointments();
          cc.selectDB(chiroprac_id);
          out.print(cc.getPassword());
          boolean isValid = cc.getchiroprac_id().equals(chiroprac_id) && cc.getPassword().equals(password);
          // Comparing user credentials 
          if(isValid){
              System.out.println("Valid credentials");
              sess.setAttribute("cc",cc);
              RequestDispatcher dispatcher = request.getRequestDispatcher("chiropractor_dashboard.jsp");
              dispatcher.forward(request, response);
          }else{
              RequestDispatcher dispatcher = request.getRequestDispatcher("404.html");
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
